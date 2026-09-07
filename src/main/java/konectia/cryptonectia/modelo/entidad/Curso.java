package konectia.cryptonectia.modelo.entidad;
public class Curso {
    private int id;
    private String nombre;
    private int horas;
    private boolean activo;
    public Curso(int id, String nombre, int horas, boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.horas = horas;
        this.activo = activo;
    }
    public Curso(String nombre, int horas, boolean activo) {
        this.nombre = nombre;
        this.horas = horas;
        this.activo = activo;
    }
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getHoras() {
        return horas;
    }
    public void setHoras(int horas) {
        this.horas = horas;
    }
    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
