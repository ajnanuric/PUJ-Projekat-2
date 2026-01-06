package lms.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lms.models.User;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class UserDAO {

    private MongoCollection<Document> users;

    public UserDAO() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        users = db.getCollection("users");
    }

    // REGISTRACIJA
    public void insertUser(User user) {
        Document doc = new Document("username", user.getUsername())
                .append("password", user.getPassword())
                .append("theme", user.getTheme());

        users.insertOne(doc);
    }

    // LOGIN
    public User findUser(String username, String password) {
        Document doc = users.find(eq("username", username)).first();

        if (doc != null && doc.getString("password").equals(password)) {
            return new User(
                    doc.getString("username"),
                    doc.getString("password"),
                    doc.getString("theme")
            );
        }
        return null;
    }
}