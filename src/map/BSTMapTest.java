package map;


import org.junit.Test;

public class BSTMapTest {

    @Test
    public void add() {
        BSTMap<Integer, String> map = new BSTMap<>();
        map.add(2, "a");
        map.add(1, "b");
        map.add(0, "c");
        map.add(3, "d");
        System.out.println(map);

        assert map.contains(1) == true;
        assert map.contains(4) == false;


        assert map.get(0) == "c";
        assert map.get(4) == null;

//        map.set(4, "x");

        map.set(3, "x");
        assert map.get(3) == "x";

        assert map.remove(2) == "a";
        assert map.getSize() == 3;



    }
}
