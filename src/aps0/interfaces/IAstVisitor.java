package aps0.interfaces;

import aps0.ast.AstArgument;
import aps0.ast.AstProgramme;
import aps0.ast.declaration.AstConst;
import aps0.ast.declaration.AstFun;
import aps0.ast.declaration.AstFunRec;
import aps0.ast.expression.AstApplication;
import aps0.ast.expression.AstBool;
import aps0.ast.expression.AstIf;
import aps0.ast.expression.AstLambda;
import aps0.ast.expression.AstNum;
import aps0.ast.expression.AstOp;
import aps0.ast.expression.AstVar;
import aps0.ast.statement.AstEcho;
import aps0.ast.type.AstBoolType;
import aps0.ast.type.AstFuncType;
import aps0.ast.type.AstIntType;

public interface IAstVisitor<Return,Data,Exc extends Throwable> {
	Return visit(AstApplication ast, Data data) throws Exc;
	Return visit(AstArgument ast, Data data) throws Exc;
	Return visit(AstBool ast, Data data) throws Exc;
	Return visit(AstBoolType ast, Data data) throws Exc;
	Return visit(AstConst ast, Data data) throws Exc;
	Return visit(AstEcho ast, Data data) throws Exc;
	Return visit(AstFun ast, Data data) throws Exc;
	Return visit(AstFuncType ast, Data data) throws Exc;
	Return visit(AstFunRec ast, Data data) throws Exc;
	Return visit(AstIf ast, Data data) throws Exc;
	Return visit(AstIntType ast, Data data) throws Exc;
	Return visit(AstLambda ast, Data data) throws Exc;
	Return visit(AstNum ast, Data data) throws Exc;
	Return visit(AstOp ast, Data data) throws Exc;
	Return visit(AstProgramme ast, Data data) throws Exc;
	Return visit(AstVar ast, Data data) throws Exc;
}
