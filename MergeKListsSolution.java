package letCode;

import java.awt.List;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKListsSolution {
	/**
	 * 
	 * Merge k sorted linked lists and return it as one sorted list. Analyze and
	 * describe its complexity.
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists(ListNode[] lists) {
		ListNode head = new ListNode(-1);
		ListNode node = head;

		int min = 0;

		while (true) {

			while (min < lists.length && lists[min] == null)
				min++;

			for (int i = min + 1; i < lists.length; i++) {
				if (lists[i] != null && lists[i].val < lists[min].val)
					min = i;
			}

			if (min == lists.length)
				break;

			node.next = lists[min];
			lists[min] = lists[min].next;
			node = node.next;
			min = 0;
		}

		return head.next;
	}

	public ListNode mergeKLists2(ListNode[] lists) {
		if (lists == null || lists.length == 0)
			return null;

		PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
			@Override
			public int compare(ListNode o1, ListNode o2) {
				if (o1.val < o2.val)
					return -1;
				else if (o1.val == o2.val)
					return 0;
				else
					return 1;
			}
		});

		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;

		for (ListNode node : lists)
			if (node != null)
				queue.add(node);

		while (!queue.isEmpty()) {
			tail.next = queue.poll();
			tail = tail.next;

			if (tail.next != null)
				queue.add(tail.next);
		}
		return dummy.next;
	}

	// 归并排序算法的时间复杂度是o(nlogn)
	public ListNode mergeKLists3(ListNode[] lists) {
		if (lists.length == 0)
			return null;
		return merge(0, lists.length - 1, lists);
	}

	public ListNode merge(int i, int j, ListNode[] lists) {

		if (j < i)
			return null;

		if (i == j)
			return lists[i];

		int mid = i + (j - i) / 2;

		ListNode l = merge(i, mid, lists);
		ListNode r = merge(mid + 1, j, lists);

		ListNode dummy = new ListNode(0);
		ListNode runner = dummy;

		while (l != null && r != null) {
			if (l.val > r.val) {
				runner.next = r;
				r = r.next;
				runner = runner.next;
			} else {
				runner.next = l;
				l = l.next;
				runner = runner.next;
			}
		}

		if (l == null && r == null)
			return dummy.next;

		if (l == null)
			runner.next = r;
		else
			runner.next = l;

		return dummy.next;
	}
}
