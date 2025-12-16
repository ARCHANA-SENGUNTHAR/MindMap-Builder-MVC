package utils;

import java.time.LocalDate;

public class DateHelper {
    // Very simple spaced repetition: if correct -> double interval, else reset to 1
    public static LocalDate calculateNextReview(LocalDate current, boolean correct) {
        LocalDate today = LocalDate.now();
        if (current == null || current.isBefore(today))
            current = today;
        long interval = java.time.temporal.ChronoUnit.DAYS.between(today, current);
        if (interval < 1)
            interval = 1;
        if (correct)
            interval *= 2;
        else
            interval = 1;
        return today.plusDays(interval);
    }
}