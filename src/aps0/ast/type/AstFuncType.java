package aps0.ast.type;

import java.util.List;

import aps0.interfaces.IAstVisitor;

/**
 * AstFuncType
 */
public class AstFuncType extends AstType{
    private List<AstType> typesArguments;
    private AstType typeResult;

    public AstFuncType(List<AstType> targs, AstType tr){
        typesArguments=targs;
        typeResult=tr;
    }

    public List<AstType> getTypesArguments(){
        return typesArguments;
    }

    public AstType getTypeResult(){
        return typeResult;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}