import java.util.Iterator;

/**
 * @author Olena ivashyna
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private int N = 0;
    private Item[] array;

    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }           // construct an empty randomized queue

    public boolean isEmpty() // is the queue empty?
    {
        return N == 0;
    }

    public int size() // return the number of items on the queue
    {
        return N;
    }

    public void enqueue(Item item) // add the item
    {
        if (item == null) {
            throw new java.lang.NullPointerException();
        }
       
        if (N == array.length && N > 0) {
            Item[] copy = (Item[]) new Object[2 * array.length];
            for (int ii = 0; ii < N; ii++) {
                copy[ii] = array[ii];
            }
            array = copy;
        }
        array[N] = item;
        N++;
    }

    public Item dequeue() // delete and return a random item
    {
        if (N == 0) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = (int) (StdRandom.uniform()*(N));
        Item item = array[randomIndex];
        for (int kk = randomIndex; kk < N-1; kk++) {
            array[kk] = array[kk + 1];
        }
        array[N-1]=null;
        N--;
        //add condition for shrinking the array when N<array.lengh/4
        if (N == array.length / 4 && N>5) {
            Item[] copy3 = (Item[]) new Object[array.length / 2];
            for (int jj = 0; jj < N; jj++) {
                copy3[jj] = array[jj];
            }
            array = copy3;
        }
        return item;
    }

    public Item sample() // return (but do not delete) a random item
    {
        if (N == 0) {
            throw new java.util.NoSuchElementException();
        }
        int randomIndex = (int) (StdRandom.uniform()*(N));
        Item item = array[randomIndex];
        return item;
    }

    private interface Iterable<Item> {

        Iterator<Item> iterator();
    }

    @Override
    public Iterator<Item> iterator() {  //return an independent iterator over items in random order
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Item> {

        private Item[] copy2;
        private int N2 = N;
        private int current;

        public ArrayIterator(){
            copy2 = (Item[]) new Object[array.length];
            for(int i=0; i<N2; i++){
                copy2[i]=array[i];
            }
        }
        
        @Override
        public boolean hasNext() {
            current = (int) (StdRandom.uniform() * N2);
            return copy2[current] != null;
        }

        @Override
        public Item next() {
            if (copy2[current] == null) {
                throw new java.util.NoSuchElementException();
            }
            Item item = copy2[current];
            copy2[current] = null;
            for (int kk = current; kk < N2; kk++) {
                copy2[kk] = copy2[kk + 1];
            }
            N2--;
            return (Item) item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}
