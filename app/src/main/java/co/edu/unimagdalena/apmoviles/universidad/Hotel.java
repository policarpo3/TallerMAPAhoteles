package co.edu.unimagdalena.apmoviles.universidad;

public class Hotel {

    private int id;
    private String pais;
    private String ciudad;
    private String nombre;
    private int estrellas;

    public Hotel(String nombre, String pais, String ciudad, int estrellas) {
        this.pais = pais;
        this.nombre = nombre;
        this.ciudad = ciudad;
        this.estrellas = estrellas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getPais() {
        return pais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getEstrellas() {
        return estrellas;
    }

    public void setEstrellas(int estrellas) {
        this.estrellas = estrellas;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", pais='" + pais + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estrellas='" + estrellas + '\'' +
                '}';
    }
}
