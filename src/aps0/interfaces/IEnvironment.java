package aps0.interfaces;

import aps0.evaluation.value.Value;

public interface IEnvironment {
    IEnvironment extend(String varName, Value value);
    Value find(String varName) throws Exception;
    IEnvironment copyEnvironment();
}
