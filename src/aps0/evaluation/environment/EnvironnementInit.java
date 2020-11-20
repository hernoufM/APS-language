package aps0.evaluation.environment;

import aps0.evaluation.value.Number;
import aps0.evaluation.value.operation.Add;
import aps0.evaluation.value.operation.And;
import aps0.evaluation.value.operation.Div;
import aps0.evaluation.value.operation.Eq;
import aps0.evaluation.value.operation.Lt;
import aps0.evaluation.value.operation.Mul;
import aps0.evaluation.value.operation.Not;
import aps0.evaluation.value.operation.Or;
import aps0.evaluation.value.operation.Sub;
import aps0.interfaces.IEnvironment;

public class EnvironnementInit {
	
	public static IEnvironment constructInitialEnvironement() {
		IEnvironment env = new EmptyEnvironment();
		env = env.extend("true", new Number(1));
		env = env.extend("false", new Number(0));
		env = env.extend("add", new Add());
		env = env.extend("and", new And());
		env = env.extend("div", new Div());
		env = env.extend("eq", new Eq());
		env = env.extend("lt", new Lt());
		env = env.extend("mul", new Mul());
		env = env.extend("not", new Not());
		env = env.extend("eq", new Eq());
		env = env.extend("or", new Or());
		env = env.extend("sub", new Sub());
		return env;
	}
}
