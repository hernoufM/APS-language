package aps1.evaluation.value;

import java.util.List;

import aps0.ast.AstArgument;
import aps1.evaluation.Interpreter;
import aps0.evaluation.value.Value;
import aps0.interfaces.IEnvironment;
import aps1.ast.AstBlock;

public class ProcedureRec extends Procedure {
	
	private String procedureName;
	
	public ProcedureRec(AstBlock body, List<AstArgument> argumentNames, IEnvironment env, String procedureName) {
		super(body,argumentNames, env);
		this.procedureName = procedureName;
	}
	
	public String getProcedureName() {
		return procedureName;
	}

	public Object apply(List<Value> parametres, Interpreter interp) throws Exception {
		IEnvironment env2 = getEnv().copyEnvironment();
		if(getArity() != parametres.size()) {
			throw new Exception("Wrong arity");
		}
		for (int i =0; i< getArity(); i++) {
			String arg = getArgumentNames().get(i).getName();
			Value val = parametres.get(i); 
			env2 = env2.extend(arg, val);
		}
		env2 = env2.extend(procedureName, this);
		return getBody().accept(interp, env2);
	}
}
