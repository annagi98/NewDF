package df;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.text.ParseException;
import java.util.*;

public class GroupByStructure implements GroupBy, Applyable {
    String[] titles;
    protected Class<? extends Value>[] vTypes;
    //String[] types;
    HashMap<String, ArrayList<ArrayList>> hashMapaDF;
    String[] groupByWhat;
    ArrayList<String> klucze;


    GroupByStructure(HashMap<String, ArrayList<ArrayList>> listDF, String[] kol, String[] oldTitles, Class<? extends Value>[] oldTypes, ArrayList<String> zbior_id) {
        titles = oldTitles.clone();
        //types = oldTypes.clone();
        vTypes = oldTypes.clone();
        hashMapaDF = (HashMap<String, ArrayList<ArrayList>>) listDF.clone();
        groupByWhat = kol.clone();
        klucze = zbior_id;
    }

    public int size() {
        return hashMapaDF.size();
    }


    @Override
    public DataFrame max() {
        return helper("max");
    }
    @Override
    public DataFrame min() {
        return helper("min");
    }

    @Override
    public DataFrame mean() {
        return helper("mean");
    }

    @Override
    public DataFrame std() {
        return helper("std");
    }

    @Override
    public DataFrame sum() {
        return helper("sum");
    }

    @Override
    public DataFrame var() {
        return helper("var");
    }


    @Override
    public DataFrame apply(DataFrame df) {
        return null;

    }



