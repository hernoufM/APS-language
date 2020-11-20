package aps1.ast.statement;

import aps0.ast.expression.AstExpression;
import aps0.ast.statement.AstStatement;
import aps0.interfaces.IAstVisitor;

public class AstSet extends AstStatement {
	
	private String varName;
	private AstExpression expression;
	
	public AstSet(String varName, AstExpression expr) {
		this.varName = varName;
		this.expression = expr;
	}

	public String getVarName() {
		return varName;
	}
	
	public AstExpression getExpression() {
		return expression;
	}
	
	@Override
	public <Result, Data, Exc extends Throwable> Result accept(IAstVisitor<Result, Data, Exc> visitor, Data data)
			throws Exc {
		return ((aps1.interfaces.IAstVisitor<Result, Data, Exc>) visitor).visit(this, data);
	}

}
