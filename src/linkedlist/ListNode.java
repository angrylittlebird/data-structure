package linkedlist;

//Definition for singly-linked list.
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    //当前链表为头结点
    public ListNode(int[] arr) {
        if(arr == null || arr.length == 0){
            throw new IllegalArgumentException("arr can not be empty");
        }

        ListNode cur = this;
        cur.val = arr[0];

        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val + "->");
            cur = cur.next;
        }
        sb.append("NULL");
        sb.append("  tail");
        return sb.toString();
    }
}