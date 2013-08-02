/**
 * @author Olena Ivashyna
 * @date February 22, 2013
 */
public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue stringArray = new RandomizedQueue();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            stringArray.enqueue(item);
        }
        for (int i = 0; i < k; i++)
            StdOut.println(stringArray.dequeue());
    }
}