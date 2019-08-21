package trie;


import org.junit.Test;
import tree.BST;
import util.FileOperation;

import java.util.ArrayList;

public class TrieTest {
    @Test
    public void test1() {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/util/pride-and-prejudice.txt", words)) {
            System.out.println("size: " + words.size());

            long startTime = System.nanoTime();

            BST<String> bst = new BST<>();
            for (String word : words) {
                bst.add(word);
            }

            for (String word : words) {
                bst.contains(word);
            }

            long endTime = System.nanoTime();

            System.out.println("BST: " + (endTime - startTime) / 10000000000.0);
        }
    }

    @Test
    public void test2() {
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile("src/util/pride-and-prejudice.txt", words)) {
            System.out.println("size: " + words.size());
            long startTime = System.nanoTime();

            Trie trie = new Trie();
            for (String word : words) {
                trie.add(word);
            }

            for (String word : words) {
                trie.contains(word);
            }

            long endTime = System.nanoTime();

            System.out.println("trie: " + (endTime - startTime) / 10000000000.0);

        }
    }

    @Test
    public void match() {
        Trie t = new Trie();
        t.add("deer");
        t.add("door");
        t.add("dog");
        assert t.contains("doo") == false;
        assert t.match("door") == true;
        assert t.match("d..r") == true;
        assert t.match("d.r") == false;
        assert t.match("doo.") == true;

        assert t.isPrefix("do") == true;
        assert t.isPrefix("doo") == true;
        assert t.isPrefix("doe") == false;
    }
}
