package df;

import java.text.ParseException;

public abstract class Value implements Comparable, Cloneable {

    //Object something;
    protected abstract Object getValue();

    public abstract String toString();
    public abstract Value add(Value sth) throws TypesNotCompatibleException;
    public abstract Value sub(Value sth) throws TypesNotCompatibleException;
    public abstract Value mul(Value sth) throws TypesNotCompatibleException;
    public abstract Value div(Value sth) throws TypesNotCompatibleException;
    public abstract Value pow(Value sth) throws TypesNotCompatibleException;
    public abstract boolean eq(Value sth) throws TypesNotCompatibleException;
    public abstract boolean lte(Value sth) throws TypesNotCompatibleException;
    public abstract boolean gte(Value sth) throws TypesNotCompatibleException;
    public abstract boolean neq(Value sth) throws TypesNotCompatibleException;
    public abstract boolean equals(Object other);
    public abstract int hashCode();
    public abstract Value create(String s) throws ParseException;

}