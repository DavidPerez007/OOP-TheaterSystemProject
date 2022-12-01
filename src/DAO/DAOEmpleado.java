
package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Admin;
import model.Empleado;


public class DAOEmpleado {
    private ArrayList<Empleado> empleados = new ArrayList<>();
    private Empleado joselito_el_admin;
    
    public DAOEmpleado(){
        //creamos un usuario de ejemplo pq no tengo función crear usuarios

        String SEPARADOR = ",";
        BufferedReader bufferLectura = null;
        try {
            // Abrir el .txt en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("./src/DAO/empleados_txt.txt"));
            // Leer una linea del archivo
            String linea = bufferLectura.readLine();
            Empleado empleado;
            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] datos = linea.split(SEPARADOR);
                empleado = new Empleado(datos[0], datos[1], datos[2], datos[3], datos[4]);
                empleados.add(empleado);
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
    
    public int agregar_empleado(Empleado empleado){
        System.out.println("nombre: " + empleado.getNombre());
        System.out.println("apellido: " + empleado.getApellido());
        System.out.println("contra: " + empleado.getContrasenia());
        System.out.println("username: " + empleado.getNombre_usuario());
        System.out.println("curp: " + empleado.getCurp());

        for(int i=0; i < empleados.size(); i++){
            if(empleado.getNombre_usuario()== empleados.get(i).getNombre_usuario()){
                System.out.println("El empleado ya existe");
                return 1;
            }
        }
        //Agregamos el nuevo admin al ArrayList de administradores
        empleados.add(empleado);
        
        //Actualizando el archivo .TXT con la lista modificada
        try{
            FileWriter writer = new FileWriter("./src/DAO/empleados_txt.txt", false);
            for(int j=0; j < empleados.size(); j++){
                writer.write(empleados.get(j).getNombre()+","+empleados.get(j).getApellido()+ "," + empleados.get(j).getContrasenia() + "," + empleados.get(j).getNombre_usuario()+ "," + empleados.get(j).getCurp() + "\r\n");
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();//printStackTrace Imprime todos los detalles de los errores
        }
        
        System.out.println("Añadido exitósamente");
        return 0; //Añadido exitósamente
    }
    
    
    public boolean modificar_empleado(Empleado empleado){
        for(int i=0; i<empleados.size(); i++){
            if(empleado.getNombre_usuario().equals(empleados.get(i).getNombre_usuario())){
                empleados.set(i, empleado);
                
                //Actualizando el archivo .TXT con la lista modificada
                try{
                    FileWriter writer = new FileWriter("./src/DAO/empleados_txt.txt", false);
                    for(int j=0; j < empleados.size(); j++){
                        writer.write(empleados.get(j).getNombre()+","+empleados.get(j).getApellido()+ "," + empleados.get(j).getContrasenia() + "," + empleados.get(j).getNombre_usuario()+ "," + empleados.get(j).getCurp() + "\r\n");
                    }
                    writer.close();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Registro modificado");
                return true;  //Regsitro modificado
            }
            
            System.out.println("Empleado Registro inxistente");
        } 
        return false; //Registro no encontrado
    }
    
    
    public int eliminar_empleado(Empleado empleado){
        for(int i=0; i<empleados.size(); i++){
            if(empleado.getNombre_usuario() == empleados.get(i).getNombre_usuario()){
                empleados.remove(i);
                
                //Actualizando el archivo .TXT con la lista modificada
                try{
                    FileWriter writer = new FileWriter("./src/DAO/empleados_txt.txt", false);
                    for(int j=0; j < empleados.size(); j++){
                        writer.write(empleados.get(j).getNombre()+","+empleados.get(j).getApellido()+ "," + empleados.get(j).getContrasenia() + "," + empleados.get(j).getNombre_usuario()+ "," + empleados.get(j).getCurp() + "\r\n");
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
    
    
    public Empleado buscar_empleado(String nombre_usuario, String contrasenia){
        for(Empleado empleado: empleados){

            if(empleado.getNombre_usuario().equals(nombre_usuario)){
                if(empleado.getContrasenia().equals(contrasenia)){
                    return empleado;
                }
            }
        }
        // si no es empleado, returnea null
        return null;
    }

    public Empleado buscar_empleado(String nombre_usuario) {
        for(Empleado empleado: empleados){
            if(empleado.getNombre_usuario().equals(nombre_usuario)){
                return empleado;   
            }
        }
         // si no es empleado, returnea null
        return null;
    } 
}
