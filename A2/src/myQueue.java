import java.io.*;
import java.util.*;

public class myQueue<T> {
    private Node<T> first;
    private Node<T> last;
    private int N;

    private static class Node<T> {
        private T instructor;
        private Node<T> next;
    }

    public myQueue() {
        first = null;
        last  = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;     
    }

    public void enqueue(T s) {
        Node<T> temp = last;
        last = new Node<T>();
        last.instructor = s;
        last.next = null;
        if (isEmpty()) 
            first = last;
        else           
            temp.next = last;
        N++;
    }


    public T dequeue() {
        if (isEmpty()) {
            return null;
        }
        T instructor = first.instructor;
        first = first.next;
        N--;
        if (isEmpty()) 
            last = null; 
        return instructor;
    }

}
