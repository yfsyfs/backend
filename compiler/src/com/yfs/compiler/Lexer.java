package com.yfs.compiler;

import java.util.Scanner;

/**
 * 词法解析器
 * 
 * @author yfs
 *
 */
@SuppressWarnings("all")
public class Lexer {

	private static final int EOF = -1; // 文件末尾
	public static final int SEMI = 1; // 分号
	public static final int PLUS = 2; // +
	public static final int TIMES = 3; // *
	public static final int LP = 4; // (
	public static final int RP = 5; // )
	public static final int NUM_OR_ID = 6; // 字母或者数字

	private int lookAhead = -1; // 等于上面的枚举值之一,表示当前的text到底是什么
	private String current = ""; // 当前用户输入的字符串（inputBuffer被截的七零八落的剩余结果就是current）
	private String inputBuffer = ""; // 仅仅用于存储用户的输入（end之前的所有内容）
	private int length; // text的长度
	private String text; // 文章中所谓的"有意义的"字符串, 编译原理里面叫symbol（符号）

	/**
	 * 运行词法解析器
	 */
	public void runLexer() {
		while (!match(EOF)) { // 只要当前不是EOF
			System.out.println("Token: " + token() + ",Symbol: " + text); // 按照【标签:具体字符串】进行打印
			advance(); // 读取下一个字符并进行分析
		}
	}

	private void advance() {
		lookAhead = lex();
	}

	private boolean match(int token) {
		if (lookAhead == -1) {
			lookAhead = lex();
		}
		return token == lookAhead;
	}

	private int lex() {
		while (true) {
			while ("".equals(current)) { // current一旦有值了, 就要开始分析了, 不能继续输入了
				Scanner scanner = new Scanner(System.in);
				while (true) {
					String line = scanner.nextLine();
					if ("end".equals(line)) { // 用户输入的是"end"的话,就结束
						break;
					}
					inputBuffer += line; // 存储用户输入 end 之前的所有内容, inputBuffer就是这个作用而已
				}
				if ("".equals(inputBuffer)) {
					current = "";
					return EOF; // 如果你在控制台仅仅输入 end的话, 则这里得到的就是EOF， 则runLexer就会结束while循环了
				}
				current = inputBuffer; // 伊始, inputBuffer还没有被截取, 所以current等于 inputBuffer
				current = current.trim();
			}
			for (int i = 0; i < current.length(); i++) {
				length = 0;
				text = current.substring(0, 1); // 对于非数字和字母, 则text就是这个字符, 而对于数字或者字母，则需要继续往后截取, 即像default中展示的那样, i还要++,
												// text还要变化
				switch (current.charAt(i)) {
				case ';':
					current = current.substring(1);
					return SEMI;
				case '+':
					current = current.substring(1);
					return PLUS;
				case '*':
					current = current.substring(1);
					return TIMES;
				case '(':
					current = current.substring(1);
					return LP;
				case ')':
					current = current.substring(1);
					return RP;
				case '\n':
				case '\t':
				case ' ':
					current = current.substring(1); // 仅仅截掉
					break;
				default: // 字母或者数字
					if (isAlplhaOrNum(current.charAt(i))) { // 如果是字母或者数字
						while (isAlplhaOrNum(current.charAt(i))) {
							i++;
							length++;
						}
						text = current.substring(0, length);
						current = current.substring(length);
						return NUM_OR_ID;
					}
				}
			}
		}
	}

	/**
	 * 判断是否是字母或者数字
	 * 
	 * @param c
	 * @return
	 */
	private boolean isAlplhaOrNum(char c) {
		return Character.isAlphabetic(c) || Character.isDigit(c);
	}

	/**
	 * 编译原理中说的token就是打上的标签，symbol就是对应的有意义的字符串
	 * 
	 * @return
	 */
	private String token() {
		switch (lookAhead) {
		case EOF:
			return "EOF";
		case PLUS:
			return "PLUS";
		case TIMES:
			return "TIMES";
		case NUM_OR_ID:
			return "NUM_OR_ID";
		case SEMI:
			return "SEMI";
		case LP:
			return "LP";
		case RP:
			return "RP";
		}
		return "";
	}

}
/*
 * 测试数据 1+2*3+4; end
 * 
 * 或者 end
 */
