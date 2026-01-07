package lms.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class HabitDAO {

    private final MongoCollection<Document> collection;

    public HabitDAO() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("habit_entries");
    }

    public void saveHabit(String username, String habit) {
        Document doc = new Document("username", username)
                .append("habit", habit);
        collection.insertOne(doc);
    }

    public List<String> loadHabits(String username) {
        List<String> list = new ArrayList<>();

        for (Document d : collection.find(eq("username", username))) {
            list.add(d.getString("habit"));
        }
        return list;
    }
}