package DAO;

import Modelo.Administrador;
import Modelo.Usuario;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class AdministradorDAO {
    
    private ArrayList<Administrador> administradores;
    String SEPARADOR = ",";
    
    public AdministradorDAO(){
        this.administradores = new ArrayList <Administrador>(); 
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .txt en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("./src/ArchivisTxt/Administrador.txt"));
            // Leer una linea del archivo
            String linea = bufferLectura.readLine();
            Administrador administrador;
            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] datos = linea.split(SEPARADOR);
                administrador = new Administrador(datos[0], datos[1], datos[2], datos[3], datos[4]);
                administradores.add(administrador);
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
    
    public int agregarAdministrador(Administrador administrador){
        for(int i=0; i < administradores.size(); i++){
            if(administrador.getNombreUsuario() == administradores.get(i).getNombreUsuario()){
                System.out.println("El empleado ya existe");
                return 1;
            }
        }
        //Agregamos el nuevo admin al ArrayList de administradores
        administradores.add(administrador);
        
        //Actualizando el archivo .TXT con la lista modificada
        try{
            FileWriter writer = new FileWriter("./src/ArchivisTxt/Administrador.txt", false);
            for(int j=0; j < administradores.size(); j++){
                writer.write(administradores.get(j).getNombre()+","+administradores.get(j).getApellido()+ "," + administradores.get(j).getCurp()+ "," + administradores.get(j).getNombreUsuario()+ administradores.get(j).getPassword() + "\r\n");
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();//printStackTrace Imprime todos los detalles de los errores
        }
        
        System.out.println("Añadido exitósamente");
        return 0; //Añadido exitósamente
    }
    
    public int modificarUsuario(Administrador administrador){
        for(int i=0; i<administradores.size(); i++){
            if(administrador.getNombreUsuario()== administradores.get(i).getNombreUsuario()){
                administradores.set(i, administrador);
                
                //Actualizando el archivo .TXT con la lista modificada
                try{
                    FileWriter writer = new FileWriter("C:\\Users\\David\\Documents\\UADY CARRERA\\3_Tercer Semestre\\POO\\NetBeansProjects\\MVC_ArchivosTexto\\src\\TxtArchivos\\Discos.txt", false);
                    for(int j=0; j < administradores.size(); j++){
                        writer.write(administradores.get(j).getNombre()+","+administradores.get(j).getApellido()+ "," + administradores.get(j).getCurp()+ "," + administradores.get(j).getNombreUsuario()+ administradores.get(j).getPassword() + "\r\n");
                    }
                    writer.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Registro modificado");
                return 0;  //Regsitro modificado
            }
        } 
        System.out.println("Registro inxistente");
        return 1; //Registro no encontrado
    }      
    
       
    
    
}
