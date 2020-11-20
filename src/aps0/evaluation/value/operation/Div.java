package aps0.evaluation.value.operation;

import aps0.evaluation.value.Number;

public class Div extends BinaryOperation {

	@Override
	public Number apply(Number op1, Number op2) throws Exception {
		if(op2.getVal() == 0) {
			throw new Exception("Division by 0");
		}
		return new Number(op1.getVal()/op2.getVal());
	}

}
