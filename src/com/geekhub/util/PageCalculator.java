package com.geekhub.util;

public class PageCalculator {

    public static int calculatePages(int count, int offset) {
        int pages;
        if (((count % offset) == 0) || (count > 0 && count < offset)) {
            pages = count / offset;
        } else {
            pages = count / offset + 1;
        }
        return pages;
    }
}
