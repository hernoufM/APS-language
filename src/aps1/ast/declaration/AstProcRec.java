package aps1.ast.declaration;

import java.util.List;

import aps0.ast.AstArgument;
import aps0.ast.declaration.AstDeclaration;
import aps0.interfaces.IAstVisitor;
import aps1.ast.AstBlock;

public class AstProcRec extends AstDeclaration {

	private List<AstArgument> arguments;
    private AstBlock body;
	
	public AstProcRec(String name, List<AstArgument> arguments, AstBlock body) {
		super(name);
		this.arguments = arguments;
		this.body = body;
	}

	public List<AstArgument> getArguments(){
        return arguments;
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
