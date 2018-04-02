package letCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class Solution {

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

	/**
	 * 
	 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
	 * Find the median of the two sorted arrays. The overall run time complexity
	 * should be O(log (m+n)).
	 * 
	 * Example 1: A[] = [1, 3] B[] = [2]
	 * 
	 * The median is 2.0
	 * 
	 * Example 2: A[] = [1, 2] B[] = [3, 4]
	 * 
	 * The median = (2+3/2) = 2.5
	 *
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public double findMedianSortedArrays(int[] A, int[] B) {
		int m = A.length;
		int n = B.length;
		if (m > n) { // to ensure m<=n
			int[] temp = A;
			A = B;
			B = temp;
			int tmp = m;
			m = n;
			n = tmp;
		}
		int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
		while (iMin <= iMax) {
			int i = (iMin + iMax) / 2;
			int j = halfLen - i;
			if (i < iMax && B[j - 1] > A[i]) {
				iMin = iMin + 1; // i is too small
			} else if (i > iMin && A[i - 1] > B[j]) {
				iMax = iMax - 1; // i is too big
			} else { // i is perfect
				int maxLeft = 0;
				if (i == 0) {
					maxLeft = B[j - 1];
				} else if (j == 0) {
					maxLeft = A[i - 1];
				} else {
					maxLeft = Math.max(A[i - 1], B[j - 1]);
				}
				if ((m + n) % 2 == 1) {
					return maxLeft;
				}

				int minRight = 0;
				if (i == m) {
					minRight = B[j];
				} else if (j == n) {
					minRight = A[i];
				} else {
					minRight = Math.min(B[j], A[i]);
				}

				return (maxLeft + minRight) / 2.0;
			}
		}
		return 0.0;
	}

	/**
	 * 
	 * Given a string, find the length of the longest substring without
	 * repeating characters.
	 * 
	 * Example:
	 * 
	 * Given "abcabcbb", the answer is "abc", which the length is 3.
	 * 
	 * Given "bbbbb", the answer is "b", with the length of 1.
	 * 
	 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the
	 * answer must be a substring, "pwke" is a subsequence and not a substring.
	 * 
	 * 
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring1(String s) {
		int i = 0, j = 0, max = 0;
		Set<Character> set = new HashSet<>();

		while (j < s.length()) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				max = Math.max(max, set.size());
			} else {
				set.remove(s.charAt(i++));
			}
		}

		return max;
	}

	public int lengthOfLongestSubstring(String s) {
		if (s.length() == 0)
			return 0;
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		int max = 0;
		for (int i = 0, j = 0; i < s.length(); ++i) {
			if (map.containsKey(s.charAt(i))) {
				j = Math.max(j, map.get(s.charAt(i)) + 1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}

	/**
	 * 
	 * Given a string s,find the longest palindromic substring(回文) in s. You may
	 * assume that the maximum length of s is 1000.
	 * 
	 * Example1:
	 * 
	 * Input: "babad" Output: "bab" Note: "aba" is also a valid answer.
	 * 
	 * Example2:
	 * 
	 * Input: "cbbd" Output: "bb"
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindromeMy(String s) {

		// Runtime: 496ms
		String str = "", subStr = "";

		for (int i = 0; i < s.length(); i++) {
			int index = s.lastIndexOf(s.charAt(i));

			str = s.substring(i, index + 1);

			// 判断是否为回文
			while (str.length() > 3) {
				int j = 1;
				for (; j < str.length() / 2; j++) {

					if (str.charAt(j) != str.charAt(str.length() - j - 1)) {
						str = str.substring(0, str.length() - 1);
						index = str.lastIndexOf(s.charAt(i));

						str = str.substring(0, index + 1);
						j = 0;
					}
				}
				break;
			}

			if (str.length() > subStr.length())
				subStr = str;
		}

		return subStr;
	}

	// 17ms
	// lo 回文开始位置 ;maxLen 回文长度
	private int lo, maxLen;

	public String longestPalindrome(String s) {
		int len = s.length();

		if (len < 2)
			return s;

		for (int i = 0; i < len - 1; i++) {
			extendPalindrome(s, i, i);// assume odd length, try to extend
										// Palindrome as possible.
			extendPalindrome(s, i, i + 1);// assume even length.
		}

		return s.substring(lo, lo + maxLen);

	}

	private void extendPalindrome(String s, int j, int k) {
		while (j > 0 && k < s.length() && s.charAt(j) == s.charAt(k)) {
			j--;
			k++;
		}

		if (maxLen < k - j - 1) {
			lo = j + 1;
			maxLen = k - j - 1;
		}
	}

	/**
	 * 
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given
	 * number of rows like this: (you may want to display this pattern in a
	 * fixed font for better legibility)
	 * 
	 * P A H N A P L S I I G Y I R
	 * 
	 * And read line by line: "PAHNAPLSIIGYIR"
	 * 
	 * Write the code that will take a string and make this conversion given a
	 * number of rows: String convert(String text, int nRows);
	 * 
	 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */

	// 52ms
	public String convert(String s, int numRows) {

		char[] c = s.toCharArray();
		int len = c.length;
		StringBuffer[] sb = new StringBuffer[numRows];
		for (int i = 0; i < sb.length; i++)
			sb[i] = new StringBuffer();

		int i = 0;
		while (i < len) {
			for (int idx = 0; idx < numRows && i < len; idx++) // vertically
																// down
				sb[idx].append(c[i++]);
			for (int idx = numRows - 2; idx >= 1 && i < len; idx--) // obliquely
																	// up
				sb[idx].append(c[i++]);
		}
		for (int idx = 1; idx < sb.length; idx++)
			sb[0].append(sb[idx]);

		return sb[0].toString();
	}

	// 40ms
	public String convert2(String s, int numRows) {
		if (s == null || numRows <= 1 || s.length() <= numRows)
			return s;

		int len = s.length();
		char[] source = s.toCharArray();
		char[] dest = new char[len];
		int step = 2 * numRows - 2;
		int k = 0;
		for (int i = 0; i < len; i += step) {
			dest[k++] = source[i];
		}
		for (int i = 1; i < numRows - 1; ++i) {
			int j = i;
			int otherJ = j + step - 2 * i;
			while (j < len && otherJ < len) {
				dest[k++] = source[j];
				dest[k++] = source[otherJ];
				j += step;
				otherJ += step;
			}
			if (j < len)
				dest[k++] = source[j];
		}
		for (int i = numRows - 1; i < len; i += step) {
			dest[k++] = source[i];
		}
		return String.valueOf(dest);
	}

	/**
	 * 
	 * Given a 32-bit signed integer, reverse digits of an integer.
	 * 
	 * Example1: Input: 123 Output: 321
	 * 
	 * Example2: Input: -123 Output: -321
	 * 
	 * Example3: Input: 120 Output: 21
	 * 
	 * Note: Assume we are dealing with an environment which could only hold
	 * integers within the 32-bit signed integer range. For the purpose of this
	 * problem, assume that your function returns 0 when the reversed integer
	 * overflows.
	 * 
	 * @param x
	 * @return
	 */
	public int reverse(int x) {

		int revInt = 0;

		while (x != 0) {
			int num = x % 10;
			int res = revInt * 10 + num;
			if (res / 10 != revInt) {
				return 0;
			}
			revInt = res;
			x = x / 10;
		}

		return revInt;
	}

	/**
	 * 
	 * Requirements for atoi:
	 * 
	 * The function first discards as many whitespace characters as necessary
	 * until the first non-whitespace character is found. Then, starting from
	 * this character, takes an optional initial plus or minus sign followed by
	 * as many numerical digits as possible, and interprets them as a numerical
	 * value.
	 * 
	 * The string can contain additional characters after those that form the
	 * integral number, which are ignored and have no effect on the behavior of
	 * this function.
	 * 
	 * If the first sequence of non-whitespace characters in str is not a valid
	 * integral number, or if no such sequence exists because either str is
	 * empty or it contains only whitespace characters, no conversion is
	 * performed.
	 * 
	 * If no valid conversion could be performed, a zero value is returned. If
	 * the correct value is out of the range of representable values, INT_MAX
	 * (2147483647) or INT_MIN (-2147483648) is returned.
	 * 
	 * @param str
	 * @return
	 */
	public int myAtoi(String str) {

		// 去除前后空格
		str = str.trim();

		if (str.equals(""))
			return 0;

		String s = "";
		int symbol = -1;

		for (int i = 0, j = 0; i < 12 && i < str.length(); i++) {

			if (i == 0) {
				char ch = str.charAt(i);

				if (str.charAt(i) > '9' || str.charAt(i) < '0')
					if (str.charAt(i) != '+' && str.charAt(i) != '-')
						return 0;
			}

			if (i > 1 && s.equals(""))
				return 0;

			if (str.charAt(i) <= '9' && str.charAt(i) >= '0') {
				s += str.charAt(i);
				if (symbol < 0)
					symbol = i;
				continue;
			}

			if (i > j)
				break;
			j++;
		}

		if (s.equals(""))
			return 0;

		if (symbol > 0 && str.charAt(symbol - 1) == '-') {
			s = "-" + s;
			if (s.length() > 11 || Long.parseLong(s) <= Integer.MIN_VALUE)
				return Integer.MIN_VALUE;
			else
				return Integer.parseInt(s);
		}

		if (s.length() > 10 || Long.parseLong(s) >= Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}

		return Integer.parseInt(s);
	}

	public int myAtoi2(String str) {

		if (str.isEmpty())
			return 0;

		str = str.trim();

		int sign = 1, base = 0, i = 0;

		if (str.charAt(i) == '-' || str.charAt(i) == '+')
			sign = str.charAt(i++) == '-' ? -1 : 1;

		while (i < str.length() && str.charAt(i) >= '0' && str.charAt(i) <= '9') {
			if (base > Integer.MAX_VALUE / 10 || (base == Integer.MAX_VALUE / 10 && str.charAt(i) > '7')) {
				return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
			}
			base = base * 10 + Integer.parseInt(str.charAt(i++) + "");
		}

		return sign * base;
	}

	/**
	 * 
	 * Determine whether an integer is a palindrome. Do this without extra
	 * space.
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {

		if (x < 0)
			return false;

		int y = 0, z = x;

		while (z != 0) {
			y = y * 10 + z % 10;
			z /= 10;
		}

		return y == x;
	}

	public boolean isPalindrome2(int x) {
		// if(x==Integer.MIN_VALUE) return false;
		if (x < 0)
			return false; // isPalindrome(-x);
		if (x < 10)
			return true;

		int tens = 1;
		int tmp = x;
		while (tmp / 10 > 0) {
			tens *= 10;
			tmp = tmp / 10;
		}

		while (tens >= 10) {
			if (x / tens != x % 10)
				return false;
			x = x % tens / 10;
			tens /= 100;
		}
		return true;
	}

	public boolean isPalindrome3(int x) {
		if (x < 0)
			return false;
		if (x < 10)
			return true;
		if (x % 10 == 0)
			return false;
		int rev = 0;

		while (rev < x) {

			rev = rev * 10 + x % 10;
			if (rev == x)
				return true;
			x = x / 10;
		}

		if (x == rev)
			return true;
		return false;

	}

	/**
	 * 
	 * Implement regular expression matching with support for "." and "*".
	 * 
	 * "." Matches any single character. "*" Matches zero or more of the
	 * preceding element.
	 * 
	 * The matching should cover the entire input string (not partial).
	 * 
	 * Some examples: isMatch("aa","a") → false isMatch("aa","aa") → true
	 * isMatch("aaa","aa") → false isMatch("aaa","aaaa") → false
	 * isMatch("aaa","a*a") → true isMatch("aa", "a*") → true isMatch("aa",
	 * ".*") → true isMatch("ab", ".*") → true isMatch("aab", "c*a*b") → true
	 * isMatch("ab", ".*c") → false
	 * 
	 * c*-->c zero or more of the preceding element
	 * 
	 * @param s
	 * @param p
	 * @return
	 */

	public boolean isMatch(String text, String pattern) {
		if (pattern.isEmpty())
			return text.isEmpty();

		// pattern非空
		boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));

		if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
			boolean bool = (isMatch(text, pattern.substring(2))
					|| (first_match && isMatch(text.substring(1), pattern)));
			return bool;
		} else {
			boolean bool = first_match && isMatch(text.substring(1), pattern.substring(1));
			return bool;
		}
	}

	public boolean isMatch2(String text, String pattern) {
		boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
		dp[text.length()][pattern.length()] = true;

		for (int i = text.length(); i >= 0; i--) {
			for (int j = pattern.length() - 1; j >= 0; j--) {
				boolean first_match = (i < text.length()
						&& (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));
				if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
					dp[i][j] = dp[i][j + 2] || first_match && dp[i + 1][j];
				} else {
					dp[i][j] = first_match && dp[i + 1][j + 1];
				}
			}
		}
		return dp[0][0];
	}

	public boolean isMatch0(String s, String p) {
		if (p.isEmpty())
			return s.isEmpty();

		if (p.equals(".*"))
			return true;

		int i = s.length() - 1, j = p.length() - 1;
		char[] chS = s.toCharArray();
		char[] chP = p.toCharArray();

		while (i >= 0 && j >= 0) {
			if (chS[i] == chP[j]) {
				i--;
				j--;
			} else {
				if (chP[j] == '*') {
					if (chS[i] == chP[j - 1])
						i--;
					else {
						j -= 2;
					}
				} else {
					break;
				}
			}
		}

		while (j > 0) {
			if (chP[j] == '*') {
				j -= 2;
				if (j > -1 && !s.isEmpty() && chP[j] == chS[i + 1]) {
					j--;
					continue;
				}
			} else {
				break;
			}
		}

		return i == j;
	}

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

	/**
	 * 
	 * Given an integer, convert it to a roman numeral. Input is guaranteed to
	 * be within the range from 1 to 3999.
	 * 
	 * 1 5 10 50 100 500 1000 I V X L C D M
	 * 
	 * example: 1776 as MDCCLXXVI 1954 as MCMLIV 1990 as MCMXC 2014 as MMXIV
	 * 
	 * @param num
	 * @return
	 */
	public String intToRoman(int num) {
		String[] dict = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] vals = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < vals.length; i++) {
			if (num >= vals[i]) {
				int count = num / vals[i];
				num = num % vals[i];
				for (int j = 0; j < count; j++) {
					res.append(dict[i]);
				}
			}
		}
		return res.toString();
	}

	/**
	 * 
	 * Given a roman numeral, convert it to an integer. Input is guaranteed to
	 * be within the range from 1 to 3999.
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		String[] dict = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		int[] vals = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

		int num = 0;

		for (int i = 0; i < dict.length && !s.equals(""); i++) {
			while (s.indexOf(dict[i]) == 0) {
				int inx = s.indexOf(dict[i]);
				s = s.substring(inx + dict[i].length());
				num = num + vals[i];
			}
		}

		return num;
	}

	public int romanToInt2(String s) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		map.put('I', 1);
		map.put('V', 5);
		map.put('X', 10);
		map.put('L', 50);
		map.put('C', 100);
		map.put('D', 500);
		map.put('M', 1000);

		int number = 0;
		char prev = '#';
		for (int i = s.length() - 1; i >= 0; i--) {
			char ch = s.charAt(i);
			int value = map.get(ch);
			if (value < number && ch != prev) {
				number = number - value;
			} else {
				number = number + value;
			}
			prev = ch;
		}
		return number;

	}

	/**
	 * 
	 * Write a function to find the longest common prefix string amongst an
	 * array of strings.
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {

		if (strs.length == 0)
			return "";

		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++)
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty())
					return "";
			}
		return prefix;
	}

	/**
	 * 
	 * Given an array S of n integers, are there elements a, b, c in S such that
	 * a + b + c = 0? Find all unique triplets in the array which gives the sum
	 * of zero.
	 * 
	 * Note: The solution set must not contain duplicate triplets.
	 * 
	 * For example, given array S = [-1, 0, 1, 2, -1, -4], A solution set is: [
	 * [-1, 0, 1], [-1, -1, 2] ]
	 * 
	 * @param nums
	 * @return
	 */
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		Arrays.sort(nums);
		for (int i = 0; i + 2 < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) { // skip same result
				continue;
			}
			int j = i + 1, k = nums.length - 1;
			int target = -nums[i];
			while (j < k) {
				if (nums[j] + nums[k] == target) {
					res.add(Arrays.asList(nums[i], nums[j], nums[k]));
					j++;
					k--;
					while (j < k && nums[j] == nums[j - 1])
						j++; // skip same result
					while (j < k && nums[k] == nums[k + 1])
						k--; // skip same result
				} else if (nums[j] + nums[k] > target) {
					k--;
				} else {
					j++;
				}
			}
		}
		return res;
	}

	/**
	 * 
	 * Given an array S of n integers, find three integers in S such that the
	 * sum is closest to a given number, target. Return the sum of the three
	 * integers. You may assume that each input would have exactly one solution.
	 * 
	 * For example, given array S = {-1 2 1 -4}, and target = 1. The sum that is
	 * closest to the target is 2. (-1 + 2 + 1 = 2).
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int sum = nums[0] + nums[1] + nums[nums.length - 1];
		int closestSum = sum;

		for (int i = 0; i < nums.length - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1])
				continue;

			int left = i + 1, right = nums.length - 1;
			while (left < right) {
				sum = nums[left] + nums[right] + nums[i];
				if (sum < target) {
					// move closer to target sum.
					while (left < right && nums[left] == nums[left + 1]) {
						left++;
					}
					left++;
				} else if (sum > target) {
					// move closer to target sum.
					while (left < right && nums[right] == nums[right - 1]) {
						right--;
					}
					right--;
				} else {
					return sum;
				}
				// update the closest sum if needed.
				if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
					closestSum = sum;
				}
			}
		}
		return closestSum;
	}

	/**
	 * 
	 * Given a digit string, return all possible letter combinations that the
	 * number could represent.
	 * 
	 * A mapping of digit to letters (just like on the telephone buttons) is
	 * given below.
	 * 
	 * 2 -- abc 3 -- def 4 -- ghi 5 -- jkl 6 -- mno 7 -- pqrs 8 -- tuv 9 -- wxyz
	 * 
	 * Input:Digit string "23" Output: ["ad", "ae", "af", "bd", "be", "bf",
	 * "cd", "ce", "cf"].
	 * 
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		String[][] letter = { {}, {}, { "a", "b", "c" }, { "d", "e", "f" }, { "g", "h", "i" }, { "j", "k", "l" },
				{ "m", "n", "o" }, { "p", "q", "r", "s" }, { "t", "u", "v" }, { "w", "x", "y", "z" } };

		List<String> list = new ArrayList<String>();
		List<String> list2 = null;

		char[] ch = digits.toCharArray();

		for (int i = 0; i < ch.length; i++) {
			String[] str = letter[ch[i] - 48];
			if (i == 0) {
				for (String s : str) {
					list.add(s);
				}
				continue;
			}
			list2 = new ArrayList<String>();
			while (!list.isEmpty()) {
				String top = list.remove(0);
				for (String s : str) {
					list2.add(top + s);
				}
			}

			list = list2;
		}

		return list;
	}

	/**
	 * 
	 * Given an array S of n integers, are there elements a, b, c, and d in S
	 * such that a + b + c + d = target? Find all unique quadruplets in the
	 * array which gives the sum of target.
	 * 
	 * Note: The solution set must not contain duplicate quadruplets.
	 * 
	 * 
	 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
	 * 
	 * A solution set is: [ [-1, 0, 0, 1], [-2, -1, 1, 2], [-2, 0, 0, 2] ]
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public List<List<Integer>> fourSum(int[] nums, int target) {
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		int len = nums.length;
		if (nums == null || len < 4)
			return res;

		Arrays.sort(nums);

		int max = nums[len - 1];
		if (4 * nums[0] > target || 4 * max < target)
			return res;

		int i, z;
		for (i = 0; i < len; i++) {
			z = nums[i];
			if (i > 0 && z == nums[i - 1])// avoid duplicate
				continue;
			if (z + 3 * max < target) // z is too small
				continue;
			if (4 * z > target) // z is too large
				break;
			if (4 * z == target) { // z is the boundary
				if (i + 3 < len && nums[i + 3] == z)
					res.add(Arrays.asList(z, z, z, z));
				break;
			}

			threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
		}

		return res;
	}

	/*
	 * Find all possible distinguished three numbers adding up to the target
	 * in sorted array nums[] between indices low and high. If there are,
	 * add all of them into the ArrayList fourSumList, using
	 * fourSumList.add(Arrays.asList(z1, the three numbers))
	 */
	public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
			int z1) {
		if (low + 1 >= high)
			return;

		int max = nums[high];
		if (3 * nums[low] > target || 3 * max < target)
			return;

		int i, z;
		for (i = low; i < high - 1; i++) {
			z = nums[i];
			if (i > low && z == nums[i - 1]) // avoid duplicate
				continue;
			if (z + 2 * max < target) // z is too small
				continue;

			if (3 * z > target) // z is too large
				break;

			if (3 * z == target) { // z is the boundary
				if (i + 1 < high && nums[i + 2] == z)
					fourSumList.add(Arrays.asList(z1, z, z, z));
				break;
			}

			twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
		}

	}

	/*
	 * Find all possible distinguished two numbers adding up to the target
	 * in sorted array nums[] between indices low and high. If there are,
	 * add all of them into the ArrayList fourSumList, using
	 * fourSumList.add(Arrays.asList(z1, z2, the two numbers))
	 */
	public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
			int z1, int z2) {

		if (low >= high)
			return;

		if (2 * nums[low] > target || 2 * nums[high] < target)
			return;

		int i = low, j = high, sum, x;
		while (i < j) {
			sum = nums[i] + nums[j];
			if (sum == target) {
				fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

				x = nums[i];
				while (++i < j && x == nums[i]) // avoid duplicate
					;
				x = nums[j];
				while (i < --j && x == nums[j]) // avoid duplicate
					;
			}
			if (sum < target)
				i++;
			if (sum > target)
				j--;
		}
		return;
	}
