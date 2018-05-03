package letCode;

import java.util.*;

public class IsValidSolution {

	/**
	 * 
	 * Given a string containing just the characters '(', ')', '{', '}', '[' and
	 * ']', determine if the input string is valid.
	 * 
	 * The brackets must close in the correct order, "()" and "()[]{}" are all
	 * valid but "(]" and "([)]" are not.
	 * 
	 * @param s
	 * @return
	 */
	public boolean isValid(String s) {
		char ch;

		Stack<Character> deque = new Stack<>();

		for (char c : s.toCharArray()) {
			if (c == '(' || c == '{' || c == '[')
				deque.add(c);
			else {
				if (deque.isEmpty())
					return false;

				if (c == ')') {
					ch = deque.pop();
					if (ch != '(')
						return false;
				} else if (c == '}') {
					ch = deque.pop();
					if (ch != '{')
						return false;
				} else if (c == ']') {
					ch = deque.pop();
					if (ch != '[')
						return false;
				}
			}
		}

		return deque.isEmpty();
	}

	public boolean isValid2(String s) {
		if ((s.length() & 1) == 1)
			return false;
		else {
			Deque<Character> p = new ArrayDeque<>(s.length());
			for (int i = 0; i < s.length(); i++)
				switch (s.charAt(i)) {
				case '(':
					p.push(')');
					break;
				case '{':
					p.push('}');
					break;
				case '[':
					p.push(']');
					break;
				case ')':
				case '}':
				case ']':
					if (p.isEmpty() || p.pop() != s.charAt(i))
						return false;
				}
			return p.isEmpty();
		}
	}
}
