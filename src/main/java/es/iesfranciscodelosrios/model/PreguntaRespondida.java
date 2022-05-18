package es.iesfranciscodelosrios.model;

public class PreguntaRespondida extends Pregunta{
    protected String respuesta;
    protected Integer rId;
    protected Integer rUserId;

    public PreguntaRespondida(Pregunta pregunta) {
        super(pregunta);
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public Integer getrId() {
        return rId;
    }

    public void setrId(Integer rId) {
        this.rId = rId;
    }

    public Integer getrUserId() {
        return rUserId;
    }

    public void setrUserId(Integer rUserId) {
        this.rUserId = rUserId;
    }

    @Override
    public String toString() {
        return "PreguntaRespondida{" +
                "respuesta='" + respuesta + '\'' +
                ", rId=" + rId +
                ", rUserId=" + rUserId +
                "} " + super.toString();
    }
}
