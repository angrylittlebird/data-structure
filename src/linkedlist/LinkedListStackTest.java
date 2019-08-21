package linkedlist;


import org.junit.Test;
import stack.ArrayStack;
import stack.Stack;

import java.util.Random;

public class LinkedListStackTest {

    @Test
    public void push() {
        LinkedListStack<Integer> stack = new LinkedListStack<>();
        stack.push(2);
        stack.push(1);
        System.out.println(stack);
        assert  stack.peek() == 1;
        assert stack.pop() == 1;
        assert stack.pop() == 2;

        System.out.println(stack);
    }

    @Test
    public void compare(){
        System.out.println(test(10000,new LinkedListStack<>()));
        System.out.println(test(10000,new ArrayStack<>()));
        System.out.println(test(10000,new ArrayStack<>(10000)));
    }

    private long test(int opCount, Stack<Integer> stack){
        Random random = new Random();
        long starttime = System.nanoTime();
        for (int i = 0; i < opCount; i++) {
            stack.push(random.nextInt());
        }

        for (int i = 0; i < opCount; i++) {
            stack.pop();
        }

        long endtime = System.nanoTime();

        return (endtime-starttime)/100000;
    }
}
