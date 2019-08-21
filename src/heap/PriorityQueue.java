package heap;

public class PriorityQueue<E extends Comparable<E>> implements Queue<E>{
    private MaxHeap<E> heap;
    @Override
    public int getSize() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        heap.add(e);
    }

    @Override
    public E dequeue() {
        return heap.extractMax();
    }

    @Override
    public E getFront() {
        return heap.findMax();
    }

    public PriorityQueue(E[] arr) {
        this.heap = new MaxHeap<>(arr);
    }
}
