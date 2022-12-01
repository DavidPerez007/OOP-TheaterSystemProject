
package controler;

import DAO.DAOObras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Obra;
import view.AdminCRUDObras;
import view.AdminMainWindow;
import view.EmpleadoMainWindow;


public class ControlObras implements ActionListener{
    private Obra modeloObras;
    private AdminCRUDObras adminCRUDObras;
    private boolean esAdmin;
  
    public ControlObras(Obra modeloObras, AdminCRUDObras adminCRUDObras, boolean esAdmin) {
        this.modeloObras = modeloObras;
        this.adminCRUDObras = adminCRUDObras;
        this.esAdmin = esAdmin;
        this.adminCRUDObras.getAnadirObra().addActionListener(this);
        this.adminCRUDObras.getModificarObra().addActionListener(this);
        this.adminCRUDObras.getBuscarObra().addActionListener(this);
        this.adminCRUDObras.getEliminarObra().addActionListener(this);
        this.adminCRUDObras.getRegresarBtn().addActionListener(this);
        
     }

    ControlObras(Obra modeloObras) {
    }

    public void actionPerformed(ActionEvent evento) {
      //Nueva Obra
        if(adminCRUDObras.getAnadirObra()== evento.getSource()) { 
            modeloObras.setNombre(adminCRUDObras.getNombreObra().getText());
            modeloObras.setPrimerActor(adminCRUDObras.getPrimerActor().getText());
            modeloObras.setSegundoActor(adminCRUDObras.getSegundoActor().getText());
            modeloObras.setResumen(adminCRUDObras.getResumenObra().getText());
            modeloObras.setGenero(adminCRUDObras.getGeneroObra().getText());
            int duracionObra = Integer.parseInt(adminCRUDObras.getDuracionObra().getText());
            modeloObras.setDuracion(duracionObra);
            double precioObra = Double.parseDouble(adminCRUDObras.getPrecioObra().getText());
            modeloObras.setPrecio(precioObra);

           // Se crea el objeto DAO para guardar la nueva obra al archivo .TXT
            DAOObras daoObras = new DAOObras();
            try{
                daoObras.insertar(modeloObras);
            }catch(Exception e){
                e.printStackTrace();
            }
        } 

        //Buscar una obra por su nombre 
        if(adminCRUDObras.getBuscarObra()== evento.getSource()) { 
            //Cambiar, necesito buscar un String
            String nombre =adminCRUDObras.getNombreObra().getText();
           // Se crea el objeto DAO para buscar la obra en el archivo .TXT
            DAOObras daoObras = new DAOObras();
            try{
                Obra obras = daoObras.consultar(nombre);
                
                if (obras!=null){
                    adminCRUDObras.getNombreObra().setText(obras.getNombre());
                    adminCRUDObras.getPrimerActor().setText(obras.getPrimerActor());
                    adminCRUDObras.getSegundoActor().setText(obras.getSegundoActor());
                    adminCRUDObras.getGeneroObra().setText(obras.getGenero());
                    adminCRUDObras.getResumenObra().setText(obras.getResumen());
                    adminCRUDObras.getPrecioObra().setText(String.valueOf(obras.getPrecio()));
                    adminCRUDObras.getDuracionObra().setText(String.valueOf(obras.getDuracion()));
              
                }else{
                    System.out.println("Registro no encontrado");
                }    
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //Modificar una Obra
        if(adminCRUDObras.getModificarObra()== evento.getSource()) { 
            modeloObras.setNombre(adminCRUDObras.getNombreObra().getText());
            modeloObras.setPrimerActor(adminCRUDObras.getPrimerActor().getText());
            modeloObras.setSegundoActor(adminCRUDObras.getSegundoActor().getText());
            modeloObras.setGenero(adminCRUDObras.getGeneroObra().getText());
            modeloObras.setResumen(adminCRUDObras.getResumenObra().getText());
            double precio = Double.parseDouble(adminCRUDObras.getPrecioObra().getText());
            modeloObras.setPrecio(precio);
            int duracion = Integer.parseInt(adminCRUDObras.getDuracionObra().getText());
            modeloObras.setDuracion(duracion);

           // Se crea el objeto DAO para modificar el libro en el archivo .TXT
            DAOObras daoObras = new DAOObras();
            try{
                daoObras.modificar(modeloObras);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        if(adminCRUDObras.getEliminarObra()== evento.getSource()) { 
            //CAMBIAR PARA BUSCAR POR NOMBRE
            String nombre =adminCRUDObras.getNombreObra().getText();
            
           // Se crea el objeto DAO para buscar el libro en el archivo .TXT
            DAOObras daoObras = new DAOObras();
            try{
                Obra obra = daoObras.consultar(nombre);
                if(obra != null){
                    daoObras.eliminar(obra);
                }else {
                    System.out.println("No se puede eliminar, no existe la obra");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        if(this.adminCRUDObras.getRegresarBtn() == evento.getSource()){
            if(this.esAdmin == true){
                AdminMainWindow ventana_main_admin = new AdminMainWindow();
                ControlAdminMainView control_admin = new ControlAdminMainView(ventana_main_admin);
                ventana_main_admin.setVisible(true);
                ventana_main_admin.setLocationRelativeTo(null);
                this.adminCRUDObras.setVisible(false);
            } else {
                EmpleadoMainWindow ventana_main_empleado = new EmpleadoMainWindow();
                ventana_main_empleado.setVisible(true);
                ventana_main_empleado.setLocationRelativeTo(null);
                this.adminCRUDObras.setVisible(false);
                ControlEmpleadoMainWindow control = new ControlEmpleadoMainWindow(ventana_main_empleado);
            }
        }

    }    
}
