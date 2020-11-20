package aps0.ast.expression;

import java.util.List;

import aps0.interfaces.IAstVisitor;

/**
 * AstOp
 */
public class AstOp extends AstExpression {
    private String operation;
    private List<AstExpression> expressions;

    public AstOp(String operation, List<AstExpression> expressions){
        this.operation = operation;
        this.expressions = expressions;
    }

    public String getOperation(){
        return operation;
    }

    public List<AstExpression> getExpressions(){
        return expressions;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}