package avl;


import org.junit.Test;
import util.FileOperation;

import java.util.ArrayList;

public class AVLTreeTest {
    @Test
    public void test() {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/util/pride-and-prejudice.txt", words)) {
            long startTime = System.nanoTime();

            AVLTree<String, String> avl = new AVLTree<>();
            for (String word : words) {
                avl.add(word, null);
            }

            for (String word : words) {
                avl.contains(word);
            }

            long endTime = System.nanoTime();

            System.out.println("BST: " + (endTime - startTime) / 10000000000.0);
        }
    }
}
