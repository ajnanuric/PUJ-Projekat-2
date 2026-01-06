package finance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class FinanceTrackerForm extends JFrame {

    private JTextField amountField;
    private JTextField descriptionField;
    private JComboBox<String> typeCombo;
    private JComboBox<String> categoryCombo;
    private JTable transactionTable;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JLabel balanceLabel;

    private TransactionManager manager;
    private Transaction selectedTransaction;

    public FinanceTrackerForm() {
        manager = new TransactionManager();
        initUI();
        loadDataIntoTable();
        updateSummary();
    }

    private void initUI() {
        setTitle("Finance Tracker");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // ===== TOP PANEL =====
        JPanel topPanel = new JPanel(new GridLayout(2, 4, 10, 10));

        typeCombo = new JComboBox<>(new String[]{"Prihod", "Rashod"});
        categoryCombo = new JComboBox<>(new String[]{
                "Hrana", "Prijevoz", "Zabava", "Računi", "Plata", "Ostalo"
        });

        amountField = new JTextField();
        descriptionField = new JTextField();

        topPanel.add(new JLabel("Tip:"));
        topPanel.add(typeCombo);
        topPanel.add(new JLabel("Kategorija:"));
        topPanel.add(categoryCombo);
        topPanel.add(new JLabel("Iznos:"));
        topPanel.add(amountField);
        topPanel.add(new JLabel("Opis:"));
        topPanel.add(descriptionField);

        add(topPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        transactionTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        transactionTable.getSelectionModel().addListSelectionListener(e -> {
            int row = transactionTable.getSelectedRow();
            if (row >= 0) {
                selectedTransaction = manager.getAllTransactions().get(row);
                typeCombo.setSelectedItem(selectedTransaction.getType());
                categoryCombo.setSelectedItem(selectedTransaction.getCategory());
                amountField.setText(String.valueOf(selectedTransaction.getAmount()));
                descriptionField.setText(selectedTransaction.getDescription());
            }
        });

        // ===== BOTTOM PANEL =====
        JPanel bottomPanel = new JPanel(new GridLayout(2, 1));

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Dodaj");
        JButton updateButton = new JButton("Ažuriraj");
        JButton deleteButton = new JButton("Obriši");
        JButton exportButton = new JButton("Export TXT");

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exportButton);

        JPanel summaryPanel = new JPanel();
        incomeLabel = new JLabel("Prihod: 0");
        expenseLabel = new JLabel("Rashod: 0");
        balanceLabel = new JLabel("Saldo: 0");

        summaryPanel.add(incomeLabel);
        summaryPanel.add(expenseLabel);
        summaryPanel.add(balanceLabel);

        bottomPanel.add(buttonPanel);
        bottomPanel.add(summaryPanel);

        add(bottomPanel, BorderLayout.SOUTH);

        // ===== BUTTON ACTIONS =====
        addButton.addActionListener(e -> addTransaction());
        updateButton.addActionListener(e -> updateTransaction());
        deleteButton.addActionListener(e -> deleteTransaction());
        exportButton.addActionListener(e -> exportData());

        setVisible(true);
    }

    private void addTransaction() {
        try {
            String type = (String) typeCombo.getSelectedItem();
            String category = (String) categoryCombo.getSelectedItem();
            double amount = Double.parseDouble(amountField.getText());
            String description = descriptionField.getText();

            Transaction t = new Transaction(type, amount, description, category);
            manager.addTransaction(t);

            loadDataIntoTable();
            updateSummary();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Neispravan unos!");
        }
    }

    private void updateTransaction() {
        if (selectedTransaction == null) return;

        try {
            Transaction updated = new Transaction(
                    selectedTransaction.getId(),
                    (String) typeCombo.getSelectedItem(),
                    Double.parseDouble(amountField.getText()),
                    descriptionField.getText(),
                    (String) categoryCombo.getSelectedItem()
            );

            manager.updateTransaction(updated);
            loadDataIntoTable();
            updateSummary();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greška pri ažuriranju!");
        }
    }

    private void deleteTransaction() {
        if (selectedTransaction == null) return;
        manager.deleteTransaction(selectedTransaction);
        loadDataIntoTable();
        updateSummary();
        clearFields();
    }

    private void exportData() {
        try (FileWriter fw = new FileWriter("finance_export.txt")) {
            fw.write(incomeLabel.getText() + "\n");
            fw.write(expenseLabel.getText() + "\n");
            fw.write(balanceLabel.getText() + "\n");
            JOptionPane.showMessageDialog(this, "Export završen!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Greška pri exportu!");
        }
    }

    private void loadDataIntoTable() {
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Tip", "Iznos", "Opis", "Kategorija"}, 0
        );

        for (Transaction t : manager.getAllTransactions()) {
            model.addRow(new Object[]{
                    t.getType(),
                    t.getAmount(),
                    t.getDescription(),
                    t.getCategory()
            });
        }

        transactionTable.setModel(model);
    }

    private void updateSummary() {
        double income = manager.getTotalIncome();
        double expense = manager.getTotalExpense();
        incomeLabel.setText("Prihod: " + income);
        expenseLabel.setText("Rashod: " + expense);
        balanceLabel.setText("Saldo: " + (income - expense));
    }

    private void clearFields() {
        amountField.setText("");
        descriptionField.setText("");
        selectedTransaction = null;
    }
}