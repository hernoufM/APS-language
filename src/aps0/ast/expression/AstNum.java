package aps0.ast.expression;

import aps0.interfaces.IAstVisitor;

/**
 * AstNum
 */
public class AstNum extends AstExpression {
    private Integer integer;

    public AstNum(Integer i){
        integer=i;
    }

    public Integer getInteger(){
        return integer;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}