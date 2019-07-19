
public class BasicParser {
	private Lexer lexer;
	private boolean isLegalStatement = true;

	public BasicParser(Lexer lexer) {
		this.lexer = lexer;
	}

	/*
	 * 规则1: statements -> expression ; | expression ; statements
	 */
	public void statements() {

		expression();

		if (lexer.match(Lexer.SEMI)) {
			/*
			 * look ahead 读取下一个字符，如果下一个字符不是 EOI 那就采用右边解析规则
			 */
			lexer.advance();
		} else {
			/*
			 *  如果算术表达式不以分号结束，那就是语法错误
			 */
			isLegalStatement = false;
			System.out.println("line: " + lexer.lineno + " Missing semicolon");
			return;
		}

		if (lexer.match(Lexer.EOF) != true) {
			/*
			 * 分号后还有字符，继续解析
			 */
			statements();
		}

		if (isLegalStatement) {
			System.out.println("The statement is legal");
		}
	}

	/*
	 * 规则2: expression -> term expression'
	 */
	private void expression() {
		term();
		expr_prime(); // expression'
	}

	/*
	 * 规则3: expression' -> PLUS term expression' | '空'
	 */
	private void expr_prime() {

		if (lexer.match(Lexer.PLUS)) {
			lexer.advance();
			term();
			expr_prime();
		} else if (lexer.match(Lexer.UNKNOWN_SYMBOL)) {
			isLegalStatement = false;
			System.out.println("unknow symbol: " + lexer.text);
			return;
		} else {
			/*
			 * "空" 就是不再解析，直接返回
			 */
			return;
		}
	}

	/*
	 * 规则4: term -> factor term'
	 */
	private void term() {

		factor();
		term_prime(); // term'
	}

	/*
	 * 规则5: term' -> * factor term' | '空'
	 */
	private void term_prime() {

		if (lexer.match(Lexer.TIMES)) {
			lexer.advance();
			factor();
			term_prime();
		} else {
			/*
			 * 如果不是以 * 开头， 那么执行 '空' 也就是不再做进一步解析，直接返回
			 */
			return;
		}
	}

	/*
	 * 规则6: factor -> NUM_OR_ID | LP expression RP
	 */
	private void factor() {

		if (lexer.match(Lexer.NUM_OR_ID)) {
			lexer.advance();
		} else if (lexer.match(Lexer.LP)) {
			lexer.advance();
			expression();
			if (lexer.match(Lexer.RP)) {
				lexer.advance();
			} else {
				/*
				 * 有左括号但没有右括号，错误
				 */
				isLegalStatement = false;
				System.out.println("line: " + lexer.lineno + " Missing )");
				return;
			}
		} else {
			/*
			 * 这里不是数字，解析出错
			 */
			isLegalStatement = false;
			System.out.println("illegal statements");
			return;
		}
	}
}
