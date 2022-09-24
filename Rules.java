public class Rules {
  //RULES

//rule #1
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
//rule # 2
if(stack.peek().getLabel() == TreeNode.Label.los) {
	if(token != RBRACE) {
	stack.push(new TreeNode(TreeNode.Label.los, currentParent));
	stack.push(new TreeNode(TreeNode.Label.stat, currentParent));
	}
	else {
	TreeNode los = new TreeNode(stack.pop().getLabel(), currentParent);
	currentParent.addChild(los);
    los.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));
	}
}
//rule # 3 
if(stack.peek().getLabel() == TreeNode.Label.stat) {
	if(token == WHILE) {
	stack.push(new TreeNode(TreeNode.Label.WHILE, currentParent));
	}
	if(token == FOR) {
	stack.push(new TreeNode(TreeNode.Label.FOR, currentParent));
	}
	if(token == IF) {
	stack.push(new TreeNode(TreeNode.Label.if, currentParent));
	}
	if(token == ID) {
	currentParent.addChild(new TreeNode(TreeNode.Label.Terminal, new Token(Token.TokenType.SEMICOLON), currentParent));
	stack.push(new TreeNode(TreeNode.Label.assign, currentParent));
	}
	if(token == TYPE) {
	currentParent.addChild(new TreeNode(TreeNode.Label.Terminal, new Token(Token.TokenType.SEMICOLON), currentParent));
	stack.push(new TreeNode(TreeNode.Label.decl, currentParent));
	}
	if(token == PRINT) {
	currentParent.addChild(new TreeNode(TreeNode.Label.Terminal, new Token(Token.TokenType.SEMICOLON), currentParent));
	stack.push(new TreeNode(TreeNode.Label.print, currentParent));
	}
	else {
	currentParent.addChild(new TreeNode(TreeNode.Label.Terminal, new Token(Token.TokenType.SEMICOLON), currentParent));
	}
}

// rule #4 
if(stack.peek().getLabel() == TreeNode.Label.WHILE) {
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.los, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RPAREN), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.boolexpr, currentParent));
	    stack.push(new TreeNode(TreeNode.Label.relexpr, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.WHILE), currentParent));
}

// rule #5
if(stack.peek().getLabel() == TreeNode.Label.FOR) {
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.los, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), currentParent));
        stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), currentParent));
         stack.push(new TreeNode(TreeNode.Label.forarith, currentParent));
        stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.SEMICOLON), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.boolexpr, currentParent));
	    stack.push(new TreeNode(TreeNode.Label.relexpr, currentParent));
        stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.SEMICOLON), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.forstart, currentParent));
        stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), currentParent));
        stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.FOR), currentParent));
}

// rule #6
if(stack.peek().getLabel() == TreeNode.Label.forstart) {
    if(token == TYPE) {
        stack.push(new TreeNode(TreeNode.Label.decl, currentParent));
    }
    if(token == ID) {
        stack.push(new TreeNode(TreeNode.Label.assign, currentParent));
    }
    else {
        TreeNode forstart = new TreeNode(stack.pop().getLabel(), currentParent);
	    currentParent.addChild(forstart);
        forstart.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));
    }
}

// rule #7
if(stack.peek().getLabel() == TreeNode.Label.forarith) {
    if(token == ARITHMETIC OPERATION) {
        stack.push(new TreeNode(TreeNode.Label.arithexpr, currentParent));
    }
    else {
        TreeNode forarith= new TreeNode(stack.pop().getLabel(), currentParent);
	    currentParent.addChild(forarith);
        forarith.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));       
    }
}

// rule #8
if(stack.peek().getLabel() == TreeNode.Label.IF) {
	    stack.push(new TreeNode(TreeNode.Label.elseifstat, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.los, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RPAREN), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.boolexpr, currentParent));
	    stack.push(new TreeNode(TreeNode.Label.relexpr, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.IF), currentParent));
}

// rule #9
if(stack.peek().getLabel() == TreeNode.Label.elseifstat) {
    if(not epsilon) {
	    stack.push(new TreeNode(TreeNode.Label.elseifstat, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RBRACE), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.los, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LBRACE), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.elseorelseif, currentParent));
    }
      else {
        TreeNode elseifstat = new TreeNode(stack.pop().getLabel(), currentParent);
	    currentParent.addChild(elseifstat);
        elseifstat.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));       
    }
}

