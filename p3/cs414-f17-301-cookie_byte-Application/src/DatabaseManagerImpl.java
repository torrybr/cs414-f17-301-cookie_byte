import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

import java.util.List;

public class DatabaseManagerImpl {
    private MongoClientURI uri = new MongoClientURI(
            "mongodb://cs414team:<GO_CSU_rams_2019!>@cluster0-shard-00-00-u3hx4.mongodb.net:27017,cluster0-shard-00-01-u3hx4.mongodb.net:27017,cluster0-shard-00-02-u3hx4.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin");
    MongoClient mongoClient = new MongoClient(uri);

    public DatabaseManagerImpl() {
        initializeDB();
    }

    /**
     * initializes the MongoDB, creates the database if it is not present
     *
     * @return
     */
    private boolean initializeDB() {
        return true;
    }

    /**
     * @param databaseName the database you want access to. ex. "cs414team"
     * @return MongoDatabase object so you can have access to database methods
     */
    public MongoDatabase getDatabase(String databaseName) {
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        return database;
    }

    public static void main(String[] args) {
        DatabaseManagerImpl newDB = new DatabaseManagerImpl();
    }

    /**
     * Get all the databases available
     *
     * @return list of database names.
     */
    public MongoIterable<String> getAllDatabases() {
        return mongoClient.listDatabaseNames();
    }
}
