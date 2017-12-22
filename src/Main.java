import gui.EntryInterface;
import gui.MenuBorderPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;
    private MenuBorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.root = new EntryInterface();
        Scene scene = new Scene(root, 1280, 720);
        this.primaryStage.setScene(scene);
        this.primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public MenuBorderPane getRoot() {
        return root;
    }
}
