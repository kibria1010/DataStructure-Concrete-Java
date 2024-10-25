package stack;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Md Golam Kibria
 */
public class Stack<T> implements Iterable<T> {

    Node<T> first;
    int n;
    
    public static class Node<T> {
        T data;
        Node<T> next;
    }

    public Stack() {
        first = null;
        n=0;
    }
    
    public void push(T data) {
        Node<T> oldFirst = first;
        first = new Node<>();
        first.data = data;
        first.next = oldFirst;
        n++;
    }
    
    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T data = first.data;
        first = first.next;
        n--;
        return data;
    }
    
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack underflow");
        }
        return first.data;
    }
    
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return n;
    }
    
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        System.out.println(stack.toString());

        for (String data : stack) {
            System.out.print(data + " ");
        }
        System.out.println("");

        Iterator<String> it = stack.iterator();
        while (it.hasNext()) {
            String data = it.next();
            System.out.print(data + " ");
        }
        
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        QueueWithTwoStacks<String> q = new QueueWithTwoStacks<>();
        q.enqueue("4");
        q.enqueue("5");
        q.enqueue("6");

        while (!q.isEmpty()) {
            System.out.println(q.dequeue());
        }
    }

    @Override
    public Iterator<T> iterator(){
        return new LinkedIterator(first);
    }
    
    private class LinkedIterator implements Iterator<T> {
        
        Node<T> current;

        public LinkedIterator(Node<T> current) {
            this.current = current;
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