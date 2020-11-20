package aps1.typage;

import aps0.ast.AstArgument;
import aps0.ast.AstCommand;
import aps0.ast.expression.AstExpression;
import aps1.ast.AstBlock;
import aps1.ast.declaration.AstProc;
import aps1.ast.declaration.AstProcRec;
import aps1.ast.declaration.AstVarDec;
import aps1.ast.statement.AstCall;
import aps1.ast.statement.AstIfStat;
import aps1.ast.statement.AstSet;
import aps1.ast.statement.AstWhile;
import aps1.ast.type.AstVoidType;
import aps1.interfaces.IAstVisitor;

public class PrologTerm extends aps0.typage.PrologTerm implements IAstVisitor<String, Void, Exception> {

	@Override
	public String visit(AstBlock ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("block( [");
		for (AstCommand cmd : ast.getCommands()) {
			sb.append(cmd.accept(this, data));
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("] )");
		return sb.toString();
	}

	@Override
	public String visit(AstSet ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("set( "+ast.getVarName()+", ");
		sb.append(ast.getExpression().accept(this, data));
		sb.append(" )");
		return sb.toString();
	}

	@Override
	public String visit(AstIfStat ast, Void data) throws Exception {
		return "if_stat( " + ast.getCondition().accept(this, data) + ", " + ast.getConsequence().accept(this, data) + ", "
				+ ast.getAlternative().accept(this, data) + " )";
	}

	@Override
	public String visit(AstWhile ast, Void data) throws Exception {
		return "while( " + ast.getCondition().accept(this, data)+ ", "+ast.getBody().accept(this, data)+" )";
	}

	@Override
	public String visit(AstCall ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("call( "+ast.getProcName()+", ");
		sb.append("[");
		for (AstExpression expr : ast.getExpressions()) {
			sb.append(expr.accept(this, data));
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("] )");
		return sb.toString();
	}

	@Override
	public String visit(AstVarDec ast, Void data) throws Exception {
		return "var_dec( "+ast.getName()+", "+ast.getType().accept(this, data)+" )";
	}

	@Override
	public String visit(AstProc ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("proc( " + ast.getName() +", ");
		sb.append("[");
		for (AstArgument arg : ast.getArguments()) {
			sb.append(arg.accept(this, data));
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("], ");
		sb.append(ast.getBody().accept(this, data));
		sb.append(" )");
		return sb.toString();
	}

	@Override
	public String visit(AstProcRec ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("proc_rec( " + ast.getName() +", ");
		sb.append("[");
		for (AstArgument arg : ast.getArguments()) {
			sb.append(arg.accept(this, data));
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("], ");
		sb.append(ast.getBody().accept(this, data));
		sb.append(" )");
		return sb.toString();
	}

	@Override
	public String visit(AstVoidType ast, Void data) throws Exception {
		return "void";
	}
	
}
