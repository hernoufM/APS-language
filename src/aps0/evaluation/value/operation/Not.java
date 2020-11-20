package aps0.evaluation.value.operation;

import aps0.evaluation.value.Number;

public class Not extends UnaryOperation {

	@Override
	public Number apply(Number op) {
		if (op.getVal()==0) 
			return new Number(1);
		else
			return new Number(0);
	}

}
