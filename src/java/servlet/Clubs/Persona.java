package servlet.Clubs;

public class Persona {
    private int idAlumno;
    private String nombre;
    private String apellido;
    private String boleta;
    private int semestre;
    private int idClub;

    public int getIdAlumno() {
        return idAlumno;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getBoleta() {
        return boleta;
    }

    public int getSemestre() {
        return semestre;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setBoleta(String boleta) {
        this.boleta = boleta;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    @Override
    public String toString() {
        return "Persona{" + "idAlumno=" + idAlumno + ", nombre=" + nombre + ", apellido=" + apellido + ", boleta=" + boleta + ", semestre=" + semestre + ", idClub=" + idClub + '}';
    }
    
    
    
}
