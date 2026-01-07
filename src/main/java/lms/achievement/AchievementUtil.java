package lms.achievement;

import java.util.ArrayList;
import java.util.List;

public class AchievementUtil {

    public static List<String> getAchievements(
            int sleepCount,
            int moodCount,
            int habitCount
    ) {
        List<String> achievements = new ArrayList<>();

        if (sleepCount >= 1)
            achievements.add("Prvi unos spavanja");

        if (sleepCount >= 7)
            achievements.add("Sedam dana praćenja sna");

        if (moodCount >= 1)
            achievements.add("Prvo zabilježeno raspoloženje");

        if (moodCount >= 5)
            achievements.add("Pet dana praćenja raspoloženja");

        if (habitCount >= 1)
            achievements.add("Prva dodana navika");

        if (habitCount >= 5)
            achievements.add("Pet dodanih navika");

        if (achievements.isEmpty())
            achievements.add("Još nema osvojenih znački");

        return achievements;
    }
}