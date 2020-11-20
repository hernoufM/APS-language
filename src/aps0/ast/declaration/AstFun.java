package aps0.ast.declaration;

import java.util.List;

import aps0.ast.AstArgument;
import aps0.ast.expression.AstExpression;
import aps0.ast.type.AstType;
import aps0.interfaces.IAstVisitor;

/**
 * AstFun
 */
public class AstFun extends AstDeclaration{

    private AstType type;
	private List<AstArgument> arguments;
    private AstExpression body;
    
    public AstFun(String name, AstType type, List<AstArgument> args, AstExpression expr){
        super(name);
        this.type = type;
        this.body = expr;
        arguments=args;
    }

    public List<AstArgument> getArguments(){
        return arguments;
    }
    
    public AstType getType(){
        return type;
    }

    public AstExpression getBody(){
        return body;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}