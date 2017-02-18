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
}
