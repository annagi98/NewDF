package df;
public class COOValue extends Value {
    //musimy zabezpieczyc int i value przed zmiana - ale jak je wtedy zainicjowac zeby mialy jakiekolwiek wartosci?
 //   private final int cIndex;
 //   private final Value element;
    private  int cIndex;
    private  Value element;

    public COOValue(int newIndex, Value newElement) {
        //super();
        cIndex = newIndex;
        element = newElement;

    }

    public COOValue(Value value) {
        super();
    }


    public int getIndex(){
        return cIndex;
    }

    @Override
    protected Object getValue() {
        return element;
    }

    @Override
    public String toString() {
        //return String.format("COOValue: value %s of index %d", element, cIndex);
        return String.format("COOValue: (%s, id: %d)", element, cIndex);

    }

    @Override
    public Value add(Value sth) {
        return null;
    }

    @Override
    public Value sub(Value sth) {
        return null;
    }

    @Override
    public Value mul(Value sth) {
        return null;
    }

    @Override
    public Value div(Value sth) {
        return null;
    }

    @Override
    public Value pow(Value sth) {
        return null;
    }

    @Override
    public boolean eq(Value toCompare) {
        if (toCompare instanceof IntegerValue && this.element instanceof IntegerValue) {
            int val = (int)toCompare.getValue();
            int this_val = (int) this.element.getValue();
            boolean equall = false;
            if (this_val== val){
                equall = true;
            }
            return equall;
        }

        if (toCompare instanceof DoubleValue && this.element instanceof DoubleValue) {
            double val = (double)toCompare.getValue();
            double this_val = (double) this.element.getValue();
            boolean equall = false;
            if (this_val== val){
                equall = true;
            }
            return equall;
        }

        if (toCompare instanceof FloatValue && this.element instanceof FloatValue) {
            float val = (float)toCompare.getValue();
            float this_val = (float) this.element.getValue();
            boolean equall = false;
            if (this_val== val){
                equall = true;
            }
            return equall;
        }

        return false;
    }

    @Override
    public boolean lte(Value toCompare) {
        if (toCompare instanceof IntegerValue && this.element instanceof IntegerValue) {
            int val = (int)toCompare.getValue();
            int this_val = (int) this.element.getValue();
            boolean greaterOrEqual = false;
            if (this_val<= val){
                greaterOrEqual = true;
            }
            return greaterOrEqual;
        }

        if (toCompare instanceof DoubleValue && this.element instanceof DoubleValue) {
            double val = (double)toCompare.getValue();
            double this_val = (double) this.element.getValue();
            boolean greaterOrEqual = false;
            if (this_val<= val){
                greaterOrEqual = true;
            }
            return greaterOrEqual;
        }

        if (toCompare instanceof FloatValue && this.element instanceof FloatValue) {
            float val = (float)toCompare.getValue();
            float this_val = (float) this.element.getValue();
            boolean greaterOrEqual = false;
            if (this_val<= val){
                greaterOrEqual = true;
            }
            return greaterOrEqual;
        }

        return false;
    }

    @Override
    public boolean gte(Value toCompare) {                   //dopisac dla typow nienumerycznych, wyrzucic wyjatek!
        if (toCompare instanceof IntegerValue && this.element instanceof IntegerValue) {
            int val = (int)toCompare.getValue();
            int this_val = (int) this.element.getValue();
            boolean greaterOrEqual = false;
            if (this_val>= val){
                greaterOrEqual = true;
            }
            return greaterOrEqual; 
        }

        if (toCompare instanceof DoubleValue && this.element instanceof DoubleValue) {
            double val = (double)toCompare.getValue();
            double this_val = (double) this.element.getValue();
            boolean greaterOrEqual = false;
            if (this_val>= val){
                greaterOrEqual = true;
            }
            return greaterOrEqual;
        }

        if (toCompare instanceof FloatValue && this.element instanceof FloatValue) {
            float val = (float)toCompare.getValue();
            float this_val = (float) this.element.getValue();
            boolean greaterOrEqual = false;
            if (this_val>= val){
                greaterOrEqual = true;
            }
            return greaterOrEqual;
        }

        return false;
    }

    @Override
    public boolean neq(Value sth) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public Value create(String s) {
        return null;
    }

    @Override
    public int compareTo(Object o) {
        if(this.getValue() instanceof IntegerValue){
            if(this.eq((IntegerValue)o)){
                return 0;
            }
            if(this.gte((IntegerValue)o)){
                return 1;
            }
            if(this.lte((IntegerValue)o)){
                return -1;
            }
        }
        if(this.getValue() instanceof DoubleValue){
            if(this.eq((DoubleValue)o)){
                return 0;
            }
            if(this.gte((DoubleValue)o)){
                return 1;
            }
            if(this.lte((DoubleValue)o)){
                return -1;
            }
        }
        
        if(this.getValue() instanceof FloatValue){
            if(this.eq((FloatValue)o)){
                return 0;
            }
            if(this.gte((FloatValue)o)){
                return 1;
            }
            if(this.lte((FloatValue)o)){
                return -1;
            }
        }
        return 0;
        
    }
}
