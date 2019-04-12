package df;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;



public class DateTimeValue extends Value {
    private Date value;

    public DateTimeValue(Date value) {
        this.value = value;
    }

    public DateTimeValue() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 0);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        this.value = cal.getTime();
    }

    public DateTimeValue(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //w takiej formie sa podawane w pliku do odczytania
        Date newDate = sdf.parse(data);
        this.value = newDate;

    }


    public DateTimeValue(int year, int month, int day, int hrs, int mins, int secs) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hrs);
        cal.set(Calendar.MINUTE, mins);
        cal.set(Calendar.SECOND, secs);
        this.value = cal.getTime();
    }

    public Object getValue() {
        return value;
    }

    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");  //pousuwac godziny z argumentow przy konstrukotrach(???)
        String date = sdf.format(this.value);
        /*System.out.println("ze stringa!:");
        System.out.println(date);*/
        return date;
    }

    @Override
    public Value add(Value sth) throws TypesNotCompatibleException{ throw new TypesNotCompatibleException(); }

    @Override
    public Value sub(Value sth) throws TypesNotCompatibleException{ throw new TypesNotCompatibleException(); }

    @Override
    public Value mul(Value added) throws TypesNotCompatibleException{ throw new TypesNotCompatibleException(); }

    @Override
    public Value div(Value added) throws TypesNotCompatibleException{ throw new TypesNotCompatibleException(); }

    @Override
    public Value pow(Value added) throws TypesNotCompatibleException{ throw new TypesNotCompatibleException(); }

    @Override
    public boolean eq(Value toCompare) throws TypesNotCompatibleException {
        if (toCompare instanceof DateTimeValue) {
            DateTimeValue comparing = (DateTimeValue) toCompare;
            int compareHelper;
            compareHelper = this.value.compareTo(comparing.value);
            if(compareHelper==0){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean lte(Value toCompare) throws TypesNotCompatibleException {
        if (toCompare instanceof DateTimeValue) {
            DateTimeValue comparing = (DateTimeValue) toCompare;
            int compareHelper;
            compareHelper = this.value.compareTo(comparing.value);
            if(compareHelper<0){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean gte(Value toCompare) throws TypesNotCompatibleException {
        if (toCompare instanceof DateTimeValue) {
            DateTimeValue comparing = (DateTimeValue) toCompare;
            int compareHelper;
            compareHelper = this.value.compareTo(comparing.value);
            if(compareHelper>0){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean neq(Value toCompare) throws TypesNotCompatibleException {
        if (toCompare instanceof DateTimeValue) {
            DateTimeValue comparing = (DateTimeValue) toCompare;
            int compareHelper;
            compareHelper = this.value.compareTo(comparing.value);
            if(compareHelper!=0){
                return true;
            }
            else{
                return false;
            }
        }
        else{
            throw new TypesNotCompatibleException();
        }
    }

    @Override
    public boolean equals(Object other) {
        if(other==null){
            return false;
        }
        if(other instanceof DateTimeValue){
            DateTimeValue another = (DateTimeValue) other;
            return Objects.equals(value, another.value);
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
    public Value create(String s) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); //w takiej formie sa podawane w pliku do odczytania
        Date newDate = sdf.parse(s);
     /*   System.out.println("moze tu sie psuje?");
        System.out.println(newDate);*/
        DateTimeValue newDTV = new DateTimeValue(newDate);
        return newDTV;
    }

    @Override
    public int compareTo(Object o) {
        try {
            if (this.eq((DateTimeValue) o)) { return 0; }
        } catch (TypesNotCompatibleException e) { e.printStackTrace(); }
        try {
            if (this.gte((DateTimeValue) o)) { return 1; }
        } catch (TypesNotCompatibleException e) { e.printStackTrace(); }
        try {
            if (this.lte((DateTimeValue) o)) { return -1; }
        } catch (TypesNotCompatibleException e) { e.printStackTrace(); }
        return 9999; //bo musi cos zwracac
    }
}

