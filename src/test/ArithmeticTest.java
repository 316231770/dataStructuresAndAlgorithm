package test;

import dataStructures.collection.stack.ArrayListStack;
import dataStructures.collection.stack.LinkedListStack;
import dataStructures.collection.stack.Stack;

public class ArithmeticTest {
	private enum Symbol{
		LEFT_BRACKETS("(",0),
		PLUS("+",1),
		MINUS("-",1),
		MULTIPLY("*",2),
		DIVISION("/",2);
		
		private String SymbolStr;
		
		private int level;

		private Symbol(String symbolStr, int level) {
			SymbolStr = symbolStr;
			this.level = level;
		}

		public static int getLevelByStr(String SymbolStr) {
			Symbol[] symbols=Symbol.values();
			for (Symbol symbol : symbols) {
				if (symbol.SymbolStr.equals(SymbolStr)) {
					return symbol.level;
				}
			}
			return -1;
		}
	}
	/**
	 * 中缀转后缀
	 * @param exp
	 */
	public static String infixToPostfix(String exp){
		//存储操作符的栈
		Stack<Character> stack = new LinkedListStack<>();
		//输出的后缀表达式的字符串
		StringBuffer suffix = new StringBuffer();
		//当前指向的字符索引
		int index = 0;
		int length = exp.length();
		while(index < length){
			switch(exp.charAt(index)){
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					//数字直接输出
					suffix.append(exp.charAt(index));
					break;
				case '(':
					//左括号直接入栈
					stack.push(exp.charAt(index));
					break;
				case '*':
				case '/':
				case '+':
				case '-':
					//判断是否空栈
					if (stack.isEmpty()) {
						stack.push(exp.charAt(index));
					}else {
						//判断栈顶元素优先级是否比当前操作符小
						int expSymbolLevel=Symbol.getLevelByStr(String.valueOf(exp.charAt(index)));
						//弹出栈中优先级大的操作符
						for(int topSymbolLevel=Symbol.getLevelByStr(String.valueOf(stack.peek()));
								topSymbolLevel >= expSymbolLevel;
								topSymbolLevel=Symbol.getLevelByStr(String.valueOf(stack.peek()))){
							suffix.append(stack.pop());
							if (stack.isEmpty())break;
						}
						stack.push(exp.charAt(index));
					}
					break;
				case ')':
					while(!stack.isEmpty() && stack.peek() != '('){
						suffix.append(stack.pop());
					}
					stack.pop();
					break;
			}
			index++;
			System.out.println(stack+"\t"+suffix);
		}
		while(!stack.isEmpty()){
			suffix.append(stack.pop());
		}
		return suffix.toString();
	}
	
	/**
	 * 计算后缀表达式的值
	 * @param exp
	 * @return
	 */
	public static int giveResult(String exp){
		Stack<Integer> stack = new LinkedListStack<>();
		int length = exp.length();
		int index = 0;
		int a = 0;
		int b = 0;
		while(index < length){
			switch(exp.charAt(index)){
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
					stack.push(Integer.parseInt(String.valueOf(exp.charAt(index))));
					break;
				case '+':
					a = stack.pop();
					b = stack.pop();
					stack.push(b + a);
					break;
				case '-':
					a = stack.pop();
					b = stack.pop();
					stack.push(b - a);
					break;
				case '*':
					a = stack.pop();
					b = stack.pop();
					stack.push(b * a);
					break;
				case '/':
					a = stack.pop();
					b = stack.pop();
					stack.push(b / a);
					break;
			}
			index++;
		}
		return stack.pop();
	}
	
	public static void main(String[] args) {
		String postStr=infixToPostfix("5+2*(3*(3-1*2+1))");
		System.out.println(postStr);
		int result = giveResult(postStr);
		System.out.println(result);
	}
}
