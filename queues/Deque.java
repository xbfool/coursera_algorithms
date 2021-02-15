/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item> {
    private Node<Item> head;
    private Node<Item> last;
    private int size;

    // construct an empty deque
    public Deque() {
        head = null;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else {
            Node<Item> i = new Node<Item>();
            i.item = item;
            i.prev = null;
            i.next = null;
            if (head == null || last == null) {
                head = i;
                last = i;
            }
            else {
                i.next = head;
                head.prev = i;
                head = i;
            }
            size++;
        }
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        else {
            Node<Item> i = new Node<Item>();
            i.item = item;
            i.next = null;
            i.prev = null;
            if (head == null || last == null) {
                head = i;
                last = i;
            }
            else {
                i.prev = last;
                last.next = i;
                last = i;
            }
            size++;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else {
            Item i = head.item;
            size--;
            if (size == 0) {
                last = null;
                head = null;
            }
            else {
                head = head.next;
                head.prev = null;
            }
            return i;
        }
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        else {
            Item i = last.item;

            size--;
            if (size == 0) {
                head = null;
                last = null;
            }
            else {
                last = last.prev;
                last.next = null;
            }
            return i;
        }
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator<Item>(this.head);
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("hello deque");
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.removeFirst();

    }

    private class Node<T> {
        public T item;
        public Node<T> prev;
        public Node<T> next;
    }


    private class DequeIterator<T> implements Iterator<T> {
        Node<T> current;

        // initialize pointer to head of the list for iteration
        public DequeIterator(Node<T> head) {
            current = head;
        }

        // returns false if next element does not exist
        public boolean hasNext() {
            return current != null;
        }

        // return current data and update pointer
        public T next() {
            if (current == null) {
                throw new NoSuchElementException();
            }
            T i = current.item;
            current = current.next;
            return i;
        }

        // implement if needed
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}