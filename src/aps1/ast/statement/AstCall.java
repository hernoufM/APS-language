package aps1.ast.statement;

import java.util.List;

import aps0.ast.expression.AstExpression;
import aps0.ast.statement.AstStatement;
import aps0.interfaces.IAstVisitor;

public class AstCall extends AstStatement {

	private String procName;
	private List<AstExpression> expressions;
	
	public AstCall(String procName, List<AstExpression> expressions) {
		this.procName = procName;
		this.expressions = expressions;
	}
	
	public String getProcName() {
		return procName;
	}
	
	public List<AstExpression> getExpressions(){
		return expressions;
	}
	
	@Override
	public <Result, Data, Exc extends Throwable> Result accept(IAstVisitor<Result, Data, Exc> visitor, Data data)
			throws Exc {
		return ((aps1.interfaces.IAstVisitor<Result, Data, Exc>) visitor).visit(this, data);
	}

}
