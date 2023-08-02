package servlet.Clubs;


public class Club{
    
    private int id;
    private String nombre;
    private String jefeClub;
    private String descripcion;
    private String horario;
    private String creditoHora;
    private int cupo;
    private int ocupacionActual;
    private String anuncios;
    private String lugar;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getJefeClub() {
        return jefeClub;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getHorario() {
        return horario;
    }

    public String getCreditoHora() {
        return creditoHora;
    }

    public int getCupo() {
        return cupo;
    }

    public int getOcupacionActual() {
        return ocupacionActual;
    }

    public String getAnuncios() {
        return anuncios;
    }

    public String getLugar() {
        return lugar;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setJefeClub(String jefeClub) {
        this.jefeClub = jefeClub;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public void setCreditoHora(String creditoHora) {
        this.creditoHora = creditoHora;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public void setOcupacionActual(int ocupacionActual) {
        this.ocupacionActual = ocupacionActual;
    }

    public void setAnuncios(String anuncios) {
        this.anuncios = anuncios;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    @Override
    public String toString() {
        return "Club{" + "nombre=" + nombre + ", jefeClub=" + jefeClub + ", descripcion=" + descripcion + ", horario=" + horario + ", creditoHora=" + creditoHora + ", cupo=" + cupo + ", ocupacionActual=" + ocupacionActual + ", anuncios=" + anuncios + ", lugar=" + lugar + '}';
    }
    

}
