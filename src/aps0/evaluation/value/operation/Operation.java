package aps0.evaluation.value.operation;

import java.util.List;

import aps0.evaluation.value.Number;
import aps0.evaluation.value.Value;
import aps0.interfaces.Invocable;

public abstract class Operation extends Value implements Invocable {

	public Number apply(Number op) throws Exception {
		throw new Exception("Operation is not allowed with this number of arguments");
	}

	public Number apply(Number op1, Number op2) throws Exception {
		throw new Exception("Operation is not allowed with this number of arguments");
	}

	public Number apply(List<Number> operands) throws Exception {
		if (getArity() == operands.size()) {
			switch (getArity()) {
			case 1:
				return apply(operands.get(0));
			case 2:
				return apply(operands.get(0), operands.get(1));
			default:
				throw new Exception("Erroneous number of arguments");
			}
		} else {
			throw new Exception("Operation is not allowed with this number of arguments");
		}
	}
}
