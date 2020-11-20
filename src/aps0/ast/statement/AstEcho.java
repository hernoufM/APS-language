package aps0.ast.statement;

import aps0.ast.expression.AstExpression;
import aps0.interfaces.IAstVisitor;

/**
 * AstEcho
 */
public class AstEcho extends AstStatement{

    private AstExpression expr;

    public AstEcho(AstExpression e){
        expr=e;
    }

    public AstExpression getExpr(){
        return expr;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}