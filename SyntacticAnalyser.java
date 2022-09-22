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
			//Set new root as prog and null
			TreeNode root = new TreeNode(TreeNode.Label.prog, null);
			//Create a new empty tree
			ParseTree tree = new ParseTree();
			//add the new root to the empty tree as its root
			tree.setRoot(root);
			//Store the current parent as root, and then change when a variable is seen
			TreeNode currParent = root;

			for(Token token : tokens) {
				//Set current type as current tokens type
				Token.TokenType currType = token.getType();
	
			if(stack.size() > 0) {
				//Peak into stack and dont read
				Token currPeek = stack.peek();

				if(currPeek.getType() == Token.TokenType.PUBLIC) {
					switch(currType) {
						case CLASS:
						case STATIC: state = TreeNode.Label.terminal; 
						stack.pop();
						break;
					}
				}
				if(currPeek.getType() == Token.TokenType.CLASS) {
					switch(currType) {
						case ID: state = TreeNode.Label.terminal; 
						stack.pop();
						break;
					}
				}
				if(currPeek.getType() == Token.TokenType.ID) {
					switch(currType) {
						case LBRACE: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}
				if(currPeek.getType() == Token.TokenType.LBRACE) {
					switch(currType) {
						case PUBLIC: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
				if(currPeek.getType() == Token.TokenType.STATIC) {
					switch(currType) {
						case PUBLIC: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
				if(currPeek.getType() == Token.TokenType.VOID) {
					switch(currType) {
						case STATIC: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
				if(currPeek.getType() == Token.TokenType.MAIN) {
					switch(currType) {
						case VOID: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
				if(currPeek.getType() == Token.TokenType.LPAREN) {
					switch(currType) {
						case MAIN: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
				if(currPeek.getType() == Token.TokenType.STRINGARR) {
					switch(currType) {
						case LPAREN: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
				if(currPeek.getType() == Token.TokenType.ARGS) {
					switch(currType) {
						case STRINGARR: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
				if(currPeek.getType() == Token.TokenType.RPAREN) {
					switch(currType) {
						case ARGS: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
				if(currPeek.getType() == Token.TokenType.RBRACE) {
					switch(currType) {
						case RBRACE:
						case LBRACE: state = TreeNode.Label.terminal;
						stack.pop();
						break;
					}
				}	
			
				
					
				
			
				}
				currParent.addChild(new TreeNode(state, token, currParent));
				stack.push(token);
							stack.pop();

			}
			System.out.println(root.getChildren());

			// if(stack.size() > 0) throw new SyntaxException(tokens.toString());
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
