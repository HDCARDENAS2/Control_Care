package hd.control_care;

/**
 * Created by hernandario on 22/10/2016.
 */
public class Usuario {

    private String nombres;
    private String apellidos;
    private String estatura;
    private String fecha_ultima_regla;



    public Usuario(String nombres, String apellidos, String estatura, String fecha_ultima_regla) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estatura = estatura;
        this.fecha_ultima_regla = fecha_ultima_regla;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public String getFecha_ultima_regla() {
        return fecha_ultima_regla;
    }

    public void setFecha_ultima_regla(String fecha_ultima_regla) {
        this.fecha_ultima_regla = fecha_ultima_regla;
    }

}
