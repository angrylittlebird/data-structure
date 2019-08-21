package linkedlist;

public class RemoveRepeatValueSolution {
    public static void main(String[] args) {
        ListNode listNode = new ListNode(new int[]{1, 1, 2, 2,1 ,3, 7, 4, 6, 6,1,1});
        ListNode retList = solution1(listNode, 1);
        System.out.println(retList);

        ListNode retList2 = solution2(listNode, 1);
        System.out.println(retList2);
    }

    public static ListNode solution1(ListNode listNode,int val){
        System.out.println(listNode);

        ListNode head = listNode;
        while (head != null && head.val == val) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        ListNode prev = head;
        while (prev.next != null) {
            if (prev.next.val == val) {
                prev.next = prev.next.next;
            }else {
                prev = prev.next;
            }
        }

        return head;
    }


    public static ListNode solution2(ListNode listNode,int val){
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = listNode;

        ListNode prev = dummyHead;
        while (prev.next != null) {
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }else {
                prev = prev.next;
            }
        }

        return dummyHead.next;
    }
}
