package aps0.evaluation.value.operation;

import aps0.evaluation.value.Number;

public class Eq extends BinaryOperation {

	@Override
	public Number apply(Number op1, Number op2) {
		if (op1.getVal()==op2.getVal())
			return new Number(1);
		else
			return new Number(0);
	}

}
