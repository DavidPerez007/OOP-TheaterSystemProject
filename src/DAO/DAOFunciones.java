
package DAO;

import errores.NotFoundPerformanceException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import model.Funcion;
import model.Obra;

public class DAOFunciones {

    private ArrayList<Funcion> funciones = new ArrayList<>();
    private static final String PERFORMANCES_TEXT_FILE = "./src/DAO/funciones_n.txt";

    public DAOFunciones() {
        loadFileData();
    }

    public void loadFileData() {
        try {
            readFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFuncion(Funcion funcion) {
        this.funciones.add(funcion);
        updateDBFile();
    }

    public void eliminarFuncion(String nombreFuncion) {
        try {
            Funcion funcion = buscarFuncion(nombreFuncion);
            eliminarFuncion(funcion);
        } catch (NotFoundPerformanceException e) {
            System.out.print(e.getMessage());
        }
    }

    public void eliminarFuncion(Funcion funcion) {
        this.funciones.remove(funcion);
        updateDBFile();
    }

    public void modificarFuncion(Funcion funcion) {
        try {
            Funcion db_funcion = buscarFuncion(funcion.getObra().getNombre());
            int index = getPerformanceIndex(db_funcion);
            this.funciones.set(index, funcion);
        } catch (NotFoundPerformanceException e) {
            System.out.print(e.getMessage());
        }
        updateDBFile();
    }

    public Funcion buscarFuncion(String nombreFuncion) throws NotFoundPerformanceException {
        for (Funcion funcion : this.funciones) {
            if (funcion.getObra().getNombre().equals(nombreFuncion)) {
                return funcion;
            }
        }
        throw new NotFoundPerformanceException("No se encontro la funcion");
    }

    public int getPerformanceIndex(Funcion funciion) {
        return this.funciones.indexOf(funciion);
    }

    public void readFile() throws FileNotFoundException, IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(PERFORMANCES_TEXT_FILE));
        while ((line = reader.readLine()) != null) {
            String[] performanceData = line.split(",");
            DAOObras daoObras = new DAOObras();
            Obra obra = daoObras.consultar(performanceData[0]);
            String fecha = performanceData[1];
            String hora = performanceData[2];
            Funcion funcion = new Funcion(obra, fecha, hora);
            this.funciones.add(funcion);
        }
    }

    public void updateDBFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PERFORMANCES_TEXT_FILE));
            for (Funcion funcion : this.funciones) {
                writer.write(funcion.getObra().getNombre() + "," + funcion.getFecha_presentacion() + "," + funcion.getHora_presentacion() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Funcion> getPerformances() {
        return this.funciones;
    }

    public ArrayList<String> gerPerformancesName() {
        ArrayList<String> nombreFunciones = new ArrayList<>();
        for (Funcion funcion : this.funciones) {
            nombreFunciones.add(funcion.getObra().getNombre());
        }
        return nombreFunciones;
    }
}