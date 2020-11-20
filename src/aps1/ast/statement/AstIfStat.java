package aps1.ast.statement;

import aps0.ast.expression.AstExpression;
import aps0.ast.statement.AstStatement;
import aps1.ast.*;
import aps0.interfaces.IAstVisitor;

public class AstIfStat extends AstStatement {

	private AstExpression condition;
	private AstBlock consequence;
	private AstBlock alternative;
	
	public AstIfStat(AstExpression cond, AstBlock cons, AstBlock alt){
        condition = cond;
        consequence = cons;
        alternative = alt;
    }

    public AstExpression getCondition(){
        return condition;
    }

    public AstBlock getConsequence(){
        return consequence;
    }

    public AstBlock getAlternative(){
        return alternative;
    }
	
	@Override
	public <Result, Data, Exc extends Throwable> Result accept(IAstVisitor<Result, Data, Exc> visitor, Data data)
			throws Exc {
		return ((aps1.interfaces.IAstVisitor<Result, Data, Exc>) visitor).visit(this, data);
	}

}
