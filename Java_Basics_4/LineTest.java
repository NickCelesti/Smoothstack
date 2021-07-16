package Java_Basics_4;
// Assignment 4

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LineTest {

    @Test
    public void getSlopeTest() {
        // edge case
        Line vertical = new Line(0, 0, 0, 5);
        assertThrows(ArithmeticException.class, vertical::getSlope);

        // edge case
        Line horizonal = new Line(0, 0, 5, 0);
        assertEquals(0, horizonal.getSlope(), 0.0001);

        // typical line
        Line diagonal = new Line(0, 0, 4, 5);
        assertEquals(1.25, diagonal.getSlope(), 0.0001);
    }

    @Test
    public void getDistanceTest() {
        // zero distance
        Line zero = new Line(0, 0, 0, 0);
        assertEquals(0, zero.getDistance(), 0.0001);

        // small distance
        Line small = new Line(1, 3, 1.01, 2.94);
        assertEquals(0.060828, small.getDistance(), 0.0001);

        // large distance
        Line big = new Line(8000, -301, -2394, 2093);
        assertEquals(10666.136695, big.getDistance(), 0.0001);

        Line negative = new Line(5, 0, 0, 0);
        assertEquals(5, negative.getDistance(), 0.0001);

    }

    @Test
    public void parallelToTest() {

        // same line
        Line a = new Line(0, 0, 5, 0);
        Line b = new Line(0, 0, 5, 0);
        assertEquals(true, a.parallelTo(b));

        // perpendicular
        a = new Line(0, 0, 3, 5);
        b = new Line(0, 5, 3, 0);
        assertEquals(false, a.parallelTo(b));

        // close
        a = new Line(0, 0, 3, 5);
        b = new Line(0, 0, 3, 4.99);
        assertEquals(false, a.parallelTo(b));

        // offset
        a = new Line(0, 0, 3, 5);
        b = new Line(0, 5, 3, 10);
        assertEquals(true, a.parallelTo(b));

        // a is a segment of b
        a = new Line(0, 0, 10, 10);
        b = new Line(2, 2, 7, 7);
        assertEquals(true, a.parallelTo(b));
    }

}
