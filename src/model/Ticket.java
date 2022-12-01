package model;

import java.util.ArrayList;

public class Ticket {

    private final String nombreTeatro = "Teatro Chachita";
    private static int numVenta;
    private Funcion funcion;
    private String nombreFuncion;
    private int cantBoletosVendidos;
    private ArrayList<Seat> asientos;
    private Double precioFuncion;
    private double total;

    
    public Ticket(){
        
    }

    public Ticket(ArrayList<Seat> asientos, Funcion Funcion) {
        this.asientos = asientos;
        setFuncion(Funcion);
        setNombreFuncion(Funcion.getObra().getNombre());
        this.cantBoletosVendidos = asientos.size();
        numVenta = numVenta + 1;
        this.precioFuncion = funcion.getObra().getPrecio();

    }
  
    public ArrayList obtenerIDAsientos(){
        ArrayList<String> idAsientos = new ArrayList<>();
         for(int i = 0; i < asientos.size(); i++){
            String idAsiento = asientos.get(i).getId();
            idAsientos.add(idAsiento);
        }
        return idAsientos;
    }
    
    public ArrayList<Double> obtenerPrecioAsientos(){
        ArrayList<Double> precioAsientos = new ArrayList<>();
        for(Seat seat: this.asientos){
            precioAsientos.add(seat.getPrice());
        }

        return precioAsientos;
    }
    
    public void calcularTotal(ArrayList<Double> precios){
        Double sumaPrecios = 0.0;
        Double precioBase = this.precioFuncion;
        for (int i = 0; i < asientos.size(); i++) {
            sumaPrecios = sumaPrecios + precios.get(i) * precioBase;
        }
        setTotal(sumaPrecios);
    }
    
    public String getNombreTeatro() {
        return nombreTeatro;
    }

    public int getNumVenta() {
        return numVenta;
    }

    public void setNumVenta(int numVentas) {
        numVenta = numVentas;
    }

    public Funcion getFuncion() {
        return funcion;
    }

    public void setFuncion(Funcion funcion) {
        this.funcion = funcion;
    }

    public int getBoletosVendidos() {
        return cantBoletosVendidos;
    }

    public void setBoletosVendidos(int boletosVendidos) {
        this.cantBoletosVendidos = boletosVendidos;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public String getNombreFuncion() {
        return nombreFuncion;
    }

    public void setNombreFuncion(String nombreFuncion) {
        this.nombreFuncion = nombreFuncion;
    }
 
}
