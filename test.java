package letCode;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] height = {1, 0, -1, 0, -2, 2};
		
		ListNode l1 = new MergeTwoListsSolution().create(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
		ListNode l2 = new MergeTwoListsSolution().create(new int[]{1, 3});
		
		ListNode[] lists = new ListNode[2];
		lists[0] = l1;
		lists[1] = l2;
		
		System.out.println(new DivideSolution().divide2(10, 5));
	}

}
