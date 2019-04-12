//import groovy.json.internal.ArrayUtils;
package df;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class SparseDataFrame extends DataFrame {

    /*SparseDataFrame(String[] newTitles, String[] newTypes) {
        super(newTitles, newTypes);
    } */

 //   String[] titles;
 //   String[] types;
    String toHide;
    int sizeOfDF;
   // ArrayList<ArrayList<COOValue>> mainFrame;
    int numbersOfColumns;
    int columnLength; //potrzebne pozniej dla metody toDense, zeby df miala rowne dlugosci kolumn

    //podstawowy konstruktor
    SparseDataFrame(String[] newTitles, String[] newTypes) {
        super(newTitles, newTypes);
        numbersOfColumns = newTitles.length;
    }



    SparseDataFrame(DataFrame someDF, String hide){
        super(someDF.titles, someDF.vTypes, true); //zrobimy bez kolumn zeby bylo latwiej
        sizeOfDF = someDF.size();
        toHide = hide;
        numbersOfColumns = someDF.titles.length;


        columnLength = someDF.mainFrame.get(0).size();

        for (int columnNumber = 0; columnNumber<numbersOfColumns; columnNumber++){
            ArrayList columnSDF= new ArrayList<>();
            int index = 0;
            for (int j=0; j<columnLength; j++){
                Value something = someDF.mainFrame.get(columnNumber).get(j);

                if (types[columnNumber].equals("int")){//(something instanceof IntegerValue){
                    Integer simpleValueInteger = ((IntegerValue) something).simpleValue();
                    String compare = simpleValueInteger.toString();
                    if(!compare.equals(toHide)){
                        COOValue newCOOV = new COOValue(index, new IntegerValue(simpleValueInteger));
                        columnSDF.add(newCOOV);
                    }
                }

                else if (something instanceof DoubleValue){  //cos jest pokielbaszone z typami, przetestowac dla innych niz double
                    Double simpleValueDouble = ((DoubleValue) something).simpleValue();
                    String compare = simpleValueDouble.toString();
                    if(!compare.equals(toHide)){
                        DoubleValue someValue = new DoubleValue(simpleValueDouble.doubleValue());
                        COOValue newCOOV = new COOValue(index, someValue);
                        columnSDF.add(newCOOV);
                    }
                    break;
                }

                else if (something instanceof FloatValue){
                    Float simpleValueFloat = ((FloatValue) something).simpleValue();
                    String compare = simpleValueFloat.toString();
                    if(!compare.equals(toHide)){
                        COOValue newCOOV = new COOValue(index, new FloatValue(simpleValueFloat));
                        columnSDF.add(newCOOV);
                    }
                    break;
                }

                else if (something instanceof StringValue){
                    String simpleValueString=(String)((StringValue)something).getValue();
                    if(!simpleValueString.equals(toHide)){
                        COOValue newCOOV = new COOValue(index, new StringValue(simpleValueString));
                        columnSDF.add(newCOOV);
                    }
                    break;
                }
                else if (something instanceof DateTimeValue){
                    Date simpleValueDate = (Date)((DateTimeValue)something).getValue();
                    String compare = simpleValueDate.toString();
                    if(!compare.equals(toHide)){
                        COOValue newCOOV = new COOValue(index, new DateTimeValue(simpleValueDate));
                        columnSDF.add(newCOOV);
                    }
                    break;
                }
                index++;

                //else if myCustomType
                //else niech wywali wyjatek ze nieznany typ
            }

            super.mainFrame.add(columnSDF);  //moze zrobi funkcje na dodawanie kolumny?
        }
    }


    public DataFrame toDense() {
        //wywal wyjatek jesli rozmiar DF nie został wczesniej podany! - bez tego nie wiemy jakiej dlugosci maja byc kolumny
        DataFrame dfNew = new DataFrame(titles, vTypes, true); //stosuje konstruktor bez wczesniejszego dodawania kolumn!

        for(int numberOfColumn = 0; numberOfColumn<titles.length; numberOfColumn++){

            ArrayList<Integer> usedIdAL = new ArrayList<>();
            ArrayList<Value> columnDF = new ArrayList<>();
            int ALindex=0; // do iterowania po array liscie z indeksami
            int spDFRowId = 0;

            for (int i = 0; i < super.mainFrame.get(numberOfColumn).size(); i++) { //idzie po dlugosci kolumn sdf, zbiera uzyte juz indeksy z coovalues
                usedIdAL.add(((COOValue) super.mainFrame.get(numberOfColumn).get(i)).getIndex());
            }


            for(int isItUsed=0; isItUsed<= Collections.max(usedIdAL); isItUsed++) {
                if (ALindex > usedIdAL.size()) {
                    break;
                }
                if (isItUsed < usedIdAL.get(ALindex)) {
                    if (mainFrame.get(numberOfColumn).get(0).getValue() instanceof IntegerValue) {  //zamiast tego uzyc create!
                        columnDF.add(new IntegerValue(Integer.parseInt(toHide)));
                    }
                }
                if (isItUsed == usedIdAL.get(ALindex)) {
                    columnDF.add((Value) mainFrame.get(numberOfColumn).get(spDFRowId).getValue());
                    spDFRowId++;
                    ALindex++;
                }
            }

            while(true) {
                if (columnDF.size() != this.columnLength) {
                    if (mainFrame.get(numberOfColumn).get(0).getValue() instanceof IntegerValue) {
                        columnDF.add(new IntegerValue(Integer.parseInt(toHide)));  //tu tez moze create?
                    }
                } else {
                    break;
                }
            }
            dfNew.mainFrame.add(columnDF);
        }
        return dfNew;
    }

    public int columnSize(int nr){
        return super.mainFrame.get(nr).size();
    }


    public int size(){
        return super.mainFrame.get(0).size();
    }

    public void wyprintuj(SparseDataFrame sdf){
        for(int i=0; i<sdf.titles.length; i++){
            System.out.print(sdf.titles[i]);
            System.out.print("           ");
        }
        System.out.println("       ");

        for(int j=0; j<sdf.types.length; j++){
            System.out.print(sdf.types[j]);
            System.out.print("       ");
        }
        System.out.println(" ");
        for (int colId=0; colId<sdf.titles.length; colId++){                        //dla sprawdzenia czy sparsedf sie dobrze dodaje
            for(int rowId=0; rowId<sdf.mainFrame.get(colId).size(); rowId++){
                System.out.print(sdf.mainFrame.get(colId).get(rowId));
            }
            System.out.println(" ");
        }
    }

    public void extendTheArray(int [] array){
        int[] temp = array.clone();
        array = new int[temp.length + 1];
        System.arraycopy(temp, 0, array, 0, temp.length);
    }
}
