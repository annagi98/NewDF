package df;

public class Simple {
    public int zmienna;
    /*public static void main(String args[]){
        System.out.println("Hello Java");
        Simple sim1 = new Simple(5);
        System.out.println(sim1.toString());
    } */
    Simple(int x){
        zmienna =x;
    }
     public String toString(){
         return "" + String.valueOf(zmienna) + "";
     }
     public int value(){
        return zmienna;
     }


}
