package Database;

import Backend.GameController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Backend.User;
import static com.mongodb.client.model.Filters.eq;

/**
 * Several methods for interacting with the game Database [ Updating, deleting , retrieving, saving ]
 */
public class DatabaseManagerImpl {

    /* The location of the remote Database. */
    private MongoClientURI uri;

    /* The mongoDB client. */
    private MongoClient mongoClient;

    /* Logs system exceptions */
    final static Logger log = Logger.getLogger(DatabaseManagerImpl.class);

    private static final DatabaseManagerImpl d = new DatabaseManagerImpl();



    /**
     * Connections to the MongoDatabase
     */
    public DatabaseManagerImpl() {
        initializeDB();
    }

    /**
     * Initializes the MongoDB, creates the database if it is not present and sets the Connection string so its available to other methods in the class.
     *
     * @return true or false if the connection to the database was successful
     */
    private boolean initializeDB() {
        try {
            uri = new MongoClientURI(
                    "mongodb://app:tsM6N8irlTgHNzMa@cluster0-shard-00-00-u3hx4.mongodb.net:27017,cluster0-shard-00-01-u3hx4.mongodb.net:27017,cluster0-shard-00-02-u3hx4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");
            mongoClient = new MongoClient(uri);
            return true;
        } catch (HTTPException e) {
            log.error("Could not connect to the Database ", e);
            return false;
        }
    }

    /**
     * Method to insert a NEW user. This method does not validate input. It takes the exact string and inputs into DB.
     *
     * @param nickname the unique name a user is identified by
     * @param email    the unique email a user uses to receive notifications
     * @param password Pass me a PRE-HASHED String
     */
    public void createNewUser(String nickname, String email, String password) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");

        Document user = new Document("nickname", nickname)
                .append("email", email)
                .append("password", password);
        collection.insertOne(user);
    }

    /**
     * User nicknames are "player2" and "player1", so pass one of those in
     *
     * @param nname
     * @return a User object
     */
    public UsersJavaObject getUserByNickname(String nname) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");

        ObjectMapper objectMapper = new ObjectMapper();

        UsersJavaObject usr = null;
        try {
            usr = objectMapper.readValue(collection.find(eq("nickname", nname)).first().toJson(), UsersJavaObject.class);
            return usr;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return null;
    }


    /**
     * Gets the game by GameID
     * GameID played by both "player1" and "player2" are "DemoGame"
     *
     * @param gameID
     * @return the Game object where you can access different fields
     */
    public GameJavaObject getGame(String gameID) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            GameJavaObject game = objectMapper.readValue(collection.find(eq("GameID", gameID)).first().toJson(), GameJavaObject.class);
            return game;
        } catch (IOException e) {
            log.error("error parsing json to java pogo", e);
        }
        return null;
        //System.out.println(collection.find(eq("GameID",gameID)).first().toJson());
    }

    public void getmyGameJson(String gameID) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");
        System.out.println(collection.find(eq("GameID", gameID)).first().toJson());
    }

    public void getmyUserJson(String nname) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");
        System.out.println(collection.find(eq("nickname", nname)).first().toJson());
    }


    public void updatePlayerTurn(String gameID,String playerTurn) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");

        collection.updateOne(eq("GameID", gameID), new Document("$set", new Document("CurrentTurn", playerTurn)));

    }

    /**
     * x1,y1 AND x2,y2 piece positions in a list of size 0,1,2,3. Based on x,y coordinates
     * @param gameID the game to update
     * @param points the new points of the pieces
     */
    public void updatePieceLocation(String gameID,List<String> points) {

        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");

        collection.updateOne(eq("GameID", gameID), new Document("$set", new Document("PieceLocation", points)));

    }

    public void createGame(Backend.Board theBoard, User player1, User player2) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");
        final GameController gameController;

        Document myGame = new Document();
        myGame.put("GameID", theBoard.getGameID());
        myGame.put("Player1", player1.getUserID());
        myGame.put("Player2", player2.getUserID());
        myGame.put("CurrentTurn", "player2"); //need to finish this

        Document myBoard = new Document();

        ArrayList<Document> array = new ArrayList<>();
        for (Backend.Piece p : theBoard.pieces1DLayout) {
            Document myUser = new Document();
            Document Piece = new Document();
            Document myPieceTypes = new Document();
            myPieceTypes.put("pieceType", p.getType().toString());

            myUser.put("userID", p.getPlayer().getUserID());
            myUser.put("password", p.getPlayer().getPassword());
            myUser.put("email", p.getPlayer().getEmail());

            Piece.append("PieceType", myPieceTypes);
            Piece.append("User", myUser);

            array.add(Piece);
        }
        myBoard.put("pieces", array);
        myGame.append("Board", myBoard);
        collection.insertOne(myGame);
    }





    public static void main(String[] args) {


    }
}