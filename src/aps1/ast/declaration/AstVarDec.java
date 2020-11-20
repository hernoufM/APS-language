package aps1.ast.declaration;

import aps0.ast.declaration.AstDeclaration;
import aps0.ast.type.AstType;
import aps0.interfaces.IAstVisitor;

public class AstVarDec extends AstDeclaration {

	private AstType type;
	
	public AstVarDec(String varName, AstType type) {
		super(varName);
		this.type = type;
	}
	
	public AstType getType() {
		return type;
	}
	
	@Override
	public <Result, Data, Exc extends Throwable> Result accept(IAstVisitor<Result, Data, Exc> visitor, Data data)
			throws Exc {
		return ((aps1.interfaces.IAstVisitor<Result, Data, Exc>) visitor).visit(this, data);
	}

}
