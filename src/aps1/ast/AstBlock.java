package aps1.ast;

import java.util.List;

import aps0.ast.Ast;
import aps0.ast.AstCommand;
import aps0.interfaces.IAstVisitor;

public class AstBlock extends Ast {

	private List<AstCommand> commands;
	
	public AstBlock(List<AstCommand> commands) {
		this.commands = commands;
	}
	
	public List<AstCommand> getCommands(){
		return commands;
	}
	
	@Override
	public <Result, Data, Exc extends Throwable> Result accept(IAstVisitor<Result, Data, Exc> visitor, Data data)
			throws Exc {
		return ((aps1.interfaces.IAstVisitor<Result, Data, Exc>) visitor).visit(this, data);
	}

}
