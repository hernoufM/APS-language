package aps0.ast.expression;

import java.util.List;

import aps0.interfaces.IAstVisitor;

/**
 * AstApplication
 */
public class AstApplication extends AstExpression{  
    private AstExpression invocable;
    private List<AstExpression> expressions;

    public AstApplication(AstExpression invocable, List<AstExpression> expressions){
        this.invocable=invocable;
        this.expressions=expressions;
    }

    public AstExpression getInvocable(){
        return invocable;
    }

    public List<AstExpression> getExpressions(){
        return expressions;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}