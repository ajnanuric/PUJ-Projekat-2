package finance;

import org.bson.Document;
import org.bson.types.ObjectId;

public class Transaction {

    private ObjectId id;
    private String type;
    private double amount;
    private String description;
    private String category;


    public Transaction(ObjectId id, String type, double amount, String description, String category) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }


    public Transaction(String type, double amount, String description, String category) {
        this.id = new ObjectId();
        this.type = type;
        this.amount = amount;
        this.description = description;
        this.category = category;
    }

    // Pretvaranje u MongoDB dokument
    public Document toDocument() {
        Document doc = new Document();
        doc.append("_id", id);
        doc.append("Vrsta", type);
        doc.append("Iznos", amount);
        doc.append("Opis", description);
        doc.append("Kategorija", category);
        return doc;
    }

    // Getteri
    public ObjectId getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }
}
