package es.iesfranciscodelosrios.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Pregunta {

    private Integer id;
    private Integer userId;
    private String titulo;
    private String rCorrecta;
    private String rIn_1;
    private String rIn_2;
    private String rIn_3;
    private String imagen;
    private String categoria;
    private LocalDateTime fecha_creacion;
    private Usuario owner;

    public Pregunta(Integer id, Integer userId, String titulo, String rCorrecta,
                    String rIn_1, String rIn_2, String rIn_3, String categoria) {
        this.id = id;
        this.userId = userId;
        this.titulo = titulo;
        this.rCorrecta = rCorrecta;
        this.rIn_1 = rIn_1;
        this.rIn_2 = rIn_2;
        this.rIn_3 = rIn_3;
        this.categoria = categoria;
    }

    public Pregunta() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getrCorrecta() {
        return rCorrecta;
    }

    public void setrCorrecta(String rCorrecta) {
        this.rCorrecta = rCorrecta;
    }

    public String getrIn_1() {
        return rIn_1;
    }

    public void setrIn_1(String rIn_1) {
        this.rIn_1 = rIn_1;
    }

    public String getrIn_2() {
        return rIn_2;
    }

    public void setrIn_2(String rIn_2) {
        this.rIn_2 = rIn_2;
    }

    public String getrIn_3() {
        return rIn_3;
    }

    public void setrIn_3(String rIn_3) {
        this.rIn_3 = rIn_3;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDateTime getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public Usuario getOwner() {
        return owner;
    }

    public void setOwner(Usuario owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Pregunta{" +
                "id=" + id +
                ", userId=" + userId +
                ", titulo='" + titulo + '\'' +
                ", rCorrecta='" + rCorrecta + '\'' +
                ", rIn_1='" + rIn_1 + '\'' +
                ", rIn_2='" + rIn_2 + '\'' +
                ", rIn_3='" + rIn_3 + '\'' +
                ", categoria='" + categoria + '\'' +
                ", fecha_creacion=" + fecha_creacion +
                '}';
    }
}
