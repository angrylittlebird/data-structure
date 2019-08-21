package stack;

import org.junit.Test;

public class ArrayStackTest {
    @Test
    public void push() throws Exception {
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        for (int i = 0; i < 10; i++) {
            arrayStack.push(i);
        }

        System.out.println(arrayStack);

        arrayStack.push(11);
        System.out.println(arrayStack);

        assert arrayStack.peek() == 11 && arrayStack.getSize() == 11;

        assert arrayStack.pop() == 11 && arrayStack.getSize() == 10;
        System.out.println(arrayStack);
    }
}
