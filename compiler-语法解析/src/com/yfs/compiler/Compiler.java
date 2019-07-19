package com.yfs.compiler;

/**
 * ±àÒëÆ÷
 * 
 * @author yfs
 *
 */
public class Compiler {
	
	public static void main(String[] args) {
		Lexer lexer = new Lexer();
		BasicParser parser = new BasicParser(lexer);
		parser.statements(); // Óï·¨½âÎö
	}

}
