package aps0.ast.expression;

import aps0.interfaces.IAstVisitor;

/**
 * AstVar
 */
public class AstVar extends AstExpression {
    
    private String name;

    public AstVar(String n){
        name=n;
    }

    public String getName(){
        return name;
    }
    
    public String toProlog() {
    	return "var("+name+")";
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}