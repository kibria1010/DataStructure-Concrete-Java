package hash;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Md Golam Kibria
 */
public class Queue<T> implements Iterable<T>{
    Node<T> first;
    Node<T> last;
    int n;
    
    private static class Node<T>{
        T data;
        Node<T> next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }
    
    public boolean isEmpty() {
        return first == null;
    }
    
    public int size() {
        return n;
    }
    
    public void enqueue(T data) {
        Node<T> oldlast = last;
        last = new Node<T>();
        last.data = data;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }
    
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        T data = first.data;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return data;
    }
    
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return first.data;
    }
    
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }
        
    public static void main(String[] args) {
        Queue<String> queue = new Queue<String>();
        
        queue.enqueue("1");
        queue.enqueue("2");
        queue.enqueue("3");
        System.out.println(queue.toString());
        
        while (!queue.isEmpty()) {            
            System.out.println(queue.dequeue());
        }
    }
        
    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator(first);
    }
    
    private class LinkedIterator implements Iterator<T>{
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
            if (!hasNext()) throw new NoSuchElementException();
            T data = current.data;
            current = current.next;
            return data;
        }
    }
        
}
