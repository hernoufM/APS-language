package aps1.ast.statement;

import aps0.ast.expression.AstExpression;
import aps0.ast.statement.AstStatement;
import aps0.interfaces.IAstVisitor;
import aps1.ast.AstBlock;

public class AstWhile extends AstStatement {

	private AstExpression condition;
	private AstBlock body;
	
	public AstWhile(AstExpression cond, AstBlock body){
        condition = cond;
        this.body = body;
    }

    public AstExpression getCondition(){
        return condition;
    }

    public AstBlock getBody(){
        return body;
    }

	
	@Override
	public <Result, Data, Exc extends Throwable> Result accept(IAstVisitor<Result, Data, Exc> visitor, Data data)
			throws Exc {
		return ((aps1.interfaces.IAstVisitor<Result, Data, Exc>) visitor).visit(this, data);
	}
}
