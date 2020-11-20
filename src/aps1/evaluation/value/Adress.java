package aps1.evaluation.value;

import aps0.evaluation.value.Value;

public class Adress extends Value{
	private int adress;
	
	public Adress(int adr) {
		adress = adr;
	}
	
	@Override
	public boolean equals(Object adr) {
		if(!( adr instanceof Adress)) {
			return false;
		}
		return adress==((Adress) adr).adress;
	}
	
	public int getAdress() {
		return adress;
	}
}
