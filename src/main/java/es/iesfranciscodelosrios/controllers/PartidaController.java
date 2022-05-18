package es.iesfranciscodelosrios.controllers;

import es.iesfranciscodelosrios.model.Pregunta;

import es.iesfranciscodelosrios.model.PreguntaRespondida;
import es.iesfranciscodelosrios.model.Usuario;
import es.iesfranciscodelosrios.utils.Utils;
import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartidaController extends Controller{
    public PartidaController(){
        super();
    }

    private static Integer index;
    private static List<PreguntaRespondida> l;
    @FXML
    Button btn_1;

    @FXML
    Button btn_2;

    @FXML
    Button btn_3;

    @FXML
    Button btn_4;

    @FXML
    Button btn_siguiente;

    @FXML
    Button btn_anterior;

    @FXML
    Button btn_terminar;

    @FXML
    Label label_num_prep;

    @FXML
    Label label_pregunta;

    @FXML
    protected void initialize(){
        l = getPreguntas();
        List<Integer> repeated = new ArrayList<>();
        index = 0;
        setScene();
        btn_anterior.setOnAction(actionEvent -> {
            index--;
            if(index == -1){
                index = l.size()-1;
            }
            setScene();
        });

        btn_siguiente.setOnAction(actionEvent -> {
            index++;
            if(index == l.size()){
                index = 0;
            }

            setScene();
        });

        btn_1.setOnAction(actionEvent -> {
            if(!btn_1.isDisabled()){
                l.get(index).setRespuesta(btn_1.getText());
            }

            if(l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                btn_1.setStyle("-fx-background-color: green");
            }else if(!l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                btn_1.setStyle("-fx-background-color: red");
            }
            btn_1.setDisable(true);
            btn_2.setDisable(true);
            btn_3.setDisable(true);
            btn_4.setDisable(true);
        });

        btn_2.setOnAction(actionEvent -> {
            if(!btn_2.isDisabled()){
                l.get(index).setRespuesta(btn_2.getText());
            }

            if(l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                btn_2.setStyle("-fx-background-color: green");
            }else if(!l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                btn_2.setStyle("-fx-background-color: red");
            }
            btn_1.setDisable(true);
            btn_2.setDisable(true);
            btn_3.setDisable(true);
            btn_4.setDisable(true);
        });

        btn_3.setOnAction(actionEvent -> {
            if(!btn_3.isDisabled()){
                l.get(index).setRespuesta(btn_3.getText());
            }

            if(l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                btn_3.setStyle("-fx-background-color: green");
            }else if(!l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                btn_3.setStyle("-fx-background-color: red");
            }
            btn_1.setDisable(true);
            btn_2.setDisable(true);
            btn_3.setDisable(true);
            btn_4.setDisable(true);
        });

        btn_4.setOnAction(actionEvent -> {
            if(!btn_4.isDisabled()){
                l.get(index).setRespuesta(btn_4.getText());
            }

            if(l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                btn_4.setStyle("-fx-background-color: green");
            }else if(!l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                btn_4.setStyle("-fx-background-color: red");
            }
            btn_1.setDisable(true);
            btn_2.setDisable(true);
            btn_3.setDisable(true);
            btn_4.setDisable(true);
        });

        btn_terminar.setOnAction(actionEvent -> {
            Integer respuestas_acertadas=0;
            Integer respuestas_equivocadas=0;
            Integer respuestas_vacias=0;

            for(PreguntaRespondida res:l){
                if(res.getRespuesta()==null){
                    respuestas_vacias++;
                }else if (res.getRespuesta().equals(res.getrCorrecta())){
                    respuestas_acertadas++;
                }else{
                    respuestas_equivocadas++;
                }
            }

            Utils.showPopUp("Partida terminada","Aciertos: "+respuestas_acertadas+" - Fallos: "+respuestas_equivocadas+
                    " - Sin contestar: "+respuestas_vacias,"Pulse nueva partida para comenzar otro test", Alert.AlertType.INFORMATION);
        });

    }

    private List<PreguntaRespondida> getPreguntas(){
        List<PreguntaRespondida> p = new ArrayList<>();
        List<Pregunta> all = new ArrayList<>();
        List<Integer> repeated = new ArrayList<>();
        all = preguntas.getAll(((Usuario)objects.get(0)).getId());
        Integer i;

        do{
            i = Utils.randomNumber(0,all.size()-1);
            if(!repeated.contains(i)){
                repeated.add(i);
                PreguntaRespondida pr = new PreguntaRespondida(all.get(i));
                pr.setrId(((Usuario)objects.get(0)).getId());
                p.add(pr);
            }
        }while(p.size()<5&&repeated.size()<all.size());
        return p;
    }

    private void setScene() {
        label_num_prep.setText((index+1) +"/"+l.size());
        label_pregunta.setText(l.get(index).getTitulo());
        Integer respuestaCorrecta = Utils.randomNumber(1, 4);
            if (respuestaCorrecta == 1) {
                btn_1.setText(l.get(index).getrCorrecta());
                btn_2.setText(l.get(index).getrIn_1());
                btn_3.setText(l.get(index).getrIn_2());
                btn_4.setText(l.get(index).getrIn_3());
            } else if (respuestaCorrecta == 2) {
                btn_1.setText(l.get(index).getrIn_1());
                btn_2.setText(l.get(index).getrCorrecta());
                btn_3.setText(l.get(index).getrIn_3());
                btn_4.setText(l.get(index).getrIn_2());
            } else if (respuestaCorrecta == 3) {
                btn_1.setText(l.get(index).getrIn_2());
                btn_2.setText(l.get(index).getrIn_1());
                btn_3.setText(l.get(index).getrCorrecta());
                btn_4.setText(l.get(index).getrIn_3());
            } else if (respuestaCorrecta == 4) {
                btn_1.setText(l.get(index).getrIn_3());
                btn_2.setText(l.get(index).getrIn_1());
                btn_3.setText(l.get(index).getrIn_2());
                btn_4.setText(l.get(index).getrCorrecta());
            }

            if(l.get(index).getRespuesta()!=null){
                btn_1.setDisable(true);
                btn_2.setDisable(true);
                btn_3.setDisable(true);
                btn_4.setDisable(true);

                if(l.get(index).getRespuesta().equals(btn_1.getText())&&l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                    btn_1.setStyle("-fx-background-color: green;-fx-border-color: blue;");
                }else if (l.get(index).getRespuesta().equals(btn_1.getText())&&!l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                    btn_1.setStyle("-fx-background-color: red;-fx-border-color: blue;");
                }else{
                    btn_1.setStyle("-fx-background-color: silver;-fx-border-color: blue;");
                }

                if(l.get(index).getRespuesta().equals(btn_2.getText())&&l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                    btn_2.setStyle("-fx-background-color: green;-fx-border-color: blue;");
                }else if (l.get(index).getRespuesta().equals(btn_2.getText())&&!l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                    btn_2.setStyle("-fx-background-color: red;-fx-border-color: blue;");
                }else{
                    btn_2.setStyle("-fx-background-color: silver;-fx-border-color: blue;");
                }

                if(l.get(index).getRespuesta().equals(btn_3.getText())&&l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                    btn_3.setStyle("-fx-background-color: green;-fx-border-color: blue;");
                }else if (l.get(index).getRespuesta().equals(btn_3.getText())&&!l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                    btn_3.setStyle("-fx-background-color: red;-fx-border-color: blue;");
                }else{
                    btn_3.setStyle("-fx-background-color: silver;-fx-border-color: blue;");
                }

                if(l.get(index).getRespuesta().equals(btn_4.getText())&&l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                    btn_4.setStyle("-fx-background-color: green;-fx-border-color: blue;");
                }else if (l.get(index).getRespuesta().equals(btn_4.getText())&&!l.get(index).getRespuesta().equals(l.get(index).getrCorrecta())){
                    btn_4.setStyle("-fx-background-color: red;-fx-border-color: blue;");
                }else{
                    btn_4.setStyle("-fx-background-color: silver;-fx-border-color: blue;");
                }
            }else{
                btn_1.setStyle("-fx-background-color: silver;-fx-border-color: blue;");
                btn_2.setStyle("-fx-background-color: silver;-fx-border-color: blue;");
                btn_3.setStyle("-fx-background-color: silver;-fx-border-color: blue;");
                btn_4.setStyle("-fx-background-color: silver;-fx-border-color: blue;");

                btn_1.setDisable(false);
                btn_2.setDisable(false);
                btn_3.setDisable(false);
                btn_4.setDisable(false);
            }
    }
}
