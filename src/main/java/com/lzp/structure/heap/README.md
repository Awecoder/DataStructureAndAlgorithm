# 堆和优先队列
## 优先队列
优先队列：出队顺序与入队顺序无关，和优先级有关。

动态地选择优先任务，并且任务并不是固定的。例如，在游戏中，英雄优先选择攻击血量少的小兵。

```java
public interface Queue<E> {
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
```
优先队列也是队列，队列头部元素出队和获取的都是优先级最高的元素。\

### JDK中对应PriorityQueue
JDK中优先队列默认采用的是最小堆。接口如下：
```
void add(E e);
E remove(); 
E peek(); 
```
## 二叉堆
### 底层实现
操作 | 入队 | 出队
---|---|---
普通线性结构 | 0(1) | 0(n) 
顺序线性结构 | 0(n) | 0(1) 
堆 | 0(logn) | 0(logn)

链表和队列属于普通线性结构 \
【注】由于二叉堆是完全二叉树，故最差时间复杂度也算是0(logn).
### 堆的性质
1. 完全二叉树，但不一定是满二叉树（元素层序排列成树的形状，右下脚可能有缺失）；
2. （最大堆）堆中某节点的值总是不大于其父节点的值。第2层的元素小于第3层元素值。
3. 父节点、左右孩子节点计算规律【数组实现】

```
若index = 0位置开始放置元素：
parent(i) = (i - 1) / 2;
left child(i) = 2 * i + 1;
right child(i) = 2 * i + 2;

若从index=1位置开始放置元素：
parent(i) = i / 2;
left child(i) = 2 * i;
right child(i) = 2 * i + 1;
```
### 自定义堆复杂度
1. add 和 extractMax时间复杂度为0(logn)
2. 将数组整理成堆 \
将n个元素逐个插入到空堆中，时间复杂度0(nlogn) \
heapify操作，时间复杂度0(n)
```
100,0000次操作：
Test mapheap completed.
Without heapify: 0.63640331 s

Test mapheap completed.
With heapify: 0.726109554 s

1000,0000次操作：
Test mapheap completed.
Without heapify: 14.219531233 s

Test mapheap completed.
With heapify: 11.518742037 s
```
## LeetCode 347
外比较器Comparator的使用，可以在类外部定义对象的比较规则，覆盖默认的比较。

## 扩展
n叉堆 \
索引堆 -- 可获取或修改中间元素 \