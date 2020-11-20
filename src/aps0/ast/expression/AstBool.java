package aps0.ast.expression;

import aps0.interfaces.IAstVisitor;

/**
 * AstBool
 */
public class AstBool extends AstExpression {

    private Boolean bool;
    
    public AstBool(Boolean b){
        bool=b;
    }

    public Boolean getBool(){
        return bool;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}