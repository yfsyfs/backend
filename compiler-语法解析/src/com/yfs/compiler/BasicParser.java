package com.yfs.compiler;

import static com.yfs.compiler.Lexer.*;

/**
 * 语法解析器， 其实整个解析过程和语法解析树是一回事, 完全遵照
 * 
 * @author yfs
 *
 */
public class BasicParser {

	private Lexer lexer;
	private boolean isLeagal = true; // 表达式是否合法.

	public BasicParser(Lexer lexer) {
		this.lexer = lexer;
	}

	/**
	 * 解析statements, 规则1: statements -> '空' | expression;statements
	 */
	public void statements() {

		expression();

		if (lexer.match(SEMI)) { // 如果当前字符是分号
			lexer.advance(); // 则继续读取下一个字符
		} else {
			isLeagal = false; // 下一个字符不是分号
			System.out.println("line " + lexer.lineno + ": Missing semicolon"); // 缺少分号
			return;
		}
		if (!lexer.match(EOF)) { // 如果下一个字符不是EOF，也就是说还有内容的话
			statements(); // 递归调用, 继续解析statements（参见规则1）
		}
		if (isLeagal) { // 如果最终合法的话, 则合法
			System.out.println("The statements is legal.");
		}
	}

	/**
	 * expression 解析为 term expression' 规则2: expression -> term expression'
	 */
	private void expression() {
		term();
		expr_prime();
	}

	/**
	 * 规则3: expression' -> + term expression' | '空'
	 */
	private void expr_prime() {
		if (lexer.match(Lexer.PLUS)) { // 如果当前字符是 + ,则
			lexer.advance(); // 则往后读取一个字符
			term();
			expr_prime(); // 递归调用
		} else if (lexer.match(UNKNOWN_SYMBOL)) {
			isLeagal = false;
			System.out.println("Unknown symbol: " + lexer.text);
			return;
		} else {
			return; // '空'
		}
	}

	/**
	 * 规则4: term -> factor term'
	 */
	private void term() {
		factor();
		term_prime();
	}

	/**
	 * 规则5: term' -> * factor term' | '空'
	 */
	private void term_prime() {
		if (lexer.match(TIMES)) { // 如果当前字符是*
			lexer.advance(); // 继续读取一个字符
			factor();
			term_prime(); // 递归
		} else if (lexer.match(UNKNOWN_SYMBOL)) {
			isLeagal = false;
			System.out.println("Unknown symbol: " + lexer.text);
			return;
		} else {
			return; // '空'
		}
	}

	/**
	 * 规则6: factor -> number | (expression)
	 */
	private void factor() {
		if (lexer.match(NUM_OR_ID)) { // 如果数字或字母
			lexer.advance(); // 继续往后读取一个字符
		} else if (lexer.match(LP)) { // 如果是左括号
			lexer.advance(); // 则继续读取(左括号)后面一个字符
			expression(); // 开始解析expression
			if (lexer.match(RP)) { // 如果是右括号
				lexer.advance(); // 则继续读取(右括号)后面一个字符
			} else { // 有左括号但是没有右括号,错误
				isLeagal = false;
				System.out.println("line " + lexer.lineno + " Missing ).");
				return;
			}
		} else {
			isLeagal = false;
			System.out.println("illegal statements");
			return;
		}
	}
}
