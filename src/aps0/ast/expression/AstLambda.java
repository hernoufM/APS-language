package aps0.ast.expression;

import java.util.List;

import aps0.ast.AstArgument;
import aps0.interfaces.IAstVisitor;

/**
 * AstLambda
 */
public class AstLambda extends AstExpression{
    private List<AstArgument> arguments;
    private AstExpression body;

    public AstLambda(List<AstArgument> arguments, AstExpression body){
        this.arguments=arguments;
        this.body=body;
    }

    public List<AstArgument> getArguments(){
        return arguments;
    }

    public AstExpression getBody(){
        return body;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}