package es.iesfranciscodelosrios.controllers;


import es.iesfranciscodelosrios.model.Pregunta;
import es.iesfranciscodelosrios.model.Usuario;
import es.iesfranciscodelosrios.utils.Utils;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.util.logging.Level;

public class PreguntasController extends Controller{
    public PreguntasController(){
        super();
    }

    @FXML
    TableView<Pregunta> tb;

    @FXML
    TableColumn<Pregunta,String> tc_title;

    @FXML
    TableColumn<Pregunta,String> tc_category;

    @FXML
    TableColumn<Pregunta,String> tc_correctAnswer;

    @FXML
    TableColumn<Pregunta,String> tc_inc1;

    @FXML
    TableColumn<Pregunta,String> tc_inc2;

    @FXML
    TableColumn<Pregunta,String> tc_inc3;

    @FXML
    Button btn_add;

    @FXML
    Button btn_eliminar;


    @FXML
    protected void initialize(){
        ObservableList<Pregunta> pr = FXCollections.observableList(((Usuario)objects.get(0)).getPreguntas());
        tb.setItems(pr);
        configureTable();

        pr.addListener((ListChangeListener<? super Pregunta>) change -> {
            change.next();
            if(change.wasAdded()){
                Pregunta p = change.getAddedSubList().get(0);
                Boolean r = preguntas.insert(p);
                if(!r){
                    change.getAddedSubList().clear();
                }
            }
        });

        pr.addListener((ListChangeListener<? super Pregunta>) delete->{
            delete.next();
            if(delete.wasRemoved()){
                Pregunta p = delete.getRemoved().get(0);
                Log.log(Level.INFO,p.toString());
                preguntas.remove(p.getId());
            }
        });
        btn_eliminar.setOnAction(actionEvent ->{
           if( tb.getSelectionModel().getSelectedItem()!= null){
               Alert a = new Alert(Alert.AlertType.CONFIRMATION);
               a.setTitle("Confirmacion de borrado");
               a.setHeaderText("¿Esta seguro de eliminar esta pregunta?");
               a.setContentText("Este cambio es irreversible");
               Stage s =(Stage)a.getDialogPane().getScene().getWindow();
               s.initOwner(btn_eliminar.getScene().getWindow());
               s.toFront();
               a.showAndWait().filter(buttonType -> buttonType== ButtonType.OK).ifPresentOrElse((e)->tb.getItems().remove(tb.getSelectionModel().getSelectedItem()),()->{});
            }
        } );

        btn_add.setOnAction(actionEvent -> {
            Pregunta p = leerPregunta();
            if(p!=null){
                tb.getItems().add(p);
            }

        });

    }

