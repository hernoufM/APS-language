package aps0.ast;

import aps0.ast.type.AstType;
import aps0.interfaces.IAstVisitor;

/**
 * AstArgument
 */
public class AstArgument extends Ast {
    private String name;
    private AstType type;

    public AstArgument(String n, AstType t){
        name=n;
        type=t;
    }

    public String getName(){
        return name;
    }

    public AstType getType(){
        return type;   
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}