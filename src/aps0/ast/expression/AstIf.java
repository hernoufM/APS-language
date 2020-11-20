package aps0.ast.expression;

import aps0.interfaces.IAstVisitor;

/**
 * AstIf
 */
public class AstIf extends AstExpression{
    private AstExpression condition;
    private AstExpression consequence;
    private AstExpression alternative;

    public AstIf(AstExpression cond, AstExpression cons, AstExpression alt){
        condition = cond;
        consequence = cons;
        alternative = alt;
    }

    public AstExpression getCondition(){
        return condition;
    }

    public AstExpression getConsequence(){
        return consequence;
    }

    public AstExpression getAlternative(){
        return alternative;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}