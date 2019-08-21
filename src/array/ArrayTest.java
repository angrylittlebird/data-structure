package array;

import org.junit.Test;

public class ArrayTest {

    @Test
    public void add() {
        Array<Integer> array = new Array<>(20);
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        System.out.println(array.toString());

        array.addFirst(-1);
        System.out.println(array);

        array.add(1,100);
        System.out.println(array);

        System.out.println(array.get(0));

        array.set(0, -11);
        System.out.println(array);

        assert array.isContains(1);

        assert array.find(-11) == 0;

        assert array.find(-12) == -1;

        assert array.remove(0) == -11;

        assert array.removeFirst() == 100;

        assert array.removeLast() == 9;

        array.removeElement(0);
        assert array.get(0) == 1;



    }

    @Test
    public void resize(){
        Array<Integer> array = new Array<>();
        for (int i = 0; i < 10; i++) {
            array.addLast(i);
        }
        array.addLast(1);
        assert array.getCapacity() == 20;
        System.out.println(array);

        array.remove(0);
        assert array.getCapacity() == 20;
        array.remove(0);
        array.remove(0);
        array.remove(0);
        array.remove(0);
        array.remove(0);
        assert array.getCapacity() == 10;
        System.out.println(array);
    }
}
