package letCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindSubstringSolution {

	/**
	 * 
	 * You are given a string, s, and a list of words, words, that are all of
	 * the same length. Find all starting indices of substring(s) in s that is a
	 * concatenation of each word in words exactly once and without any
	 * intervening characters.
	 * 
	 * Example 1:
	 * 
	 * Input: s = "barfoothefoobarman", words = ["foo","bar"]
	 * 
	 * Output: [0,9]
	 * 
	 * Explanation: Substrings starting at index 0 and 9 are "barfoor" and
	 * "foobar" respectively. The output order does not matter, returning [9,0]
	 * is fine too.
	 * 
	 * 
	 * Example 2:
	 * 
	 * Input: s = "wordgoodstudentgoodword", words = ["word","student"]
	 * 
	 * Output:[]
	 *
	 * @param s
	 * @param words
	 * @return
	 */

	/*
	 * Idea:
	 * 
	 * Basic idea is construct frequency map of words <tt> seen </tt>. At each
	 * char, try to build similar map <tt> tmp </tt>, anytime <tt> tmp </tt>
	 * doesn't have the <tt> substr </tt> we can restart the search process from
	 * the next char <tt> i+1 </tt> or when the count goes above what is in the
	 * <tt> seen </tt> for the key. If all goes well at the current <tt> i </tt>
	 * i.e <tt> j </tt> stands at the 1 character more than all the words in
	 * <tt> words </tt> concatenated, so our current search window was <tt> s[i...j] </tt>, if the
	 * length of this window is all the chars in <tt> words </tt>, then we found all the
	 * words, add it to list.
	 * 
	 */
	public List<Integer> findSubstring(String s, String[] words) {
		List<Integer> list = new ArrayList<>();
		Map<String, Integer> seen = new HashMap<>();
		int len = 0, i = 0;
		if (words.length == 0)
			return list;
		for (String w : words) {
			if (len != 0 && len != w.length())
				return list;
			len = w.length();
			seen.put(w, seen.getOrDefault(w, 0) + 1);
		}
		Map<String, Integer> tmp = new HashMap<>();
		while (i < s.length() - words.length * len + 1) {
			tmp.clear();
			int j = i;
			while (j + len <= s.length()) {
				String substr = s.substring(j, j + len);
				tmp.put(substr, tmp.getOrDefault(substr, 0) + 1);
				if (seen.containsKey(s.substring(j, j + len))) {
					if (tmp.get(substr) > seen.get(substr))
						break;
				} else
					break;

				j = j + len;
			}
			if ((j - i) == words.length * len)
				list.add(i);
			i++;
		}
		return list;
	}

}
