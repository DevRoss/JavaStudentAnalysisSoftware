package com.ross.view;

import com.ross.MainApp;
import com.ross.model.ScoreTable;
import com.ross.model.TableItem;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import util.ReadData;

import java.io.File;
import java.io.IOException;

public class TableItemOverviewController {

    @FXML
    private TableView<TableItem> table;
    @FXML
    private TableColumn<TableItem, String> numColumn;
    @FXML
    private TableColumn<TableItem, String> nameColumn;
    @FXML
    private TableColumn<TableItem, Double> scoreColumn;


    private File file = new File("");
    private File datFile = new File("");
    private String courseName = "";
    final static File workingDir = new File("");

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem fileOpenItem;

    private MainApp mainApp;

    public TableItemOverviewController() {
        super();
    }

    @FXML
    private void initialize() {
//        table.setEditable(true);
        numColumn.setCellValueFactory(cellData -> cellData.getValue().num);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().score);
        scoreColumn.setCellFactory(new ScoreCellFactory());
    }

//    @FXML

    @FXML
    private void openFileAction() throws IOException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("TXT", "*.txt")
        );

        // 优先读取.dat文件
        if (this.file.getAbsolutePath().equals(workingDir.getAbsolutePath())) {
            fileChooser.setInitialDirectory(this.file.getAbsoluteFile());
        } else {
            fileChooser.setInitialDirectory(this.file.getParentFile());
        }


        System.out.println(this.file.getAbsolutePath());
        this.file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        // 如果之前存在.dat数据，则优先读取


        courseName = "数据库系统";


        this.datFile = new File(ReadData.getDatPath(file, courseName));
        System.out.println(datFile);


        ScoreTable scoreTable;
        if (datFile.exists()) {
            scoreTable = ReadData.readTableData(ReadData.getDatPath(file, courseName));
            System.out.println("DAT 读取成功");
        } else scoreTable = new ScoreTable(ReadData.readStudentFile(file.getAbsolutePath()), courseName);

        // 清除数据后重新添加
//        mainApp.getStudentData().clear();
        mainApp.getStudentData().addAll(scoreTable.getItems());
        table.setItems(mainApp.getStudentData());
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        table.setItems(mainApp.getStudentData());
    }

    public File getFile() {
        return file;
    }

    @FXML
    private void onEditCommit() {

    }

    @FXML
    private void onEditCancel() {

    }
}