// rule #10
if(stack.peek().getLabel() == TreeNode.Label.elseorelseif) {
	    stack.push(new TreeNode(TreeNode.Label.possif, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ELSE), currentParent));
}

// rule #11
if(stack.peek().getLabel() == TreeNode.Label.possif) {
    if(not epsilon) {
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RPAREN), currentParent));
	    stack.push(new TreeNode(TreeNode.Label.boolexpr, currentParent));
	    stack.push(new TreeNode(TreeNode.Label.relexpr, currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), currentParent));
    	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.IF), currentParent));
    }
    else {
        TreeNode possif = new TreeNode(stack.pop().getLabel(), currentParent);
	    currentParent.addChild(possif);
        possif.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));        
    }
}

// rule #12
if(stack.peek().getLabel() == TreeNode.Label.assign) {
    stack.push(new TreeNode(TreeNode.Label.expr, currentParent));
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.EQUAL), currentParent));
    stack.push(new TreeNode(TreeNode.Label.ID, currentParent));
}

// rule #13
if(stack.peek().getLabel() == TreeNode.Label.decl) {
    stack.push(new TreeNode(TreeNode.Label.possassign, currentParent));
    stack.push(new TreeNode(TreeNode.Label.ID, currentParent));
    stack.push(new TreeNode(TreeNode.Label.type, currentParent));
}

// rule #14
if(stack.peek().getLabel() == TreeNode.Label.possassign) {
    if(not epsilon) {
    stack.push(new TreeNode(TreeNode.Label.expr, currentParent));
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.EQUAL), currentParent));
    }
    else {
    TreeNode possassign = new TreeNode(stack.pop().getLabel(), currentParent);
	 currentParent.addChild(possassign);
     possassign.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent)); 
    }
}

// rule #15
if(stack.peek().getLabel() == TreeNode.Label.print) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RPAREN), currentParent));
    stack.push(new TreeNode(TreeNode.Label.printexpr, currentParent));
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), currentParent));
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PRINT), currentParent));
}

// rule #16
if(stack.peek().getLabel() == TreeNode.Label.TYPE) {
    if(token == int) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.num), currentParent));
    }
    if(token == boolean) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.bool), currentParent));
    }
    if(token == char) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.char), currentParent));
    }
}

// rule #17
if(stack.peek().getLabel() == TreeNode.Label.expr) {
    if(token == quote) {
    stack.push(new TreeNode(TreeNode.Label.charexpr, currentParent));
    }
    else {
    stack.push(new TreeNode(TreeNode.Label.boolexpr, currentParent));
    stack.push(new TreeNode(TreeNode.Label.relexpr, currentParent));
    }
}

// rule #18
if(stack.peek().getLabel() == TreeNode.Label.charexpr) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.DQUOTE), currentParent));
    stack.push(new TreeNode(TreeNode.Label.CHAR, currentParent));
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.DQUOTE), currentParent));
}

// rule #19
if(stack.peek().getLabel() == TreeNode.Label.boolexpr) {
    if(not epsilon) {
    stack.push(new TreeNode(TreeNode.Label.boolexpr, currentParent));
    stack.push(new TreeNode(TreeNode.Label.relexpr, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.boolop, currentParent));        
    }
    else {
     TreeNode boolexpr = new TreeNode(stack.pop().getLabel(), currentParent);
	 currentParent.addChild(boolexpr);
     boolexpr.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));  
    }
}

// rule #20
if(stack.peek().getLabel() == TreeNode.Label.boolop) {
    if(token == equals) {
    stack.push(new TreeNode(TreeNode.Label.booleq, currentParent));        
    }
    if(token == log) {
    stack.push(new TreeNode(TreeNode.Label.boollog, currentParent));        
    }
}

// rule #21
if(stack.peek().getLabel() == TreeNode.Label.booleq) {
    if(token == dequals) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.dequals), currentParent));
    }
    if(token == NEQUAL) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.NEQUAL), currentParent));
    }
}

