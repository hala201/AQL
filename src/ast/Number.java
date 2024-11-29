package ast;

import controller.AQLVisitorType;

public class Number extends Value {
    private int num;

    public Number(int num) {
        this.num = num;
    }

    public int getNum() {
        return this.num;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getNum());
    }

    @Override
    public <T, U> U accept(AQLVisitorType<T, U> v, T t) {
        return v.visit(this, t);
    }
}
