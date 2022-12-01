package errores;

public class NotFoundPerformanceException extends Exception {

    public NotFoundPerformanceException(String mensaje) {
        super(mensaje);
    }

    public NotFoundPerformanceException() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}