package lms.analytics;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import lms.HabitTrackerScreen;
import lms.MoodTrackerScreen;
import lms.SleepTrackerScreen;
import lms.achievement.AchievementUtil;
import lms.models.User;

import java.io.FileOutputStream;

public class PdfExportUtil {

    public static void export(User user) {
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("LMS_Izvjestaj.pdf"));
            doc.open();

            doc.add(new Paragraph("LIFE MANAGEMENT SYSTEM"));
            doc.add(new Paragraph("Korisnik: " + user.getUsername()));
            doc.add(new Paragraph("Tema: " + user.getTheme()));
            doc.add(new Paragraph(" "));

            doc.add(new Paragraph("SPAVANJE:"));
            for (String s : SleepTrackerScreen.getData())
                doc.add(new Paragraph(s));

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("NAVIKE:"));
            for (String h : HabitTrackerScreen.getHabits())
                doc.add(new Paragraph(h));

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("RASPOLOŽENJE:"));
            for (String m : MoodTrackerScreen.getMoods())
                doc.add(new Paragraph(m));

            doc.add(new Paragraph(" "));
            doc.add(new Paragraph("POSTIGNUĆA:"));
            for (String a : AchievementUtil.getAchievements(
                    SleepTrackerScreen.getSleepCount(),
                    MoodTrackerScreen.getMoodCount(),
                    HabitTrackerScreen.getHabitCount()
            )) {
                doc.add(new Paragraph(a));
            }

            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}