package linkedlist;

import org.junit.Test;

public class LinkedListTest {
    @Test
    public void add() {
        LinkedList<Integer> l = new LinkedList<>();
        l.addFirst(1);
        System.out.println(l);
        l.addFirst(2);
        assert l.getFirst() == 2;
        assert l.getSize() == 2;
        System.out.println(l);

        l.add(1, 3);
        assert l.get(1) == 3;
        System.out.println(l);

        l.addLast(4);
        assert l.getLast() ==4;
        System.out.println(l);

        l.set(1, 11);
        assert l.get(1) == 11;
        l.set(0,12);
        assert l.getFirst() == 12;

        assert l.contains(12);
        assert l.contains(13) == false;

        System.out.println(l);
        assert l.remove(1) == 11;
        assert l.get(1) == 1;
        System.out.println(l);

        assert l.removeFirst()==12;
        assert l.removeLast() == 4;
    }
}
