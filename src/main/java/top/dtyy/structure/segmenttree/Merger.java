package top.dtyy.structure.segmenttree;

/**
 * 数据融合器
 *
 * @author lzp
 * @version v1.0 at 2019/1/3
 */
public interface Merger<E> {
    E merge(E a, E b);
}
