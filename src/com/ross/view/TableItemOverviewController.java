package com.ross.view;

import com.ross.MainApp;
import com.ross.model.TableItem;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TableItemOverviewController {

    @FXML
    private TableView<TableItem> table;
    @FXML
    private TableColumn<TableItem, String> numColumn;
    @FXML
    private TableColumn<TableItem, String> nameColumn;
    @FXML
    private TableColumn<TableItem, Double> scoreColumn;

    private MainApp mainApp;

    @FXML
    private void initialize() {
        numColumn.setCellValueFactory(cellData -> cellData.getValue().num);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().score);
    }

    public TableItemOverviewController() {
        super();
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        table.setItems(mainApp.getStudentData());
    }
}
