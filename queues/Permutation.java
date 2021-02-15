/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        int i = Integer.parseInt(args[0]);
        for (int j = 0; j < i; j++) {
            String s = StdIn.readString();
            q.enqueue(s);
        }
        for (Iterator<String> iter = q.iterator(); iter.hasNext();){
            String s = iter.next();
            System.out.println(s);
        }
    }
}
