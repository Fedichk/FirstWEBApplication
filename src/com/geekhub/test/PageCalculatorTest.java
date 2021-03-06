package com.geekhub.test;

import com.geekhub.util.PageCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PageCalculatorTest {

    @Test
    public void calculateMultipleAmountTest() throws Exception {
        assertEquals(5, PageCalculator.calculatePages(25, 5));
    }

    @Test
    public void calculateNotMultipleAmountTest() throws Exception {
        assertEquals(6, PageCalculator.calculatePages(27, 5));
    }

    @Test
    public void calculateAmountLessThanLimitTest() throws Exception {
        assertEquals(1, PageCalculator.calculatePages(4, 5));
    }

    @Test
    public void calculateAmountEqualsNullTest() throws Exception {
        assertEquals(0, PageCalculator.calculatePages(0, 5));
    }

    @Test(expected = ArithmeticException.class)
    public void calculateLimitEqualsNullTest() throws Exception {
        assertEquals(0, PageCalculator.calculatePages(5, 0));
    }
}
