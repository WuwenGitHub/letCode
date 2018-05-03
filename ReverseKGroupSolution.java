package letCode;

import java.util.ArrayList;
import java.util.Stack;

public class ReverseKGroupSolution {

	/**
	 * 
	 * Given a linked list, reverse the nodes of a linked list k at a time and
	 * return its modified list.
	 * 
	 * k is a positive integer and is less than or equal to the length of the
	 * linked list. If the number of nodes is not a multiple of k then left-out
	 * nodes in the end should remain as it is.
	 * 
	 * You may not alter the values in the nodes, only nodes itself may be
	 * changed.
	 * 
	 * Only constant memory is allowed.
	 * 
	 * For example, Given this linked list: 1->2->3->4->5
	 * 
	 * For k = 2, you should return: 2->1->4->3->5
	 * 
	 * For k = 3, you should return: 3->2->1->4->5
	 * 
	 * 
	 * 
	 * @param head
	 * @param k
	 * @return
	 */
	//Runtime: 10 ms
	public ListNode reverseKGroup(ListNode head, int k) {
		
		ListNode node = null;
		ListNode node2 = null;
		
		ArrayList<ListNode> list = new ArrayList<ListNode>();
		int len = -1;
		
		while (head != null){
			list.add(head);
			head = head.next;
			len++;
		}
		
		for (int i = len; i >= 0; i--){
			if ((len + 1) %  k != 0 && i / k == len / k){
				node = list.get(i);
			}else{
				int j = i / k * k + k - i % k - 1;
				node = list.get(j);
			}
			
			node.next = node2;
			node2 = node;
		}
		
		return node2;
	}
	
	//Runtime: 8 ms
	public ListNode reverseKGroup2(ListNode head, int k) {
        int n = 0;
        for (ListNode i = head; i != null; n++, i = i.next);
        
        ListNode dmy = new ListNode(0);
        dmy.next = head;
        for(ListNode prev = dmy, tail = head; n >= k; n -= k) {
            for (int i = 1; i < k; i++) {
                ListNode next = tail.next.next;
                tail.next.next = prev.next;
                prev.next = tail.next;
                tail.next = next;
            }
            
            prev = tail;
            tail = tail.next;
        }
        return dmy.next;
    }
	
	//Runtime: 8 ms
	public ListNode reverseKGroup3(ListNode head, int k) {
	    ListNode curr = head;
	    int count = 0;
	    while (curr != null && count != k) { // find the k+1 node
	        curr = curr.next;
	        count++;
	    }
	    if (count == k) { // if k+1 node is found
	        curr = reverseKGroup3(curr, k); // reverse list with k+1 node as head
	        // head - head-pointer to direct part, 
	        // curr - head-pointer to reversed part;
	        while (count-- > 0) { // reverse current k-group: 
	            ListNode tmp = head.next; // tmp - next head
	            head.next = curr; // preappending "direct" head to the reversed list 
	            curr = head; // move head of reversed part to a new node
	            head = tmp; // move "direct" head to the next node in direct part
	        }
	        head = curr;
	    }
	    return head;
	}
}
