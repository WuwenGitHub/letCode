package letCode;

public class ConvertSolution {

	/**
	 * 
	 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given
	 * number of rows like this: (you may want to display this pattern in a
	 * fixed font for better legibility)
	 * 
	 * P A H N A P L S I I G Y I R
	 * 
	 * And read line by line: "PAHNAPLSIIGYIR"
	 * 
	 * Write the code that will take a string and make this conversion given a
	 * number of rows: String convert(String text, int nRows);
	 * 
	 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */

	// 52ms
	public String convert(String s, int numRows) {

		char[] c = s.toCharArray();
		int len = c.length;
		StringBuffer[] sb = new StringBuffer[numRows];
		for (int i = 0; i < sb.length; i++)
			sb[i] = new StringBuffer();

		int i = 0;
		while (i < len) {
			for (int idx = 0; idx < numRows && i < len; idx++) // vertically
																// down
				sb[idx].append(c[i++]);
			for (int idx = numRows - 2; idx >= 1 && i < len; idx--) // obliquely
																	// up
				sb[idx].append(c[i++]);
		}
		for (int idx = 1; idx < sb.length; idx++)
			sb[0].append(sb[idx]);

		return sb[0].toString();
	}

	// 40ms
	public String convert2(String s, int numRows) {
		if (s == null || numRows <= 1 || s.length() <= numRows)
			return s;

		int len = s.length();
		char[] source = s.toCharArray();
		char[] dest = new char[len];
		int step = 2 * numRows - 2;
		int k = 0;
		for (int i = 0; i < len; i += step) {
			dest[k++] = source[i];
		}
		for (int i = 1; i < numRows - 1; ++i) {
			int j = i;
			int otherJ = j + step - 2 * i;
			while (j < len && otherJ < len) {
				dest[k++] = source[j];
				dest[k++] = source[otherJ];
				j += step;
				otherJ += step;
			}
			if (j < len)
				dest[k++] = source[j];
		}
		for (int i = numRows - 1; i < len; i += step) {
			dest[k++] = source[i];
		}
		return String.valueOf(dest);
	}
}
