package graph;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Bag<T> implements Iterable<T> {

    Node<T> first;
    int n;

    private static class Node<T> {
        
        T data;
        Node<T> next;
    }

    public Bag() {
        first = null;
        n = 0;
    }

    public static void main(String[] args) {
        Bag<String> list = new Bag<>();
        list.add("first");
        list.add("mid");
        list.add("last");

        for (String data : list) {
            System.out.print(data + " ");
        }

    }

    public void add(T data) {
        Node<T> oldfirst = first;
        first = new Node<>();
        first.data = data;
        first.next = oldfirst;
        n++;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<T> {

        Node<T> current;

        public LinkedIterator(Node<T> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T data = current.data;
            current = current.next;
            return data;
        }
    }

}
