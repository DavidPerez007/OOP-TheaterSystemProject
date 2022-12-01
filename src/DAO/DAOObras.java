/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Obra;

public class DAOObras {
    private ArrayList<Obra>lista;
    public DAOObras(){
        this.lista=new ArrayList<Obra>();
        String SEPARADOR=",";
        BufferedReader bufferLectura=null;
        try{
          // Abrir el .txt en buffer de lectura
            bufferLectura = new BufferedReader(new FileReader("./src/DAO/Obras.txt"));
            // Leer una linea del archivo
            String linea = bufferLectura.readLine();
            Obra obras;
            while (linea != null) {
                // Sepapar la linea leída con el separador definido previamente
                String[] datos = linea.split(SEPARADOR);
                obras = new Obra(datos[0],datos[1],datos[2],datos[3],datos[4], Double.parseDouble(datos[5]),Integer.parseInt(datos[6]));
                lista.add(obras);
                // Volver a leer otra línea del fichero
                linea = bufferLectura.readLine();
            }   
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(bufferLectura != null){
                try{
                bufferLectura.close();    
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        }
    }
     public int insertar(Obra obras){
         for(int i=0; i < lista.size(); i++){
             if(obras.getNombre().equals(lista.get(i).getNombre())){
                 System.out.println("Registro existente");
                 return 1;
             }
         }
         lista.add(obras);
           try{
            FileWriter writer = new FileWriter("./src/DAO/Obras.txt", false);
            for(int j=0; j < lista.size(); j++){
                writer.write(lista.get(j).getNombre()+","+lista.get(j).getPrimerActor()+ "," + lista.get(j).getSegundoActor() + "," + lista.get(j).getGenero()+ "," +lista.get(j).getResumen()+","+ lista.get(j).getPrecio()+ "," +lista.get(j).getDuracion()+"\r\n");
            }
            writer.close();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("Añadido exitósamente");
        return 0;
     }
       public int eliminar(Obra obras){
        for(int i=0; i<lista.size(); i++){
            if(obras.getNombre().equals(lista.get(i).getNombre())){
                lista.remove(i);
                
                //Actualizando el archivo .TXT con la lista modificada
                try{
                    FileWriter writer = new FileWriter("./src/DAO/Obras.txt", false);
                    for(int j=0; j < lista.size(); j++){
                        writer.write(lista.get(j).getNombre()+","+lista.get(j).getPrimerActor()+ "," + lista.get(j).getSegundoActor() + "," + lista.get(j).getGenero()+","+lista.get(j).getResumen()+","+lista.get(j).getPrecio()+","+lista.get(j).getDuracion()+"\r\n");
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
       public int modificar(Obra obras){
        for(int i=0; i<lista.size(); i++){
            if(obras.getNombre().equals(lista.get(i).getNombre())){
                lista.set(i, obras);
                
                //Actualizando el archivo .TXT con la lista modificada
                try{
                    FileWriter writer = new FileWriter("./src/DAO/Obras.txt", false);
                    for(int j=0; j < lista.size(); j++){
                        writer.write(lista.get(j).getNombre()+","+lista.get(j).getPrimerActor()+ "," + lista.get(j).getSegundoActor() + "," + lista.get(j).getGenero()+","+lista.get(j).getResumen()+","+lista.get(j).getPrecio()+","+lista.get(j).getDuracion()+"\r\n");
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
    public Obra consultar(String nombreObra){
        for(Obra obra: lista){
            if(obra.getNombre().equals(nombreObra)){
                return obra;  // Retorna Registro Encontrado
            }
        }
        return null; //Registro no encontrado
    }    
}
