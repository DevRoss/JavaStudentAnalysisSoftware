package test;
import gui.MenuBorderPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class testJavaFX extends Application{
    @Override
    public void start(Stage primaryStage){
        MenuBorderPane root = new MenuBorderPane();
        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
