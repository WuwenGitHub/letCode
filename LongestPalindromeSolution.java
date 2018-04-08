package letCode;

public class LongestPalindromeSolution {

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
}
