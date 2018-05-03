package letCode;

public class RemoveNthFromEndSolution {

	/**
	 * 
	 * Given a linked list, remove the nth node from the end of list and return
	 * its head.
	 * 
	 * For example:Given linked list: 1->2->3->4->5, and n = 2.
	 * 
	 * After removing the second node from the end, the linked list becomes
	 * 1->2->3->5.
	 * 
	 * Note: Given n will always be valid. Try to do this in one pass.
	 * 
	 * @param head
	 * @param n
	 * @return
	 */
	public ListNode removeNthFromEnd(ListNode head, int n) {
		int len = 0;
		ListNode node = head;

		while (node != null) {
			len++;
			node = node.next;
		}

		// node = new ListNode(-1);
		// ListNode ret = node;
		// node.next = head;
		node = new ListNode(-1);
		node.next = head;
		head = node;
		int step = len - n;
		while (step > 0) {
			step--;
			node = node.next;
		}

		node.next = node.next.next;

		return head.next;
	}
}
