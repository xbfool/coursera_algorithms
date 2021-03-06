/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size = 0;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (size >= items.length) {
            Item[] n = (Item[]) new Object[items.length * 2];
            for (int i = 0; i < size; i++) {
                n[i] = items[i];
            }
            items = n;
        }
        items[size++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int n = StdRandom.uniform(size);
        Item temp = items[n];
        items[n] = items[size-1];
        items[size-1] = temp;
        size--;
        if (size <= items.length / 4 && items.length >= 4) {
            Item[] ni = (Item[]) new Object[items.length / 2];
            for (int i = 0; i < size; i++) {
                ni[i] = items[i];
            }
            items = ni;
        }

        return temp;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int n = StdRandom.uniform(size);
        return items[n];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizeIterator<Item>(this.items, this.size);
    }

    // unit testing (required)
    public static void main(String[] args) {
        System.out.println("hello Randomized Queue");
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(44);
        rq.enqueue(19);
        Iterator<Integer> i = rq.iterator()    ;
        int a = i.next();
        int b= i.next();
        System.out.println("hello Randomized Queue");
    }

    private class RandomizeIterator<T> implements Iterator<T> {
        private final T[] items;
        private final int size;
        private int current;

        // initialize pointer to head of the list for iteration
        public RandomizeIterator(T[] items, int size) {
            this.items = (T[])new Object[size];
            for (int i = 0; i < size; i++) {
                this.items[i] = items[i];
            }
            StdRandom.shuffle(this.items);
            this.size = size;
            current = 0;
        }

        // returns false if next element does not exist
        public boolean hasNext() {
            return current < size;
        }

        // return current data and update pointer
        public T next() {
            if (current >= size)
                throw new NoSuchElementException();
            T i = items[current];
            current++;
            return i;
        }

        // implement if needed
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}