
package test;

import controler.ControlAdminMainView;
import controler.ControlInicioSesion;
import model.Usuario;
import view.AdminMainWindow;
import view.VistaLogIn;

public class Testin2 {

    public static void main(String[] args) {
        AdminMainWindow vista = new AdminMainWindow();
        ControlAdminMainView controlVista = new ControlAdminMainView(vista);
        vista.setVisible(true);
    }
    
}
