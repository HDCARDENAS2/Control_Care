package hd.control_care;

/**
 * Created by hernandario on 14/11/2016.
 */
public class Peso {

    private int id;
    private String peso;
    private int cod_semana;
    private int cambio;
    private String fecha_creacion;


    public Peso(int id, String peso, int cod_semana, int cambio, String fecha_creacion) {
        this.id = id;
        this.peso = peso;
        this.cod_semana = cod_semana;
        this.cambio = cambio;
        this.fecha_creacion = fecha_creacion;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCambio() {
        return cambio;
    }

    public void setCambio(int cambio) {
        this.cambio = cambio;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getCod_semana() {
        return cod_semana;
    }

    public void setCod_semana(int cod_semana) {
        this.cod_semana = cod_semana;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "Peso{" +
                "id=" + id +
                ", peso='" + peso + '\'' +
                ", cod_semana=" + cod_semana +
                ", cambio=" + cambio +
                ", fecha_creacion='" + fecha_creacion + '\'' +
                '}';
    }
}

