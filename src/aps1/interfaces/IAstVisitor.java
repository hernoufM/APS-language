package aps1.interfaces;

import aps1.ast.AstBlock;
import aps1.ast.declaration.AstProc;
import aps1.ast.declaration.AstProcRec;
import aps1.ast.declaration.AstVarDec;
import aps1.ast.statement.*;
import aps1.ast.type.AstVoidType;

public interface IAstVisitor<Return, Data, Exc extends Throwable> extends aps0.interfaces.IAstVisitor<Return, Data, Exc> {
	Return visit(AstBlock ast, Data data) throws Exc;
	Return visit(AstSet ast, Data data) throws Exc;
	Return visit(AstIfStat ast, Data data) throws Exc;
	Return visit(AstWhile ast, Data data) throws Exc;
	Return visit(AstCall ast, Data data) throws Exc;
	Return visit(AstVarDec ast, Data data) throws Exc;
	Return visit(AstProc ast, Data data) throws Exc;
	Return visit(AstProcRec ast, Data data) throws Exc;
	Return visit(AstVoidType ast, Data data) throws Exc;
}
