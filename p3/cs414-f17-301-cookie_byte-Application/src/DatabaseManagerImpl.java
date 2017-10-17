import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.log4j.Logger;
import org.bson.Document;


import javax.xml.ws.http.HTTPException;


public class DatabaseManagerImpl {
    private MongoClientURI uri;
    private MongoClient mongoClient;
    final static Logger log = Logger.getLogger(DatabaseManagerImpl.class);

    public DatabaseManagerImpl() {
        initializeDB();
    }

    /**
     * initializes the MongoDB, creates the database if it is not present
     *
     * @return
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
     * Method to insert a NEW user.
     * @param nickname the unique name a user is identifed by
     * @param email the unique email a user uses to recieve notifications
     * @param password Pass me a PRE-HASHED String that gets stored in the DB
     */
    public void insertNewUser(String nickname, String email, String password) {
        MongoDatabase db = mongoClient.getDatabase("cs414Application");
        MongoCollection<Document> collection = db.getCollection("users");

        Document user = new Document("nickname", nickname)
                                    .append("email",email)
                                    .append("password",password);
        collection.insertOne(user);
    }

    public static void main(String[] args) {
        DatabaseManagerImpl newDB = new DatabaseManagerImpl();
    }
}