    public DataFrame helper(String operation) {

        ArrayList<String> keys = new ArrayList<>(this.hashMapaDF.keySet());
        Collections.sort(keys);
        DataFrame result = null;
        String[] newTitles = new String[0];

        ArrayList<Integer> indexesOfUsedTypes = new ArrayList<>();  // potem posluzy do zrobienia nowej tablicy z typami class <? extends Value>

        boolean startDF = true;
        for (int iterujePoKluczach = 0; iterujePoKluczach < keys.size(); iterujePoKluczach++) {//z kazdej petli mam miec 1 rzad
            HashSet<Integer> usedColsIds = new HashSet<>();
            ArrayList<Value> newRow = new ArrayList<>();
            int tableId = 0;
            int typesForUse = 0;
            int nrOfGroupingTitlesAlreadyUsed = 0;

            Task3:
            for (int colIndex = 0; colIndex < this.titles.length; colIndex++) { //idziemy po kolumnach
                for (int idGrupujacychNazw = 0; idGrupujacychNazw < groupByWhat.length; idGrupujacychNazw++) {
                    if (this.titles[colIndex].equals(this.groupByWhat[idGrupujacychNazw]) && !usedColsIds.contains(idGrupujacychNazw)) {
                        newTitles = extendTheArray(newTitles);
                        newTitles[tableId] = this.titles[colIndex];

                        indexesOfUsedTypes.add(colIndex);
                        tableId++;

                        String fromKey = keys.get(iterujePoKluczach);
                        String groupingPieces[];

                        groupingPieces = fromKey.split(";;");

                        switch (this.vTypes[colIndex].toString()) {
                            case "class df.StringValue":
                                StringValue SVpiece = new StringValue(groupingPieces[nrOfGroupingTitlesAlreadyUsed]);
                                newRow.add(SVpiece);
                                break;
                            case "class df.DateTimeValue":
                                DateTimeValue DTVpiece = null;
                                try {
                                    DTVpiece = new DateTimeValue(groupingPieces[nrOfGroupingTitlesAlreadyUsed]);
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                newRow.add(DTVpiece);
                                break;
                            case "class df.IntegerValue":
                                IntegerValue IVpiece = new IntegerValue();
                                IVpiece.create(groupingPieces[nrOfGroupingTitlesAlreadyUsed]);
                                newRow.add(IVpiece);
                                break;
                            case "class df.DoubleValue":
                                DoubleValue DVpiece = new DoubleValue();
                                DVpiece.create(groupingPieces[nrOfGroupingTitlesAlreadyUsed]);
                                newRow.add(DVpiece);
                                break;
                            case "class df.FloatValue":
                                FloatValue FVpiece = new FloatValue();
                                FVpiece.create(groupingPieces[nrOfGroupingTitlesAlreadyUsed]);
                                newRow.add(FVpiece);
                                break;
                        }
                        usedColsIds.add(idGrupujacychNazw);
                        nrOfGroupingTitlesAlreadyUsed++;
                        continue Task3;
                    }
                }

                if(operation.equals("max") || operation.equals("min")) {
                    if (this.vTypes[colIndex].toString().equals("class df.DateTimeValue") || this.vTypes[colIndex].toString().equals("class df.IntegerValue") || this.vTypes[colIndex].toString().equals("class df.DoubleValue") || this.vTypes[colIndex].toString().equals("class df.FloatValue")) {
                        newTitles = extendTheArray(newTitles);
                        newTitles[tableId] = this.titles[colIndex];

                        indexesOfUsedTypes.add(colIndex);

                        tableId++;
                        ArrayList<Value> kol = new ArrayList<>();

                        for (int i = 0; i < this.hashMapaDF.get(klucze.get(iterujePoKluczach)).size(); i++) {
                            switch (this.vTypes[colIndex].toString()) {
                                case "class df.DateTimeValue":
                                    kol.add((DateTimeValue) this.hashMapaDF.get(klucze.get(iterujePoKluczach)).get(i).get(typesForUse));
                                    break;
                                case "class df.IntegerValue":
                                    kol.add((IntegerValue) this.hashMapaDF.get(klucze.get(iterujePoKluczach)).get(i).get(typesForUse));
                                    break;
                                case "class df.DoubleValue":
                                    kol.add((DoubleValue) this.hashMapaDF.get(klucze.get(iterujePoKluczach)).get(i).get(typesForUse));
                                    break;
                                case "class df.FloatValue":
                                    kol.add(new FloatValue((float) this.hashMapaDF.get(klucze.get(iterujePoKluczach)).get(i).get(typesForUse)));
                                    break;
                            }
                        }
                        Column investigated = new Column(kol); //tu  sie wybiera rzad zamiast kolumny

                        Value wartoscWybrana = null;
                        try {
                            if (operation.equals("max")) {
                                wartoscWybrana = investigated.getMax();
                            }
                            if (operation.equals("min")) {
                                wartoscWybrana = investigated.getMin(); //
                            }
                        } catch (TypesNotCompatibleException e) {
                            e.printStackTrace();
                        }
                        newRow.add(wartoscWybrana);
                        typesForUse++;

                    } else {
                        typesForUse++;
                    }
                }

                if(operation.equals("mean") || operation.equals("std") || operation.equals("var") || operation.equals("sum")) {

                    if ( this.vTypes[colIndex].toString().equals("class df.IntegerValue") || this.vTypes[colIndex].toString().equals("class df.DoubleValue") || this.vTypes[colIndex].toString().equals("class df.FloatValue")) {
                        newTitles = extendTheArray(newTitles);
                        newTitles[tableId] = this.titles[colIndex];

                        indexesOfUsedTypes.add(colIndex);

                        tableId++;
                        ArrayList<Value> kol = new ArrayList<>();

                        for (int i = 0; i < this.hashMapaDF.get(klucze.get(iterujePoKluczach)).size(); i++) {
                            switch (this.vTypes[colIndex].toString()) {
                                case "class df.IntegerValue":
                                    kol.add((IntegerValue) this.hashMapaDF.get(klucze.get(iterujePoKluczach)).get(i).get(typesForUse));
                                    break;
                                case "class df.DoubleValue":
                                    kol.add((DoubleValue) this.hashMapaDF.get(klucze.get(iterujePoKluczach)).get(i).get(typesForUse));
                                    break;
                                case "class df.FloatValue":
                                    kol.add(new FloatValue((float) this.hashMapaDF.get(klucze.get(iterujePoKluczach)).get(i).get(typesForUse)));
                                    break;
                            }
                        }
                        Column investigated = new Column(kol); //tu  sie wybiera rzad zamiast kolumny

                        Value wartoscWybrana = null;
                        try {
                            if (operation.equals("mean")) {
                                wartoscWybrana = investigated.getMean();
                            }
                            if (operation.equals("std")) {
                                wartoscWybrana = investigated.getStandardDeviation();
                            }
                            if (operation.equals("var")) {
                                wartoscWybrana = investigated.getVariance();
                            }
                            if (operation.equals("sum")) {
                                wartoscWybrana = investigated.columnAdder();
                            }

                        } catch (TypesNotCompatibleException e) {
                            e.printStackTrace();
                        }
                        newRow.add(wartoscWybrana);
                        typesForUse++;

                    } else {
                        typesForUse++;
                    }
                }
            }

            if (startDF) {
                ArrayList<Integer> uniqueIndexes = new ArrayList<>();
                for (Integer element : indexesOfUsedTypes) {
                    if (!uniqueIndexes.contains(element)) {
                        uniqueIndexes.add(element);
                    }
                }

                int vTypesLength = uniqueIndexes.size();
                Class<? extends Value>[] vTypesNew = (Class<? extends Value>[]) (new Class[vTypesLength]);
                for (int i = 0; i < vTypesLength; i++) {
                    vTypesNew[i] = this.vTypes[uniqueIndexes.get(i)];
                }

                result = new DataFrame(newTitles, vTypesNew);
                startDF = false;
            }
            result.addRow(newRow);
        }
        if (result != null) {
            return result;
        } else {
            return null;
        }

    }



    @Override
    public DataFrame apply(Applyable operation) {
        return null;
    }



  /*  @Override
    public DataFrame apply(Applyable operation) {
        DataFrame output = new DataFrame(titles, types);

        for (int i = 0; i <= this.hashMapaDF.size(); i++) {
            DataFrame df = this.hashMapaDF.get(i);
            DataFrame newDF = operation.apply(df);
            if (newDF.size() > 0) {
                for (int j = 0; j < newDF.size(); j++) {
                    ArrayList<Value> rzad = newDF.getRow(j);
                    output.addRow(rzad);
                }
            }
        }
        return output;
    }
*/


    public String[] extendTheArray(String[] array) {
        String[] temp = array.clone();
        array = new String[temp.length + 1];
        System.arraycopy(temp, 0, array, 0, temp.length);
        return array;
    }


}
