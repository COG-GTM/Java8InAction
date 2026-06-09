package lambdasinaction.chap12;

import org.junit.Test;

import java.time.*;
import java.time.chrono.JapaneseDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;
import static org.junit.Assert.*;

public class DateTimeTest {

    @Test
    public void localDateCreation() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        assertEquals(2014, date.getYear());
        assertEquals(Month.MARCH, date.getMonth());
        assertEquals(18, date.getDayOfMonth());
        assertEquals(DayOfWeek.TUESDAY, date.getDayOfWeek());
        assertEquals(31, date.lengthOfMonth());
        assertFalse(date.isLeapYear());
    }

    @Test
    public void localDateChronoFieldAccess() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        assertEquals(2014, date.get(ChronoField.YEAR));
        assertEquals(3, date.get(ChronoField.MONTH_OF_YEAR));
        assertEquals(18, date.get(ChronoField.DAY_OF_MONTH));
    }

    @Test
    public void localTimeCreation() {
        LocalTime time = LocalTime.of(13, 45, 20);
        assertEquals(13, time.getHour());
        assertEquals(45, time.getMinute());
        assertEquals(20, time.getSecond());
    }

    @Test
    public void localDateTimeCreation() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        LocalTime time = LocalTime.of(13, 45, 20);
        LocalDateTime dt = LocalDateTime.of(date, time);
        assertEquals(date, dt.toLocalDate());
        assertEquals(time, dt.toLocalTime());
    }

    @Test
    public void duration() {
        LocalTime t1 = LocalTime.of(13, 45, 10);
        LocalTime t2 = LocalTime.of(13, 45, 20);
        Duration d = Duration.between(t1, t2);
        assertEquals(10, d.getSeconds());
    }

    @Test
    public void durationOfChronoUnit() {
        Duration threeMinutes = Duration.of(3, ChronoUnit.MINUTES);
        assertEquals(180, threeMinutes.getSeconds());
    }

    @Test
    public void temporalAdjusterNextOrSame() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        LocalDate sunday = date.with(nextOrSame(DayOfWeek.SUNDAY));
        assertEquals(DayOfWeek.SUNDAY, sunday.getDayOfWeek());
        assertTrue(sunday.isAfter(date) || sunday.isEqual(date));
    }

    @Test
    public void temporalAdjusterLastDayOfMonth() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        LocalDate lastDay = date.with(lastDayOfMonth());
        assertEquals(31, lastDay.getDayOfMonth());
    }

    @Test
    public void dateTimeFormatter() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String isoFormatted = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        assertEquals("2014-03-18", isoFormatted);

        DateTimeFormatter custom = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        assertEquals("18/03/2014", date.format(custom));
    }

    @Test
    public void parseDateFromString() {
        LocalDate parsed = LocalDate.parse("2014-03-18");
        assertEquals(LocalDate.of(2014, 3, 18), parsed);
    }

    @Test
    public void instantCreation() {
        Instant instant = Instant.ofEpochSecond(0);
        assertEquals(0, instant.getEpochSecond());
    }

    @Test
    public void japaneseDateConversion() {
        LocalDate date = LocalDate.of(2014, 3, 18);
        JapaneseDate japaneseDate = JapaneseDate.from(date);
        assertNotNull(japaneseDate);
    }

    @Test
    public void periodBetweenDates() {
        LocalDate d1 = LocalDate.of(2014, 1, 1);
        LocalDate d2 = LocalDate.of(2014, 3, 18);
        Period period = Period.between(d1, d2);
        assertEquals(2, period.getMonths());
        assertEquals(17, period.getDays());
    }
}
