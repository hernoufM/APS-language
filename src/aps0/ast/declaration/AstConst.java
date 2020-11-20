package aps0.ast.declaration;

import aps0.ast.expression.AstExpression;
import aps0.ast.type.AstType;
import aps0.interfaces.IAstVisitor;

/**
 * AstConst
 */
public class AstConst extends AstDeclaration{

    private AstType type;
    private AstExpression expression;
    
    public AstConst(String name, AstType type, AstExpression expression){
        super(name);
        this.type = type;
        this.expression = expression;
    }
    
    public AstType getType(){
        return type;
    }

    public AstExpression getExpression(){
        return expression;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}