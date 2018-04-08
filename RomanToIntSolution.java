package letCode;

import java.util.HashMap;
import java.util.Map;

public class RomanToIntSolution {

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
}
