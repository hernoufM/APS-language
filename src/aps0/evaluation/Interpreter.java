package aps0.evaluation;

import java.util.ArrayList;
import java.util.List;

import aps0.ast.AstArgument;
import aps0.ast.AstCommand;
import aps0.ast.AstProgramme;
import aps0.ast.declaration.AstConst;
import aps0.ast.declaration.AstDeclaration;
import aps0.ast.declaration.AstFun;
import aps0.ast.declaration.AstFunRec;
import aps0.ast.expression.AstApplication;
import aps0.ast.expression.AstBool;
import aps0.ast.expression.AstExpression;
import aps0.ast.expression.AstIf;
import aps0.ast.expression.AstLambda;
import aps0.ast.expression.AstNum;
import aps0.ast.expression.AstOp;
import aps0.ast.expression.AstVar;
import aps0.ast.statement.AstEcho;
import aps0.ast.statement.AstStatement;
import aps0.ast.type.AstBoolType;
import aps0.ast.type.AstFuncType;
import aps0.ast.type.AstIntType;
import aps0.evaluation.environment.EnvironnementInit;
import aps0.evaluation.value.Function;
import aps0.evaluation.value.FunctionRec;
import aps0.evaluation.value.Number;
import aps0.evaluation.value.Value;
import aps0.evaluation.value.operation.Operation;
import aps0.interfaces.IEnvironment;

public class Interpreter implements aps0.interfaces.IAstVisitor<Object, IEnvironment, Exception> {

	@Override
	public Object visit(AstApplication ast, IEnvironment env) throws Exception {
		AstExpression invoc = ast.getInvocable();
		Object obj = invoc.accept(this, env);
		
		if (obj instanceof Function) {
			Function func = (Function) obj;
			List<Value> params = new ArrayList<Value>();
			for(AstExpression expr : ast.getExpressions()) {
				Value val = (Value) expr.accept(this, env);
				params.add(val);
			}
			return func.apply(params, this);
		} else {
			throw new Exception("Application to the not functional value");
		}
	}

	@Override
	public Object visit(AstArgument ast, IEnvironment env) throws Exception {
		return null;
	}

	@Override
	public Object visit(AstBool ast, IEnvironment env) throws Exception {
		if (ast.getBool())
			return new Number(1);
		else
			return new Number(0);
	}

	@Override
	public Object visit(AstBoolType ast, IEnvironment env) throws Exception {
		return null;
	}

	@Override
	public Object visit(AstConst ast, IEnvironment env) throws Exception {
		Object obj = ast.getExpression().accept(this, env);
		if(obj instanceof Value) {
			Value val = (Value) obj;
			return env.extend(ast.getName(), val);
		}
		else {
			throw new Exception("Resultat is not value");
		}
	}

	@Override
	public Object visit(AstEcho ast, IEnvironment env) throws Exception {
		Object obj = ast.getExpr().accept(this, env);
		if(obj instanceof Number) {
			Number n = (Number) obj;
			System.out.println(n.getVal());
		}
		else {
			throw new Exception("Echo of value different from number");
		}
		return null;
	}

	@Override
	public Object visit(AstFun ast, IEnvironment env) throws Exception {
		Function func = new Function(ast.getBody(), ast.getArguments(), env);
		return env.extend(ast.getName(), func);
	}

	@Override
	public Object visit(AstFuncType ast, IEnvironment env) throws Exception {
		return null;
	}

	@Override
	public Object visit(AstFunRec ast, IEnvironment env) throws Exception {
		FunctionRec funcrec = new FunctionRec(ast.getBody(), ast.getArguments(), env, ast.getName());
		return env.extend(ast.getName(), funcrec);
	}

	@Override
	public Object visit(AstIf ast, IEnvironment env) throws Exception {
		Number cond = (Number) ast.getCondition().accept(this, env);
		if (cond.getVal() == 1) {
			return ast.getConsequence().accept(this, env);
		}
		return ast.getAlternative().accept(this, env);
	}

	@Override
	public Object visit(AstIntType ast, IEnvironment env) throws Exception {
		return null;
	}

	@Override
	public Object visit(AstLambda ast, IEnvironment env) throws Exception {
		return new Function(ast.getBody(), ast.getArguments(), env);
	}

	@Override
	public Object visit(AstNum ast, IEnvironment env) throws Exception {
		return new Number(ast.getInteger());
	}

	@Override
	public Object visit(AstOp ast, IEnvironment env) throws Exception {
		List<AstExpression> exprs = ast.getExpressions();

		Value val = env.find(ast.getOperation());
		if (!(val instanceof Operation)) {
			throw new Exception("Operation " + ast.getOperation() + " is not supported");
		}
		Operation op = (Operation) val;

		List<Number> params = new ArrayList<Number>();
		for (AstExpression expr : exprs) {
			Object obj = expr.accept(this, env);
			Number param = (Number) obj;
			params.add(param);
		}

		return op.apply(params);
	}

	@Override
	public Object visit(AstProgramme ast, IEnvironment env) throws Exception {
		IEnvironment courrant_env = EnvironnementInit.constructInitialEnvironement();
		for(AstCommand command : ast.getCommands()) {
			if(command instanceof AstDeclaration) {
				AstDeclaration dec = (AstDeclaration) command;
				courrant_env = (IEnvironment) dec.accept(this, courrant_env);
			}
			else {
				AstStatement stat = (AstStatement) command;
				stat.accept(this, courrant_env);
			}
		}
		return null;
	}

	@Override
	public Object visit(AstVar ast, IEnvironment env) throws Exception {
		return env.find(ast.getName());
	}
}
