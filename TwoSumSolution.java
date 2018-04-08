package letCode;

import java.util.HashMap;

public class TwoSumSolution {

	/**
	 * 
	 * Given an array of integers, return indices of the two numbers such that
	 * they add up to a specific target. You may assume that each input would
	 * have exactly one solution, and you may not use the same element twice.
	 * 
	 * Example: Given nums = [2, 7, 11, 15], target = 9, Because nums[0] +
	 * nums[1] = 2 + 7 = 9, return [0, 1].
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
		HashMap<Integer, Integer> tracker = new HashMap<Integer, Integer>();
		int len = nums.length;

		for (int i = 0; i < len; i++) {
			if (tracker.containsKey(nums[i])) {
				int left = tracker.get(nums[i]);
				return new int[] { left, i };
			} else {
				tracker.put(target - nums[i], i);
			}
		}

		return new int[2];
	}
}