    private void configureTable(){
        tc_title.setCellValueFactory(eachPregunta -> new SimpleStringProperty(eachPregunta.getValue().getTitulo()));
        tc_category.setCellValueFactory(eachPregunta -> new SimpleStringProperty(eachPregunta.getValue().getCategoria()));
        tc_correctAnswer.setCellValueFactory(eachPregunta -> new SimpleStringProperty(eachPregunta.getValue().getrCorrecta()));
        tc_inc1.setCellValueFactory((eachPregunta -> new SimpleStringProperty(eachPregunta.getValue().getrIn_1())));
        tc_inc2.setCellValueFactory((eachPregunta -> new SimpleStringProperty(eachPregunta.getValue().getrIn_2())));
        tc_inc3.setCellValueFactory((eachPregunta -> new SimpleStringProperty(eachPregunta.getValue().getrIn_3())));

        tc_title.setCellFactory(param -> {
            TableCell<Pregunta, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1) {
                    if (!cell.isEmpty()) {
                        Pregunta p = tb.getSelectionModel().getSelectedItem();
                        String newTitle = Utils.showDialogString((Stage) tb.getScene().getWindow(),"Modificar Pregunta",
                                "Cambiar enunciado","Introduzca nuevo enunciado:", 350);
                        if(newTitle!=null&&!newTitle.equals("")){
                            p.setTitulo(newTitle);
                            p.setFecha_creacion(LocalDateTime.now());
                            preguntas.update(p);
                            tb.refresh();
                        }
                    }
                }
            });
            return cell;
        });

        tc_category.setCellFactory(param -> {
            TableCell<Pregunta, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1) {
                    if (!cell.isEmpty()) {
                        Pregunta p = tb.getSelectionModel().getSelectedItem();
                        String newTitle = Utils.showDialogString((Stage) tb.getScene().getWindow(),"Modificar Pregunta",
                                "Cambiar Categoria","Introduzca nueva categoria:", 20);
                        if(newTitle!=null&&!newTitle.equals("")){
                            p.setCategoria(newTitle);
                            p.setFecha_creacion(LocalDateTime.now());
                            preguntas.update(p);
                            tb.refresh();
                        }
                    }
                }
            });
            return cell;
        });

        tc_correctAnswer.setCellFactory(param -> {
            TableCell<Pregunta, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1) {
                    if (!cell.isEmpty()) {
                        Pregunta p = tb.getSelectionModel().getSelectedItem();
                        String newTitle = Utils.showDialogString((Stage) tb.getScene().getWindow(),"Modificar Pregunta",
                                "Cambiar rerspuesta correcta","Introduzca nueva respuesta correcta:", 100);
                        if(newTitle!=null&&!newTitle.equals("")){
                            p.setrCorrecta(newTitle);
                            p.setFecha_creacion(LocalDateTime.now());
                            preguntas.update(p);
                            tb.refresh();
                        }
                    }
                }
            });
            return cell;
        });

        tc_inc1.setCellFactory(param -> {
            TableCell<Pregunta, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1) {
                    if (!cell.isEmpty()) {
                        Pregunta p = tb.getSelectionModel().getSelectedItem();
                        String newTitle = Utils.showDialogString((Stage) tb.getScene().getWindow(),"Modificar Pregunta",
                                "Cambiar rerspuesta incorrecta","Introduzca nueva respuesta incorrecta:", 100);
                        if(newTitle!=null&&!newTitle.equals("")){
                            p.setrIn_1(newTitle);
                            p.setFecha_creacion(LocalDateTime.now());
                            preguntas.update(p);
                            tb.refresh();
                        }
                    }
                }
            });
            return cell;
        });

        tc_inc2.setCellFactory(param -> {
            TableCell<Pregunta, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1) {
                    if (!cell.isEmpty()) {
                        Pregunta p = tb.getSelectionModel().getSelectedItem();
                        String newTitle = Utils.showDialogString((Stage) tb.getScene().getWindow(),"Modificar Pregunta",
                                "Cambiar rerspuesta incorrecta","Introduzca nueva respuesta incorrecta:", 100);
                        if(newTitle!=null&&!newTitle.equals("")){
                            p.setrIn_2(newTitle);
                            p.setFecha_creacion(LocalDateTime.now());
                            preguntas.update(p);
                            tb.refresh();
                        }
                    }
                }
            });
            return cell;
        });

        tc_inc3.setCellFactory(param -> {
            TableCell<Pregunta, String> cell = new TableCell<>() {
                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(item);
                }
            };
            cell.setOnMouseClicked(event -> {
                if (event.getClickCount() > 1) {
                    if (!cell.isEmpty()) {
                        Pregunta p = tb.getSelectionModel().getSelectedItem();
                        String newTitle = Utils.showDialogString((Stage) tb.getScene().getWindow(),"Modificar Pregunta",
                                "Cambiar rerspuesta incorrecta","Introduzca nueva respuesta incorrecta:", 100);
                        if(newTitle!=null&&!newTitle.equals("")){
                            p.setrIn_3(newTitle);
                            p.setFecha_creacion(LocalDateTime.now());
                            preguntas.update(p);
                            tb.refresh();
                        }
                    }
                }
            });
            return cell;
        });
    }

    private Pregunta leerPregunta(){
        Pregunta p;
        String pregunta=null,categoria = null,respuestaCorrecta=null,rInc1=null,rInc2=null,rInc3=null;

        pregunta = Utils.showDialogString((Stage)btn_add.getScene().getWindow(),"Nueva Pregunta",
                "Enunciado","Introduzca el enunciado de la pregunta:", 350);
        if(pregunta!=null&&!pregunta.equals("")){
            categoria = Utils.showDialogString((Stage)btn_add.getScene().getWindow(),"Nueva Pregunta",
                    "Categoria","Introduzca la categoria de la pregunta:", 20);
        }

        if(categoria!=null&&!categoria.equals("")){
            respuestaCorrecta = Utils.showDialogString((Stage)btn_add.getScene().getWindow(),"Nueva Pregunta",
                    "Respuesta Correcta","Introduzca la respuesta correcta de la pregunta:", 100);
        }
        if(respuestaCorrecta!=null&&!respuestaCorrecta.equals("")){
            rInc1 = Utils.showDialogString((Stage)btn_add.getScene().getWindow(),"Nueva Pregunta",
                    "Respuesta Incorrecta","Introduzca la primera respuesta incorrecta de la pregunta:", 100);
        }

        if(rInc1!=null&&!rInc1.equals("")){
            rInc2 = Utils.showDialogString((Stage)btn_add.getScene().getWindow(),"Nueva Pregunta",
                    "Respuesta Incorrecta","Introduzca la segunda respuesta incorrecta de la pregunta:", 100);
        }
        if(rInc2!=null&&!rInc2.equals("")){
            rInc3 = Utils.showDialogString((Stage)btn_add.getScene().getWindow(),"Nueva Pregunta",
                    "Respuesta Incorrecta","Introduzca la tercera respuesta incorrecta de la pregunta:", 100);
        }

        if(pregunta!=null&&!pregunta.equals("")&&categoria!=null&&!categoria.equals("")&&respuestaCorrecta!=null
                &&!respuestaCorrecta.equals("")&&rInc1!=null&&!rInc1.equals("")&&rInc2!=null&&!rInc2.equals("")&&rInc3!=null&&!rInc3.equals("")){
            p=new Pregunta(null,((Usuario)objects.get(0)).getId(),pregunta,respuestaCorrecta,rInc1,rInc2,rInc3,categoria);
            Log.log(Level.INFO,"Pregunta leida: "+p);
            return p;
        }

        return null;
    }

}
