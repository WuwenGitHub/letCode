package letCode;

public class MyAtoiSolution {

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
}
