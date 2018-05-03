package letCode;

public class SwapPairsSolution {
	
	/**
	 * 
	 * Given a linked list, swap every two adjacent nodes and return its head.
	 * 
	 * For example, Given 1->2->3->4, you should return the list as 2->1->4->3.
	 * 
	 * Your algorithm should use only constant space. You may not modify the
	 * values in the list, only nodes itself can be changed.
	 * 
	 * @param head
	 * @return
	 */
	public ListNode swapPairs(ListNode head) {
		
		ListNode node = new ListNode(-1);
		node.next = head;
		head = node;
		
		head.next = swap(head.next);
		
		return node.next;
	}
	
	private ListNode swap(ListNode head){
		
		if (head == null || head.next == null)
			return head;
		
		ListNode node = head.next;
		head.next = swap(head.next.next);
		node.next = head;
		
		return node;
	}
	
	/*
    a -> b -> c
        b -> a -> c
    a
    a -> b -> c -> d
        b -> a -> d -> c
    */
	public ListNode swapPairs2(ListNode head) {
        
        //make the first swap, keep track of new head;
        
        if(head == null) {
            return null;
        }
        
        if(head.next == null) {
            return head;
        }
        
        ListNode startOfPair = head;
        ListNode endOfPair = startOfPair.next;
        ListNode nextPair = endOfPair.next;
        ListNode prev = null;
        
        startOfPair.next = nextPair;
        endOfPair.next = startOfPair;
        ListNode newHead = endOfPair;
        
        prev = startOfPair;
        startOfPair = nextPair;
        
        while(startOfPair != null && startOfPair.next != null) {
            
            endOfPair = startOfPair.next;
            
            //save start of the next pair as temp
            nextPair = endOfPair.next;
            
            //swap
            startOfPair.next = nextPair;
            endOfPair.next = startOfPair;
            
            
            prev.next = endOfPair;
            prev = startOfPair;
            //move forward
            startOfPair = nextPair;
        }
        
        return newHead;
    }
    
}
