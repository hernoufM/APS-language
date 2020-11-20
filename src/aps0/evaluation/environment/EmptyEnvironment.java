package aps0.evaluation.environment;

import aps0.evaluation.value.Value;
import aps0.interfaces.IEnvironment;

public class EmptyEnvironment implements IEnvironment {

    public Environment extend(String varName, Value value){
        return  new Environment(varName, value,this);
    }

    public Value find(String varName) throws Exception {
        throw new Exception(varName+ " is not in the environment");
    }

	@Override
	public IEnvironment copyEnvironment() {
		return new EmptyEnvironment();
	}
}
