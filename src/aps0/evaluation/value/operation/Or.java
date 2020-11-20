package aps0.evaluation.value.operation;

import aps0.evaluation.value.Number;

public class Or extends BinaryOperation {

	@Override
	public Number apply(Number op1, Number op2) {
		return new Number(Math.max(op1.getVal(), op2.getVal()));
	}

}
