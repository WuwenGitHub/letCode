package letCode;

public class MaxAreaSolution {

	/**
	 * 
	 * Given n non-negative integers a1, a2, ..., an, where each represents a
	 * point at coordinate (i, ai). n vertical lines are drawn such that the two
	 * endpoints of line i is at (i, ai) and (i, 0). Find two lines, which
	 * together with x-axis forms a container, such that the container contains
	 * the most water.
	 * 
	 * Note: You may not slant the container and n is at least 2.
	 * 
	 * @param height
	 * @return
	 */

	// Complexity Analysis
	// Time complexity : O(n^2). Calculating area for all n(n−1)/2 height pairs.
	// Space complexity : O(1). Constant extra space is used
	public int maxArea(int[] height) {

		int area = 0;

		for (int i = 0; i < height.length; i++) {
			for (int j = i + 1; j < height.length; j++) {
				area = Math.max(area, Math.min(height[i], height[j]) * (j - i));
			}
		}

		return area;
	}

	// Two Pointer Approach
	// Complexity Analysis
	// Time complexity : O(n). Single pass.
	// Space complexity : O(1)O(1). Constant space is used.
	public int maxArea2(int[] height) {
		int area = 0, i = 0, j = height.length - 1;

		while (i < j) {
			area = Math.max(area, Math.min(height[i], height[j]) * (j - i));

			if (height[i] > height[j])
				j--;
			else
				i++;
		}

		return area;
	}
}
