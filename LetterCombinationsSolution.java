package letCode;

import java.util.ArrayList;
import java.util.List;

public class LetterCombinationsSolution {

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
}
