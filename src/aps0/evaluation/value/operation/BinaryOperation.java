package aps0.evaluation.value.operation;

import aps0.evaluation.value.Number;

public abstract class BinaryOperation extends Operation {

	@Override
	public int getArity() {
		return 2;
	}
	
	public abstract Number apply(Number op1, Number op2) throws Exception;
	
}
