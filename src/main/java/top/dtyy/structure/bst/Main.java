package top.dtyy.structure.bst;

/**
 * @author lzp
 * @version v1.0 at 2018/10/28
 */
public class Main {
    public static void main(String[] args) {
        int[] nums = {5, 3, 6, 8, 4, 2};
        BST<Integer> bst = new BST<>();
        for (int num : nums) {
            bst.add(num);
        }
        /////////////////
        //      5      //
        //    /   \    //
        //   3    6    //
        //  / \    \   //
        // 2  4     8  //
        /////////////////
        System.out.println("测试前序遍历和contains");
        bst.preOrder();
        System.out.println(bst.contains(4));
        System.out.println();

        System.out.println("测试重写toString");
        System.out.println(bst);
        System.out.println();

        System.out.println("测试中序遍历");
        bst.inOrder();
        System.out.println();
        System.out.println("测试后序遍历");
        bst.postOrder();
        System.out.println();

        System.out.println("测试非递归前序遍历");
        bst.preOrderNR();
        System.out.println();

        System.out.println("测试层序遍历");
        bst.levelOrder();
        System.out.println();

        System.out.println("测试最小和最大值搜索");
        System.out.println(bst.minimum());
        System.out.println(bst.maximum());
        System.out.println();

        System.out.println("测试最小和最大值删除");
        BST bst1 = bst;
        bst1.removeMin();
        System.out.println(bst1);
        System.out.println();
        bst1.removeMax();
        System.out.println(bst1);
        System.out.println();

        System.out.println("测试节点删除");
        BST bst2 = bst;
        bst2.remove(3);
        System.out.println(bst);
    }
}
