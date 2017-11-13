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
 * Several methods for interacting with the Database [ Updating, deleting , retrieving, saving ]
 */
public class DatabaseManagerImpl {

    /* The location of the remote Database. */
    private MongoClientURI uri;

    /* The mongoDB client. */
    private MongoClient mongoClient;

    /* Logs system exceptions */
    final static Logger log = Logger.getLogger(DatabaseManagerImpl.class);

    /*The Database connection*/
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
     * Get a specific user that has signed up.
     *
     * @param nname the nickname of the user.
     * @return a User object for that nickname.
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
     * Gets the game by GameID and maps it to java POGO Objects.
     *
     * @param gameID the gameID for the game you want to retrieve.
     * @return the BoardJavaObject for the game you are trying to access.
     */
    public BoardJavaObject getGame(int gameID) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            BoardJavaObject game = objectMapper.readValue(collection.find(eq("GameID", gameID)).first().toJson(), BoardJavaObject.class);
            return game;
        } catch (IOException e) {
            log.error("error parsing json to java pogo", e);
        }
        return null;
        //System.out.println(collection.find(eq("GameID",gameID)).first().toJson());
    }

    /**
     * A method for printing the JSON response of the Board from the MongoDB db server.
     *
     * @param gameID the id of the game you want to print out in json format.
     */
    private void getmyGameJson(int gameID) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");
        System.out.println(collection.find(eq("GameID", gameID)).first().toJson());
    }

    /**
     * A method for printing the JSON response of the User from the MongoDB db server.
     *
     * @param nname the nickname of the user.
     */
    private void getmyUserJson(String nname) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");
        System.out.println(collection.find(eq("nickname", nname)).first().toJson());
    }

    /**
     * Update the players turn in the game.
     *
     * @param gameID
     * @param playerTurn
     */
    public void updatePlayerTurn(String gameID, String playerTurn) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");

        collection.updateOne(eq("GameID", gameID), new Document("$set", new Document("CurrentTurn", playerTurn)));

    }

    /**
     * Create an initial game and set the default board layout.
     *
     * @param theBoard the board object
     * @param player1  A User who is player 1
     * @param player2  A User who is player 2
     */
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
}