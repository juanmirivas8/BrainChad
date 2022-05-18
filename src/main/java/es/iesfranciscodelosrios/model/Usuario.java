package es.iesfranciscodelosrios.model;

import java.sql.Date;
import java.util.List;

public class Usuario {
    private String nombre;
    private String nickname;
    private String password;
    private Date bornDate;
    private Boolean sexo;
    private Integer id;
    private Date register_date;
    private Double puntuacion;
    private Double moneda;
    public List<Pregunta> preguntas;


    public Usuario(String nombre, String nickname, String password, Date bornDate, Boolean sexo,
                   Integer id, Date register_date, Double puntuacion, Double moneda) {
        this.nombre = nombre;
        this.nickname = nickname;
        this.password = password;
        this.bornDate = bornDate;
        this.sexo = sexo;
        this.id = id;
        this.register_date = register_date;
        this.puntuacion = puntuacion;
        this.moneda = moneda;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public Boolean getSexo() {
        return sexo;
    }

    public void setSexo(Boolean sexo) {
        this.sexo = sexo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getRegister_date() {
        return register_date;
    }

    public void setRegister_date(Date register_date) {
        this.register_date = register_date;
    }

    public Double getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Double puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Double getMoneda() {
        return moneda;
    }

    public void setMoneda(Double moneda) {
        this.moneda = moneda;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", nickname='" + nickname + '\'' +
                ", bornDate=" + bornDate +
                ", sexo=" + sexo +
                ", id=" + id +
                ", register_date=" + register_date +
                ", puntuacion=" + puntuacion +
                ", moneda=" + moneda +
                '}';
    }
}
