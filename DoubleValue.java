package df;

import java.util.Objects;

public class DoubleValue extends Value{
    protected double value;

    public DoubleValue(double value) {
        this.value = value;
    }
    public DoubleValue(){
        this.value=0.0;
    }
    public DoubleValue(DoubleValue sth){
        this.value=sth.simpleValue();
    }
    public DoubleValue(Object sth){
        DoubleValue newSth = (DoubleValue) sth;
        double dValue = newSth.simpleValue();
        this.value = dValue;
    }

    public Object getValue() {
        return value;
    }

    public double simpleValue(){return value;}


    public String toString() {
        return String.valueOf(value);
        //zeby wyprintowalo obiekt:   return "DoubleValue{value=" + String.valueOf(value) + "}";
    }


    public Value add(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)){
            Value result = new DoubleValue(this.value + Double.parseDouble(sth.toString()));
            return result;
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public Value sub(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)){
            Value result = new DoubleValue(this.value - Double.parseDouble(sth.toString()));
            return result;
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public Value mul(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)){
            Value result = new DoubleValue(this.value * Double.parseDouble(sth.toString()));
            return result;
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public Value div(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)){
            Value result = new DoubleValue(this.value / Double.parseDouble(sth.toString()));
            return result;
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public Value pow(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)){
            double val = Double.parseDouble(sth.toString());
            return new DoubleValue(Math.pow(this.value, val));
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean eq(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)) {
            double val = Double.parseDouble(sth.toString());
            boolean equal = false;
            if (this.value == val){
                equal = true;
            }
            return equal;
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean lte(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)) {
            double val = Double.parseDouble(sth.toString());
            boolean lessOrEqual = false;
            if (this.value <= val){
                lessOrEqual = true;
            }
            return lessOrEqual;
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean gte(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)) {
            double val = Double.parseDouble(sth.toString());
            boolean greaterOrEqual = false;
            if (this.value >= val){
                greaterOrEqual = true;
            }
            return greaterOrEqual;
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean neq(Value sth) throws TypesNotCompatibleException {
        if ((sth instanceof IntegerValue) || (sth instanceof DoubleValue) || (sth instanceof FloatValue)) {
            double val = Double.parseDouble(sth.toString());
            boolean notEqual = false;
            if (this.value != val){
                notEqual = true;
            }
            return notEqual;
        }
        else {
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean equals(Object other) {
        if(other==null){
            return false;
        }
        if(other instanceof DoubleValue){
            DoubleValue otherDoubleValue = (DoubleValue) other;
            if (this.value == otherDoubleValue.value){
                return true; //(this==other); - wtedy porownywalibysmy tez referencje
            }
            else{
                return false;
            }
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Value create(String s) {
        return new DoubleValue(Double.parseDouble(s));
    }

    @Override
    public int compareTo(Object o){
        try {
            if (this.eq((DoubleValue) o)) { return 0; }
        } catch (TypesNotCompatibleException e) { e.printStackTrace(); }
        try {
            if (this.gte((DoubleValue) o)) { return 1; }
        } catch (TypesNotCompatibleException e) { e.printStackTrace(); }
        try {
            if (this.lte((DoubleValue) o)) { return -1; }
        } catch (TypesNotCompatibleException e) { e.printStackTrace(); }

        return 9999; //bo musi cos zwracac
    }
}

