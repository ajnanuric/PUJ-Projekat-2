package lms.reminder;

import java.util.Random;

public class ReminderUtil {

    private static final String[] REMINDERS = {
            "Popij čašu vode",
            "Vrijeme je za kratku pauzu",
            "Ne zaboravi zapisati troškove",
            "Idi ranije spavati",
            "Prošetaj 10 minuta",
            "Uradila si dovoljno za danas"
    };

    public static String getRandomReminder() {
        Random random = new Random();
        return REMINDERS[random.nextInt(REMINDERS.length)];
    }
}