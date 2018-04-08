package letCode;

public class ReverseSoution {

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
}
