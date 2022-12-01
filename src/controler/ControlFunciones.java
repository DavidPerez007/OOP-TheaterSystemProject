
package controler;

import DAO.DAOFunciones;
import DAO.DAOObras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import model.Funcion;
import view.AdminCRUDFuncion;
import controler.ControlObras;
import model.Obra;
import view.AdminMainWindow;
import view.EmpleadoMainWindow;


public class ControlFunciones implements ActionListener{
    private Funcion modeloFuncion;
    private Obra modeloObras;
    private AdminCRUDFuncion vistaCRUDFuncion;
    private boolean esAdmin;


    ControlFunciones(Funcion modeloFuncion, Obra modeloObras, AdminCRUDFuncion adminCRUDFuncion, boolean esAdmin) {
        this.modeloFuncion = modeloFuncion;
        this.modeloObras = modeloObras;
        this.vistaCRUDFuncion = adminCRUDFuncion;
        this.esAdmin = esAdmin;
        this.vistaCRUDFuncion.getAniadirFuncion().addActionListener(this);
        this.vistaCRUDFuncion.getModificarFuncion().addActionListener(this);
        this.vistaCRUDFuncion.getBuscarFuncion().addActionListener(this);
        this.vistaCRUDFuncion.getEliminarFuncion().addActionListener(this);
        this.vistaCRUDFuncion.getRegresarBtn().addActionListener(this);
    }
    
    public Obra buscarObras(String nombre_obra){
        ControlObras control_obra = new ControlObras(modeloObras);
        Obra obra;
        DAOObras dao_obras = new DAOObras();
        obra = dao_obras.consultar(nombre_obra);
        return obra;
    }

    public void actionPerformed(ActionEvent evento) {
      //Nueva Obra
        if(vistaCRUDFuncion.getAniadirFuncion()== evento.getSource()) {
            System.out.println("Queremos agregar funcion");
            Obra obra = buscarObras(vistaCRUDFuncion.getTxtNombreObra().getText());
            System.out.println(obra);
            modeloFuncion.setObra(obra);
            modeloFuncion.setFecha_presentacion(vistaCRUDFuncion.getTxtFecha().getText());
            modeloFuncion.setHora_presentacion(vistaCRUDFuncion.getTxtHora().getText());

           // Se crea el objeto DAO para guardar la nueva obra al archivo .TXT
            DAOFunciones daoFuncion = new DAOFunciones();
            try{
                daoFuncion.addFuncion(modeloFuncion);
            }catch(Exception e){
                e.printStackTrace();
            }
        } 

        //Buscar una obra por su nombre 
        if(vistaCRUDFuncion.getBuscarFuncion()== evento.getSource()) { 
            System.out.println("Queremos buscar funcion");
            //Cambiar, necesito buscar un String
            String nombre =vistaCRUDFuncion.getTxtNombreObra().getText();
            Obra obra = buscarObras(nombre);
            // Se crea el objeto DAO para buscar la obra en el archivo .TXT
            DAOFunciones daoFunciones = new DAOFunciones();
            try{
                Funcion funcion = daoFunciones.buscarFuncion(nombre);
                if (obra!=null){
                    vistaCRUDFuncion.getTxtNombreObra().setText(obra.getNombre());
                    vistaCRUDFuncion.getTxtFecha().setText(funcion.getFecha_presentacion());
                    vistaCRUDFuncion.getTxtHora().setText(funcion.getHora_presentacion());
              
                }else{
                    System.out.println("Registro no encontrado");
                }    
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        //Modificar una Funcion
        if(vistaCRUDFuncion.getModificarFuncion()== evento.getSource()) {
            System.out.println("Queremos modificar funcion");
            modeloFuncion.setFecha_presentacion(vistaCRUDFuncion.getTxtFecha().getText());
            modeloFuncion.setHora_presentacion(vistaCRUDFuncion.getTxtHora().getText());
            Obra obra = buscarObras(vistaCRUDFuncion.getTxtNombreObra().getText());
            modeloFuncion.setObra(obra);
           

           // Se crea el objeto DAO para modificar el libro en el archivo .TXT
            DAOFunciones daoFunciones = new DAOFunciones();
            try{
                daoFunciones.modificarFuncion(modeloFuncion);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        if(vistaCRUDFuncion.getEliminarFuncion()== evento.getSource()) { 
            System.out.println("Queremos eliminar funcion");
            //CAMBIAR PARA BUSCAR POR NOMBRE
            String nombre = vistaCRUDFuncion.getTxtNombreObra().getText();
            DAOFunciones dao_funcion = new DAOFunciones(); 
            
            try{
                Funcion funcion = dao_funcion.buscarFuncion(nombre);
                 
                if(funcion != null){
                    dao_funcion.eliminarFuncion(funcion);
                }else {
                    System.out.println("No se puede eliminar, no existe la obra");
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        
        if(this.vistaCRUDFuncion.getRegresarBtn() == evento.getSource()){
            if(this.esAdmin == true){
                AdminMainWindow ventana_main_admin = new AdminMainWindow();
                ControlAdminMainView control_admin = new ControlAdminMainView(ventana_main_admin);
                ventana_main_admin.setVisible(true);
                ventana_main_admin.setLocationRelativeTo(null);
                this.vistaCRUDFuncion.setVisible(false);
            } else {
                EmpleadoMainWindow ventana_main_empleado = new EmpleadoMainWindow();
                ventana_main_empleado.setVisible(true);
                ventana_main_empleado.setLocationRelativeTo(null);
                this.vistaCRUDFuncion.setVisible(false);
                ControlEmpleadoMainWindow control = new ControlEmpleadoMainWindow(ventana_main_empleado);
            }
        }

    }    
}
