package hd.control_care;

/**
 * Created by Hernan on 14/11/2016.
 */
public class Cita {
    private String fecha_cita;
    private String hora_cita;
    private String observacion;

    public Cita(String fecha_cita, String hora_cita, String observacion) {
        this.fecha_cita = fecha_cita;
        this.hora_cita = hora_cita;
        this.observacion = observacion;
    }

    public String getFecha_cita() {
        return fecha_cita;
    }

    public void setFecha_cita(String fecha_cita) {
        this.fecha_cita = fecha_cita;
    }

    public String getHora_cita() {
        return hora_cita;
    }

    public void setHora_cita(String hora_cita) {
        this.hora_cita = hora_cita;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "fecha_cita='" + fecha_cita + '\'' +
                ", hora_cita='" + hora_cita + '\'' +
                ", observacion='" + observacion + '\'' +
                '}';
    }
}
