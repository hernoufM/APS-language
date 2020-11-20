package aps1.ast.type;

import aps0.ast.type.AstType;
import aps0.interfaces.IAstVisitor;

public class AstVoidType extends AstType {

	@Override
	public <Result, Data, Exc extends Throwable> Result accept(IAstVisitor<Result, Data, Exc> visitor, Data data)
			throws Exc {
		return ((aps1.interfaces.IAstVisitor<Result, Data, Exc>) visitor).visit(this, data);
	}

}
