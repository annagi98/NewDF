package df;

import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;

import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class Column {
    String title;
    String type;
    ArrayList<Value> elements;
    
    Column(String title, DataFrame df1){
        this.title = title;
        String [] oldTitles = df1.titles.clone();

        int index1 = 0;
            for(int columnIndex=0; columnIndex<oldTitles.length; columnIndex++){
                if (title.equals(oldTitles[index1])){
                    this.elements = (ArrayList<Value>) df1.mainFrame.get(index1).clone();
                    this.type = df1.types[index1];
                    break;
                }
                index1++;
            }
        wyprintujKolumne();
    }

    Column(ArrayList<Value> list){
        elements = (ArrayList<Value>) list.clone();
    }

    Column(String title_, String type_, ArrayList<Value> els_){
        title = title_;
        type = type_;
        elements = (ArrayList<Value>) els_.clone();
    }

    public Value getMax() throws TypesNotCompatibleException {
        Value wartoscMax = this.elements.get(0);
        for (Value value : this.elements) {
            if (wartoscMax.lte(value)) {
                wartoscMax = value;
            }
        }
        return wartoscMax;
    }

    public Value getMin() throws TypesNotCompatibleException {
        Value wartoscMin = this.elements.get(0);
        for (Value value : this.elements) {
            if (wartoscMin.gte(value)) {
                wartoscMin = value;
            }
        }
        return wartoscMin;
    }

    public Value getMean() throws TypesNotCompatibleException {
        double quantity = this.elements.size();
        DoubleValue sum = this.columnAdder();
        double meanHelper = sum.value / quantity;
        DoubleValue mean = new DoubleValue(meanHelper);
        return mean;
    }

    public Value getVariance() throws TypesNotCompatibleException {
        double quantity = this.elements.size();
        DoubleValue mean = (DoubleValue)this.getMean();
        double helper = 0.0;

        for(int id=0; id<quantity; id++){
            DoubleValue el = (DoubleValue)this.elements.get(id);
            helper += pow(el.simpleValue()-mean.simpleValue(),2);
        }
        double var = helper/quantity;

        DoubleValue variance = new DoubleValue(var);
        return variance;
    }

    public DoubleValue getStandardDeviation() throws TypesNotCompatibleException {
        DoubleValue variance = (DoubleValue)this.getVariance();
        double simpleVariance = variance.simpleValue();
        double std = sqrt(simpleVariance);
        DoubleValue stD = new DoubleValue(std);
        return stD;
    }



    public void wyprintujKolumne(){
        System.out.println(this.title);
        System.out.println(this.type);
        for(int index=0; index<this.elements.size(); index++){
            System.out.println(this.elements.get(index));
        }
    }


    public DoubleValue columnAdder() throws TypesNotCompatibleException {  //na poczatku tylko dla double
        DoubleValue wynik = new DoubleValue(0.0);

        for(int colId=0; colId<this.size(); colId++){
            wynik = (DoubleValue)wynik.add(this.elements.get(colId));
        }

        return wynik;
    }

    public int size(){
        return elements.size();
    }

    public Column add(Value sth){
        ArrayList<Value> newCol = new ArrayList<>();
        for(int i=0; i<this.elements.size(); i++){
            DoubleValue newD = new DoubleValue(this.elements.get(i));
            try {
                Value newV = newD.add(sth);
                newCol.add(newV);
            } catch (TypesNotCompatibleException e) {
                e.printStackTrace();
            }
        }
        Column result = new Column(this.title, this.type, newCol);
        return result;
    }
    public Column sub(Value sth){
        ArrayList<Value> newCol = new ArrayList<>();
        for(int i=0; i<this.elements.size(); i++){
            DoubleValue newD = new DoubleValue(this.elements.get(i));
            try {
                Value newV = newD.sub(sth);
                newCol.add(newV);
            } catch (TypesNotCompatibleException e) {
                e.printStackTrace();
            }
        }
        Column result = new Column(this.title, this.type, newCol);
        return result;
    }
    public Column mul(Value sth){
        ArrayList<Value> newCol = new ArrayList<>();
        for(int i=0; i<this.elements.size(); i++){
            DoubleValue newD = new DoubleValue(this.elements.get(i));
            try {
                Value newV = newD.mul(sth);
                newCol.add(newV);
            } catch (TypesNotCompatibleException e) {
                e.printStackTrace();
            }
        }
        Column result = new Column(this.title, this.type, newCol);
        return result;
    }
    public Column div(Value sth){
        ArrayList<Value> newCol = new ArrayList<>();
        for(int i=0; i<this.elements.size(); i++){
            DoubleValue newD = new DoubleValue(this.elements.get(i));
            try {
                Value newV = newD.div(sth);
                newCol.add(newV);
            } catch (TypesNotCompatibleException e) {
                e.printStackTrace();
            }
        }
        Column result = new Column(this.title, this.type, newCol);
        return result;
    }

    public Column addC2C(Column another){
        if(this.elements.size()!=another.elements.size()){
            System.out.println("Columns have different sizes");
        }
        return null;
    }


}
