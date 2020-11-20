package aps0.evaluation.value.operation;

import aps0.evaluation.value.Number;

public abstract class UnaryOperation extends Operation {

	@Override
	public int getArity() {
		return 1;
	}
	
	public abstract Number apply(Number op) throws Exception;
	
}
