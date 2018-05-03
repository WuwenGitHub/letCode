package letCode;

public class DivideSolution {

	/**
	 * 
	 * Given two integers dividend and divisor, divide two integers without
	 * using multiplication, division and mod operator.
	 * 
	 * Return the quotient after dividing dividend by divisor.
	 * 
	 * The integer division should truncate toward zero.
	 * 
	 * Example 1:
	 * 
	 * Input: dividend = 10, divisor = 3 Output: 3
	 * 
	 * Example 2:
	 * 
	 * Input: dividend = 7, divisor = -3 Output: -2 Note:
	 * 
	 * Both dividend and divisor will be 32-bit signed integers. The divisor
	 * will never be 0. Assume we are dealing with an environment which could
	 * only store integers within the 32-bit signed integer range: [−231, 231 −
	 * 1]. For the purpose of this problem, assume that your function returns
	 * 231 − 1 when the division result overflows.
	 * 
	 * 
	 * @param dividend
	 * @param divisor
	 * @return
	 */
	public int divide(int dividend, int divisor) {

		String result = "";

		if (dividend == 0)
			return 0;

		String end = dividend > 0 ? dividend + "" : (dividend + "").substring(1);
		String sor = divisor > 0 ? divisor + "" : (divisor + "").substring(1);

		if (end.length() < sor.length())
			return 0;

		long num = Long.parseLong(end.substring(0, sor.length()));
		int inx = sor.length();
		long divs = divisor > 0 ? divisor : (0 - (long) divisor);

		while (true) {
			int div = 0;
			while (num >= divs) {
				num -= divs;
				div++;
			}

			result += div;
			if (inx == end.length())
				break;
			num = Long.parseLong(num + (end.charAt(inx++) + ""));
		}

		return (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)
				? (result.equals((Integer.MIN_VALUE + "").substring(1)) ? Integer.MAX_VALUE : Integer.parseInt(result))
				: Integer.parseInt("-" + result);
	}

	public int divide2(int dividend, int divisor) {

		if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1)
			return Integer.MAX_VALUE;
		
		int res = 0;
		
		int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
		long dvd = Math.abs((long) dividend);
		long dvs = Math.abs((long) divisor);
		
		// x * 2 等同于左移一位
		// x / 2 等同于右移一位
		while (dvs <= dvd) {
			long temp = dvs, mul = 1;
			while (dvd >= temp << 1) {
				temp <<= 1;
				mul <<= 1;
			}
			dvd -= temp;
			res += mul;
		}
		return sign == 1 ? res : -res;
	}

}
