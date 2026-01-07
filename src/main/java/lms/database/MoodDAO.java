package lms.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class MoodDAO {

    private final MongoCollection<Document> collection;

    public MoodDAO() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("mood_entries");
    }

    public void saveMood(String username, String date, String mood) {
        Document doc = new Document("username", username)
                .append("date", date)
                .append("mood", mood);
        collection.insertOne(doc);
    }

    public List<String> loadMoods(String username) {
        List<String> list = new ArrayList<>();

        for (Document d : collection.find(eq("username", username))) {
            list.add(d.getString("date") + " - " + d.getString("mood"));
        }
        return list;
    }
}