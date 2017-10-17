import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;


import javax.xml.ws.http.HTTPException;


public class DatabaseManagerImpl {

    /* The location of the remote Database. */
    private MongoClientURI uri;

    /* The mongoDB client. */
    private MongoClient mongoClient;

    /* Logs system exceptions */
    final static Logger log = Logger.getLogger(DatabaseManagerImpl.class);

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
     * Method to insert a NEW user. This method does not valid input. It takes the exact string and inputs into DB.
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
}
