package letCode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GenerateParenthesisSolution {

	/**
	 * 
	 * Given n pairs of parentheses, write a function to generate all
	 * combinations of well-formed parentheses.
	 * 
	 * For example, given n = 3, a solution set is: 
	 * 
	 * [ 
	 *    "((()))", 
	 *    "(()())",
	 *    "(())()", 
	 *    "()(())", 
	 *    "()()()" 
	 * ]
	 * 
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<String>();
        backtrack(list, "", 0, 0, n);
        return list;
    }
    
	//open -- '('的个数  , close -- ')'的个数  , max -- "()"的对数
	//'('与')'必须成对出现
    public void backtrack(List<String> list, String str, int open, int close, int max){
        
        if(str.length() == max*2){
            list.add(str);
            return;
        }
        
        if(open < max)
            backtrack(list, str+"(", open+1, close, max);
        if(close < open)
            backtrack(list, str+")", open, close+1, max);
    }
}
