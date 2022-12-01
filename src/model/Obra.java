
package model;

public class Obra {

    private String nombre;
    private String primerActor;
    private String segundoActor;
    private String genero;
    private String resumen;
    private double precio;
    private int duracion;
    
    public Obra(){
        
    }
    public Obra(String nombre, String primerActor, String segundoActor,String genero, String resumen, double precio,int duracion){
        this.nombre=nombre;
        this.primerActor=primerActor;
        this.segundoActor=segundoActor;
        this.genero=genero;
        this.resumen=resumen;
        this.precio=precio;
        this.duracion=duracion;
    }
    public String getNombre() {
        return nombre;
    }

   
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

  
    public String getPrimerActor() {
        return primerActor;
    }

   
    public void setPrimerActor(String primerActor) {
        this.primerActor = primerActor;
    }

   
    public String getSegundoActor() {
        return segundoActor;
    }

   
    public void setSegundoActor(String segundoActor) {
        this.segundoActor = segundoActor;
    }

   
    public String getGenero() {
        return genero;
    }

   
    public void setGenero(String genero) {
        this.genero = genero;
    }

  
    public String getResumen() {
        return resumen;
    }

    
    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    
    public double getPrecio() {
        return precio;
    }

    
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    
    public int getDuracion() {
        return duracion;
    }
    
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
