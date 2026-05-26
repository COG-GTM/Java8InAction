package lambdasinaction.chap5;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.*;

public class StreamProcessingTest {

    private List<Transaction> transactions;

    @Before
    public void setUp() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950));
    }

    @Test
    public void findTransactionsIn2011SortedByValue() {
        List<Transaction> result = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(comparing(Transaction::getValue))
                .collect(toList());

        assertEquals(2, result.size());
        assertEquals(300, result.get(0).getValue());
        assertEquals(400, result.get(1).getValue());
    }

    @Test
    public void findUniqueCities() {
        List<String> cities = transactions.stream()
                .map(t -> t.getTrader().getCity())
                .distinct()
                .collect(toList());

        assertEquals(2, cities.size());
        assertTrue(cities.contains("Cambridge"));
        assertTrue(cities.contains("Milan"));
    }

    @Test
    public void findCambridgeTradersSortedByName() {
        List<Trader> traders = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> t.getCity().equals("Cambridge"))
                .distinct()
                .sorted(comparing(Trader::getName))
                .collect(toList());

        assertEquals(3, traders.size());
        assertEquals("Alan", traders.get(0).getName());
        assertEquals("Brian", traders.get(1).getName());
        assertEquals("Raoul", traders.get(2).getName());
    }

    @Test
    public void allTraderNamesSortedAlphabetically() {
        String result = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2);

        assertEquals("AlanBrianMarioRaoul", result);
    }

    @Test
    public void anyTraderInMilan() {
        boolean milanBased = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));

        assertTrue(milanBased);
    }

    @Test
    public void highestTransactionValue() {
        int highest = transactions.stream()
                .map(Transaction::getValue)
                .reduce(0, Integer::max);

        assertEquals(1000, highest);
    }

    @Test
    public void traderToString() {
        Trader trader = new Trader("Test", "London");
        assertTrue(trader.toString().contains("Test"));
        assertTrue(trader.toString().contains("London"));
    }

    @Test
    public void transactionToString() {
        Trader trader = new Trader("Test", "London");
        Transaction tx = new Transaction(trader, 2020, 500);
        String str = tx.toString();
        assertTrue(str.contains("2020"));
        assertTrue(str.contains("500"));
    }
}
