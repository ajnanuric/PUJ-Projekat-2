package lms.analytics;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lms.models.User;

import java.io.FileOutputStream;
import java.time.LocalDate;

public class PdfExportUtil {

    public static void exportUserReport(User user) {

        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("lms_report.pdf"));

            document.open();

            document.add(new Paragraph("Life Management System - Izvještaj"));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("Korisnik: " + user.getUsername()));
            document.add(new Paragraph("Tema: " + user.getTheme()));
            document.add(new Paragraph("Datum: " + LocalDate.now()));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}