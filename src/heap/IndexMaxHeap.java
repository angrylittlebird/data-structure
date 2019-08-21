package heap;

/**
 * 在构建堆的过程中，数组data中的元素需要交换位置，
 * 那么在需要改变某个元素的E大小时，则需要遍历整个数组来找到该元素。
 * 索引堆：
 * data数组：实际存放的元素
 * indexes数组：索引堆,其中元素的值表示在data数组中的实际位置
 * reverses数组：其索引代表indexes数组中的值，其值代表indexes数组的索引
 * i.e.
 * indexes[i] = j;             indexes[reverses[i]] = i;
 * ==================>
 * reverses[j] = i;            reverses[indexes[i]] = i;
 *
 * @param <E>
 */
public class IndexMaxHeap<E extends Comparable<E>> {
    private Array<Integer> indexes;
    private Array<Integer> reverses;
    private Array<E> data;

    public IndexMaxHeap(int capacity) {
        this.data = new Array<>(capacity);
        indexes = new Array<>(capacity);
    }

    public IndexMaxHeap() {
        this(10);
    }

    public IndexMaxHeap(E[] arr) {
        data = new Array<>(arr);
        indexes = new Array<>(arr.length);
        reverses = new Array<>(arr.length);
        for (int i = 0; i < indexes.getCapacity(); i++) {
            indexes.add(i, i);
            reverses.add(i, i);
        }
        for (int i = parentIndex(indexes.getSize() - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    /**
     * @param index
     * @return 返回当前索引index节点的父节点的索引
     */
    private int parentIndex(int index) {
        return (index - 1) / 2;
    }

    //实际是交换了indexes数组中的值
    private void shiftDown(int k) {
        while (leftChild(k) < size()) {
            int j = leftChild(k);
            if (j + 1 < size() && data.get(indexes.get(j)).compareTo(data.get(indexes.get(j + 1))) < 0) {
                j++;
            }

            if (data.get(indexes.get(k)).compareTo(data.get(indexes.get(j))) < 0) {
                swapIndex(k, j);
                reverses.set(indexes.get(k), k);
                reverses.set(indexes.get(j), j);
            } else {
                //if (data.get(k).compareTo(data.get(j)) >= 0)
                break;
            }

            k = j;
        }
    }

    private void swapIndex(int i, int j) {
        if (i < 0 || i > size() || j < 0 || j > size()) {
            throw new IllegalArgumentException("index is illegal.");
        }
        indexes.swap(i, j);
    }

    /**
     * @param index
     * @return 返回当前索引index节点的左子节点的索引
     */
    private int leftChild(int index) {
        return (2 * index) + 1;
    }

    public int size() {
        return indexes.getSize();
    }

    public void add(E e) {
        data.addLast(e);
        indexes.addLast(data.getSize() - 1);
        reverses.add(indexes.get(data.getSize() - 1), data.getSize() - 1);
        shiftUp(data.getSize() - 1);
    }

    //  在data数组索引为i的位置插入e
    public void change(int i, E e) {
        if (!contains(i)) {
            throw new IllegalArgumentException("index is illegal.");
        }
        data.set(i, e);
        indexes.set(reverses.get(i), i);
        shiftUp(reverses.get(i));
        shiftDown(reverses.get(i));
    }

    private boolean contains(int i) {
        if (i < 0 || i >= indexes.getCapacity()) {
            throw new IllegalArgumentException("index is illegal.");
        }
        return reverses.get(i) != -1;
    }

    //对索引堆中索引为k的值进行上浮的操作，实际比较的是data中的值，交换的是indexes中的值
    private void shiftUp(int k) {
        while (k != 0 && data.get(indexes.get(k)).compareTo(data.get(indexes.get(parentIndex(k)))) > 0) {
            int parent = parentIndex(k);
            swapIndex(k, parent);
            reverses.set(indexes.get(k), k);
            reverses.set(indexes.get(parent), parent);
            k = parent;
        }
    }

    public E extractMax() {
        E ret = findMax();

        swapIndex(0, size() - 1);
        reverses.swap(indexes.get(0), indexes.get(indexes.getSize() - 1));
        Integer removeLast = indexes.removeLast();
        reverses.set(removeLast, -1);

        shiftDown(0);
        return ret;
    }

    public E findMax() {
        if (size() <= 0) {
            throw new IllegalArgumentException("Can not find max when heap is empty.");
        }
        return data.get(indexes.get(0));
    }

    public Boolean isEmpty() {
        return indexes.isEmpty();
    }

    public E replace(E e) {
        E ret = findMax();
        data.set(indexes.get(0), e);
        shiftDown(0);
        return ret;
    }

    /**
     * @param index
     * @return 返回当前索引index节点的右子节点的索引
     */
    private int rightChild(int index) {
        return (2 * index) + 2;
    }
}
