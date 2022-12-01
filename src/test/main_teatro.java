package test;

import controler.ControlInicioSesion;
import model.Usuario;
import view.VistaLogIn;

public class main_teatro {


    public static void main(String[] args) {
        Usuario modeloUsuario = new Usuario();
        VistaLogIn vista_log_in = new VistaLogIn();
        ControlInicioSesion control_log_in = new ControlInicioSesion(modeloUsuario, vista_log_in);
        vista_log_in.setVisible(true);
    }
    
}
