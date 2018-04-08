package letCode;

public class IsPalindromeSolution {

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
}
