/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int i = Integer.parseInt(args[0]);
        try {
            while(true) {
                String s = StdIn.readString();
                q.enqueue(s);
            }
        }  catch (NoSuchElementException e) {

        }
        int j = 0;
        for (Iterator<String> iter = q.iterator(); iter.hasNext() && j < i; j++) {
            String s = iter.next();
            System.out.println(s);
        }
    }
}
