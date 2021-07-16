package Java_Basics_5;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalAdjusters;

public class Assignment5_1_Date_Time {

    // for question 7
    static boolean isSpooky(LocalDate date) {
        return (date.getDayOfMonth() == 13 && date.getDayOfWeek() == DayOfWeek.FRIDAY);
    }

    public static void main(String[] args) {
        // 1. LocalDateTime, maybe ZonedDateTime

        // ---------------------------------------------------------

        // 2.
        // the key is TemporalAdjusters.previous
        LocalDate randomDate = LocalDate.now();
        LocalDate lastThursday = randomDate.with(TemporalAdjusters.previous(DayOfWeek.THURSDAY));
        System.out.println("2. Last Thursday: " + lastThursday);

        // TemporalAdjusters.previous returns the date of the previous first instance of
        // the given day of the week

        // ---------------------------------------------------------

        // 3.
        // They both track an offset from UTC time, however:
        // ZoneID uses ZoneRules for converting between an Instant and LocalDateTime.
        // ZoneOffset is used to represent the absolute zone offset from UTC time zone.

        // ---------------------------------------------------------

        // 4.
        // Instant to ZonedDateTime
        Instant instant = Instant.now();
        // Instant has an atZone method which returns the ZonedDateTime at the given
        // ZoneID
        ZonedDateTime indiaDT = instant.atZone(ZoneId.of("Asia/Kolkata"));
        System.out.println("4. ZonedDateTime : " + indiaDT);

        // ZonedDateTime to Instant
        // ZonedDateTime has the toInstant method which returns the instant of the DT
        instant = indiaDT.toInstant();
        System.out.println("4. Instant Time: " + instant);

        // ---------------------------------------------------------

        // 5.
        // given year: 2000 (leap year just to be sure)
        for (Month month : Month.values()) {
            YearMonth yearMonth = YearMonth.of(2000, month);
            System.out.println("5. " + month + ": " + yearMonth.lengthOfMonth());
        }

        // ---------------------------------------------------------

        // 6.
        // given month: February
        Month feb = Month.FEBRUARY;
        LocalDate date = Year.now().atMonth(feb).atDay(1).with(TemporalAdjusters.firstInMonth(DayOfWeek.MONDAY));
        while (date.getMonth() == feb) {
            System.out.println("6. " + date);
            date = date.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        }

        // ---------------------------------------------------------

        // 7.
        // given dates: July 13, 2021 (not Friday)
        // November 13, 2020 (is Friday)
        LocalDate notSpooky = Year.of(2021).atMonth(Month.JULY).atDay(13);
        LocalDate spooky = Year.of(2020).atMonth(Month.NOVEMBER).atDay(13);
        System.out.println("7. " + notSpooky + ": " + isSpooky(notSpooky));
        System.out.println("7. " + spooky + ": " + isSpooky(spooky));

    }
}
