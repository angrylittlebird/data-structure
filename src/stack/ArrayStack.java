package stack;

public class ArrayStack<E> implements Stack<E> {
    private int size;
    private E[] data;

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    public ArrayStack() {
        this(10);
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(E o) {
        if (size == data.length)
            resize(2 * data.length);

        data[size] = o;
        size++;
    }

    @Override
    public E peek() {
        return data[size - 1];
    }

    @Override
    public E pop() {
        E datum = data[size - 1];
        data[size - 1] = null;
        size--;
        if (size == data.length / 4 && data.length / 2 !=0)
            resize(data.length / 2);
        return datum;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(',');
            }
        }
        sb.append("] top");
        return sb.toString();
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }
}
