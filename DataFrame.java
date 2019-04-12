
package df;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.*;
import java.text.ParseException;
import java.util.*;


public class DataFrame{

    protected ArrayList<ArrayList<Value>> mainFrame;
   // protected ArrayList<ArrayList<Object>> mainFrame1;
    protected String[] titles;
    protected String[] types;
    ArrayList<Class<? extends Value>> lTypes;
    protected Class<? extends Value>[] vTypes;

    DataFrame(){
        mainFrame = new ArrayList<ArrayList<Value>>();
        titles = new String [1]; //1-elementowa tablica
        types = new String [1];
    }

    DataFrame(String[] newTitles, String[] newTypes){
        this.titles = newTitles.clone();
        this.types = newTypes.clone();
        this.mainFrame = new ArrayList<ArrayList<Value>>();

        for (String title : titles) {         //adding columns to the DF
            this.mainFrame.add(new ArrayList<>());
        }
    }

    DataFrame(String[] newTitles,  Class<? extends Value>[]  newTypes, boolean bezkolumn){  //do konstruowania z pliku?
        titles = newTitles.clone();
        vTypes = newTypes.clone();
        mainFrame = new ArrayList<ArrayList<Value>>();
    }

    //zmodyfikowane konstruktory --------------------------- poprawić
    DataFrame(String[] newTitles, Class<? extends Value>[] newTypes){
        titles = newTitles.clone();
        vTypes = newTypes.clone();

        mainFrame = new ArrayList<>();
        for (String title : titles) {         //adding columns to the DF
            mainFrame.add(new ArrayList<>());
        }
    }


