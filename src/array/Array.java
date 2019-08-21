package array;

public class Array<E> {
    /**
     * data数组实际有效个数
     */
    private int size;
    private E[] data;

    /**
     * @param capacity 初始化数组长度
     */
    public Array(int capacity) {
        this.data = (E[]) new Object[capacity];
    }

    /**
     * 默认数组长度为10
     */
    public Array() {
        this(10);
    }

    /**
     * @return 返回数组中元素实际有效个数
     */
    public int getSize() {
        return size;
    }

    /**
     * @return 返回数组的容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * @return 数组中实际有效个数是否为 0 个
     */
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * @param index 在数组中的index位置插入元素
     * @param e     被插入元素
     */
    public void add(int index, E e) {
//        if (size == data.length)
//            throw new IllegalArgumentException("add failed.data is already full");

        if (index < 0 || index > size)
            throw new IllegalArgumentException("add failed.index must ge 0 and le size");

        if (size == data.length)
            resize(2 * data.length);

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;
        size++;
    }

    /**
     * @param e 数组中的第一个位置插入元素e
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * @param e 数组有效元素末尾插入元素e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * @param index 获取索引为index的元素
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed.Required index >= 0 and index < size");
        return data[index];
    }

    /**
     *
     * @return 返回data数组中的第一个元素
     */
    public E getFirst(){
        return get(0);
    }

    /**
     *
     * @return 返回data数组中的最后一个元素
     */
    public E getLast(){
        return get(size - 1);
    }

    /**
     * @param index 设置索引为index的元素
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("get failed.Required index >= 0 and index < size");
        data[index] = e;
    }

    /**
     * @param e 元素e是否在数组中存在
     * @return
     */
    public boolean isContains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param e 获取第一个在数组中为e的索引
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * @param index 移除索引为index的元素
     * @return 返回被移除的元素
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("remove failed.index is illegal");
        E e = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        //data[size] is loitering object
        data[size] = null;

//        if (size == data.length / 2)
        if(size == data.length /4 && data.length / 2 != 0)  //lazy resize to reduce complexity
            resize(data.length / 2);
        return e;
    }

    /**
     * @return 移除第一个元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * @return 移除最有一个有效元素
     */
    public E removeLast() {
        return remove(size - 1);
    }

    /**
     * @param e 移除数组中的首个e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("Array's size is %d,capacity is %d \n", size, data.length));
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(',');
            }
        }
        sb.append(']');
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
