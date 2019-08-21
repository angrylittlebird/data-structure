package map;


import org.junit.Test;

public class LinkedListMapTest {

    @Test(expected = IllegalArgumentException.class)
    public void add() {
        LinkedListMap<Integer, Integer> map = new LinkedListMap<>();
        assert map.isEmpty() == true;
        for (int i = 0; i < 100; i++) {
            map.add(i, i);
        }
        assert map.get(1) == 1;
        assert map.get(100) == null;

        assert map.getSize() == 100;
        assert map.isEmpty() == false;

        assert map.contains(100) == false;
        assert map.contains(99) == true;

        map.add(1, 2);
        assert map.get(1) == 2;
        map.add(1000,1000);
        assert map.get(1000) == 1000;


        map.set(1, 1);
        assert map.get(1) == 1;

        map.set(100,100);


        assert map.remove(1) == 1;
        assert map.getSize() == 99;

        assert map.remove(101) == null;
        assert map.getSize() == 99;
    }
}