    DataFrame(String dataFile, Class<? extends Value>[] newTypes, boolean trueHeader) throws IOException, ClassNotFoundException, ParseException {
        mainFrame = new ArrayList<>();

        vTypes = newTypes.clone();

        FileInputStream fstream = new FileInputStream(dataFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

        String newLine;
        boolean noNames=true;

        Task1:
        while((newLine = br.readLine()) != null ){
            if(noNames && trueHeader){
                titles = newLine.split(",");
                noNames = false;
                continue Task1;
            }

            if(!trueHeader){
                String [] newTitles = new String[vTypes.length];
                for (int i = 0; i < vTypes.length; i++){
                    newTitles[i] = "typ" + newTypes[i].toString(); //tymczasowo
                }
                titles = newTitles.clone();
                trueHeader = true;
                noNames = false;
            }
            ArrayList<Value> newRow = new ArrayList<Value>();
            String [] newObjects = newLine.split(",");

            Task2:
            for(int i = 0; i<newObjects.length; i++) {
               // System.out.println(this.vTypes[i].toString());
                switch (this.vTypes[i].toString()) {
                    case "int":
                        int simpleInt = Integer.parseInt(newObjects[i]);
                        newRow.add(new IntegerValue(simpleInt));
                        continue Task2;

                    case "class df.DoubleValue":
                        double simpleDouble = Double.parseDouble(newObjects[i]);
                        newRow.add(new DoubleValue(simpleDouble));
                        continue Task2;

                    case "float":
                        float simpleFloat = Float.parseFloat(newObjects[i]);
                        newRow.add(new FloatValue(simpleFloat));
                        continue Task2;

                    case "class df.StringValue":
                        newRow.add(new StringValue(newObjects[i]));
                        continue Task2;

                    case "class df.DateTimeValue":
                        newRow.add(new DateTimeValue(newObjects[i]));
                        continue Task2;

                    default:     //wyrzucanie wyjatku ze cos poszlo nie tak w typach
                        continue Task2;

                }
            }
            addRow(newRow);
        }
    }

    public DataFrame(String[] newTitles, ArrayList<Class<? extends Value>> listOfElements) {
        titles = newTitles.clone();
        lTypes = (ArrayList<Class<? extends Value>>) listOfElements.clone();

        mainFrame = new ArrayList<>();
        for (String title : titles) {         //adding columns to the DF
            mainFrame.add(new ArrayList<>());
        }
    }


    int size(){
        if(this.mainFrame.isEmpty()){
            return 0;
        }
        for(int i=0; i<titles.length; i++){               //dla konstruktora ze stringami bylo types zamiast titles
            for(int j=0; j<titles.length; j++){
                if(mainFrame.get(i).size()!=mainFrame.get(j).size()){
                    System.out.print("Columns doesn't have equal sizes.");  //zamienic to na wyrzucanie wyjatku
                    return 0;
                }
            }
        }
        return this.mainFrame.get(0).size();
    }




    public DataFrame get(String colname) {
        DataFrame dfGet = new DataFrame();

        int indexOfColumn;
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equals(colname)) {
                indexOfColumn = i;
                dfGet.mainFrame.add(this.mainFrame.get(indexOfColumn));
                dfGet.titles[0] = titles[indexOfColumn];
                dfGet.types[0] = types[indexOfColumn];

               // wyprintuj(dfGet);
                return dfGet;
            }
        }
        return null;
    }

    public DataFrame get (String [] cols, boolean copy) {
        int index = 0;
        int[] indexesOfColumns = new int[cols.length];
        ArrayList<Integer> uniqueIndexes = new ArrayList<>();
        for (int i = 0; i < titles.length; i++) {
            for (int j = 0; j < cols.length; j++) {
                if (titles[i].equals(cols[j])) {
                    indexesOfColumns[index] = i;

                    index++;
                }
            }
        }

        index = 0;
        String[] oldTitles = titles.clone();


        //tworzenie tablicy z nowymi nazwami
      /*   newTypes = (Class<? extends Value>[]) (new Class[cols.length]);
        for (int i = 0; i < uniqueIndexes ; i++) {
            newTypes[i] = vTypes[uniqueIndexes.get(i)];
        }*/
        Class<? extends Value>[] newTypes = (Class<? extends Value>[]) (new Class[cols.length]);
        for (int k = 0; k < cols.length; k++) {
            newTypes[k] = vTypes[indexesOfColumns[index]];
            index++;
        }

        DataFrame newDF = new DataFrame(cols, newTypes, true);

        int index1 = 0;
        Task1:
        for (String name: cols){
            for(int columnIndex=0; columnIndex<oldTitles.length; columnIndex++){
                if (name.equals(oldTitles[index1])){
                    if(copy){
                        newDF.mainFrame.add((ArrayList<Value>)mainFrame.get(index1).clone());
                    }
                    else{
                        newDF.mainFrame.add(mainFrame.get(index1));
                    }
                    index1++;
                    continue Task1;
                }
                index1++;
            }
        }
        //wyprintuj(newDF);
        return newDF;
    }

    public ArrayList<Value> getRow(int i){
        ArrayList<Value> oneRow = new ArrayList<>();
        int index = 0;
        for (ArrayList column : this.mainFrame){ //this.mainFrame zamiast mainFrame
            oneRow.add((Value) column.get(i));
        /*    dfOneRow.mainFrame.add(new ArrayList<Value>());
            dfOneRow.mainFrame.get(index).add();//czy to bylo testowane?
            index++;*/
        }

       return oneRow;

    }

    public DataFrame iloc(int i){ //wywalic wyjatek jesli i jest wieksze od size
       String [] newTitles=titles.clone();
       Class<? extends Value>[] newTypes =vTypes.clone();

       DataFrame dfOneRow = new DataFrame(newTitles, newTypes, true);

        int index = 0;
        for (ArrayList column : this.mainFrame){ //this.mainFrame zamiast mainFrame
            dfOneRow.mainFrame.add(new ArrayList<Value>());
            dfOneRow.mainFrame.get(index).add((Value) column.get(i));//czy to bylo testowane?
            index++;
        }

        if (dfOneRow.mainFrame.get(0).isEmpty()){
            return null;
        }
        //wyprintuj(dfOneRow);
        return dfOneRow;
   }

    public DataFrame iloc(int from, int to){ //wywalic wyjatek jesli i jest w zlym zakresie
       String [] newTitles=titles.clone();
       Class<? extends Value>[] newTypes=vTypes.clone();

       DataFrame moreRows = new DataFrame(newTitles, newTypes, true);

       int index = 0;
       for (ArrayList column : mainFrame){
           moreRows.mainFrame.add(new ArrayList<Value>());

           for(int j = from; j <= to; j++){
               moreRows.mainFrame.get(index).add((Value) column.get(j));
           }
           index++;
       }

       if (moreRows.mainFrame.get(0).isEmpty()){
           return null;
       }
       return moreRows;
   }



    public void addRow(ArrayList<Value> row){               //poprawne dla Values, dopisac jeszcze dla ArrayList<Object> bo latwiej testowac
        boolean emptyDF = mainFrame.isEmpty();
        if (!emptyDF){                                    //sprawdzic jeszcze size

            for(int colIndex=0; colIndex<this.titles.length; colIndex++) {
                mainFrame.get(colIndex).add(row.get(colIndex));
            }
        }
        else{
            for (int colIndex=0; colIndex<this.titles.length; colIndex++){
                ArrayList<Value> column = new ArrayList<>();
                column.add(row.get(colIndex));
                mainFrame.add(column);
            }
        }
    }



    public void addElement(Value element) {
      //  boolean emptyDF = mainFrame.isEmpty();
        //System.out.println(mainFrame.size());
      //  if(emptyDF){
            mainFrame.get(0).add(element);
      //  }
       // if (!emptyDF){

            //sprawdzic jeszcze size
         //   for(int colIndex=0; colIndex<this.titles.length; colIndex++) {
                //mainFrame.get(colIndex).add(row.get(colIndex));
         //       mainFrame.get(0).add(element);
            //}
       // }
      /*  else{
            for (int colIndex=0; colIndex<this.titles.length; colIndex++){
                ArrayList<Value> column = new ArrayList<>();
                column.add(row.get(colIndex));
                mainFrame.add(column);
            }
        }*/
       /* if (element instanceof IntegerValue) {

            allTypesAdder("int", element);
        }
        else if (element instanceof DoubleValue) {
            allTypesAdder("double", element);
        }
        else if (element instanceof FloatValue) {
            allTypesAdder("float", element);
        }
        else if (element instanceof StringValue) {
            allTypesAdder("String", element);
        }
        else if (element instanceof DateTimeValue) {
            allTypesAdder("Date", element);
        }
        //else if mytype
        //else wyrzucenie warunku ze nie mozna utowrzyc takiego obiektu
        */
    }

    public void extendStringArray(String [] array){
        String[] temp = array.clone();
        array = new String[temp.length + 1];
        System.arraycopy(temp, 0, array, 0, temp.length);
    }
    
    public void addTypeToTypesTable(String typ, Value element){
        extendStringArray(types);
        extendStringArray(titles);
        ArrayList<Value> column = new ArrayList<>();
        column.add(element);
        mainFrame.add(column);
        types[types.length - 1] = typ;
        titles[titles.length-1] = "tytul" + typ;
    }
    
    public void allTypesAdder(String typeName, Value element){
        boolean typeIndexFound=false;
        int indexOfType=9999;
        
        for (int i = 0; i < types.length; i++) {
            if (types[i].equals(typeName)) {
                indexOfType = i;
                typeIndexFound = true;
                break;
            }
        }
        if(typeIndexFound){
            mainFrame.get(indexOfType).add(element);
        }
        if (!typeIndexFound) {  //type is not in array of types - add it at the end of array of types
            addTypeToTypesTable(typeName, element);
        }
    }

    public void wyprintuj(DataFrame df){
       /* for(int i=0; i<df.titles.length; i++){
            System.out.print(df.titles[i]);
            System.out.print("           ");
        }
        System.out.println("       ");

        for(int j=0; j<df.types.length; j++){
            System.out.print(df.types[j]);
            System.out.print("       ");
        }
        System.out.println(" ");

        if(df.size()==0){
            System.out.println("DF jest pusta");
        } */
// ^ dla konstruktora ze stringami


        // v dla konstruktora z lista klas

        for(int i=0; i<df.titles.length; i++){
            System.out.print(df.titles[i]);
            System.out.print("           ");
        }
        System.out.println("       ");

        for(int j=0; j<df.vTypes.length; j++){
            System.out.print(df.vTypes[j].toString());
            System.out.print("       ");
        }
        System.out.println(" ");

        if(df.size()==0){
            System.out.println("DF jest pusta");
        }





       /* for (int colId=0; colId<df.titles.length; colId++){                        //dla sprawdzenia czy sparsedf sie dobrze dodaje
            for(int rowId=0; rowId<df.mainFrame.get(colId).size(); rowId++){
                System.out.println(df.mainFrame.get(colId).get(rowId));
           }
        }*/



        for(int colIndex=0; colIndex<df.size(); colIndex++){                        //starsza wersja - uznaje dla df tylko kolumny o rownej ilosc elementow
            for(int columnIndex=0; columnIndex<df.titles.length; columnIndex++){
                System.out.print(df.mainFrame.get(columnIndex).get(colIndex));
                System.out.print("   ");
            }
            System.out.println(" ");
        }

    }








    @Override      //zaimplementowac klonowanie
    public DataFrame clone() throws CloneNotSupportedException {
        DataFrame sklonowana = new DataFrame(this.titles, this.types);
        ArrayList<Value> newRow=null;
        for(int i=0; i<this.size(); i++){
            for(int j=0; j<this.titles.length; j++){
                newRow = new ArrayList<>();
                newRow.add(this.mainFrame.get(j).get(i));
            }
            sklonowana.addRow(newRow);
        }
        return sklonowana;
    }


  public GroupByStructure groupBy(String[] colname){  //wywalaj wyjatek jak nie ma takiej kolumny
     if(colname.length ==1){

     }
     ArrayList<String> zbior_id = new ArrayList<>();
     int[] indexOfWhat=new int[0];
     for(int i=0; i<titles.length; i++){
        for(int j=0; j<colname.length; j++){
           if(titles[i].equals(colname[j])){
                 indexOfWhat=extendIntArray(indexOfWhat);
                 indexOfWhat[indexOfWhat.length-1] = i;
           }
        }
     }
     //System.out.println(indexOfWhat.length);

     for(int colIndex=0; colIndex<mainFrame.get(0).size(); colIndex++){
         String checkedString = keyMaker(indexOfWhat, colIndex);
         if(! zbior_id.contains(checkedString)) {
             zbior_id.add(checkedString);
         }
     }
     Collections.sort(zbior_id);

      HashMap<String, ArrayList<ArrayList>> zbior_df = new HashMap<>();
      HashSet<Integer> usedRowsIds = new HashSet<>();

      for(int keyIterator=0; keyIterator<zbior_id.size(); keyIterator++){ //to sie wykona tyle razy, ile jest kluczy
          ArrayList<ArrayList> added_DF = new ArrayList<>();

          for(int colIndex=0; colIndex<this.mainFrame.get(0).size(); colIndex++){ //to sie wykona dlugosc kolumny razy
              if (usedRowsIds.contains(colIndex)) {
                  continue;
              }
              else{
                  String someKey = keyMaker(indexOfWhat, colIndex);

                  if(someKey.equals(zbior_id.get(keyIterator))){
                      ArrayList<Value> row= new ArrayList<>();
                      Task1:
                      for(int columnIt=0; columnIt<titles.length; columnIt++){
                          for(int i=0; i<indexOfWhat.length; i++) {
                              if(columnIt==indexOfWhat[i]) {
                                  continue Task1;
                              }
                          }
                          row.add(this.mainFrame.get(columnIt).get(colIndex));
                      }
                      added_DF.add(row);
                      usedRowsIds.add(colIndex);
                  }
              }
          }
          zbior_df.put(zbior_id.get(keyIterator), added_DF);
      }
      return new GroupByStructure(zbior_df, colname, titles, vTypes, zbior_id);
    }



    public ThreadedStructure threadedGroupBy(String[] colname){  //wywalaj wyjatek jak nie ma takiej kolumny
        if(colname.length ==1){

        }
        ArrayList<String> zbior_id = new ArrayList<>();
        int[] indexOfWhat=new int[0];
        for(int i=0; i<titles.length; i++){
            for(int j=0; j<colname.length; j++){
                if(titles[i].equals(colname[j])){
                    indexOfWhat=extendIntArray(indexOfWhat);
                    indexOfWhat[indexOfWhat.length-1] = i;
                }
            }
        }

        for(int colIndex=0; colIndex<mainFrame.get(0).size(); colIndex++){
            String checkedString = keyMaker(indexOfWhat, colIndex);
            if(! zbior_id.contains(checkedString)) {
                zbior_id.add(checkedString);
            }
        }
        Collections.sort(zbior_id);

        HashMap<String, ArrayList<ArrayList>> zbior_df = new HashMap<>();
        HashSet<Integer> usedRowsIds = new HashSet<>();

        for(int keyIterator=0; keyIterator<zbior_id.size(); keyIterator++){ //to sie wykona tyle razy, ile jest kluczy
            ArrayList<ArrayList> added_DF = new ArrayList<>();

            for(int colIndex=0; colIndex<this.mainFrame.get(0).size(); colIndex++){ //to sie wykona dlugosc kolumny razy
                if (usedRowsIds.contains(colIndex)) {
                    continue;
                }
                else{
                    String someKey = keyMaker(indexOfWhat, colIndex);

                    if(someKey.equals(zbior_id.get(keyIterator))){
                        ArrayList<Value> row= new ArrayList<>();
                        Task1:
                        for(int columnIt=0; columnIt<titles.length; columnIt++){
                            for(int i=0; i<indexOfWhat.length; i++) {
                                if(columnIt==indexOfWhat[i]) {
                                    continue Task1;
                                }
                            }
                            row.add(this.mainFrame.get(columnIt).get(colIndex));
                        }
                        added_DF.add(row);
                        usedRowsIds.add(colIndex);
                    }
                }
            }
            zbior_df.put(zbior_id.get(keyIterator), added_DF);
        }
        return new ThreadedStructure(zbior_df, colname, titles, vTypes, zbior_id);
    }


    public int [] extendIntArray(int [] array){
        int[] temp = array.clone();
        array = new int[temp.length + 1];
        System.arraycopy(temp, 0, array, 0, temp.length);
        return array;
    }

    public String keyMaker(int[] indeksyGroupByWhat, int colIndex){
        String klucz1 =  mainFrame.get(indeksyGroupByWhat[0]).get(colIndex).toString();
        StringBuilder jakisKlucz = new StringBuilder(klucz1);
        if (indeksyGroupByWhat.length>1){
            for(int i=1; i<indeksyGroupByWhat.length; i++){
                jakisKlucz.append(";;");
                String klucz2 =  mainFrame.get(indeksyGroupByWhat[i]).get(colIndex).toString();
                jakisKlucz.append(klucz2);
            }
        }
        String gotowyKlucz = jakisKlucz.toString();
        return gotowyKlucz;
    }




}



