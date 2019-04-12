package df;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException, ParseException {
      //  DB sth = new DB();
      //  sth.connect();
      //  sth.listNames();
        //DataFrame df11 = new DataFrame("src/resources/groupby.csv", new String[]{"double", "double", "double"}, true, true);
      //  System.out.println(df10.size());
       // df11.wyprintuj(df11);
     /*   for(int i=0; i< df11.titles.length; i++){
            System.out.println(df11.titles[i]);
        }*/
      //  ArrayList<Class<? extends Value>> listaTypow = new ArrayList<>();
  /*      ArrayList<Value> doKonstruktora = new ArrayList<>();
        doKonstruktora.add(StringValue.class);
*/

   //     DataFrame df11 = new DataFrame("src/resources/groupby.csv", new ArrayList<Class<? extends Value>>({StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class}), true);

        DataFrame df11 = new DataFrame("src/resources/groupby_skrot2.csv", new Class[]{StringValue.class, DateTimeValue.class, DoubleValue.class, DoubleValue.class}, true);

     //   GroupByStructure struktura = df11.groupBy(new String[]{"id"});

    //    DataFrame df2 = struktura.max();
      //  df2.wyprintuj(df2);

//        df11.wyprintuj(df11); //- dziala
//        System.out.println("koniec df11");
        //liczba typow z konstruktora musi byc rowna liczbie kolumn w tworzonej df -  jesli nie, wywalic wyjatek! (uzytkownik ma poprawic liczbe nazwa typow)

        /*    SparseDataFrame sdpf2 = new SparseDataFrame(df11, "0");
        System.out.println("printuje ta z pliku:");  //nie do konca dziala jak trzeba przez typy!
        sdpf2.wyprintuj(sdpf2);
        System.out.println("skonczylam printowac");

      */
       /* ArrayList<Value> rzad = df11.getRow(0);
        for (int j=0; j<rzad.size(); j++){
            System.out.println(rzad.get(j));
        }*/

       DataFrameDB nowa = new DataFrameDB(df11);


      /*  DataFrame nowa =  df11.iloc(1);
        nowa.wyprintuj(nowa);  */
      //  GroupByStructure struktura = df11.groupBy(new String[]{"id"});
     //   boolean threaded = false;
//
      /*  DataFrame max = struktura.max();
        max.wyprintuj(max);
*/
      /*  if(threaded ==true){
           // ThreadedStructure watkowaStr = df11.threadedGroupBy(new String[]{"id"});
            GroupByStructure watkowaStr = df11.groupBy(new String[]{"id"});
            String typ_operacji = "max";
            OperationOnDf nowa_operacja = new OperationOnDf(watkowaStr, typ_operacji);
            DataFrame trMax = watkowaStr.max();
            trMax.wyprintuj(trMax);

            //tutaj odpalamy wielowatkowo
        }
*/
   // df11.wyprintuj(df11);


      //  struktura.apply(max);
       // DataFrame max  = struktura.max();
       // DataFrame min = struktura.min();

      //  max.wyprintuj(max);
       // min.wyprintuj(min);

   /*     DataFrame mean = struktura.mean();
        mean.wyprintuj(mean);*/
   /*     System.out.println("tytuly:");
        for(int i=0; i<mean.titles.length; i++){
            System.out.println(mean.titles[i]);
        }
        System.out.println("typy:");
        for(int i=0; i<mean.types.length; i++){
            System.out.println(mean.types[i]);
        }*/
   /*     DataFrame var = struktura.var();
        var.wyprintuj(var);*/

        /*DataFrame std = struktura.std();
        std.wyprintuj(std);*/

      /*  DataFrame sum = struktura.sum();
        sum.wyprintuj(sum);*/





      //  wyprintuj(struktura);

        /*  DataFrame df7 = new DataFrame(new String[]{"id", "v1", "v2"}, new String[]{"String", "int", "int"});
        df7.addRow(new ArrayList<Value>(){{add("a"); add(1); add(3);}});
        df7.addRow(new ArrayList<Object>(){{add("c"); add(7); add(9);}});
        df7.addRow(new ArrayList<Object>(){{add("b"); add(5); add(6);}});
        df7.addRow(new ArrayList<Object>(){{add("a"); add(2); add(4);}});
        df7.addRow(new ArrayList<Object>(){{add("c"); add(8); add(10);}});

       // Column

        GroupByStructure dgbList = df7.groupBy("id"); */

        //DataFrame df8 = dgbList.apply(max);




       /* String s1 = "7";
        IntegerValue int1 = new IntegerValue();
        IntegerValue int2 = (IntegerValue)int1.create(s1);
        System.out.println(int2);

        String s2 = "7";
        DoubleValue double1 = new DoubleValue();
        DoubleValue double2 = (DoubleValue)double1.create(s2);
        System.out.println(double2);

        String s3 = "7.0f";
        FloatValue float1 = new FloatValue();
        FloatValue float2 = (FloatValue)float1.create(s3);
        System.out.println(float2);

        String s4 = "something";
        StringValue str1 = new StringValue();
        StringValue str2 = (StringValue)str1.create(s4);
        System.out.println(str2);

        String s5 = "2018.02.08_23:43:23";
        DateTimeValue dt1 = new DateTimeValue();
        DateTimeValue dt2 = (DateTimeValue)dt1.create(s5);
        System.out.println(dt2);*/


       //------------------------------SPARSE DATA FRAME TESTS---------------------------
       // DataFrame df1 = new DataFrame(new String[]{"kol1"}, new String[]{"int"});//, true   dla podawania pojedynczej kolumny jako wartosci
/*        DataFrame df1 = new DataFrame(new String[]{"kol1", "kol2", "kol3"}, new String[]{"int", "int", "int"});

         IntegerValue int1 = new IntegerValue(0);
         IntegerValue int2 = new IntegerValue(5);
         IntegerValue int3 = new IntegerValue(0);
         IntegerValue int4 = new IntegerValue(0);
         IntegerValue int5 = new IntegerValue(2);
         IntegerValue int6 = new IntegerValue(0);
         IntegerValue int7 = new IntegerValue(0);

         ArrayList<Value> list1 = new ArrayList<Value>();//({int1, int2, int3, int4});
         list1.add(int1);
         list1.add(int2);
         list1.add(int1);

        ArrayList<Value> list2 = new ArrayList<Value>();//({int1, int2, int3, int4});
        list2.add(int5);
        list2.add(int1);
        list2.add(int1);

        ArrayList<Value> list3 = new ArrayList<Value>();//({int1, int2, int3, int4});
        list3.add(int1);
        list3.add(int5);
        list3.add(int5);

        df1.addRow(list1);
        df1.addRow(list2);
        df1.addRow(list3);
        System.out.println("Teraz wyprintuję starą df:");
        df1.wyprintuj(df1);
        System.out.println("Skonczylam ja printowac");
       // System.out.println("Skoczylam printowac df1!");

*/


       //---------------------COOValue tests!----------------------
    /*    IntegerValue int1 = new IntegerValue(7);
        IntegerValue int2 = new IntegerValue(5);
        COOValue cooV1 = new COOValue(1, int1);
        COOValue cooV2 = new COOValue(3, int2);

        String exp1 = "blablab";
        String exp2 = "sialalla";

        System.out.println(cooV1.toString());
        System.out.println(cooV2.toString());             */
       //koniec COOValue test--------------------------------
        //------------------------------koniec sparseDataFrameTest----------------------------

    /*    DataFrame df1= new DataFrame(new String[]{"kol1", "kol2"}, new String[]{"int", "double"});
      //  DataFrame df2= new DataFrame("data.csv", new String[]{"double", "double", "double"}, false);
      //  DataFrame df3= new DataFrame("data.csv", new String[]{"double", "double", "double"}, true);

    //    ArrayList row1 = new ArrayList<Object>()
        df1.addRow(new ArrayList<Object>(){{add(8); add(9.0);}});
        df1.addRow(new ArrayList<Object>(){{add(9); add(6.12);}});
        df1.addRow(new ArrayList<Object>(){{add(7); add(1.078);}});
      //  df1.addRow(new Object[]{9,6.12});
      //  df1.addRow(new Object[]{7,1.078});
                            */
/////////////////////////////////////////////////////////////////////////////////

/*
        IntegerValue int1 =new IntegerValue(7);
        String intString = int1.toString();
        System.out.print(intString);

        IntegerValue int2 = int1;//new IntegerValue(int1);
        String intString2 = int2.toString();
        System.out.print(intString2);

        boolean isEqual = int1.equals(int2);
        System.out.print(isEqual);
*/


        
////////////////////////////////////////////////////////////// TESTY STRING tez dzialaja
/*
        StringValue flo1 = new StringValue("cos1");
        String floatString = flo1.toString();
        System.out.print(floatString);

        StringValue flo2 = new StringValue("cos2");
        String floatString2 = flo2.toString();
        System.out.print(floatString2);

        StringValue stdD = (StringValue)flo1.add(flo2);
        System.out.print(stdD);

        StringValue stdD1 = (StringValue)flo1.sub(flo2);
        System.out.print(stdD1);

        StringValue stdD2 = (StringValue)flo1.mul(flo2);
        System.out.print(stdD2);

        StringValue stdD3 = (StringValue)flo1.div(flo2);
        System.out.print(stdD3);

        StringValue stdD4 = (StringValue)flo1.pow(flo2);
        System.out.print(stdD4);

        boolean isEqual = flo1.eq(flo2);
        System.out.print(isEqual);

        boolean lessOrEqual = flo1.lte(flo2);
        System.out.print(lessOrEqual);

        boolean greaterOrEqual = flo1.gte(flo2);
        System.out.print(greaterOrEqual);

        boolean notEqual = flo1.neq(flo2);
        System.out.print(notEqual);   */


////////////////////////////////////////////////////////////// TESTY DATAFRAME

       // String timeStamp1 = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss").format(Calendar.getInstance().getTime());
       // System.out.print(timeStamp1+"\n");
/*
        DateTimeValue flo1 = new DateTimeValue(new Date());
        String floatString = flo1.toString();
        System.out.print(floatString);

        System.out.print("\n");          //opoznienie kolejnej daty
        String litery = "";
        for(long i=0; i<30000L; i++){
            litery += floatString;
        }

        DateTimeValue flo2 = new DateTimeValue(new Date());
        String floatString2 = flo2.toString();
        System.out.print(floatString2);


        DateTimeValue stdD = (DateTimeValue)flo1.add(flo2);
        System.out.print(stdD);

        DateTimeValue stdD1 = (DateTimeValue)flo1.sub(flo2);
        System.out.print(stdD1);

        DateTimeValue stdD2 = (DateTimeValue)flo1.mul(flo2);
        System.out.print(stdD2);

        DateTimeValue stdD3 = (DateTimeValue)flo1.div(flo2);
        System.out.print(stdD3);

        DateTimeValue stdD4 = (DateTimeValue)flo1.pow(flo2);
        System.out.print(stdD4);

        boolean isEqual = flo1.eq(flo2);
        System.out.print(isEqual);

        boolean lessOrEqual = flo1.lte(flo2);
        System.out.print(lessOrEqual);

        boolean greaterOrEqual = flo1.gte(flo2);
        System.out.print(greaterOrEqual);

        boolean notEqual = flo1.neq(flo2);
        System.out.print(notEqual);

*/

























        //tesciki do lab1
        /*DataFrame dfIloc = df1.iloc(1);
        for(int i=0; i<dfIloc.titles.length; i++){
            System.out.print(dfIloc.mainFrame.get(i));
        }

        DataFrame dfIloc2 = df1.iloc(0,2);
        for(int i=0; i<dfIloc2.titles.length; i++){
            //printuje slupki
            for(int j=0; j<dfIloc2.titles.length; j++){
                System.out.print(dfIloc2.mainFrame.get(i).get(j));
            }
        }  */



     /*   ArrayList kolumna1 = df1.get("kol1");
        for (Object elK1 : kolumna1) {
            System.out.print("\n" + elK1);
        }
        ArrayList kolumna2 = df1.get("kol2");
        for (Object elK2 : kolumna2) {
            System.out.print("\n" + elK2);
        }  */


      //  System.out.print("Hello!"+ df2.size());
      //  System.out.print("Hello!"+ df1.size());
       // System.out.print("Hello!"+ df3.size());
    }
}
