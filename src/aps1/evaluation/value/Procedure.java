package aps1.evaluation.value;

import java.util.List;

import aps0.ast.AstArgument;
import aps0.evaluation.value.Value;
import aps0.interfaces.IEnvironment;
import aps0.interfaces.Invocable;
import aps1.ast.AstBlock;
import aps1.evaluation.Interpreter;

public class Procedure extends Value implements Invocable {
	
	private AstBlock body;
	private List<AstArgument> argumentNames;
	private IEnvironment env;
	
	public Procedure(AstBlock body, List<AstArgument> argumentNames, IEnvironment env) {
		this.body = body;
		this.env = env;
		this.argumentNames = argumentNames;
	}
	
	public AstBlock getBody() {
		return body;
	}
	
	public IEnvironment getEnv() {
		return env;
	}
	
	public int getArity() {
		return argumentNames.size();
	}
	
	public List<AstArgument> getArgumentNames(){
		return argumentNames;
	}
	
	public Object apply(List<Value> parametres, Interpreter interp) throws Exception {
		IEnvironment env2 = env.copyEnvironment();
		if(getArity() != parametres.size()) {
			throw new Exception("Wrong arity");
		}
		for (int i =0; i< getArity(); i++) {
			String arg = argumentNames.get(i).getName();
			Value val = parametres.get(i); 
			env2 = env2.extend(arg, val);
		}
		return body.accept(interp, env2);
	}

}
