package letCode;

import java.util.ArrayList;
import java.util.List;

public class AddTwoNumbersSolution {
	/**
	 * 
	 * You are given two non-empty linked lists representing two non-negative
	 * integers. The digits are stored in reverse order and each of their nodes
	 * contain a single digit. Add the two numbers and return it as a linked
	 * list.
	 * 
	 * You may assume the two numbers do not contain any leading zero, except
	 * the number 0 itself.
	 * 
	 * Example Input: (2 -> 4 -> 3) + (5 -> 6 -> 4) Output: 7 -> 0 -> 8
	 * 
	 * Explanation: 342 + 465 = 807.
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	/**** Runtime: 75 ms *****/
	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = carry + x + y;
			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;
			if (p != null)
				p = p.next;
			if (q != null)
				q = q.next;
		}
		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

	/**** Runtime: 77 ms *****/
	public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 == null ? l2 : l1;
		}

		int sum = 0;

		List list = new ArrayList();

		while (l1 != null && l2 != null) {
			int value = l1.val + l2.val + sum;
			sum = value >= 10 ? 1 : 0;
			value = value >= 10 ? value - 10 : value;
			list.add(value);
			l1 = l1.next;
			l2 = l2.next;
		}

		ListNode result = l1 == null ? l2 : l1;

		while (result != null) {
			int value = result.val + sum;
			sum = value >= 10 ? 1 : 0;
			value = value >= 10 ? value - 10 : value;
			list.add(value);
			result = result.next;
		}

		if (sum == 1) {
			list.add(sum);
		}

		for (int i = list.size() - 1; i > -1; i--) {
			ListNode node = new ListNode((Integer) list.get(i));
			node.next = result;
			result = node;
		}
		return result;
	}

	/**** Runtime: 76 ms *****/
	public ListNode addTwoNumbersMy(ListNode l1, ListNode l2) {
		ListNode node;

		if (l1 == null || l2 == null)
			return l1 == null ? l2 : l1;

		int sum = (l1.val + l2.val) / 10;

		node = new ListNode((l1.val + l2.val) % 10);
		ListNode node2 = node;
		l1 = l1.next;
		l2 = l2.next;

		while (l1 != null && l2 != null) {
			node2.next = new ListNode((l1.val + l2.val + sum) % 10);
			sum = (l1.val + l2.val + sum) / 10;
			node2 = node2.next;
			l1 = l1.next;
			l2 = l2.next;
		}

		if (l1 == null || l2 == null)
			node2.next = l1 == null ? (node2.next = l2) : (node2.next = l1);

		if (node2.next != null) {
			// node2.next.val += sum;
			node2 = node2.next;
			while (sum != 0) {
				int val = node2.val;
				node2.val = (val + sum) % 10;
				sum = (val + sum) / 10;
				if (node2.next == null && sum != 0) {
					node2.next = new ListNode(sum);
					sum = 0;
				}
				node2 = node2.next;
			}
		} else {
			node2.next = sum == 0 ? null : new ListNode(sum);
		}

		return node;
	}
}
