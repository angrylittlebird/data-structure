package queue;

public class LoopQueue<E> implements Queue<E> {
    private int size;
    private E[] data;
    private int front, tail;

    public LoopQueue() {
        this(10);
    }

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front)
            resize(getCapacity() * 2);

        data[tail] = e;
        size++;
        tail = (tail + 1) % data.length;
    }

    @Override
    public E dequeue() {
        if (isEmpty())
            throw new IllegalArgumentException("dequeue failed.LoopQueue is empty.");

        E ret = data[front];
        data[front] = null;
        size--;
        front = (front + 1) % data.length;
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("dequeue failed.LoopQueue is empty.");
        return data[front];
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("LoopQueue's size is %s,capacity is %s \n", getSize(), getCapacity()));
        sb.append("front");
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[(i + front) % data.length]);
            if ((i + front) % data.length != tail-1) {
                sb.append(',');
            }
        }
        sb.append(']');
        sb.append("tail");
        return sb.toString();
    }
}
