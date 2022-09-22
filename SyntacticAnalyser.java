import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;
public class SyntacticAnalyser {

	static TreeNode.Label state = TreeNode.Label.prog;

		  	static Stack<Token> stack = new Stack<Token>();
	public static ParseTree parse(List<Token> tokens) throws SyntaxException {
		if(tokens.size() == 0) throw new SyntaxException(tokens.toString());
			  TreeNode root = new TreeNode(TreeNode.Label.prog, null);
			  ParseTree tree = new ParseTree();
			  TreeNode node = null;
			  tree.setRoot(root);
			for(Token token : tokens) {
				Token.TokenType currentType = token.getType();
				
			if(stack.size() > 0) {
				Token currPeek = stack.peek();

				if(currPeek.getType() == Token.TokenType.PUBLIC) {
					switch(currentType) {
						default: node =  new TreeNode(state = TreeNode.Label.terminal, stack.pop(), root);
						; break;
					}
					}
					if(currPeek.getType() == Token.TokenType.CLASS) {
					switch(currentType) {
						default: node = new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
					; break;
					}
					}
					if(currPeek.getType() == Token.TokenType.ID) {
					switch(currentType) {
						default :node =  new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
					; break;
					}
}

					if(currPeek.getType() == Token.TokenType.STATIC) {
					switch(currentType) {
						default: node = new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
					; break;
					}
					}
					if(currPeek.getType() == Token.TokenType.VOID) {
					switch(currentType) {
						default : node = new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
					; break;
					}
					}
					if(currPeek.getType() == Token.TokenType.MAIN) {
					switch(currentType) {
						default: node = new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
					; break;
					}
					}
					if(currPeek.getType() == Token.TokenType.LPAREN) {
					switch(currentType) {
						default: new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
; break;
					}
}
					if(currPeek.getType() == Token.TokenType.STRINGARR) {
					switch(currentType) {
						default:node =  new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
; break;
					}
}
					if(currPeek.getType() == Token.TokenType.ARGS) {
					switch(currentType) {
						default : node = new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
; break;
					}
}
					if(currPeek.getType() == Token.TokenType.RPAREN) {
					switch(currentType) {
						default:node =  new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
; break;
					}
}
					if(currPeek.getType() == Token.TokenType.LBRACE) {
					switch(currentType) {
						case PUBLIC: node = new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
						break;
						default: node = new TreeNode(TreeNode.Label.los, root);
						stack.pop();
; break;
					}
}
					if(currPeek.getType() == Token.TokenType.RBRACE) {
					switch(currentType) {
						default:node =  new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
					; break;
					}
					}
					if(currPeek.getType() == Token.TokenType.SEMICOLON) {
					switch(currentType) {
						default: node = new TreeNode(TreeNode.Label.terminal, stack.pop(), root);
					; break;
					}
				}
				
			
				}
				
				System.out.println(stack.size());
				 stack.push(token);
				// if(stack.size() > 0) throw new SyntaxException("Broke");
			}
				root.addChild(node = new TreeNode(TreeNode.Label.terminal, stack.pop(), root));
				// node.getLabel();
			// if(stack.size() > 0) throw new SyntaxException("Broke stack not empty");
			// if(stack.size() > 1) throw new SyntaxException(tokens.toString());
			// System.out.println(root.getChildren());
			// System.out.println(root.getChildren());
		return tree;

		
	}


}
		// TreeNode assign = new TreeNode(TreeNode.Label.assign, null);
			// TreeNode node1 = new TreeNode(TreeNode.Label.prog, assign);
			// TreeNode node2 = new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PLUS, "+"), assign);
			// assign.addChild(node1);
			// assign.addChild(node2);
			
			// ParseTree smallTree = new ParseTree();
			// smallTree.setRoot(assign);
			// System.out.println(smallTree.getRoot());
			// System.out.println(assign.getChildren());
			// System.out.println(node1.getParent());
			// System.out.println(node2.getParent());
			// System.out.println(node1.getLabel());
			// System.out.println(smallTree.toString());

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