package aps0.evaluation.environment;

import aps0.evaluation.value.Value;
import aps0.interfaces.IEnvironment;

public class Environment implements IEnvironment {
   String varName;
   Value value;
   IEnvironment next;

   public Environment(String varName, Value value, IEnvironment next) {
       this.varName = varName;
       this.value = value;
       this.next = next;
   }

    public String getVarName() {
        return varName;
    }

    public Value getValue() {
        return value;
    }

    public IEnvironment getNext() {
        return next;
    }

    public IEnvironment extend(String varName, Value value){
        return  new Environment(varName, value,this);
    }

    public Value find(String varName) throws Exception {
        if(getVarName().equals(varName)) return getValue();
        else return next.find(varName);
    }

	@Override
	public IEnvironment copyEnvironment() {
		return new Environment(varName, value, next);
	}


}
