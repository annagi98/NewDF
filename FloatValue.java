package df;

import java.util.Objects;

public class FloatValue extends Value {
    private float value;

    public FloatValue(float value) {
        this.value = value;
    }

    public FloatValue(){
        this.value=0.0f;
    }

    public float simpleValue(){return value;}


    public Object getValue() {
        return value;
    }


    public String toString() {
        return String.valueOf(value);
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
        if(other instanceof FloatValue || other instanceof DoubleValue || other instanceof IntegerValue){
            float val = Float.parseFloat(other.toString());
            FloatValue otherFloatValue = new FloatValue(val);
            if (this.value == otherFloatValue.value){
                return true; //(this==other);
            }
            else{ return false; }
        }
        else{ return false; }
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public Value create(String s) {
        return new FloatValue(Float.parseFloat(s));
    }

    @Override
    public int compareTo(Object o) {
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
