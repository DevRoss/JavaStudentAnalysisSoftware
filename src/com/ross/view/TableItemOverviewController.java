package com.ross.view;

import com.ross.MainApp;
import com.ross.model.ScoreTable;
import com.ross.model.TableItem;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import util.ReadData;
import util.WriteData;

import java.awt.*;
import java.awt.MenuBar;
import java.io.File;
import java.io.IOException;
import java.nio.file.StandardCopyOption;
import java.text.NumberFormat;

public class TableItemOverviewController {

    // 表成员
    @FXML
    private TableView<TableItem> table;
    @FXML
    private TableColumn<TableItem, String> numColumn;
    @FXML
    private TableColumn<TableItem, String> nameColumn;
    @FXML
    private TableColumn<TableItem, Double> scoreColumn;

    // 文件路径
    @FXML
    private Text pathText;

    // 人数
    @FXML
    private Text numOfunder60;
    @FXML
    private Text numOfbetween60_70;
    @FXML
    private Text numOfbetween70_80;
    @FXML
    private Text numOfbetween80_90;
    @FXML
    private Text numOfover90;

    //  百分比
    @FXML
    private Text perOfunder60;
    @FXML
    private Text perOfbetween60_70;
    @FXML
    private Text perOfbetween70_80;
    @FXML
    private Text perOfbetween80_90;
    @FXML
    private Text perOfover90;

    // 最高，最低，平均分
    @FXML
    private Text highest;
    @FXML
    private Text lowest;
    @FXML
    private Text avg;

    // 可视化
    @FXML
    private PieChart pieChart;
    private File file = new File("");
    private File datFile = new File("");
    private String courseName = "";
    final static File workingDir = new File("");
    private ScoreTable scoreTable;

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
        initTable();
    }

    private void initTable() {
        numColumn.setCellValueFactory(cellData -> cellData.getValue().num);
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().name);
        scoreColumn.setCellValueFactory(cellData -> cellData.getValue().score);
        scoreColumn.setCellFactory(new ScoreCellFactory());
        scoreColumn.setOnEditCommit(event -> {
            final Double value = event.getNewValue() > 0.0 ? event.getNewValue() : event.getOldValue();
            ((TableItem) event.getTableView().getItems().get(event.getTablePosition().getRow())).setScore(value);
//            table.refresh();
            analyse();
            refreshPieChart();

        });

    }

    private void analyse() {
        numOfunder60.setText(scoreTable.range(0.0, 60.0).toString());
        numOfbetween60_70.setText(scoreTable.range(60.0, 70.0).toString());
        numOfbetween70_80.setText(scoreTable.range(70.0, 80.0).toString());
        numOfbetween80_90.setText(scoreTable.range(80.0, 90.0).toString());
        numOfover90.setText(scoreTable.range(90.0, 100.0).toString());
        perOfunder60.setText(scoreTable.rangePercentage(0.0, 60.0).toString());
        perOfbetween60_70.setText(scoreTable.rangePercentage(60.0, 70.0).toString());
        perOfbetween70_80.setText(scoreTable.rangePercentage(70.0, 80.0).toString());
        perOfbetween80_90.setText(scoreTable.rangePercentage(80.0, 90.0).toString());
        perOfover90.setText(scoreTable.rangePercentage(90.0, 100.0).toString());
        highest.setText(scoreTable.getHighest().toString());
        lowest.setText(scoreTable.getLowest().toString());
        avg.setText(scoreTable.getAvg().toString());

    }

    private ObservableList<Data> getChartData() {
        ObservableList<Data> answer = FXCollections.observableArrayList();
        NumberFormat perFormat = NumberFormat.getPercentInstance();
        perFormat.setMinimumFractionDigits(2);
        answer.addAll(new Data("<60分" + perFormat.format(scoreTable.rangePercentage(0.0, 60.0)), scoreTable.range(0.0, 60.0)),
                new Data("60-70分" + perFormat.format(scoreTable.rangePercentage(60.0, 70.0)), scoreTable.range(60.0, 70.0)),
                new Data("70-80分" + perFormat.format(scoreTable.rangePercentage(70.0, 80.0)), scoreTable.range(70.0, 80.0)),
                new Data("80-90分" + perFormat.format(scoreTable.rangePercentage(80.0, 90.0)), scoreTable.range(80.0, 90.0)),
                new Data("90-100分" + perFormat.format(scoreTable.rangePercentage(90.0, 100.0)), scoreTable.range(90.0, 100.0))
                );
        return answer;
    }

//    private void initPieChart(){
//        pieChart = new PieChart();
//        pieChart.ti
//    }

    private void refreshPieChart(){
        pieChart.getData().clear();
        pieChart.getData().addAll(getChartData());

    }

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


//        ScoreTable scoreTable;
        if (datFile.exists()) {
            scoreTable = ReadData.readTableData(ReadData.getDatPath(file, courseName));
            System.out.println("DAT 读取成功");
        } else scoreTable = new ScoreTable(ReadData.readStudentFile(file.getAbsolutePath()), courseName);

        // 清除数据后重新添加
//        mainApp.getStudentData().clear();
        mainApp.getStudentData().addAll(scoreTable.getItems());
        table.setItems(mainApp.getStudentData());
        analyse(); // 刷新统计数据
        refreshPieChart();

        // 显示路径
        pathText.setText(file.getAbsolutePath() + "\t共" + scoreTable.getSize().toString() + "人");
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        table.setItems(mainApp.getStudentData());
    }

    public File getFile() {
        return file;
    }

    @FXML
    private void save() throws IOException {
        WriteData.writeTable2File(scoreTable, ReadData.getDatPath(file, "数据库系统"));
    }
}
