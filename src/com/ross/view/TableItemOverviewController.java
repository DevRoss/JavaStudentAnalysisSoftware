package com.ross.view;

import com.ross.MainApp;
import com.ross.model.ScoreTable;
import com.ross.model.TableItem;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.DefaultStringConverter;
import javafx.util.converter.DoubleStringConverter;
import util.Filter;
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

    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    CategoryAxis lineXAxis;
    @FXML
    NumberAxis lineYAxis;

    final static String under60 = "<60分";
    final static String btwn60_70 = "60-69分";
    final static String btwn70_80 = "70-79分";
    final static String btwn80_90 = "80-89分";
    final static String over90 = ">=90分";


    private File file = new File("");
    private File datFile = new File("");
    private String courseName = "";
    final static File workingDir = new File("");
    private ScoreTable scoreTable;
    private ObservableList<TableItem> filteredTable = FXCollections.observableArrayList();
    @FXML
    private TextField searchField;

    @FXML
    private Menu fileMenu;

    @FXML
    private MenuItem fileOpenItem;

    private MainApp mainApp;

    private NumberFormat perFormat;

    public TableItemOverviewController() {
        perFormat = NumberFormat.getPercentInstance();
        perFormat.setMinimumFractionDigits(2);
    }

    @FXML
    private void initialize() {
        initBarChart();
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
            refreshBarChart();

        });

    }

    private void analyse() {
        numOfunder60.setText(scoreTable.range(0.0, 60.0).toString());
        numOfbetween60_70.setText(scoreTable.range(60.0, 70.0).toString());
        numOfbetween70_80.setText(scoreTable.range(70.0, 80.0).toString());
        numOfbetween80_90.setText(scoreTable.range(80.0, 90.0).toString());
        numOfover90.setText(scoreTable.range(90.0, 100.0).toString());
        perOfunder60.setText(perFormat.format(scoreTable.rangePercentage(0.0, 60.0)));
        perOfbetween60_70.setText(perFormat.format(scoreTable.rangePercentage(60.0, 70.0)));
        perOfbetween70_80.setText(perFormat.format(scoreTable.rangePercentage(70.0, 80.0)));
        perOfbetween80_90.setText(perFormat.format(scoreTable.rangePercentage(80.0, 90.0)));
        perOfover90.setText(perFormat.format(scoreTable.rangePercentage(90.0, 100.0)));
        highest.setText(scoreTable.getHighest().toString());
        lowest.setText(scoreTable.getLowest().toString());
        avg.setText(scoreTable.getAvg().toString());

    }


    private XYChart.Series<String, Integer> getBarChartData(boolean init) {

        XYChart.Series<String, Integer> series = new XYChart.Series<String, Integer>();
        if (!init) {
            series.getData().add(new XYChart.Data<>(under60, scoreTable.range(0.0, 60.0)));
            series.getData().add(new XYChart.Data<>(btwn60_70, scoreTable.range(60.0, 70.0)));
            series.getData().add(new XYChart.Data<>(btwn70_80, scoreTable.range(70.0, 80.0)));
            series.getData().add(new XYChart.Data<>(btwn80_90, scoreTable.range(80.0, 90.0)));
            series.getData().add(new XYChart.Data<>(over90, scoreTable.range(90.0, 100.0)));
        } else series.getData().add(new XYChart.Data<>("", 0));
        return series;

    }

    private void initBarChart(){
     barChart.getData().add(getBarChartData(true));
    }

    private void refreshBarChart() {
//        barChart.getData().clear();
        barChart.getData().setAll(getBarChartData(false));
    }


    private ObservableList<Data> getPieChartData() {
        ObservableList<Data> answer = FXCollections.observableArrayList();

        answer.addAll(new Data(under60 + perFormat.format(scoreTable.rangePercentage(0.0, 60.0)), scoreTable.range(0.0, 60.0)),
                new Data(btwn60_70 + perFormat.format(scoreTable.rangePercentage(60.0, 70.0)), scoreTable.range(60.0, 70.0)),
                new Data(btwn70_80 + perFormat.format(scoreTable.rangePercentage(70.0, 80.0)), scoreTable.range(70.0, 80.0)),
                new Data(btwn80_90 + perFormat.format(scoreTable.rangePercentage(80.0, 90.0)), scoreTable.range(80.0, 90.0)),
                new Data(over90 + perFormat.format(scoreTable.rangePercentage(90.0, 100.0)), scoreTable.range(90.0, 100.0))
        );
        return answer;
    }


    private void refreshPieChart() {
        pieChart.getData().clear();
        pieChart.getData().addAll(getPieChartData());

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
        filteredTable.addAll(scoreTable.getItems());
        table.setItems(filteredTable);
        analyse(); // 刷新统计数据
        refreshPieChart();
        refreshBarChart();
        initSearchField();

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

    private void initSearchField() {
        searchField.setOnKeyPressed(event -> {
            if (searchField.getText() != null)
                filteredTable.setAll(Filter.filter(scoreTable, searchField.getText()));
        });
        searchField.setOnKeyReleased(event -> {
            if (searchField.getText() != null)
                filteredTable.setAll(Filter.filter(scoreTable, searchField.getText()));
        });
    }

    @FXML
    private void exit() {
        Platform.exit();
    }
}
