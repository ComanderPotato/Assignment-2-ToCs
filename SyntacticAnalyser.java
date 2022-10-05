import com.sun.source.tree.Tree;
import java.util.*;
public class SyntacticAnalyser {

	public static ParseTree parse(List<Token> tokens) throws SyntaxException {
		ParseTree tree = new ParseTree();

		TreeNode prog = new TreeNode(TreeNode.Label.prog, null);
		if (tokens.size() == 0) throw new SyntaxException(tokens.toString());
		Stack<Pair<Symbol, TreeNode>> stack = new Stack<>();

		Stack<TreeNode> parentStack = new Stack<TreeNode>();

		Map<Pair<Symbol, Token.TokenType>, List<Symbol>> rules = new HashMap<Pair<Symbol, Token.TokenType>, List<Symbol>>();

		List<Symbol> rule1 = new LinkedList<>(Arrays.asList(Token.TokenType.RBRACE, Token.TokenType.RBRACE, TreeNode.Label.los, Token.TokenType.LBRACE, Token.TokenType.RPAREN, Token.TokenType.ARGS, Token.TokenType.STRINGARR, Token.TokenType.LPAREN, Token.TokenType.MAIN, Token.TokenType.VOID, Token.TokenType.STATIC, Token.TokenType.PUBLIC, Token.TokenType.LBRACE, Token.TokenType.ID, Token.TokenType.CLASS, Token.TokenType.PUBLIC));
		rules.put(new Pair(TreeNode.Label.prog, Token.TokenType.PUBLIC), rule1);

		List<Symbol> rule2p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.los, TreeNode.Label.stat));
		rules.put(new Pair(TreeNode.Label.los,  Token.TokenType.WHILE), rule2p1);

		List<Symbol> rule2p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.los, TreeNode.Label.stat));
		rules.put(new Pair(TreeNode.Label.los,  Token.TokenType.FOR), rule2p2);

		List<Symbol> rule2p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.los, TreeNode.Label.stat));
		rules.put(new Pair(TreeNode.Label.los,  Token.TokenType.IF), rule2p3);

		List<Symbol> rule2p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.los, TreeNode.Label.stat));
		rules.put(new Pair(TreeNode.Label.los,  Token.TokenType.ID), rule2p4);

		List<Symbol> rule2p5 = new LinkedList<>(Arrays.asList(TreeNode.Label.los, TreeNode.Label.stat));
		rules.put(new Pair(TreeNode.Label.los,  Token.TokenType.TYPE), rule2p5);

		List<Symbol> rule2p6 = new LinkedList<>(Arrays.asList(TreeNode.Label.los, TreeNode.Label.stat));
		rules.put(new Pair(TreeNode.Label.los,  Token.TokenType.PRINT), rule2p6);

		List<Symbol> rule2p7 = new LinkedList<>(Arrays.asList(TreeNode.Label.los, TreeNode.Label.stat));
		rules.put(new Pair(TreeNode.Label.los,  Token.TokenType.SEMICOLON), rule2p7);

		List<Symbol> rule2p8 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.los, Token.TokenType.RBRACE), rule2p8);

		/////////////////////////////////////////////////////////////


		List<Symbol> rule3p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.whilestat));
		rules.put(new Pair(TreeNode.Label.stat, Token.TokenType.WHILE), rule3p1);

		List<Symbol> rule3p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.forstat));
		rules.put(new Pair(TreeNode.Label.stat, Token.TokenType.FOR), rule3p2);

		List<Symbol> rule3p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.ifstat));
		rules.put(new Pair(TreeNode.Label.stat, Token.TokenType.IF), rule3p3);

		List<Symbol> rule3p4 = new LinkedList<>(Arrays.asList(Token.TokenType.SEMICOLON, TreeNode.Label.assign));
		rules.put(new Pair(TreeNode.Label.stat, Token.TokenType.ID), rule3p4);

		List<Symbol> rule3p5 = new LinkedList<>(Arrays.asList(Token.TokenType.SEMICOLON, TreeNode.Label.decl));
		rules.put(new Pair(TreeNode.Label.stat, Token.TokenType.TYPE), rule3p5);

		List<Symbol> rule3p6 = new LinkedList<>(Arrays.asList(Token.TokenType.SEMICOLON, TreeNode.Label.print));
		rules.put(new Pair(TreeNode.Label.stat, Token.TokenType.PRINT), rule3p6);

		List<Symbol> rule3p7 = new LinkedList<>(Arrays.asList(Token.TokenType.SEMICOLON));
		rules.put(new Pair(TreeNode.Label.stat, Token.TokenType.SEMICOLON), rule3p7);


		/////////////////////////////////////////////////////////////



		List<Symbol> rule4 = new LinkedList<>(Arrays.asList(Token.TokenType.RBRACE, TreeNode.Label.los, Token.TokenType.LBRACE, Token.TokenType.RPAREN, TreeNode.Label.boolexpr, TreeNode.Label.relexpr, Token.TokenType.LPAREN, Token.TokenType.WHILE));
		rules.put(new Pair(TreeNode.Label.whilestat, Token.TokenType.WHILE), rule4);


		/////////////////////////////////////////////////////////////


		List<Symbol> rule5 = new LinkedList<>(Arrays.asList(Token.TokenType.RBRACE, TreeNode.Label.los, Token.TokenType.LBRACE, Token.TokenType.RPAREN, TreeNode.Label.forarith, Token.TokenType.SEMICOLON, TreeNode.Label.boolexpr, TreeNode.Label.relexpr, Token.TokenType.SEMICOLON, TreeNode.Label.forstart, Token.TokenType.LPAREN, Token.TokenType.FOR));
		rules.put(new Pair(TreeNode.Label.forstat, Token.TokenType.FOR), rule5);


		/////////////////////////////////////////////////////////////



		List<Symbol> rule6p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.decl));
		rules.put(new Pair(TreeNode.Label.forstart, Token.TokenType.TYPE), rule6p1);

		List<Symbol> rule6p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.assign));
		rules.put(new Pair(TreeNode.Label.forstart, Token.TokenType.ID), rule6p2);

		List<Symbol> rule6p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.forstart, Token.TokenType.SEMICOLON), rule6p3);



		/////////////////////////////////////////////////////////////




		List<Symbol> rule7p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexpr));
		//Double check
		rules.put(new Pair(TreeNode.Label.forarith, Token.TokenType.LPAREN), rule7p1);

		List<Symbol> rule7p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexpr));
		//Double check
		rules.put(new Pair(TreeNode.Label.forarith, Token.TokenType.ID), rule7p2);

		List<Symbol> rule7p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexpr));
		//Double check
		rules.put(new Pair(TreeNode.Label.forarith, Token.TokenType.NUM), rule7p3);

		List<Symbol> rule7p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.forarith, Token.TokenType.RPAREN), rule7p4);



		/////////////////////////////////////////////////////////////



		List<Symbol> rule8 = new LinkedList<>(Arrays.asList(TreeNode.Label.elseifstat, Token.TokenType.RBRACE, TreeNode.Label.los, Token.TokenType.LBRACE, Token.TokenType.RPAREN, TreeNode.Label.boolexpr, TreeNode.Label.relexpr, Token.TokenType.LPAREN, Token.TokenType.IF));
		rules.put(new Pair(TreeNode.Label.ifstat, Token.TokenType.IF), rule8);


		/////////////////////////////////////////////////////////////


		List<Symbol> rule9p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.elseifstat, Token.TokenType.RBRACE, TreeNode.Label.los, Token.TokenType.LBRACE, TreeNode.Label.elseorelseif));
		rules.put(new Pair(TreeNode.Label.elseifstat, Token.TokenType.ELSE), rule9p1);
		//MAybe not RBRACE check later
		List<Symbol> rule9p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.elseifstat, Token.TokenType.RBRACE), rule9p2);


		/////////////////////////////////////////////////////////////


		List<Symbol> rule10 = new LinkedList<>(Arrays.asList(TreeNode.Label.possif, Token.TokenType.ELSE));
		rules.put(new Pair(TreeNode.Label.elseorelseif, Token.TokenType.ELSE), rule10);


		/////////////////////////////////////////////////////////////


		List<Symbol> rule11p1 = new LinkedList<>(Arrays.asList(Token.TokenType.RPAREN, TreeNode.Label.boolexpr, TreeNode.Label.relexpr, Token.TokenType.LPAREN, Token.TokenType.IF));
		rules.put(new Pair(TreeNode.Label.possif, Token.TokenType.IF), rule11p1);
		//MAybe double check later
		List<Symbol> rule11p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.possif, Token.TokenType.LBRACE), rule11p2);


		/////////////////////////////////////////////////////////////



		List<Symbol> rule12 = new LinkedList<>(Arrays.asList(TreeNode.Label.expr, Token.TokenType.ASSIGN, Token.TokenType.ID));
		rules.put(new Pair(TreeNode.Label.assign, Token.TokenType.ID), rule12);


		/////////////////////////////////////////////////////////////



		List<Symbol> rule13 = new LinkedList<>(Arrays.asList(TreeNode.Label.possassign, Token.TokenType.ID, TreeNode.Label.type));
		rules.put(new Pair(TreeNode.Label.decl, Token.TokenType.TYPE), rule13);


		/////////////////////////////////////////////////////////////



		List<Symbol> rule14p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.expr, Token.TokenType.ASSIGN));
		rules.put(new Pair(TreeNode.Label.possassign, Token.TokenType.ASSIGN), rule14p1);
		//Double check this bs
		List<Symbol> rule14p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.possassign, Token.TokenType.SEMICOLON), rule14p2);

		List<Symbol> rule15 = new LinkedList<>(Arrays.asList(Token.TokenType.RPAREN, TreeNode.Label.printexpr, Token.TokenType.LPAREN, Token.TokenType.PRINT));
		rules.put(new Pair(TreeNode.Label.print, Token.TokenType.PRINT), rule15);


		/////////////////////////////////////////////////////////////



		List<Symbol> rule16 = new LinkedList<>(Arrays.asList(Token.TokenType.TYPE));
		rules.put(new Pair(TreeNode.Label.type, Token.TokenType.TYPE), rule16);


		/////////////////////////////////////////////////////////////



		//Double check later xoxo
		List<Symbol> rule17p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.expr, Token.TokenType.LPAREN), rule17p1);

		List<Symbol> rule17p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.expr, Token.TokenType.ID), rule17p2);

		List<Symbol> rule17p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.expr, Token.TokenType.NUM), rule17p3);

		List<Symbol> rule17p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.expr, Token.TokenType.TRUE), rule17p4);

		List<Symbol> rule17p5 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.expr, Token.TokenType.FALSE), rule17p5);

		List<Symbol> rule17p6 = new LinkedList<>(Arrays.asList(TreeNode.Label.charexpr));
		rules.put(new Pair(TreeNode.Label.expr, Token.TokenType.SQUOTE), rule17p6);



		//////////////////////////////////////////////



		List<Symbol> rule18 = new LinkedList<>(Arrays.asList(Token.TokenType.SQUOTE, Token.TokenType.CHARLIT, Token.TokenType.SQUOTE));
		rules.put(new Pair(TreeNode.Label.charexpr, Token.TokenType.SQUOTE), rule18);


		//////////////////////////////////////////////



		List<Symbol> rule19p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr, TreeNode.Label.boolop));
		rules.put(new Pair(TreeNode.Label.boolexpr, Token.TokenType.EQUAL), rule19p1);

		List<Symbol> rule19p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr, TreeNode.Label.boolop));
		rules.put(new Pair(TreeNode.Label.boolexpr, Token.TokenType.NEQUAL), rule19p2);

		List<Symbol> rule19p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr, TreeNode.Label.boolop));
		rules.put(new Pair(TreeNode.Label.boolexpr, Token.TokenType.AND), rule19p3);

		List<Symbol> rule19p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr, TreeNode.Label.boolop));
		rules.put(new Pair(TreeNode.Label.boolexpr, Token.TokenType.OR), rule19p4);

		List<Symbol> rule19p5 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.boolexpr, Token.TokenType.SEMICOLON), rule19p5);

		List<Symbol> rule19p6 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.boolexpr, Token.TokenType.RPAREN), rule19p6);


		////////////////////////////////////////


		List<Symbol> rule20p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.booleq));
		rules.put(new Pair(TreeNode.Label.boolop, Token.TokenType.EQUAL), rule20p1);

		List<Symbol> rule20p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.booleq));
		rules.put(new Pair(TreeNode.Label.boolop, Token.TokenType.NEQUAL), rule20p2);

		List<Symbol> rule20p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.boollog));
		rules.put(new Pair(TreeNode.Label.boolop, Token.TokenType.AND), rule20p3);

		List<Symbol> rule20p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.boollog));
		rules.put(new Pair(TreeNode.Label.boolop, Token.TokenType.OR), rule20p4);


		//////////////////////////////////////


		List<Symbol> rule21p1 = new LinkedList<>(Arrays.asList(Token.TokenType.EQUAL));
		rules.put(new Pair(TreeNode.Label.booleq, Token.TokenType.EQUAL), rule21p1);

		List<Symbol> rule21p2 = new LinkedList<>(Arrays.asList(Token.TokenType.NEQUAL));
		rules.put(new Pair(TreeNode.Label.booleq, Token.TokenType.NEQUAL), rule21p2);


		////////////////////////////////////


		List<Symbol> rule22p1 = new LinkedList<>(Arrays.asList(Token.TokenType.AND));
		rules.put(new Pair(TreeNode.Label.boollog, Token.TokenType.AND), rule22p1);

		List<Symbol> rule22p2 = new LinkedList<>(Arrays.asList(Token.TokenType.OR));
		rules.put(new Pair(TreeNode.Label.boollog, Token.TokenType.OR), rule22p2);



		List<Symbol> rule23p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.relexprprime,TreeNode.Label.arithexpr));
		rules.put(new Pair(TreeNode.Label.relexpr, Token.TokenType.LPAREN), rule23p1);

		List<Symbol> rule23p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.relexprprime,TreeNode.Label.arithexpr));
		rules.put(new Pair(TreeNode.Label.relexpr, Token.TokenType.ID), rule23p2);

		List<Symbol> rule23p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.relexprprime,TreeNode.Label.arithexpr));
		rules.put(new Pair(TreeNode.Label.relexpr, Token.TokenType.NUM), rule23p3);

		List<Symbol> rule23p4 = new LinkedList<>(Arrays.asList(Token.TokenType.TRUE));
		rules.put(new Pair(TreeNode.Label.relexpr, Token.TokenType.TRUE), rule23p4);

		List<Symbol> rule23p5 = new LinkedList<>(Arrays.asList(Token.TokenType.FALSE));
		rules.put(new Pair(TreeNode.Label.relexpr, Token.TokenType.FALSE), rule23p5);

		//////////////////////////////////////////////


		//Double check ///////////////////////////
		List<Symbol> rule24p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexpr,TreeNode.Label.relop));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.LT), rule24p1);

		List<Symbol> rule24p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexpr,TreeNode.Label.relop));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.LE), rule24p2);

		List<Symbol> rule24p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexpr,TreeNode.Label.relop));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.GT), rule24p3);

		List<Symbol> rule24p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexpr,TreeNode.Label.relop));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.GE), rule24p4);

		List<Symbol> rule24p5 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.EQUAL), rule24p5);

		List<Symbol> rule24p6 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.NEQUAL), rule24p6);

		List<Symbol> rule24p7 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.AND), rule24p7);

		List<Symbol> rule24p8 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.OR), rule24p8);

		List<Symbol> rule24p9 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.SEMICOLON), rule24p9);

		List<Symbol> rule24p10 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.relexprprime, Token.TokenType.RPAREN), rule24p10);


		////////////////////////////////////////



		List<Symbol> rule25p1 = new LinkedList<>(Arrays.asList(Token.TokenType.LT));
		rules.put(new Pair(TreeNode.Label.relop, Token.TokenType.LT), rule25p1);

		List<Symbol> rule25p2 = new LinkedList<>(Arrays.asList(Token.TokenType.LE));
		rules.put(new Pair(TreeNode.Label.relop, Token.TokenType.LE), rule25p2);

		List<Symbol> rule25p3 = new LinkedList<>(Arrays.asList(Token.TokenType.GT));
		rules.put(new Pair(TreeNode.Label.relop, Token.TokenType.GT), rule25p3);

		List<Symbol> rule25p4 = new LinkedList<>(Arrays.asList(Token.TokenType.GE));
		rules.put(new Pair(TreeNode.Label.relop ,Token.TokenType.GE), rule25p4);


		////////////////////////////////////////////


		List<Symbol> rule26p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexprprime,TreeNode.Label.term));
		rules.put(new Pair(TreeNode.Label.arithexpr, Token.TokenType.LPAREN), rule26p1);

		List<Symbol> rule26p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexprprime,TreeNode.Label.term));
		rules.put(new Pair(TreeNode.Label.arithexpr, Token.TokenType.ID), rule26p2);

		List<Symbol> rule26p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexprprime,TreeNode.Label.term));
		rules.put(new Pair(TreeNode.Label.arithexpr, Token.TokenType.NUM), rule26p3);


		///////////////////////////////


		List<Symbol> rule27p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexprprime, TreeNode.Label.term, Token.TokenType.PLUS));
		rules.put(new Pair(TreeNode.Label.arithexprprime, Token.TokenType.PLUS), rule27p1);

		List<Symbol> rule27p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.arithexprprime, TreeNode.Label.term, Token.TokenType.MINUS));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.MINUS), rule27p2);

		List<Symbol> rule27p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.RPAREN), rule27p3);

		List<Symbol> rule27p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.SEMICOLON), rule27p4);

		List<Symbol> rule27p5 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.EQUAL), rule27p5);

		List<Symbol> rule27p6 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.NEQUAL), rule27p6);

		List<Symbol> rule27p7 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.AND), rule27p7);

		List<Symbol> rule27p8 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.OR), rule27p8);

		List<Symbol> rule27p9 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.LT), rule27p9);

		List<Symbol> rule27p10 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.LE), rule27p10);

		List<Symbol> rule27p11 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.GT), rule27p11);

		List<Symbol> rule27p12 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.arithexprprime , Token.TokenType.GE), rule27p12);


		////////////////////////////////////////


		List<Symbol> rule28p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.termprime, TreeNode.Label.factor));
		rules.put(new Pair(TreeNode.Label.term, Token.TokenType.LPAREN), rule28p1);

		List<Symbol> rule28p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.termprime, TreeNode.Label.factor));
		rules.put(new Pair(TreeNode.Label.term, Token.TokenType.ID), rule28p2);

		List<Symbol> rule28p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.termprime, TreeNode.Label.factor));
		rules.put(new Pair(TreeNode.Label.term, Token.TokenType.NUM), rule28p3);


		//////////////////////////////////////


		//DOuble check
		List<Symbol> rule29p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.termprime, TreeNode.Label.factor, Token.TokenType.TIMES));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.TIMES), rule29p1);

		List<Symbol> rule29p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.termprime, TreeNode.Label.factor, Token.TokenType.DIVIDE));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.DIVIDE), rule29p2);

		List<Symbol> rule29p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.termprime, TreeNode.Label.factor, Token.TokenType.MOD));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.MOD), rule29p3);

		List<Symbol> rule29p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.RPAREN), rule29p4);

		List<Symbol> rule29p5 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.SEMICOLON), rule29p5);

		List<Symbol> rule29p6 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.EQUAL), rule29p6);

		List<Symbol> rule29p7 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.NEQUAL), rule29p7);

		List<Symbol> rule29p8 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.AND), rule29p8);

		List<Symbol> rule29p9 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.OR), rule29p9);

		List<Symbol> rule29p10 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.LT), rule29p10);

		List<Symbol> rule29p11 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.LE), rule29p11);

		List<Symbol> rule29p12 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.GT), rule29p12);

		List<Symbol> rule29p13 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.GE), rule29p13);

		List<Symbol> rule29p14 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.PLUS), rule29p14);

		List<Symbol> rule29p15 = new LinkedList<>(Arrays.asList(TreeNode.Label.epsilon));
		rules.put(new Pair(TreeNode.Label.termprime, Token.TokenType.MINUS), rule29p15);


		////////////////////////////////


		List<Symbol> rule30p1 = new LinkedList<>(Arrays.asList(Token.TokenType.RPAREN, TreeNode.Label.arithexpr, Token.TokenType.LPAREN));
		rules.put(new Pair(TreeNode.Label.factor, Token.TokenType.LPAREN), rule30p1);

		List<Symbol> rule30p2 = new LinkedList<>(Arrays.asList(Token.TokenType.ID));
		rules.put(new Pair(TreeNode.Label.factor, Token.TokenType.ID), rule30p2);

		List<Symbol> rule30p3 = new LinkedList<>(Arrays.asList(Token.TokenType.NUM));
		rules.put(new Pair(TreeNode.Label.factor, Token.TokenType.NUM), rule30p3);



		//////////////////////////////////////////////


		List<Symbol> rule31p1 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.printexpr, Token.TokenType.LPAREN), rule31p1);

		List<Symbol> rule31p2 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.printexpr, Token.TokenType.ID), rule31p2);

		List<Symbol> rule31p3 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.printexpr, Token.TokenType.NUM), rule31p3);

		List<Symbol> rule31p4 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.printexpr, Token.TokenType.TRUE), rule31p4);

		List<Symbol> rule31p5 = new LinkedList<>(Arrays.asList(TreeNode.Label.boolexpr, TreeNode.Label.relexpr));
		rules.put(new Pair(TreeNode.Label.printexpr, Token.TokenType.FALSE), rule31p5);

		List<Symbol> rule31p6 = new LinkedList<>(Arrays.asList(Token.TokenType.DQUOTE, Token.TokenType.STRINGLIT, Token.TokenType.DQUOTE));
		rules.put(new Pair(TreeNode.Label.printexpr, Token.TokenType.DQUOTE), rule31p6);




		parentStack.push(prog);
		stack.push(new Pair(TreeNode.Label.prog, prog));
		int listSize = tokens.size();
		int i = 0;
		int count = 0;
		while (listSize > i){
			Token token = tokens.get(i);
			TreeNode currentParent = parentStack.peek();

			if(stack.peek().fst().isVariable()) {
				parentStack.push(stack.peek().snd());
				if(tree.getRoot() == null) {
					tree.setRoot(currentParent);
				}
				else if(stack.peek().fst() != TreeNode.Label.epsilon){
					TreeNode variable = new TreeNode(TreeNode.Label.valueOf(stack.peek().fst().toString()), stack.peek().snd());
					stack.peek().snd().addChild(variable);
					parentStack.push(variable);

				}
				else if(stack.peek().fst() == TreeNode.Label.epsilon) {
					TreeNode epsilon = new TreeNode(TreeNode.Label.epsilon, currentParent);
					currentParent.addChild(epsilon);
					stack.pop();
				}
				if(rules.containsKey(new Pair(stack.peek().fst(), token.getType()))) {
					rules.get(new Pair(stack.pop().fst(), token.getType())).forEach(child -> { 
						stack.push(new Pair(child, parentStack.peek()));
					});
				}

			}
			if(!stack.peek().fst().isVariable()) {
				if(stack.peek().fst().toString() == token.getType().toString()) {
					stack.peek().snd().addChild(new TreeNode(TreeNode.Label.terminal, token, stack.pop().snd()));
					i++;
				}
			}
			count++;
			if(count == 50) break;
			
		}
		 if(stack.size() > 0) throw new SyntaxException(tokens.toString());
		return tree;
	}
}


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
