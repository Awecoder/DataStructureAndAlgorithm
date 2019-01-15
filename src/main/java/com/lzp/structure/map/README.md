
## 映射 Map
存储键值对的数据结构，根据Key，寻找Value。

映射，又称为字典。

- 非常容易采用链表或者二分搜索树实现Map。
```$xslt
class Node{
    K key;
    V value;
    Node left;
    Node right;
}
```
- Map中Key是唯一的。
### Map链表实现
### Map二分搜索树实现
### 时间复杂度分析
操作 | LinkedListMap | BSTMap | 平均 | 最差
---|---|---|---|---
新增 add | 0(n) | 0(h) | 0(log n) | 0(n)
删除 remove | 0(n) | 0(h) | 0(log n) | 0(n)
包含 contains | 0(n) | 0(h) | 0(log n) | 0(n)
查询 get | 0(n) | 0(h) | 0(log n) | 0(n)
修改 set | 0(n) | 0(h) | 0(log n) | 0(n)

【注】当二分搜索树退化成链表时，复杂度也退化为0(n)。
### 运行结果
```$xslt
Total words: 125901
Total diff words: 6530
Frequency of pride: 53
Frequency of prejudice: 11
LinkedListMap time costs : 10.418319962s
Total words: 125901
Total diff words: 6530
Frequency of pride: 53
Frequency of prejudice: 11
BSTMap time costs : 0.072187859s
``` 
## 有序映射和无序映射
有序映射中键具有顺序性，基于搜索树实现。

无序映射中键没有顺序性，基于哈希表实现。

## 集合和映射的关系
集合用来操作单值数据，映射用来操作键值对。

Map中的Key机制与Set一致，因此可以用Map来包装出Set，此时视value值为null，仅采用Map的key值。当然此时，Map的get和set方法是没有意义的。
