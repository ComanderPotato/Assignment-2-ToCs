import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
public class SyntacticAnalyser {
	public static ParseTree parse(List<Token> tokens) throws SyntaxException {
//start & declaring stuff
		ParseTree tree = new ParseTree();
		TreeNode prog = new TreeNode(TreeNode.Label.prog, null);
		tree.setRoot(prog);
		if (tokens.size() == 0) throw new SyntaxException(tokens.toString());
		Stack<Symbol> stack = new Stack<Symbol>();
		Stack<TreeNode> parentStack = new Stack<TreeNode>();
		Map<Pair<Symbol, Token.TokenType>, List<Symbol>> rules = new HashMap<Pair<Symbol, Token.TokenType>, List<Symbol>>();
//making rule lists
		List<Symbol> rule1 = new LinkedList<>();
		List<Symbol> rule2 = new LinkedList<>();
		List<Symbol> rule2p2 = new LinkedList<>();
		List<Symbol> rule3 = new LinkedList<>();
		List<Symbol> rule3p2 = new LinkedList<>();
		List<Symbol> rule3p3= new LinkedList<>();
		List<Symbol> rule3p4 = new LinkedList<>();
		List<Symbol> rule3p5 = new LinkedList<>();
		List<Symbol> rule3p6 = new LinkedList<>();
		List<Symbol> rule3p7 = new LinkedList<>();
// adding symbols to rules
		Collections.addAll(rule1, Token.TokenType.RBRACE, Token.TokenType.RBRACE, TreeNode.Label.los, Token.TokenType.LBRACE, Token.TokenType.RPAREN, Token.TokenType.ARGS, Token.TokenType.STRINGARR, Token.TokenType.LPAREN, Token.TokenType.MAIN, Token.TokenType.VOID, Token.TokenType.STATIC, Token.TokenType.PUBLIC, Token.TokenType.LBRACE, Token.TokenType.ID, Token.TokenType.CLASS, Token.TokenType.PUBLIC);
		Collections.addAll(rule2, TreeNode.Label.los,TreeNode.Label.stat);
		Collections.addAll(rule2p2, TreeNode.Label.epsilon);
		Collections.addAll(rule3p7, Token.TokenType.SEMICOLON);
//adding rules to map
		rules.put(new Pair(TreeNode.Label.prog, "none"), rule1);
		rules.put(new Pair(TreeNode.Label.los, "none"), rule2);
		rules.put(new Pair(TreeNode.Label.los, Token.TokenType.RBRACE), rule2p2);
		rules.put(new Pair(TreeNode.Label.stat, Token.TokenType.SEMICOLON), rule3p7);
//filling the stack initally (backwards)
		TreeNode topParent = prog;
		parentStack.push(prog);
		rule1.forEach(child -> {
			stack.push(child);
		});
		int i = 0;

//MAIN
//MAIN
// looping though input and popping
		while( i < tokens.size()){
			Token token = tokens.get(i);
			TreeNode currentParent = parentStack.peek();
//default token match and pop from stack
			if(stack.peek()==token.getType()){
				stack.pop();
				//System.out.println(stack);
				currentParent.addChild(new TreeNode(TreeNode.Label.terminal, new Token(token.getType()), currentParent));
				i++;
			}
//break if there are missing tokens for syntax (first few tests)
			// else if (!stack.peek().isVariable()){
			// 	throw new SyntaxException(tokens.toString());
			// }
//if epsilon, make child, pop parent
			else if(stack.peek()==TreeNode.Label.epsilon){
				currentParent.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));
				System.out.println("test2");
				stack.pop();
				parentStack.pop();
			}
// if terminal, do that rule
			else{
				System.out.println("test3");
				for (TreeNode.Label l : TreeNode.Label.values()) {
					if (l.equals(stack.peek())) {
//break for all
//finding what terminal, making it new parent, pop from stack
						TreeNode variable = new TreeNode(l, currentParent);
						currentParent.addChild(variable);
						parentStack.push(variable);
						stack.pop();
						System.out.println(parentStack.peek());
//checks if next token is right brace, for top of stack is RBRACE (i.e. last before right brace)
						if(stack.peek()==token.getType()){
							rules.get(new Pair(variable.getLabel(), stack.peek())).forEach(child -> {
							stack.push(child);
							});
						}
//checksif next stack is a terminal (i.e. los), so not to break
						else if (stack.peek().isVariable()){
							rules.get(new Pair(variable.getLabel(), token.getType())).forEach(child -> {
							stack.push(child);
							});
						}
//standard?
						else{
							rules.get(new Pair(variable.getLabel(), "none")).forEach(child -> {
							stack.push(child);
							});
						}					
						break;
					}
				}
			}
		}
		return tree;
	}
}


// The following class may be helpful.

class Pair<A, B> {
	private final A a;
	private final B b;

	public Pair(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public A fst() {
		return a;		
	}

	public B snd() {
		return b;
	}

	@Override
	public int hashCode() {
		return 3 * a.hashCode() + 7 * b.hashCode();
	}

	@Override
	public String toString() {
		return "{" + a + ", " + b + "}";
	}

	@Override
	public boolean equals(Object o) {
		if ((o instanceof Pair<?, ?>)) {
			Pair<?, ?> other = (Pair<?, ?>) o;
			return other.fst().equals(a) && other.snd().equals(b);
		}

		return false;
	}

}
