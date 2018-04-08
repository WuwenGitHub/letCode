package letCode;

public class IntToRomanSolution {

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
}
