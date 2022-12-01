package controler;

import DAO.DAOAdmin;
import DAO.DAOEmpleado;
import model.Admin;
import view.AdminUsuarioCRUDWindow;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.Empleado;
import view.AdminMainWindow;

public class ControlModificarUsuarios implements ActionListener {

    private Admin modeloAdmin;
    private Empleado modeloEmpleado;
    private AdminUsuarioCRUDWindow vistaUsuario;

    public ControlModificarUsuarios(Admin modeloAdmin, Empleado modeloEmpleado, AdminUsuarioCRUDWindow vistaUsuario) {
        this.modeloAdmin = modeloAdmin;
        this.modeloEmpleado = modeloEmpleado;
        this.vistaUsuario = vistaUsuario;
        //Botones
        this.vistaUsuario.getAniadirUsuario().addActionListener(this);
        this.vistaUsuario.getModificarUsuarioBtn().addActionListener(this);
        this.vistaUsuario.getBuscarUsuarioBtn().addActionListener(this);
        this.vistaUsuario.getEliminarUsuarioBtn().addActionListener(this);
        this.vistaUsuario.getAniadirAdminBtn().addActionListener(this);
        this.vistaUsuario.getRegresarBtn().addActionListener(this);

    }

    public ControlModificarUsuarios() {
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        
        if (vistaUsuario.getAniadirAdminBtn() == evento.getSource()) {
            
            //Obtenemos el password, pero como es array de chars, hacemos casting a String
            String password = new String(vistaUsuario.getIngreseContraseniaTxtField().getPassword());
            String confirmacion_password = new String(vistaUsuario.getConfirmeContraseniaTxtField().getPassword());
            if (password.equals(confirmacion_password)) {
               
                modeloAdmin.setNombre(vistaUsuario.getNombreTxtField().getText());
                modeloAdmin.setApellido(vistaUsuario.getApellidoTxtField().getText());
                modeloAdmin.setContrasenia(password);
                modeloAdmin.setNombre_usuario(vistaUsuario.getUsuarioTxtField().getText());
                modeloAdmin.setCurp(vistaUsuario.getCurpTxtField().getText());

                DAOAdmin daoAdmin = new DAOAdmin();

                try {
                    
                    daoAdmin.agregar_admin(modeloAdmin);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error");
            }
        }

        if (vistaUsuario.getAniadirUsuario() == evento.getSource()) {
            if (vistaUsuario.getIngreseContraseniaTxtField().getText().equals(vistaUsuario.getConfirmeContraseniaTxtField().getText())) {
                modeloEmpleado.setNombre(vistaUsuario.getNombreTxtField().getText());
                modeloEmpleado.setApellido(vistaUsuario.getApellidoTxtField().getText());
                modeloEmpleado.setCurp(vistaUsuario.getCurpTxtField().getText());
                modeloEmpleado.setNombre_usuario(vistaUsuario.getUsuarioTxtField().getText());
                modeloEmpleado.setContrasenia(vistaUsuario.getIngreseContraseniaTxtField().getText());

                DAOEmpleado daoEmpleado = new DAOEmpleado();

                try {
                    daoEmpleado.agregar_empleado(modeloEmpleado);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Error");
            }
        }

        if (vistaUsuario.getBuscarUsuarioBtn() == evento.getSource()) {
            String nombre_usuario = vistaUsuario.getUsuarioTxtField().getText();

            DAOAdmin daoAdmin = new DAOAdmin();
            try {
                Admin administrador = daoAdmin.buscar_admin(nombre_usuario);
                if (administrador != null) {
                    vistaUsuario.getNombreTxtField().setText(administrador.getNombre());
                    vistaUsuario.getApellidoTxtField().setText(administrador.getApellido());
                    vistaUsuario.getCurpTxtField().setText(administrador.getCurp());
                    vistaUsuario.getIngreseContraseniaTxtField().setText(administrador.getContrasenia());
                    vistaUsuario.getConfirmeContraseniaTxtField().setText(administrador.getContrasenia());
                    try {
                        daoAdmin.modificar_admin(modeloAdmin);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    DAOEmpleado daoEmpleado = new DAOEmpleado();
                    Empleado empleado = daoEmpleado.buscar_empleado(nombre_usuario);
                    if (empleado != null) {
                        vistaUsuario.getNombreTxtField().setText(empleado.getNombre());
                        vistaUsuario.getApellidoTxtField().setText(empleado.getApellido());
                        vistaUsuario.getCurpTxtField().setText(empleado.getCurp());
                        vistaUsuario.getUsuarioTxtField().setText(empleado.getNombre_usuario());
                        vistaUsuario.getIngreseContraseniaTxtField().setText(empleado.getContrasenia());
                        vistaUsuario.getConfirmeContraseniaTxtField().setText(empleado.getContrasenia());

                        try {
                            daoEmpleado.modificar_empleado(modeloEmpleado);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("No existe el usuario");
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (vistaUsuario.getEliminarUsuarioBtn() == evento.getSource()) {
            String nombre_usuario = vistaUsuario.getUsuarioTxtField().getText();
            //Basaremos el código en el de buscarusuario porque debe checar ambas listas
            DAOAdmin daoAdmin = new DAOAdmin();
            try {
                Admin administrador = daoAdmin.buscar_admin(nombre_usuario);
                if (administrador != null) {
                    daoAdmin.eliminar_admin(administrador);
                } else if (administrador == null) {
                    DAOEmpleado daoEmpleado = new DAOEmpleado();
                    try {
                        Empleado empleado = daoEmpleado.buscar_empleado(nombre_usuario);
                        if (empleado != null) {
                            daoEmpleado.eliminar_empleado(empleado);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Empleado no encontrado");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        if(vistaUsuario.getModificarUsuarioBtn() == evento.getSource()){
            String nombre_usuario = vistaUsuario.getUsuarioTxtField().getText();
            DAOAdmin daoAdmin = new DAOAdmin();
            try{
                Admin admin = daoAdmin.buscar_admin(nombre_usuario);
                if(admin != null){
                    modeloAdmin.setNombre(vistaUsuario.getNombreTxtField().getText());
                    modeloAdmin.setApellido(vistaUsuario.getApellidoTxtField().getText());
                    modeloAdmin.setCurp(vistaUsuario.getCurpTxtField().getText());
                    modeloAdmin.setNombre_usuario(vistaUsuario.getUsuarioTxtField().getText());
                    String contrasenia = new String(vistaUsuario.getIngreseContraseniaTxtField().getPassword());
                    modeloAdmin.setContrasenia(contrasenia);
                    try{
                        daoAdmin.modificar_admin(modeloAdmin);
                    }catch(Exception e){
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Vamos a modificar empleado");
                    DAOEmpleado daoEmpleado = new DAOEmpleado();
                    Empleado empleado = daoEmpleado.buscar_empleado(nombre_usuario);
                    if(empleado != null){
                        modeloEmpleado.setNombre(vistaUsuario.getNombreTxtField().getText());
                        modeloEmpleado.setApellido(vistaUsuario.getApellidoTxtField().getText());
                        modeloEmpleado.setCurp(vistaUsuario.getCurpTxtField().getText());
                        modeloEmpleado.setNombre_usuario(vistaUsuario.getUsuarioTxtField().getText());
                        String contrasenia = new String(vistaUsuario.getIngreseContraseniaTxtField().getPassword());
                        modeloEmpleado.setContrasenia(contrasenia);
                        try{
                            daoEmpleado.modificar_empleado(modeloEmpleado);
                        }catch(Exception e){
                            e.printStackTrace();
                        }
                    }  
                }
                
            }catch(Exception e) {
                e.printStackTrace();
                System.out.println("Error, no existe el usuario");
            }

        }
        if(this.vistaUsuario.getRegresarBtn() == evento.getSource()){
            AdminMainWindow ventana_main_admin = new AdminMainWindow();
            ControlAdminMainView control_admin = new ControlAdminMainView(ventana_main_admin);
            ventana_main_admin.setVisible(true);
            ventana_main_admin.setLocationRelativeTo(null);
            this.vistaUsuario.setVisible(false);
        }
    }
}
