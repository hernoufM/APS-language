package aps0.ast;

import java.util.List;

import aps0.interfaces.IAstVisitor;

/**
 * AstProgramme
 */
public class AstProgramme extends Ast{

    private List<AstCommand> commands;

    public AstProgramme (List<AstCommand> cmds){
        commands = cmds;
    }

    public List<AstCommand> getCommands(){
        return commands;
    }
    
    public <Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc{
		return visitor.visit(this, data);
	}
}