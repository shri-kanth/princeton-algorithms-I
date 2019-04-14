package com.my.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;

public class PointTest
{
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue( true );
    }

    @Test
    public void testSlopeTo(){
        Point point = new Point(0, 0);
        Point vertical = new Point(0,1);
        Point horizontal = new Point(1, 0);
        Point inclined = new Point(5,5);
        assertTrue(Double.NEGATIVE_INFINITY == point.slopeTo(point));
        assertTrue(Double.POSITIVE_INFINITY == point.slopeTo(vertical));
        assertTrue(0d == point.slopeTo(horizontal));
        assertTrue(1d == point.slopeTo(inclined));
    }

    @Test
    public void testCompareTo(){
        Point p0 = new Point(0,0);
        Point p1 = new Point(1,1);
        Point p2 = new Point(2,2);
        Point p3 = new Point(1,0);
        Point p4 = new Point(0,0);

        assertTrue(p0.compareTo(p4) == 0);
        assertTrue(p0.compareTo(p1) == -1);
        assertTrue(p0.compareTo(p2) == -1);
        assertTrue(p2.compareTo(p0) == 1);
        assertTrue(p2.compareTo(p1) == 1);
        assertTrue(p0.compareTo(p3) == -1);
    }

    @Test
    public void testSlopeOrder(){
        Point p0 = new Point(0,0);
        Point p1 = new Point(1,0);
        Point p2 = new Point(1,1);
        Point p3 = new Point(0,1);

        Point[] toBeSortedArray = {p3, p1, p2};
        Point[] sortedArray = {p1, p2, p3};

        Arrays.sort(toBeSortedArray,p0.slopeOrder());

        assertTrue(Arrays.equals(toBeSortedArray,sortedArray));
    }
}
