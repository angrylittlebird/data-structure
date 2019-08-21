package tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Random;

public class BSTTest {
    @Test
    public void remove() throws Exception {
        BST<Integer> bst = new BST<>();

        //     2
        //  1      4
        //            6
        //          5   7
        //                 8
        Integer[] nums = new Integer[]{2, 4, 1, 6, 7, 8, 5};
        for (Integer num: nums) {
            bst.add(num);
        }

        bst.remove(8);
        bst.inOrder();

        bst.remove(5);
        bst.inOrder();

        bst.remove(2);
        bst.inOrder();
    }

    @Test
    public void preOrder() throws Exception {
        BST<Integer> bst = new BST<>();

        //     2
        //  1      4
        //            6
        //          5   7
        //                 8
        Integer[] nums = new Integer[]{2, 4, 1, 6, 7, 8, 5};
        for (Integer num: nums) {
            bst.add(num);
        }

        assert bst.contains(1) == true;
        assert bst.contains(9) == false;

        bst.preOrder();

        System.out.println("===");

        bst.preOrderNR();

        System.out.println("==");

        bst.inOrder();

        System.out.println("==");

        bst.levelOrder();
    }

    @Test
    public void maximum(){
        BST<Integer> bst = new BST<>();

        Integer[] nums = new Integer[]{2, 4, 1, 6, 7, 8, 5};
        for (Integer num: nums) {
            bst.add(num);
        }

        assert 8 == bst.maximum();
        assert 1  == bst.minimum();
    }

    @Test
    public void removeMin(){
        BST<Integer> bst = new BST<>();

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!bst.isEmpty()) {
            arrayList.add(bst.removeMin());
        }

        for (int i = 1; i < arrayList.size(); i++) {
            if(arrayList.get(i) < arrayList.get(i-1))
                throw new IllegalArgumentException("error");
        }
    }

    @Test
    public void removeMax(){
        BST<Integer> bst = new BST<>();

        Random random = new Random();
        for (int i = 0; i < 1000; i++) {
            bst.add(random.nextInt(10000));
        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        while (!bst.isEmpty()) {
            arrayList.add(bst.removeMax());
        }

        for (int i = 1; i < arrayList.size(); i++) {
            if(arrayList.get(i) > arrayList.get(i-1))
                throw new IllegalArgumentException("error");
        }
    }



}
