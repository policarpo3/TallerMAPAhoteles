package co.edu.unimagdalena.apmoviles.universidad;

public class Hotel {

    private int id;
    private String departamento;
    private String ciudad;
    private String nombre;
    private int estrellas;
    private String direccion;
    private int latitud;
    private int longitud;

    public Hotel(int id, String nombre, String departamento, String ciudad, int estrellas, String direccion, int latitud, int longitud) {
        this.id = id;//nuevo
        this.departamento = departamento;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estrellas = estrellas;
        this.direccion = direccion;//nuevo
        this.latitud = latitud;//nuevo
        this.longitud = longitud;//nuevo
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getLatitud() {
        return latitud;
    }

    public void setLatitud(int latitud) {
        this.latitud = latitud;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    @Override
    public String toString() {
        return "Hoteles{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", departamento='" + departamento + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estrellas='" + estrellas + '\'' +
                ", direccion='" + direccion + '\'' +
                ", latitud='" + latitud + '\'' +
                ", longitud='" + longitud + '\'' +
                '}';
    }
}
