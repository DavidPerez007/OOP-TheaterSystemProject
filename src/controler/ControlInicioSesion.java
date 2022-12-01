
package controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import view.VistaLogIn;
import model.Usuario;
import DAO.DAOAdmin;
import DAO.DAOEmpleado;
import view.AdminMainWindow;
import view.EmpleadoMainWindow;

public class ControlInicioSesion implements ActionListener{

    public Usuario getModelo_usuario() {
        return modelo_usuario;
    }

    public void setModelo_usuario(Usuario modelo_usuario) {
        this.modelo_usuario = modelo_usuario;
    }

    public VistaLogIn getVista_log_in() {
        return vista_log_in;
    }

    public void setVista_log_in(VistaLogIn vista_log_in) {
        this.vista_log_in = vista_log_in;
    }
    
    private Usuario modelo_usuario;
    private VistaLogIn vista_log_in;
    public ControlInicioSesion(Usuario modelo_usuario, VistaLogIn vista_log_in){
        setModelo_usuario(modelo_usuario);
        setVista_log_in(vista_log_in);
        getVista_log_in().getBtnIngresar().addActionListener(this);
        getVista_log_in().getBtnCancelar().addActionListener(this); 
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(getVista_log_in().getBtnIngresar() == e.getSource()){
            String nombre = getVista_log_in().getFieldNombreUsuario().getText();
            String contrasenia = getVista_log_in().getFieldContrasenia().getText();
            DAOAdmin dao_admin = new DAOAdmin();
            DAOEmpleado dao_empleado = new DAOEmpleado();
            if (dao_admin.buscar_admin(nombre, contrasenia) != null){
                
                AdminMainWindow ventana_main_admin = new AdminMainWindow();
                ControlAdminMainView control_admin = new ControlAdminMainView(ventana_main_admin);
                ventana_main_admin.setVisible(true);
                getVista_log_in().setVisible(false);
            }
            else if (dao_empleado.buscar_empleado(nombre, contrasenia) != null){
                EmpleadoMainWindow ventana_main_empleado = new EmpleadoMainWindow();
                ventana_main_empleado.setVisible(true);
                getVista_log_in().setVisible(false);
                ControlEmpleadoMainWindow control = new ControlEmpleadoMainWindow(ventana_main_empleado);
            }
            else{

                System.out.println("Usuario no encontrado");
            }
        } else if(getVista_log_in().getBtnCancelar() == e.getSource()){
            getVista_log_in().getFieldNombreUsuario().setText(null);
            getVista_log_in().getFieldContrasenia().setText(null);
        }
    }

    
}
