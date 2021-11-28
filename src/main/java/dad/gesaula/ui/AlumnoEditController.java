package dad.gesaula.ui;

import dad.gesaula.ui.model.Alumno;
import dad.gesaula.ui.model.Sexo;
import javafx.beans.Observable;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AlumnoEditController implements Initializable {

    //model

    private StringProperty nombre = new SimpleStringProperty();
    private StringProperty apellidos = new SimpleStringProperty();
    private ObjectProperty<LocalDate> nacimiento = new SimpleObjectProperty<>();
    private ObjectProperty<Sexo> sexo = new SimpleObjectProperty<>();
    private BooleanProperty repite = new SimpleBooleanProperty();

    private ObjectProperty<Alumno> alumno = new SimpleObjectProperty<>();

    //View
    @FXML
    private TextField apellidosText;

    @FXML
    private DatePicker nacimientoDatePicker;

    @FXML
    private TextField nombreText;

    @FXML
    private CheckBox repiteCheck;

    @FXML
    private ComboBox<Sexo> sexoCombo;

    @FXML
    private GridPane view;

    public AlumnoEditController() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AlumnosEditView.fxml"));
        loader.setController(this);
        loader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Bindings
        nombreText.textProperty().bindBidirectional(nombre);
        apellidosText.textProperty().bindBidirectional(apellidos);
        nacimientoDatePicker.valueProperty().bindBidirectional(nacimiento);
        sexoCombo.valueProperty().bindBidirectional(sexo);
        repiteCheck.selectedProperty().bindBidirectional(repite);

        sexoCombo.getItems().addAll(Sexo.values());

        //listeners

        alumno.addListener(this::onAlumnoChanged);

    }

    private void onAlumnoChanged(ObservableValue<?extends Alumno>o, Alumno ov, Alumno nv) {

        if (ov != null) {

            nombre.unbindBidirectional(ov.nombreProperty());
            apellidos.unbindBidirectional(ov.apellidosProperty());
            nacimiento.unbindBidirectional(ov.fechaNacimientoProperty());
            sexo.unbindBidirectional(ov.sexoProperty());
            repite.unbindBidirectional(ov.repiteProperty());

        }

        if (nv != null) {

            nombre.bindBidirectional(nv.nombreProperty());
            apellidos.bindBidirectional(nv.apellidosProperty());
            nacimiento.bindBidirectional(nv.fechaNacimientoProperty());
            sexo.bindBidirectional(nv.sexoProperty());
            repite.bindBidirectional(nv.repiteProperty());

        }
    }

    public GridPane getView() {
        return view;
    }

    public AlumnoEditController(ObjectProperty<Alumno> alumno) {
        this.alumno = alumno;
    }

    public Alumno getAlumno() {
        return alumno.get();
    }

    public ObjectProperty<Alumno> alumnoProperty() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno.set(alumno);
    }
}
