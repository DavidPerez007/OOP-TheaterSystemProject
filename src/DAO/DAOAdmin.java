
package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Admin;

public class DAOAdmin {
    public ArrayList<Admin> admins = new ArrayList<>();
    
    public DAOAdmin(){

        String SEPARADOR = ",";
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .txt en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("./src/DAO/admins_txt.txt"));
            // Leer una linea del archivo
            String linea = bufferLectura.readLine();
            Admin administrador;
            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] datos = linea.split(SEPARADOR);
                administrador = new Admin(datos[0], datos[1], datos[2], datos[3], datos[4]);
                admins.add(administrador);
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } 
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    public int agregar_admin(Admin administrador){
        for(int i=0; i < admins.size(); i++){
            if(administrador.getNombre_usuario() == admins.get(i).getNombre_usuario()){
                System.out.println("El empleado ya existe");
                return 1;
            }
        }
        //Agregamos el nuevo admin al ArrayList de administradores
        admins.add(administrador);
        
        //Actualizando el archivo .TXT con la lista modificada
        try{
            FileWriter writer = new FileWriter("./src/DAO/admins_txt.txt", false);
            for(int j=0; j < admins.size(); j++){
                writer.write(admins.get(j).getNombre()+","+admins.get(j).getApellido()+ "," + admins.get(j).getContrasenia()+ "," + admins.get(j).getNombre_usuario()+ "," + admins.get(j).getCurp()+ "\r\n");
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();//printStackTrace Imprime todos los detalles de los errores
        }
        
        System.out.println("Añadido exitósamente");
        return 0; //Añadido exitósamente
    }
    
    public boolean modificar_admin(Admin administrador){
        for(int i=0; i<admins.size(); i++){
            
            if(administrador.getNombre_usuario().equals(admins.get(i).getNombre_usuario())){
                admins.set(i, administrador);
                //admins.get(i).getNombre_usuario();
                //admins.get(i).getContrasenia();
                //Actualizando el archivo .TXT con la lista modificada
                try{
                    FileWriter writer = new FileWriter("./src/DAO/admins_txt.txt", false);
                    for(int j=0; j < admins.size(); j++){
                        writer.write(admins.get(j).getNombre()+","+admins.get(j).getApellido()+ "," + admins.get(j).getContrasenia()+ "," + admins.get(j).getNombre_usuario()+ "," + admins.get(j).getCurp()+ "\r\n");
                    }
                    writer.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Registro modificado");
                return true;  //Regsitro modificado
            }
        } 
        System.out.println("Admin Registro inxistente");
        return false; //Registro no encontrado
    }
    
    public int eliminar_admin(Admin administrador){
        for(int i=0; i<admins.size(); i++){
            if(administrador.getNombre_usuario() == admins.get(i).getNombre_usuario()){
                admins.remove(i);
                
                //Actualizando el archivo .TXT con la lista modificada
                try{
                    FileWriter writer = new FileWriter("./src/DAO/admins_txt.txt", false);
                    for(int j=0; j < admins.size(); j++){
                        writer.write(admins.get(j).getNombre()+","+admins.get(j).getApellido()+ "," + admins.get(j).getContrasenia()+ "," + admins.get(j).getNombre_usuario()+ "," + admins.get(j).getCurp()+ "\r\n");

                    }
                    writer.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }            
                System.out.println("Registro eliminado");
                return 0;  //Regsitro eliminado
            }
        }    
        System.out.println("Registro no encontrado");
        return 1; //Registro no encontrado
    }
    
    public Admin buscar_admin(String nombre_usuario, String contrasenia){
        for(Admin admin: admins){
            if(admin.getNombre_usuario().equals(nombre_usuario)){
                if(admin.getContrasenia().equals(contrasenia)){
                    return admin;
                }
            }
        }
        // si no es admin, returnea null
        return null;
    }

    public Admin buscar_admin(String nombre_usuario) {
        for(Admin admin: admins){

            if(admin.getNombre_usuario().equals(nombre_usuario)){
                return admin; 
            }
        }
        // si no es admin, returnea null
        return null;
    }
    

    
}
