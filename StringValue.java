package df;

import java.util.Objects;

public class StringValue extends Value {
    private String value;

    public StringValue(String value) {
        this.value = value;
    }

    public StringValue() {
        this.value = "";
    }

    public Object getValue() {
        return value;
    }


    public String toString() {
        return String.valueOf(value);
    }


    public Value add(Value added) {
        String helper = added.toString();
        String helper2 = this.value + helper;
        return new StringValue(helper2);
    }

    @Override
    public Value sub(Value subtracted) throws TypesNotCompatibleException {
        throw new TypesNotCompatibleException();
    }
    @Override
    public Value mul(Value subtracted) throws TypesNotCompatibleException {
        throw new TypesNotCompatibleException();
    }

    @Override
    public Value div(Value subtracted) throws TypesNotCompatibleException {
        throw new TypesNotCompatibleException();
    }

    @Override
    public Value pow(Value subtracted) throws TypesNotCompatibleException {
        throw new TypesNotCompatibleException();
    }

    @Override
    public boolean eq(Value toCompare) { //comparing the length
        String helper1 = toCompare.toString();
        return this.value.length() == helper1.length();
    }

    @Override
    public boolean lte(Value toCompare) { //comparing the length
        String helper1 = toCompare.toString();
        return this.value.length() <= helper1.length();
    }

    @Override
    public boolean gte(Value toCompare) { //comparing the length
        String helper1 = toCompare.toString();
        return this.value.length() >= helper1.length();
    }

    @Override
    public boolean neq(Value toCompare) { //comparing the length
        String helper1 = toCompare.toString();
        return this.value.length() != helper1.length();
    }

    @Override
    public boolean equals(Object other) {
        String helper1 = other.toString();
        return this.value.equals(helper1);
    }

    @Override
    public int hashCode() { return Objects.hash(value); }

    @Override
    public Value create(String s) {
        return new StringValue(s);
    }

    @Override
    public int compareTo(Object o) { //comparing only the length
        if(this.eq((Value) o)){
            return 0;
        }
        else{
            if(this.gte((Value)o)){
                return 1;
            }
            else{
                return -1;
            }
        }
    }
}