//	public List<List<Integer>> fourSum(int[] nums, int target) {
//		List<List<Integer>> res = new ArrayList<>();
//		
//		//Arrays.sort(nums);
//		
//		HashMap<Integer, Integer> map = new HashMap<>();
//		for (int i : nums){
//			if (map.containsKey(i))
//				map.replace(i, map.get(i) + 1);
//			else
//				map.put(i, 1);
//		}
//		
//		Integer[] arr = new Integer[map.size()];
//		map.keySet().toArray(arr);
//		Arrays.sort(arr);
//		List<Integer> list = null;
//		
//		//值:  n1  n2  n3  n4
//		//个数: i   j   k   l
//		//结果: n1*i + n2*j + n3*k + n4*l == target
//		for (int i = 0; i < map.size(); i++){
//			int sizei = map.get(arr[i]);
//			if (i + 1 == map.size()){
//				if (sizei ==4 && arr[i] * sizei == target){
//					list = new ArrayList<>();
//					list.add(arr[i]);
//					list.add(arr[i]);
//					list.add(arr[i]);
//					list.add(arr[i]);
//				}
//				res.add(list);
//			}else if (i + 2 == map.size()){
//				int j = i + 1;
//				int sizej = map.get(arr[j]);
//				
//				if (sizei + sizej < 4)
//					continue;
//				else{
//					while(sizei >= 0){
//						if (4 > sizei + map.get(arr[j]))
//							break;
//						sizej = 4 - sizei;
//						
//						if (sizei * arr[i] + sizej * arr[j] == target){
//							
//							list = new ArrayList<>();
//							int k = 0;
//							while(k++ < sizei){
//								list.add(arr[i]);
//							}
//							k = 0;
//							while(k++ < sizej){
//								list.add(arr[j]);
//							}
//							
//							res.add(list);
//						}
//						sizei--;
//					}
//				}
//			}else{
//				int j = i + 1, k = map.size() - 1;
//				int sizej = 0, sizek = 0;
//				while (sizei >= 0) {
//					if (sizei + sizej + sizek == 4){
//						if (arr[i] * sizei + arr[j] * sizej + arr[k] * sizek == target){
//							list = new ArrayList<>();
//							int l = 0;
//							while(l < sizei)
//								list.add(arr[i]);
//							l = 0;
//							while(l < sizej)
//								list.add(arr[j]);
//							l = 0;
//							while(l < sizek)
//								list.add(arr[k]);
//						}
//					}else{
//						int sum = target - arr[i] * sizei - arr[j] * sizej - arr[k] * sizek;
//						int num = 4 - sizei - sizej - sizek;
//						if (map.containsKey(sum)){
//							int l = map.get(sum);
//							if (l == arr[i]){
//								continue;
//							}else if (l == arr[j]){
//								if (map.get(arr[j]) -  sizej > 0){
//									list = new ArrayList<>();
//									int p = 0;
//									while(p < sizei)
//										list.add(arr[i]);
//									p = 0;
//									while(l < sizej + 1)
//										list.add(arr[j]);
//									p = 0;
//									while(p < sizek)
//										list.add(arr[k]);
//									res.add(list);
//								}
//							}else if (l == arr[k]){
//								if (map.get(arr[k]) -  sizek > 0){
//									list = new ArrayList<>();
//									int p = 0;
//									while(p < sizei)
//										list.add(arr[i]);
//									p = 0;
//									while(l < sizej)
//										list.add(arr[j]);
//									p = 0;
//									while(p < sizek + 1)
//										list.add(arr[k]);
//									res.add(list);
//								}
//							}else{
//								list = new ArrayList<>();
//								int p = 0;
//								while(p < sizei)
//									list.add(arr[i]);
//								p = 0;
//								while(l < sizej)
//									list.add(arr[j]);
//								p = 0;
//								while(p < sizek)
//									list.add(arr[k]);
//								list.add(l);
//								res.add(list);
//							}
//						}
//					}
//					sizei--;
//				}
//			}
//		}
//		
//		return res;
//	}
}
