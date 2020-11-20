package aps1.interfaces;

import aps0.evaluation.value.Number;
import aps1.evaluation.value.Adress;

public interface IMemory {
    Adress alloc();
    void affect(Adress adresse, Number number) throws Exception;
    Number get(Adress adresse) throws Exception;
    void print();
}
