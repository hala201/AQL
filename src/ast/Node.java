package ast;

import controller.AQLVisitorType;

public abstract class Node {
    abstract public <T, U> U accept(AQLVisitorType<T, U> v, T t);
}