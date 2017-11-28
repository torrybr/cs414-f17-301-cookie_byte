package Database;

import Backend.*;
import Backend.InvitationStatus;
import Backend.Invite;
import Backend.Piece;
import Backend.PieceType;
import Backend.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.BasicDBObject;
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
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.mongodb.client.model.Filters.eq;

/**
 * Several methods for interacting with the Database [ Updating, deleting , retrieving, saving ]
 */
public abstract class DatabaseManagerImpl {

    /* The location of the remote Database. */
    private static MongoClientURI uri;

    /* The mongoDB client. */
    private static MongoClient mongoClient;

    /* Logs system exceptions */
    final static Logger log = Logger.getLogger(DatabaseManagerImpl.class);

    /*The Database connection*/


    /**
     * Connections to the MongoDatabase
     */
    public DatabaseManagerImpl() {
        
    }

    /**
     * Initializes the MongoDB, creates the database if it is not present and sets the Connection string so its available to other methods in the class.
     *
     * @return true or false if the connection to the database was successful
     */
    public static boolean initializeDB() {
        try {
            uri = new MongoClientURI(
                    "mongodb://app:tsM6N8irlTgHNzMa@cluster0-shard-00-00-u3hx4.mongodb.net:27017,cluster0-shard-00-01-u3hx4.mongodb.net:27017,cluster0-shard-00-02-u3hx4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&&connectTimeoutMS=200000");
            mongoClient = new MongoClient(uri);
            return true;
        } catch (HTTPException e) {
            log.error("Could not connect to the Database ", e);
            return false;
        }
    }

    /**
     * Method to insert a NEW user. This method does not validate input. It takes the exact string and inputs into DB.
     * Creates empty list of game_history , invites , and current games
     *
     * @param nickname the unique name a user is identified by
     * @param email    the unique email a user uses to receive notifications
     * @param password Pass me a PRE-HASHED String
     */
    public static void createNewUser(String nickname, String email, String password) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");

        ArrayList<String> the_game_history = new ArrayList<>();
        ArrayList<Document> the_invites = new ArrayList<>();
        ArrayList<Document> current_games = new ArrayList<>();

        Document user = new Document("nickname", nickname)
                .append("email", email)
                .append("password", password);

        user.append("game_history", the_game_history);

        user.append("invites", the_invites);

        user.append("current_games", current_games);

