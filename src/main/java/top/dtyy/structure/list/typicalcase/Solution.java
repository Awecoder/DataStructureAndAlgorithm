package top.dtyy.structure.list.typicalcase;

/**
 * 删除值为val的元素，返回删除后链表的头节点
 *
 * @author lzp
 * @version v1.0 at 2018/11/21
 */
public class Solution {
    /**
     * <pre>
     *     先处理头节点，在处理其他元素
     * </pre>
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements1(ListNode head, int val) {
        // 判断头节点
        // 此处一定要用while，链表头部可能有连续多个需要删除的元素
        while (head != null && head.val == val) {
            ListNode delNode = head;
            head = delNode.next;
            delNode.next = null;
        }
        if (head == null) {
            return null;
        }
        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                ListNode delNode = head;
                head = delNode.next;
                delNode.next = null;
            } else {
                prev = prev.next;
            }
        }
        return head;
    }

    /**
     * 虚拟头节点辅助解决
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements2(ListNode head, int val) {
        // 设置虚拟头节点，值任意
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        // 找出前一个元素
        ListNode prev = dummyHead;
        while (prev.next != null) {
            if (prev.next.val == val) {
                // 此处省略内存清理操作
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return dummyHead.next;
    }

    public ListNode removeElements3(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements3(head.next, val);
        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution()).removeElements3(head, 6);
        System.out.println(res);
    }
}
