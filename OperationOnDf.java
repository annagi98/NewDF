package df;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Callable;

    public class OperationOnDf implements Callable<DataFrame> { //zwracanie dowolnego typu wynikow
        private String typ_operacji;
        private ArrayList<ArrayList> kawalek;
      //  private ArrayList<String> key_id;  -  nr ko
        public OperationOnDf(String type, ArrayList<ArrayList> gbs){
            typ_operacji = type;
            kawalek = gbs;
           // key_id = id;
        }

        @Override
        public DataFrame call() {
            DataFrame ret_val = null;



     return null;
        }
    }

