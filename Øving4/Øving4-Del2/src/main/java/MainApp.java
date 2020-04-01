import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class MainApp extends Application {

  public static Scene scene;

  public static void main(String[] args) {
    launch(args);
  }

  private static Parent loadFXML(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    scene = new Scene(loadFXML("MainPage"));
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
