package aps0.interfaces;

public interface IAstVisitable {
	<Result,Data,Exc extends Throwable> Result accept(IAstVisitor<Result,Data,Exc> visitor, Data data) throws Exc;
}
