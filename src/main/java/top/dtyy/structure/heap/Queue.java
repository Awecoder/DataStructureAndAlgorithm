package top.dtyy.structure.heap;

/**
 * 队列接口
 *
 * @author lzp
 * @version v1.0 at 2018/12/5
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
