package com.epam.rd.autocode.decorator;

import java.util.List;
import java.util.AbstractList;
import java.util.Iterator;


public class Decorators {
    public static List<String> evenIndexElementsSubList(List<String> sourceList) {
        return new EvenIndexList(sourceList);
    }

    private static class EvenIndexList extends AbstractList<String> {
        private final List<String> source;

        public EvenIndexList(List<String> source) {
            this.source = source;
        }

        @Override
        public String get(int index) {
            if (index < 0 || index >= size()) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
            }
            return source.get(index * 2); // Accessing even indices from the original list
        }

        @Override
        public int size() {
            return (source.size() + 1) / 2; // Total even-indexed elements
        }

        @Override
        public Iterator<String> iterator() {
            return new Iterator<String>() {
                private int currentIndex = 0;

                @Override
                public boolean hasNext() {
                    return currentIndex < size();
                }

                @Override
                public String next() {
                    return get(currentIndex++);
                }
            };
        }
    }
}

