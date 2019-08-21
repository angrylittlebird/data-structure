package recursive;

public class RemoveRepeatVal {

    /**
     * 该方法的“宏观”语义：如果 head链表的头结点 和 val 值一样，则移除头结点
     * @param head
     * @param val
     * @return 移除 head 链表中 所有 和 val 值一样的 结点
     */
    public ListNode removeElements(ListNode head,Integer val){
        if (head == null) {
            return null;
        }

        //"宏观" 语义体现的地方：不关系具体的实现，把头结点不包含val的ListNode"接到" head 后面
        head.next = removeElements(head.next, val);

        //真正实现消除头结点为val的操作
        return  head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        ListNode listNode = new RemoveRepeatVal().removeElements(new ListNode(new int[]{1, 2, 3, 4, 1, 2, 1, 1}), 1);
        System.out.println(listNode);
    }
}
