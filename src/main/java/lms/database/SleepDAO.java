package lms.database;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;

public class SleepDAO {

    private final MongoCollection<Document> collection;

    public SleepDAO() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("sleep_entries");
    }

    public void saveSleep(String username, String date, int hours) {
        Document doc = new Document("username", username)
                .append("date", date)
                .append("hours", hours);
        collection.insertOne(doc);
    }

    public List<String> loadSleep(String username) {
        List<String> data = new ArrayList<>();

        for (Document d : collection.find(eq("username", username))) {
            data.add(d.getString("date") + " - " + d.getInteger("hours") + " sati");
        }
        return data;
    }
}