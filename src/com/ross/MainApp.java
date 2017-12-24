package com.ross;

import com.ross.model.TableItem;
import com.ross.view.TableItemOverviewController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainApp extends Application {

    private Stage primaryStage;
    private VBox rootLayout;
    private ObservableList<TableItem> studentData = FXCollections.observableArrayList();
    private Scene scene;
    private Stage aboutStage = new Stage();
    private Stage helpStage = new Stage();

    public MainApp() {
//        studentData.add(new TableItem("cjcj", "12355235626", 0.0));
//        studentData.add(new TableItem("cj", "1235626", 0.0));
//        studentData.add(new TableItem("j", "55235626", 0.0));

    }

    public ObservableList<TableItem> getStudentData() {
        return studentData;
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("AddressApp");
        this.primaryStage.setOnCloseRequest(event -> exit());
        try (FileInputStream image = new FileInputStream("icon.jpg")) {
            primaryStage.getIcons().add(new Image(image));
        } catch (IOException e) {
        }
        initRootLayout();

//        showPersonOverview();
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/EditView.fxml"));
            rootLayout = (VBox) loader.load();
//            rootLayout.getChildren();

            // Show the scene containing the root layout.
            TableItemOverviewController controller = loader.getController();
            controller.setMainApp(this);
            this.scene = new Scene(rootLayout, 1280, 720);
            primaryStage.setScene(this.scene);
            primaryStage.show();

        } catch (IOException e) {
//            e.printStackTrace();
            showHelp();
        }
    }


    /**
     * Returns the main stage.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public VBox getRootLayout() {
        return rootLayout;
    }

    public Scene getScene() {
        return scene;
    }

    public void exit() {
        aboutStage.close();
        helpStage.close();
        Platform.exit();
    }

    public Stage getAboutStage() {
        return aboutStage;
    }

    public Stage getHelpStage() {
        return helpStage;
    }


    public void showHelp() {
        getHelpStage().setTitle("帮助");
        VBox root = new VBox();
        root.setSpacing(5);
        root.setAlignment(Pos.CENTER);
        Label mainHelp = new Label("将course.txt文件放在当前文件夹\n成绩单资源文件保存后会放在resource文件夹");
        root.getChildren().add(mainHelp);
        Scene scene = new Scene(root, 600, 100);
        getHelpStage().setScene(scene);
        getHelpStage().show();
    }
}