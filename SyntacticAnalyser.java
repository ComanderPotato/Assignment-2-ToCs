import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
public class SyntacticAnalyser {


	public static ParseTree parse(List<Token> tokens) throws SyntaxException {
		ParseTree tree = new ParseTree();
		TreeNode prog = new TreeNode(TreeNode.Label.prog, null);
		TreeNode currentParent = prog;
		tree.setRoot(currentParent);
		if(tokens.size() == 0) throw new SyntaxException(tokens.toString());
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(currentParent);
		int listSize = tokens.size();
		int i = 0;
		//RULE 1 needs to be refactored
		if(stack.peek().getLabel() == TreeNode.Label.prog) {
			stack.pop();
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), currentParent));
			stack.push(new TreeNode(TreeNode.Label.los, currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RPAREN), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ARGS), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.STRINGARR), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.MAIN), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.VOID), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.STATIC), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PUBLIC), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ID), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.CLASS), currentParent));
			stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PUBLIC), currentParent));
			
		}
		
		Token token = tokens.get(i);
		while(listSize > i) {
		
		if(stack.peek().getLabel() == TreeNode.Label.los) {
			stack.pop();
			stack.push(new TreeNode(TreeNode.Label.los, currentParent));
			stack.push(new TreeNode(TreeNode.Label.stat, currentParent));
			
				if(token.getType() == Token.TokenType.RBRACE) {
					stack.pop();
					TreeNode los = new TreeNode(TreeNode.Label.los, currentParent);
					currentParent.addChild(los);
					currentParent = los;
					currentParent.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));
					stack.pop();
					currentParent = prog;
					}
			}
			if(stack.peek().getToken().get().getType() == token.getType()) {
				currentParent.addChild(new TreeNode(TreeNode.Label.terminal, token, currentParent));
		
				}
			stack.pop();
			i++;
		
			
		
		}
		System.out.println(tree.toString());
			if(stack.size() > 0) throw new SyntaxException(tokens.toString());
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