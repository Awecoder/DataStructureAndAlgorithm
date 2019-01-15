package top.dtyy.structure.queue;

/**
 * 自定义循环队列
 * <pre>
 *     主要解决普通数组队列元素出队时间复杂度是0(n).
 *     添加两个指针分别指向队列头和尾，此时出队操作不需要移动所有元素，
 *     而只需维护指针指向位置即可。
 *     front == tail时，队列为空。
 *     front指向队首元素，tail指向尾部元素后面一个位置。
 * </pre>
 *
 * @author lzp
 * @version v1.0 at 2018/10/27
 */
public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front;
    private int tail;
    private int size;

    public LoopQueue(int capacity) {
        // 初始化容量加1的数组
        data = (E[]) new Object[capacity];
        front = 0;
        // tail初始化为0
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    /**
     * 队列容量
     *
     * @return
     */
    public int getCapacity() {
        // 循环队列的实现中有意识地浪费了一个空间
        return data.length - 1;
    }

    /**
     * 获取队列元素个数
     *
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * 队列是否为空
     *
     * @return
     */
    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    /**
     * 元素入队
     *
     * @param e
     */
    @Override
    public void enqueue(E e) {
        if ((tail + 1) % data.length == front) {
            // 队列满，扩容一倍
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[]) new Object[newCapacity + 1];
        // 循环队列遍历方法1
        for (int i = 0; i < size; i++) {
            // 新的数组将原队列front位置元素放到新队列0位置
            newData[i] = data[(i + front) / data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    /**
     * 元素出队
     *
     * @return
     */
    @Override
    public E dequeue() {
        if (front == tail) {
            throw new IllegalArgumentException("队列为空");
        }
        E res = data[front];
        front = (front + 1) % data.length;
        size--;
        // 缩容处理
        if (size == getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return res;
    }

    /**
     * 获取队首元素
     *
     * @return
     */
    @Override
    public E getFront() {
        if (front == tail) {
            throw new IllegalArgumentException("队列为空");
        }
        return data[front];
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("LoopQueue: size = %d, capacity = %d\n", getSize(), getCapacity()));
        res.append("front [");
        // 循环队列遍历方法2
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            res.append(i);
            if ((i + 1) % data.length != tail) {
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>();
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
        }
        for (int i = 0; i < 10; i++){
            queue.dequeue();
            System.out.println(queue);
        }
    }
}
