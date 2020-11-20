package aps1.evaluation.memory;

import java.util.HashMap;
import java.util.Map;

import aps0.evaluation.value.Any;
import aps0.evaluation.value.Number;
import aps0.evaluation.value.ValeurMemory;
import aps1.evaluation.value.Adress;
import aps1.interfaces.IMemory;

public class Memory implements IMemory{
	
	private static int adresse_count = 0;
	private Map<Adress,ValeurMemory> memory = new HashMap<Adress,ValeurMemory>();
	
	public Adress alloc() {
		Adress adr = new Adress(adresse_count++);
		memory.put(adr, new Any());
		return adr;
	}

	@Override
	public void affect(Adress adresse, Number number) throws Exception {
		if(memory.containsKey(adresse)) {
			memory.put(adresse, number);
		}
		else {
			throw new Exception("Segmentation fault");
		}
	}
	
	public void print() {
		for(Map.Entry<Adress, ValeurMemory> entry : memory.entrySet()) {
			System.out.println(entry.getKey().getAdress()+" = "+entry.getValue().toString());
		}
	}
	@Override
	public Number get(Adress adresse) throws Exception {
		ValeurMemory val = memory.get(adresse);
		if(val != null && val instanceof Number) {
			return (Number) val;
		}
		else {
			throw new Exception("Segmentation fault : Variable at adresse " + adresse.getAdress() + " is not initialized");
		}
	}
	
}
