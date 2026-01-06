package finance;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

public class TransactionManager {

    private final MongoCollection<Document> collection;

    public TransactionManager() {
        MongoDatabase db = MongoDBConnection.getDatabase();
        collection = db.getCollection("transactions");
    }


    public void addTransaction(Transaction t) {
        collection.insertOne(t.toDocument());
    }


    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> list = new ArrayList<>();

        MongoCursor<Document> cursor = collection.find().iterator();

        while (cursor.hasNext()) {
            Document d = cursor.next();

            ObjectId id = d.getObjectId("_id");
            String type = d.getString("Vrsta");
            double amount = d.getDouble("Iznos");
            String description = d.getString("Opis");
            String category = d.getString("Kategorija");

            list.add(new Transaction(id, type, amount, description, category));
        }
        return list;
    }


    public double getTotalIncome() {
        double total = 0;

        for (Transaction t : getAllTransactions()) {
            if (t.getType().equals("Prihod")) {
                total += t.getAmount();
            }
        }
        return total;
    }


    public double getTotalExpense() {
        double total = 0;

        for (Transaction t : getAllTransactions()) {
            if (t.getType().equals("Rashod")) {
                total += t.getAmount();
            }
        }
        return total;
    }


    public void updateTransaction(Transaction t) {
        Document filter = new Document("_id", t.getId());

        Document updated = new Document("$set",
                new Document("Vrsta", t.getType())
                        .append("Iznos", t.getAmount())
                        .append("Opis", t.getDescription())
                        .append("Kategorija", t.getCategory())
        );

        collection.updateOne(filter, updated);
    }

    public void deleteTransaction(Transaction t) {
        Document filter = new Document("_id", t.getId());
        collection.deleteOne(filter);
    }

    public double getTotalByCategory(String category) {
        double total = 0;

        for (Transaction t : getAllTransactions()) {
            if (t.getCategory() != null &&
                    t.getCategory().equals(category) &&
                    t.getType().equals("Rashod")) {

                total += t.getAmount();
            }
        }
        return total;
    }


}
