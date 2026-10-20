package com.dataforge.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility to render data collections as clean, bordered ASCII tables in the console.
 */
public class TableRenderer {

    public static void render(String[] headers, List<String[]> rows) {
        if (headers == null || headers.length == 0) {
            System.out.println("(Empty headers)");
            return;
        }

        int cols = headers.length;
        int[] colWidths = new int[cols];

        for (int i = 0; i < cols; i++) {
            colWidths[i] = headers[i] != null ? headers[i].length() : 4;
        }

        if (rows != null) {
            for (String[] row : rows) {
                if (row == null) continue;
                for (int i = 0; i < cols; i++) {
                    String val = (i < row.length && row[i] != null) ? row[i] : "NULL";
                    if (val.length() > colWidths[i]) {
                        colWidths[i] = val.length();
                    }
                }
            }
        }

        // Add 1 char padding on left and right
        for (int i = 0; i < cols; i++) {
            colWidths[i] += 2;
        }

        String separator = buildSeparator(colWidths);

        System.out.println(separator);
        printRow(headers, colWidths);
        System.out.println(separator);

        if (rows == null || rows.isEmpty()) {
            System.out.println("| " + padRight("No records found", getRowTotalWidth(colWidths) - 3) + "|");
        } else {
            for (String[] row : rows) {
                printRow(row, colWidths);
            }
        }
        System.out.println(separator);
    }

    private static void printRow(String[] items, int[] colWidths) {
        StringBuilder sb = new StringBuilder();
        sb.append("|");
        for (int i = 0; i < colWidths.length; i++) {
            String val = (items != null && i < items.length && items[i] != null) ? items[i] : "NULL";
            sb.append(" ").append(padRight(val, colWidths[i] - 1)).append("|");
        }
        System.out.println(sb.toString());
    }

    private static String buildSeparator(int[] colWidths) {
        StringBuilder sb = new StringBuilder();
        sb.append("+");
        for (int w : colWidths) {
            for (int j = 0; j < w; j++) {
                sb.append("-");
            }
            sb.append("+");
        }
        return sb.toString();
    }

    private static int getRowTotalWidth(int[] colWidths) {
        int sum = 1;
        for (int w : colWidths) {
            sum += w + 1;
        }
        return sum;
    }

    private static String padRight(String s, int n) {
        return String.format("%-" + n + "s", s);
    }
}
