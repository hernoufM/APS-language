package aps0.ast.type;

import aps0.interfaces.IAstVisitor;

/**
 * AstBoolType
 */
public class AstBoolType extends AstType{

	public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}