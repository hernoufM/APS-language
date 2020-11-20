package aps0.typage;

import aps0.ast.AstArgument;
import aps0.ast.AstCommand;
import aps0.ast.AstProgramme;
import aps0.ast.declaration.AstConst;
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
import aps0.ast.type.AstBoolType;
import aps0.ast.type.AstFuncType;
import aps0.ast.type.AstIntType;
import aps0.ast.type.AstType;
import aps0.interfaces.IAstVisitor;

public class PrologTerm implements IAstVisitor<String, Void, Exception> {

	@Override
	public String visit(AstArgument ast, Void data) throws Exception {
		return "("+ast.getName() + "," + ast.getType().accept(this, data)+")";
	}

	@Override
	public String visit(AstBool ast, Void data) throws Exception {
		return ast.getBool().toString();
	}

	@Override
	public String visit(AstBoolType ast, Void data) throws Exception {
		return "bool";
	}

	@Override
	public String visit(AstConst ast, Void data) throws Exception {
		return "const( " + ast.getName() + ", " + ast.getType().accept(this, data) + ", "
				+ ast.getExpression().accept(this, data)+" )";
	}

	@Override
	public String visit(AstEcho ast, Void data) throws Exception {
		return "echo( " + ast.getExpr().accept(this, data)+" )";
	}

	@Override
	public String visit(AstFun ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("func( " + ast.getName() + ", "+ast.getType().accept(this, data)+", ");
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
	public String visit(AstFuncType ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("([");
		for (AstType arg : ast.getTypesArguments()) {
			sb.append(arg.accept(this, data));
			sb.append(",");
		}
		sb.delete(sb.length() - 1, sb.length());
		sb.append("],");
		sb.append(ast.getTypeResult().accept(this, data));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public String visit(AstFunRec ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("func_rec( " + ast.getName() + ", "+ast.getType().accept(this, data)+", ");
		sb.append("[");
		for (AstArgument arg : ast.getArguments()) {
			sb.append(arg.accept(this, data));
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("], ");
		sb.append(ast.getBody().accept(this, data));
		sb.append(")");
		return sb.toString();
	}

	@Override
	public String visit(AstIf ast, Void data) throws Exception {
		return "if( " + ast.getCondition().accept(this, data) + ", " + ast.getConsequence().accept(this, data) + ", "
				+ ast.getAlternative().accept(this, data) + " )";
	}

	@Override
	public String visit(AstIntType ast, Void data) throws Exception {
		return "int";
	}

	@Override
	public String visit(AstLambda ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("abs( [");
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
	public String visit(AstNum ast, Void data) throws Exception {
		return "num("+ast.getInteger().toString()+")";
	}

	@Override
	public String visit(AstOp ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("app( "+ast.getOperation()+", [");
		for (AstExpression expr : ast.getExpressions()) {
			sb.append(expr.accept(this, data));
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("] )");
		return sb.toString();
	}

	@Override
	public String visit(AstProgramme ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("prog( [");
		for (AstCommand cmd : ast.getCommands()) {
			sb.append(cmd.accept(this, data));
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("] )");
		return sb.toString();
	}

	@Override
	public String visit(AstVar ast, Void data) throws Exception {
		return "var("+ast.getName()+")";
	}

	@Override
	public String visit(AstApplication ast, Void data) throws Exception {
		StringBuilder sb = new StringBuilder();
		sb.append("app( "+ast.getInvocable().accept(this, data)+", [");
		for (AstExpression expr : ast.getExpressions()) {
			sb.append(expr.accept(this, data));
			sb.append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("] )");
		return sb.toString();
	}

}
