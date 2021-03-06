package ceu.dam.edusoft.gui.controller;

import ceu.dam.edusoft.gui.util.AppKeys;
import ceu.dam.edusoft.gui.util.C3kUtil;
import ceu.dam.edusoft.gui.util.FXMLPATH;
import ceu.dam.edusoft.gui.util.FadeLogoTask;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.File;
import java.io.IOException;

public class MainMenuController extends AppController implements EventHandler {

    @FXML
    private BorderPane bpWindow;

    @FXML
    private ImageView ivDarkLogo;

    @FXML
    private Button btCifer;

    @FXML
    private Button btDecifer;

    @FXML
    private Button btLoadPublicKey;

    @FXML
    private Button btLoadPrivateKey;

    @FXML
    private Button btGenerateKeys;
    @FXML
    private Label lbCifrar;

    @FXML
    private Label lbDescifrar;

    @FXML
    private Label lbLoadPubK;

    @FXML
    private Label lbLoadPriKey;

    @FXML
    private Label lbGenerateKeys;

    @FXML
    private Button btExit;

    @FXML
    private Label lbExit;


    @Override
    public void init() {

        fadeLogo();

        setCurrentController(this); // se establece como controlador en uso en el controlador padre

        disableButtons();

        addButtonEvents();

        labelTransparent();


    }

    private void checkKeys() {

        if(getAppKeys().havePublicKey()){
            btCifer.setDisable(false);
        }
        if(getAppKeys().havePrivateKey()){
            btDecifer.setDisable(false);
        }


    }

    private void disableButtons() {
        btCifer.setDisable(true);
        btDecifer.setDisable(true);
    }


    /**
     * Evita que el texto de los botones sea sensible al ratón y estropee los efectos
     */
    private void labelTransparent() {
        lbCifrar.setMouseTransparent(true);
        lbDescifrar.setMouseTransparent(true);
        lbLoadPriKey.setMouseTransparent(true);
        lbLoadPubK.setMouseTransparent(true);
        lbExit.setMouseTransparent(true);
        lbGenerateKeys.setMouseTransparent(true);
    }

    /**
     * Añade eventos de entrada y salida de ratón en botones
     */
    private void addButtonEvents() {

        addGraphicEvents();
        addNavigationEvents();
    }

    private void addNavigationEvents() {
        btCifer.addEventHandler(ActionEvent.ACTION, this);
        btDecifer.addEventHandler(ActionEvent.ACTION, this);
        btLoadPublicKey.addEventHandler(ActionEvent.ACTION, this);
        btLoadPrivateKey.addEventHandler(ActionEvent.ACTION, this);
        btExit.addEventHandler(ActionEvent.ACTION, this);
        btGenerateKeys.addEventHandler(ActionEvent.ACTION, this);
    }

    private void addGraphicEvents() {
        btCifer.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btCifer.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btCifer.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btCifer.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btDecifer.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btDecifer.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btDecifer.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btDecifer.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btLoadPublicKey.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btLoadPublicKey.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btLoadPublicKey.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btLoadPublicKey.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btLoadPrivateKey.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btLoadPrivateKey.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btLoadPrivateKey.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btLoadPrivateKey.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btExit.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btExit.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btExit.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btExit.addEventHandler(MouseEvent.MOUSE_RELEASED, this);

        btGenerateKeys.addEventHandler(MouseEvent.MOUSE_ENTERED_TARGET, this);
        btGenerateKeys.addEventHandler(MouseEvent.MOUSE_EXITED_TARGET, this);
        btGenerateKeys.addEventHandler(MouseEvent.MOUSE_PRESSED, this);
        btGenerateKeys.addEventHandler(MouseEvent.MOUSE_RELEASED, this);


    }

    /**
     * Lanza un hilo que hace desaparecer al logo
     */
    private void fadeLogo() {
        FadeLogoTask fadeTask = new FadeLogoTask(this);
        Thread thread = new Thread(fadeTask);
        thread.start();
    }


    @Override
    protected void saveSceneState() {

    }

    @Override
    protected void savePanelState() {

    }

    @Override
    protected void loadSceneState() {

    }

    @Override
    protected void loadPanelState() {

    }

    public BorderPane getBpWindow() {
        return bpWindow;
    }

    public ImageView getIvDarkLogo() {
        return ivDarkLogo;
    }

    public void setIvDarkLogo(ImageView ivDarkLogo) {
        this.ivDarkLogo = ivDarkLogo;
    }


    private void loadKey(String oldKeyMsg, String newKeyMsg, String keyType) {


        File keyFileChosen = C3kUtil.selectFile("Select  key");
        if (keyFileChosen != null) {
            System.out.println(oldKeyMsg + getAppKeys().getPublicKeyFile().toPath());

            if (keyType.equalsIgnoreCase("PUBLIC")) {
                getAppKeys().setPublicKeyFile(keyFileChosen);
            } else if (keyType.equalsIgnoreCase("PRIVATE")) {
                getAppKeys().setPrivateKeyFile(keyFileChosen);
            }


            System.out.println(newKeyMsg + getAppKeys().getPublicKeyFile().toPath());
        }
    }

    @Override
    public void handle(Event event) {

        //Eventos gráficos

        Glow glow = new Glow();
        glow.setLevel(10);


        C3kUtil.handleC3KMouseEvents(event);

        if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
            ((Button) event.getSource()).setEffect(glow);
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
            ((Button) event.getSource()).setEffect(null);
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_PRESSED)) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(5);
            ((Button) event.getSource()).setEffect(colorAdjust);
        }
        if (event.getEventType().equals(MouseEvent.MOUSE_RELEASED)) {
            ((Button) event.getSource()).setEffect(null);
        }


        //Eventos de navegación

        if (event.getEventType().equals(ActionEvent.ACTION)) {


            Button button = (Button) event.getSource();
            String buttonId = button.getId();

            try {
                if (buttonId.equals(btCifer.getId())) {
                    changePane(FXMLPATH.Panel.CIFER_PANEL);
                }
                if (buttonId.equals(btDecifer.getId())) {
                    changePane(FXMLPATH.Panel.DECIFER_PANEL);
                }
                if (buttonId.equals(btGenerateKeys.getId())) {
                    getAppKeys().loadKeys(); //todo ??? se supone que aquí estoy cargando las claves en la insancia app
                    if(getAppKeys().haveKeys()){
                        System.out.println("Tengo claves");
                    }
                    enableButtons();

                }
            } catch (IOException | InterruptedException e) {
                C3kUtil.informUser(C3kUtil.ErrorString.ES_PANEL_CHANGE_ERROR);
            }
            if (buttonId.equals(btLoadPublicKey.getId())) {
                loadKey("antigua clave pública", "nueva clave pública asignada", "PUBLIC");
            }
            if (buttonId.equals(btLoadPrivateKey.getId())) {
                loadKey("antigua clave privada ", "nueva clave privada asignada", "PRIVATE");
            }
            if (buttonId.equals(btExit.getId())) {
                Platform.exit();
                /*getAppKeys().deleteGeneratedKeyes();*/ //todo ??
                //todo eliminar las claves generadas?
                getAppKeys().deleteGeneratedKeyes(AppKeys.KEY_PATH, AppKeys.PUBLIC_KEY_FILE_NAME,AppKeys.PRIVATE_KEY_FILE_NAME);
            }


        }


    }

    private void enableButtons() {
        btCifer.setDisable(false);
        btDecifer.setDisable(false);
    }

}
