package letCode;

public class MergeTwoListsSolution {

	
	public ListNode create(int[] arr){
		ListNode head = new ListNode(-1);
		ListNode node = head;
		
		for (int i : arr){
			node.next = new ListNode(i);
			node = node.next;
		}
		
		return head.next;
	}
	
	/**
	 * 
	 * Merge two sorted linked lists and return it as a new list. The new list
	 * should be made by splicing together the nodes of the first two lists.
	 * 
	 * Example:
	 * 
	 * Input: 1->2->4, 1->3->4 Output: 1->1->2->3->4->4
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode head = new ListNode(-1);
		ListNode node = head;
		
		while (l1 != null && l2 != null){
			if (l1.val < l2.val){
				node.next = l1;
				l1 = l1.next;
			}else{
				node.next = l2;
				l2 = l2.next;
			}
			
			node = node.next;
		}
		
		if (l1 == null)
			node.next = l2;
		
		if (l2 == null)
			node.next = l1;
		
		return head.next;
	}
}
