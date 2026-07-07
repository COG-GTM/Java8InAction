package lambdasinaction.chap11;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilTest {

    @Test
    public void formatRoundsToTwoDecimals() {
        assertEquals(3.14, Util.format(3.14159), 0.0001);
        assertEquals(5.0, Util.format(5.0), 0.0001);
        assertEquals(2.5, Util.format(2.5), 0.0001);
    }
}
