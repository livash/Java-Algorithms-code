import java.util.Iterator;

/*
 * @author Olena Ivashyna
 * @date February 22, 2013
 */
public class Deque<Item> implements Iterable<Item> {

    
    private Node first = null, last = null;
    private int size = 0;

    private class Node<Item> {

        Item item;
        Node next;
        Node previous;
    }

    public Deque() {
    } // construct an empty deque

    public boolean isEmpty() // is the deque empty?
    {
        return (size() == 0);
    }

    public int size()// return the number of items on the deque
    {
        return size;
    }

    public void addFirst(Item item) // insert the item at the front
    {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (size() == 0) {
            first = new Node();
            first.item = item;
            first.next = null;
            first.previous = null;
            last = first;
            size++;
        } else {

            Node oldFirst = first;
            first = new Node();
            first.item = item;
            first.next = oldFirst;
            first.previous = null;
            size++;
        }
    }

    public void addLast(Item item) // insert the item at the end
    {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
        if (size() == 0) {
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = null;
            first = last;
            size++;
        } else {
            Node oldLast = last;
            last = new Node();
            last.item = item;
            last.next = null;
            last.previous = oldLast;
            oldLast.next = last;
            size++;
        }
    }

    public Item removeFirst() // delete and return the item at the front
    {
        Object item = null;
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } else if (size() == 1) {

            item = first.item;
            first = null;
            last = null;
            size--;

        } else {
            item = first.item;
            first = first.next;
            first.previous = null;
            size--;
        }
        return (Item) item;
    }

    public Item removeLast() // delete and return the item at the end
    {
        Object item = null;
        if (isEmpty()) {
            throw new java.util.NoSuchElementException();
        } else if (size() == 1) {

            item = last.item;
            first = null;
            last = null;
            size--;

        } else {
            item = last.item;
            last = last.previous;
            last.next = null;
            size--;
        }
        return (Item) item;
    }

    private interface Iterable<Item> {

        Iterator<Item> iterator();
    }

    @Override
    public Iterator<Item> iterator() {  // return an iterator over items in order from front to end
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item> {

        private Node current = first;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (current == null) {
                throw new java.util.NoSuchElementException();
            }
            Object item = current.item;
            current = current.next;
            return (Item) item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}