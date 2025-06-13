
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene s_scene;
    public static String wString;

    @Override
    public void start(Stage stage) throws IOException {
        s_scene = new Scene(loadFXML("mainMenu"), 800, 675);
        stage.setScene(s_scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        s_scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}