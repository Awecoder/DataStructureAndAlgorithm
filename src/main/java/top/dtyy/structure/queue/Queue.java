package top.dtyy.structure.queue;

/**
 * 自定义队列接口
 *
 * @author lzp
 * @version v1.0 at 2018/10/27
 */
public interface Queue<E> {
    /**
     * 获取队列元素个数
     *
     * @return
     */
    int getSize();

    /**
     * 队列是否为空
     *
     * @return
     */
    boolean isEmpty();

    /**
     * 元素入队
     *
     * @param e
     */
    void enqueue(E e);

    /**
     * 元素出队
     *
     * @return
     */
    E dequeue();

    /**
     * 获取队首元素
     *
     * @return
     */
    E getFront();
}
