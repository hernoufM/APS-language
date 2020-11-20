package aps0.evaluation.value;

import java.util.List;

import aps0.ast.AstArgument;
import aps0.ast.expression.AstExpression;
import aps0.evaluation.Interpreter;
import aps0.interfaces.IEnvironment;

public class FunctionRec extends Function {

	private String functionName;
	
	public FunctionRec(AstExpression body, List<AstArgument> argumentNames, IEnvironment env, String functionName) {
		super(body,argumentNames, env);
		this.functionName = functionName;
	}
	
	public String getFunctionName() {
		return functionName;
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
		env2 = env2.extend(functionName, this);
		return getBody().accept(interp, env2);
	}
	
}
