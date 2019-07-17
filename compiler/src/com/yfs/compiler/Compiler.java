package com.yfs.compiler;

/**
 * 编译器
 * 
 * @author yfs
 *
 */
public class Compiler {

	public static void main(String[] args) {
		Lexer lexer = new Lexer();
		lexer.runLexer(); // 运行词法解析器
	}

}
