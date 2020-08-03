public class ListNode {
    public static void main(String[] args) {
        ListNode list = new ListNode();
    }



    public ListNode deleteAtTail(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode curr = head;
        ListNode prev = curr;
        //goto last node of the list
        while(curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        return head;
    }
}