// rule #22
if(stack.peek().getLabel() ==TreeNode.Label.boollog) {
    if(token == AND){
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.AND), currentParent));
    }
    if(token == OR){
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.OR), currentParent));
    }
}

// rule #23
if(stack.peek().getLabel() == TreeNode.Label.relexpr) {
    if(token == true) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.TRUE), currentParent));
    }
    if(token == false) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.FALSE), currentParent));
    }
    else {
    stack.push(new TreeNode(TreeNode.Label.relexprprime, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.arithexpr, currentParent));        
    }
}

// rule #24
if(stack.peek().getLabel() == TreeNode.Label.relexprprime) {
    if(not epsilon) {
    stack.push(new TreeNode(TreeNode.Label.arithexpr, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.relop, currentParent));        
    }
    else {
     TreeNode relexprprime = new TreeNode(stack.pop().getLabel(), currentParent);
	 currentParent.addChild(relexprprime);
     relexprprime.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));    
    }
}

// rule #25
if(stack.peek().getLabel() == TreeNode.Label.relop) {
    if(token == >=) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.GE), currentParent));
    }
    if(token == >) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.GT), currentParent));
    }
    if(token == <=) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LE), currentParent));
    }
    if(token == <) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LT), currentParent));
    }
}

// rule #26
if(stack.peek().getLabel() == TreeNode.Label.arithexpr) {
    stack.push(new TreeNode(TreeNode.Label.arithexprprime, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.term, currentParent));        
}

// rule #27
if(stack.peek().getLabel() == TreeNode.Label.arithexprprime) {
    if(token == +) {
    stack.push(new TreeNode(TreeNode.Label.arithexprprime, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.term, currentParent));        
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.PLUS), currentParent));
    }  
    if(token == -) {
    stack.push(new TreeNode(TreeNode.Label.arithexprprime, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.term, currentParent));        
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.MINUS), currentParent));
    }  
    else {
    TreeNode arithexprprime = new TreeNode(stack.pop().getLabel(), currentParent);
	currentParent.addChild(arithexprprime);
    arithexprprime.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));           
    }
}

// rule #28
if(stack.peek().getLabel() == TreeNode.Label.term) {
    stack.push(new TreeNode(TreeNode.Label.termprime, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.factor, currentParent));        
}

// rule #29
if(stack.peek().getLabel() == TreeNode.Label.termprime) {
    if(token == *) {
    stack.push(new TreeNode(TreeNode.Label.termprime, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.factor, currentParent));        
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.TIMES), currentParent));
    }
    if(token == /) {
    stack.push(new TreeNode(TreeNode.Label.termprime, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.factor, currentParent));        
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.DIVIDE), currentParent));
    }
    if(token == %) {
    stack.push(new TreeNode(TreeNode.Label.termprime, currentParent));        
    stack.push(new TreeNode(TreeNode.Label.factor, currentParent));        
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.MOD), currentParent));
    }
    else {
    TreeNode termprime = new TreeNode(stack.pop().getLabel(), currentParent);
	currentParent.addChild(termprime);
    termprime.addChild(new TreeNode(TreeNode.Label.epsilon, currentParent));        
    }
}

// rule #30
if(stack.peek().getLabel() == TreeNode.Label.factor) {
    if(token == num) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.NUM), currentParent));
    }
    if(token == ID) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.ID), currentParent));
    }
    else {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.RPAREN), currentParent));
    stack.push(new TreeNode(TreeNode.Label.arithexpr, currentParent));        
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.LPAREN), currentParent));
    }
}

// rule #31
if(stack.peek().getLabel == TreeNode.Label.printexpr) {
    if(token == dquote) {
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.DQUOTE), currentParent));
    stack.push(new TreeNode(TreeNode.Label.STRINGLIT, currentParent));        
 	stack.push(new TreeNode(TreeNode.Label.terminal, new Token(Token.TokenType.DQUOTE), currentParent));
    }
    else {
	stack.push(new TreeNode(TreeNode.Label.boolexpr, currentParent));
	stack.push(new TreeNode(TreeNode.Label.relexpr, currentParent));        
    }
}
}
