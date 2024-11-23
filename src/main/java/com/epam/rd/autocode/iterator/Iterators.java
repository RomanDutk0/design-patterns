package com.epam.rd.autocode.iterator;


import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators {

    public static Iterator<Integer> intArrayTwoTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;  // Current index in the array
            private int count = 0;   // Count how many times we've returned the current element

            @Override
            public boolean hasNext() {
                // Check if we can return more elements
                return index < array.length && count < 2;
            }

            @Override
            public Integer next() {
                // If no next element is available, throw an exception
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer value = array[index];
                count++;
                if (count == 2) { // After returning twice, move to the next element
                    index++;
                    count = 0; // Reset the count for the next element
                }
                return value;
            }
        };
    }

    public static Iterator<Integer> intArrayThreeTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;  // Current index in the array
            private int count = 0;   // Count how many times we've returned the current element

            @Override
            public boolean hasNext() {
                // Check if we can return more elements
                return index < array.length && count < 3;
            }

            @Override
            public Integer next() {
                // If no next element is available, throw an exception
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer value = array[index];
                count++;
                if (count == 3) { // After returning three times, move to the next element
                    index++;
                    count = 0; // Reset the count for the next element
                }
                return value;
            }
        };
    }

    public static Iterator<Integer> intArrayFiveTimesIterator(int[] array) {
        return new Iterator<Integer>() {
            private int index = 0;  // Current index in the array
            private int count = 0;   // Count how many times we've returned the current element

            @Override
            public boolean hasNext() {
                // Check if we can return more elements
                return index < array.length && count < 5;
            }

            @Override
            public Integer next() {
                // If no next element is available, throw an exception
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Integer value = array[index];
                count++;
                if (count == 5) { // After returning five times, move to the next element
                    index++;
                    count = 0; // Reset the count for the next element
                }
                return value;
            }
        };
    }

    public static Iterable<String> table(String[] columns, int[] rows) {
        return () -> new Iterator<String>() {
            private int columnIndex = 0; // Current column index
            private int rowIndex = 0; // Current row index

            @Override
            public boolean hasNext() {
                // Check if there are more elements to iterate through
                return columnIndex < columns.length && rowIndex < rows.length;
            }

            @Override
            public String next() {
                // If no next element is available, throw an exception
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                String cell = columns[columnIndex] + rows[rowIndex];
                rowIndex++;
                if (rowIndex == rows.length) { // Move to the next column after going through all rows
                    columnIndex++;
                    rowIndex = 0; // Reset row index for the new column
                }
                return cell;
            }
        };
    }
}
