package aps1.evaluation;

public class Pair<T1, T2> {
	private T1 first;
	private T2 second;
	
	public Pair(T1 e1, T2 e2) {
		first = e1;
		second = e2;
	}
	
	public T1 getFirst() {
		return first;
	}
	
	public T2 getSecond() {
		return second;
	}
}
