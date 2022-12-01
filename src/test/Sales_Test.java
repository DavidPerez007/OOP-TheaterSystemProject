
package test;

import DAO.DAOFunciones;
import DAO.DAOObras;
import controler.ControlResumen;
import errores.NotFoundPerformanceException;
import java.util.ArrayList;
import model.Funcion;
import model.Obra;
import model.Ticket;
import view.VistaResumen;
import model.Seat;

public class Sales_Test {

    
    public static void main(String[] args) {
        ArrayList<Seat> asientos = new ArrayList<>();
        DAOFunciones daoFuncion = new DAOFunciones();
        try{
            Funcion funcion = daoFuncion.buscarFuncion("El rey Leon");
            String obraNombre = funcion.getObra().getNombre();

            DAOObras daoObra = new DAOObras();
            Obra obra = daoObra.consultar(obraNombre);

            //Creando los boletos para pasarlos a Resumen
            Seat asiento = new Seat("A53");
            asientos.add(asiento);
            //Boleto boleto = new Boleto(funcion, );
            

           
            Ticket ticket = new Ticket(asientos, funcion);
            VistaResumen vistaResumen = new VistaResumen();
            ControlResumen controlResumen = new ControlResumen(ticket, vistaResumen);
            vistaResumen.setVisible(true);
        }
        catch(NotFoundPerformanceException e){
            System.out.println(e.getMessage());
        }
        
        
        
       
        

    }
    
}
