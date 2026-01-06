package finance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FinanceTrackerForm extends JFrame {

    private JPanel mainPanel;
    private JTextField amountField;
    private JTextField descriptionField;
    private JComboBox<String> typeCombo;
    private JComboBox<String> categoryCombo;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton exportButton;
    private JTable transactionTable;
    private JLabel incomeLabel;
    private JLabel expenseLabel;
    private JLabel balanceLabel;

    private TransactionManager manager;
    private Transaction selectedTransaction = null;

    public FinanceTrackerForm() {

        manager = new TransactionManager();

        transactionTable.getSelectionModel().addListSelectionListener(e -> {
            int row = transactionTable.getSelectedRow();
            if (row >= 0) {
                ArrayList<Transaction> all = manager.getAllTransactions();
                selectedTransaction = all.get(row);

                typeCombo.setSelectedItem(selectedTransaction.getType());
                amountField.setText(String.valueOf(selectedTransaction.getAmount()));
                descriptionField.setText(selectedTransaction.getDescription());
                categoryCombo.setSelectedItem(selectedTransaction.getCategory());
            }
        });

        addButton.addActionListener(e -> {
            try {
                String type = (String) typeCombo.getSelectedItem();
                String category = (String) categoryCombo.getSelectedItem();
                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();

                if (description.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Opis ne može biti prazan!");
                    return;
                }

                Transaction t = new Transaction(type, amount, description, category);
                manager.addTransaction(t);

                loadDataIntoTable();
                updateSummary();
                clearFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Iznos mora biti broj!");
            }
        });

        updateButton.addActionListener(e -> {
            if (selectedTransaction == null) {
                JOptionPane.showMessageDialog(this, "Morate odabrati transakciju!");
                return;
            }

            try {
                String type = (String) typeCombo.getSelectedItem();
                String category = (String) categoryCombo.getSelectedItem();
                double amount = Double.parseDouble(amountField.getText());
                String description = descriptionField.getText();

                Transaction updated = new Transaction(
                        selectedTransaction.getId(),
                        type,
                        amount,
                        description,
                        category
                );

                manager.updateTransaction(updated);

                loadDataIntoTable();
                updateSummary();
                clearFields();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Iznos mora biti broj!");
            }
        });

        deleteButton.addActionListener(e -> {
            if (selectedTransaction == null) {
                JOptionPane.showMessageDialog(this, "Morate odabrati transakciju!");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "Jeste li sigurni da želite izbrisati ovu transakciju?",
                    "Potvrda",
                    JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                manager.deleteTransaction(selectedTransaction);
                loadDataIntoTable();
                updateSummary();
                clearFields();
            }
        });

        exportButton.addActionListener(e -> {
            try {
                FileWriter fw = new FileWriter("export.txt");

                fw.write("Ukupni prihod: " + manager.getTotalIncome() + "\n");
                fw.write("Ukupni rashod: " + manager.getTotalExpense() + "\n");
                fw.write("Saldo: " + (manager.getTotalIncome() - manager.getTotalExpense()) + "\n");

                fw.close();
                JOptionPane.showMessageDialog(this, "Podaci su exportovani!");

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Greška pri exportu!");
            }
        });

        setContentPane(mainPanel);
        setTitle("Finance Tracker");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        loadDataIntoTable();
        updateSummary();
    }

    private void loadDataIntoTable() {
        ArrayList<Transaction> list = manager.getAllTransactions();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Vrsta");
        model.addColumn("Iznos");
        model.addColumn("Opis");
        model.addColumn("Kategorija");

        for (Transaction t : list) {
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