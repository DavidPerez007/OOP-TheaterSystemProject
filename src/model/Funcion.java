
package model;
import java.util.ArrayList;
import model.Obra;

public class Funcion {

   private Obra obra;
   private String fecha_presentacion;
   private String hora_presentacion;
   private ArrayList<Seat> asientos = new ArrayList<>();
 
    public Funcion(){
        
    }
    public Funcion(Obra obra, String fecha_presentacion, String hora_presentacion){
       this.obra = obra;
       this.fecha_presentacion = fecha_presentacion;
       this.hora_presentacion = hora_presentacion;
    }
    
     public Obra getObra() {
        return this.obra;
    }

    public void setObra(Obra obra) {
        this.obra = obra;
    }

    public String getFecha_presentacion() {
        return fecha_presentacion;
    }

    public void setFecha_presentacion(String fecha_presentacion) {
        this.fecha_presentacion = fecha_presentacion;
    }

    public String getHora_presentacion() {
        return hora_presentacion;
    }

    public void setHora_presentacion(String hora_presentacion) {
        this.hora_presentacion = hora_presentacion;
    }
}
