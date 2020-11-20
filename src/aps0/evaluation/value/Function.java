package aps0.evaluation.value;

import java.util.List;

import aps0.ast.AstArgument;
import aps0.ast.expression.AstExpression;
import aps0.evaluation.Interpreter;
import aps0.interfaces.IEnvironment;
import aps0.interfaces.Invocable;

public class Function extends Value implements Invocable{
	private AstExpression body;
	private List<AstArgument> argumentNames;
	private IEnvironment env;
	
	public Function(AstExpression body, List<AstArgument> argumentNames, IEnvironment env) {
		this.body = body;
		this.env = env;
		this.argumentNames = argumentNames;
	}
	
	public AstExpression getBody() {
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
