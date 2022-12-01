package controler;

import controler.ControlObras;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Admin;
import model.Empleado;
import model.Funcion;
import model.Obra;
import model.Usuario;
import view.AdminCRUDFuncion;
import view.AdminMainWindow;
import view.AdminUsuarioCRUDWindow;
import view.AdminCRUDObras;
import view.VistaLogIn;

public class ControlAdminMainView implements ActionListener {

    AdminMainWindow vista = new AdminMainWindow();

    public ControlAdminMainView(AdminMainWindow vista) {
        this.vista = vista;
        this.vista.getModificar_usuarios().addActionListener(this);
        this.vista.getModificarObra().addActionListener(this);
        this.vista.getModificar_funciones().addActionListener(this);
        this.vista.getCerrarSesion().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean esAdmin = true;
        if (this.vista.getModificar_usuarios() == e.getSource()) {
            AdminUsuarioCRUDWindow usuario_CRUD = new AdminUsuarioCRUDWindow();
            Admin modelo_admin = new Admin();
            Empleado modelo_empleado = new Empleado();
            ControlModificarUsuarios ctrl_usuario_CRUD = new ControlModificarUsuarios(modelo_admin, modelo_empleado, usuario_CRUD);
            System.out.println("Preisonamos botón para ir a CRUD");
            usuario_CRUD.setVisible(true);
            usuario_CRUD.setLocationRelativeTo(null);
            this.vista.setVisible(false);

        } else if (this.vista.getModificarObra() == e.getSource()) {
            System.out.println("modificar obras");
            Obra modeloObras = new Obra();
            AdminCRUDObras adminCRUDObras = new AdminCRUDObras();
            ControlObras controlObras = new ControlObras(modeloObras, adminCRUDObras, esAdmin);
            //controlador.iniciarVista();
            adminCRUDObras.setVisible(true);
            adminCRUDObras.setLocationRelativeTo(null);
            this.vista.setVisible(false);
        
        }else if (this.vista.getModificar_funciones()== e.getSource()){
            System.out.println("modificar funciones");
            Funcion modeloFuncion = new Funcion();
            Obra modeloObras = new Obra();
            AdminCRUDFuncion adminCRUDFuncion = new AdminCRUDFuncion();
            ControlFunciones controlFuncion = new ControlFunciones(modeloFuncion, modeloObras, adminCRUDFuncion, esAdmin);
            adminCRUDFuncion.setVisible(true);
            adminCRUDFuncion.setLocationRelativeTo(null);
            this.vista.setVisible(false);
        
        }else if(this.vista.getCerrarSesion() == e.getSource()){
            System.out.println("Cerrando sesión");
            Usuario modeloUsuario = new Usuario();
            VistaLogIn vista_log_in = new VistaLogIn();
            ControlInicioSesion control_log_in = new ControlInicioSesion(modeloUsuario, vista_log_in);
            vista_log_in.setVisible(true);
            vista_log_in.setLocationRelativeTo(null);
            this.vista.setVisible(false);
        }
       
        
    }

}
