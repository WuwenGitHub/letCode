package letCode;

public class IsMatchSolution {

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
}
