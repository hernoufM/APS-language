package aps1.evaluation;

import aps1.ast.AstBlock;
import aps1.ast.declaration.AstProc;
import aps1.ast.declaration.AstProcRec;
import aps1.ast.declaration.AstVarDec;
import aps1.ast.statement.AstCall;
import aps1.ast.statement.AstIfStat;
import aps1.ast.statement.AstSet;
import aps1.ast.statement.AstWhile;
import aps1.ast.type.AstVoidType;
import aps1.evaluation.memory.Memory;
import aps1.evaluation.value.Adress;
import aps1.evaluation.value.Procedure;
import aps1.evaluation.value.ProcedureRec;
import aps1.interfaces.IAstVisitor;
import aps1.interfaces.IMemory;

import java.util.ArrayList;
import java.util.List;

import aps0.ast.AstCommand;
import aps0.ast.declaration.AstDeclaration;
import aps0.ast.expression.AstExpression;
import aps0.ast.expression.AstVar;
import aps0.ast.statement.AstStatement;
import aps0.evaluation.value.Number;
import aps0.evaluation.value.Value;
import aps0.interfaces.IEnvironment;

public class Interpreter extends aps0.evaluation.Interpreter implements IAstVisitor<Object, IEnvironment, Exception> {

	private IMemory memory = new Memory();

	@Override
	public Object visit(AstVar ast, IEnvironment env) throws Exception {
		Value val = env.find(ast.getName());
		if (val instanceof Adress) {
			Adress adr = (Adress) val;
			return memory.get(adr);
		}
		return val;
	}

	@Override
	public Object visit(AstBlock ast, IEnvironment env) throws Exception {
		IEnvironment courrant_env = env;
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
	public Object visit(AstSet ast, IEnvironment env) throws Exception {
		Value val = env.find(ast.getVarName());
		if (val instanceof Adress) {
			Adress adr = (Adress) val;
			Number number = (Number) ast.getExpression().accept(this, env);
			memory.affect(adr, number);
		}
		return null;
	}

	@Override
	public Object visit(AstIfStat ast, IEnvironment env) throws Exception {
		Number cond = (Number) ast.getCondition().accept(this, env);
		if (cond.getVal() == 1) {
			return ast.getConsequence().accept(this, env);
		}
		return ast.getAlternative().accept(this, env);
	}

	@Override
	public Object visit(AstWhile ast, IEnvironment env) throws Exception {
		Number cond = (Number) ast.getCondition().accept(this, env);
		if (cond.getVal() == 1) {
			ast.getBody().accept(this, env);
			return ast.accept(this, env);
		}
		return null;
	}

	@Override
	public Object visit(AstCall ast, IEnvironment env) throws Exception {
		Value value = env.find(ast.getProcName());
		
		if (value instanceof Procedure) {
			Procedure proc = (Procedure) value;
			List<Value> params = new ArrayList<Value>();
			for(AstExpression expr : ast.getExpressions()) {
				Value val = (Value) expr.accept(this, env);
				params.add(val);
			}
			return proc.apply(params, this);
		} else {
			throw new Exception("Application to the not functional value");
		}
	}

	@Override
	public Object visit(AstVarDec ast, IEnvironment env) throws Exception {
		Adress adr = memory.alloc();
		return env.extend(ast.getName(), adr);
	}

	@Override
	public Object visit(AstProc ast, IEnvironment env) throws Exception {
		Procedure proc = new Procedure(ast.getBody(), ast.getArguments(), env);
		return env.extend(ast.getName(), proc);
	}

	@Override
	public Object visit(AstProcRec ast, IEnvironment env) throws Exception {
		ProcedureRec procRec = new ProcedureRec(ast.getBody(), ast.getArguments(), env, ast.getName());
		return env.extend(ast.getName(), procRec);
	}

	@Override
	public Object visit(AstVoidType ast, IEnvironment env) throws Exception {
		return null;
	}

}