        collection.insertOne(user);
    }

    public void setInviteStatus(String nickname, Backend.Invite theInvite ) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");

        Document match = Document.parse(" {\"nickname\": \"" + nickname +"\",\"invites\": { $elemMatch: {\"Invite.gameID\" : NumberInt("+theInvite.getGameID()+") }} }");

        Document invitationStatus = new Document();
        invitationStatus.append("invitationStatus", theInvite.getStatus().toString());
        //invite.append("InvitationStatus", invitationStatus);

        collection.updateOne(match, new Document("$set", new Document("InvitationStatus", invitationStatus)));

    }

    public void removeInvite(String nickname, Backend.Invite theInvite) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");

        Document q = Document.parse(" {$pull: {\"Invite.gameID\" : NumberInt("+theInvite.getGameID()+") }}");
        //Document command =
        Document match = Document.parse(" {\"nickname\": \"" + nickname +"\",\"invites\": { $elemMatch: {\"Invite.gameID\" : NumberInt("+theInvite.getGameID()+") }} }");

        Document update = new Document().parse("{\"Invite.gameID\" : NumberInt(" + theInvite.getGameID() + ") }");
        //BasicDBObject update = new BasicDBObject();
        //update.put(match, new BasicDBObject("$pull", q));
        //Document invite = new Document();
        //collection.findOneAndDelete(q);
        //collection.updateOne(p, new BasicDBObject("$pull", update));
        //collection.updateOne(match,q);

        //BasicDBObject data = new BasicDBObject();
        //data.put("Board.pieces", array);

        //BasicDBObject command = new BasicDBObject();
        //command.put("$set", data);
        //Document query = Document.parse("{ \"GameID\": NumberInt(" + gameID + ")}");
        //collection.updateOne(query, command);
    }


    public static void addInvite(Backend.Invite theInvite) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");

        Document main = new Document();
        //Document myUser = new Document();
        Document invite = new Document();
        invite.append("gameID", theInvite.getGameID());

        invite.append("userFrom", theInvite.getUserFrom().getUserID());

        invite.append("InvitationStatus", theInvite.getStatus().toString());

        new Document();
		Document query = Document.parse("{ \"nickname\": \"" + theInvite.getUserTo().getUserID() + "\" }");

        BasicDBObject data = new BasicDBObject();
        main.append("Invite",invite);
        data.put("invites", main);

        BasicDBObject command = new BasicDBObject();
        command.put("$push", data);

        collection.findOneAndUpdate(query, command);
    }

    /**
     * Get a specific user that has signed up.
     *
     * @param nname the nickname of the user.
     * @return a User object for that nickname.
     */
    public static UsersJavaObject getUserByNickname(String nname) {
    	
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
    public static BoardJavaObject getGame(int gameID) {
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
    }

    /**
     * A method for printing the JSON response of the Board from the MongoDB db server.
     *
     * @param gameID the id of the game you want to print out in json format.
     */
    private static void getmyGameJson(int gameID) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");
        System.out.println(collection.find(eq("GameID", gameID)).first().toJson());
    }

    /**
     * A method for printing the JSON response of the User from the MongoDB db server.
     *
     * @param nname the nickname of the user.
     */
    public static void getmyUserJson(String nname) {
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
    public static void updatePlayerTurn(int gameID, String playerTurn) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");

        collection.updateOne(eq("GameID", gameID), new Document("$set", new Document("CurrentTurn", playerTurn)));

    }


    public static int createGameID() {

        Random randomGenerator = new Random();

        List<Integer> randIntegers = new Random().ints(1, 100000).distinct().limit(10000).boxed().collect(Collectors.toList());
        int len = randIntegers.size();
        int randomInt = randomGenerator.nextInt(len);
        return randIntegers.get(randomInt);

    }
    /**
     * Update the status of the game from PENDING, ACTIVE, FINSIHED.
     * @param gameID the id of the game you what to access.
     * @param theStatus the new status of the game.
     */

    public void updateGameStatus(int gameID, GameStatus theStatus) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");

        collection.updateOne(eq("GameID", gameID), new Document("$set", new Document("GameStatus", theStatus.toString())));

    }
    

    public static void addToCurrentGames(int gameID, User player) {


        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");

        Document main = new Document();
        Document query = new Document().parse("{ \"nickname\": \"" + player.getUserID() + "\" }");

        BasicDBObject data = new BasicDBObject();
        //main.append("gameID",gameID);
        data.put("current_games",gameID);

        BasicDBObject command = new BasicDBObject();
        command.put("$push", data);

        collection.findOneAndUpdate(query, command);
    }

    /**
     * Create an initial game and set the default board layout.
     *
     * @param theBoard the board object
     * @param player1  A User who is player 1
     * @param player2  A User who is player 2
     */
    public static void createGame(Backend.Board theBoard, User player1, User player2, User offence, User defence) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");
//        final GameController gameController = new GameController(theBoard.getGameID(),player1,player2);
        int theID = createGameID();
        Document myGame = new Document();
        myGame.put("GameID", theID); //1234322

        myGame.put("Player1", player1.getUserID());
        myGame.put("Player2", player2.getUserID());
        myGame.put("Offense", offence.getUserID());
        myGame.put("Defense", defence.getUserID());
        myGame.put("CurrentTurn", "A"); //need to finish this

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

        addToCurrentGames(theID,player1);
        addToCurrentGames(theID,player2);


    }

    /**
     * When a piece has been moved, use this method to save the new piece location to the database.
     * This should be called after every piece has been moved in order to save the state of the game.
     *
     * @param gameID the ID of game we need to update
     */
    public static void updateGame(int gameID, Backend.Board theBoard) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("game");

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

        BasicDBObject data = new BasicDBObject();
        data.put("Board.pieces", array);

        BasicDBObject command = new BasicDBObject();
        command.put("$set", data);
        Document query = Document.parse("{ \"GameID\": NumberInt(" + gameID + ")}");
        collection.updateOne(query, command);
    }

    public static void main(String args[]) {
        DatabaseManagerImpl.getmyGameJson(0);
        
    }


}