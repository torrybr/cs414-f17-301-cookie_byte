import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;

public class DatabaseManagerImpl {
    private MongoClientURI uri = new MongoClientURI(
            "mongodb://cs414team:<GO_CSU_rams_2019!>@cluster0-shard-00-00-u3hx4.mongodb.net:27017,cluster0-shard-00-01-u3hx4.mongodb.net:27017,cluster0-shard-00-02-u3hx4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");

    public DatabaseManagerImpl() {
        initializeDB();
    }

    /**
     * initializes the MongoDB, creates the database if it is not present
     *
     * @return
     */
    private boolean initializeDB() {
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase("cs414Application");
        System.out.println(database.getName());
        return true;
    }



    public static void main(String [] args)
    {
        DatabaseManagerImpl newDB = new DatabaseManagerImpl();
        newDB.initializeDB();
    }
}
