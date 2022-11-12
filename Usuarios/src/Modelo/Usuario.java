package Modelo;


public abstract class Usuario {

    private String nombreUsuario;
    private String nombre;
    private String apellido;
    private String curp;
    private String password;
    
    public Usuario(String nombre, String apellido, String curp, String nombreUsuario, String password){
        this.nombre = nombre;
        this.apellido = apellido;
        this.curp = curp;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
    
    public void buscarObra(){
        
    }
    
    public void agregarObra(){
        
    }
    
    public void modificarObra(){
        
    }
    
    public void eliminarObra(){
        
    }
    
    public void buscarFuncionTeatral(){
        
    }
    
    public void agregarFuncionTeatral(){
        
    }
    
    public void modificarFuncionTeatral(){
        
    }
    
    public void eliminarFuncionTeatral(){
        
    }
    
    public void venderBoletos(){
        
    }
    
    
    
     public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
