package df;

class TypesNotCompatibleException extends Exception{
    public TypesNotCompatibleException() {
        System.out.println("Types of arguments are not compatible");
    }
    public TypesNotCompatibleException(String message){
        super(message);
    }
}
class ExceptionSample {
    public void doSomething() throws TypesNotCompatibleException{
        System.out.println("Zgloszenie wyjatku MyException");
        throw new TypesNotCompatibleException();
    }
    public static void main(String[] args) {
        ExceptionSample sample = new ExceptionSample();
        try {
            sample.doSomething();
        } catch (TypesNotCompatibleException e) {
            e.printStackTrace();
            System.out.println("Wyjatek zostal zlapany");
        }
    }
}