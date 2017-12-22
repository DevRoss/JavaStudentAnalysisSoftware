package com.ross.view;


import com.ross.model.TableItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;

public class ScoreCellFactory implements Callback<TableColumn<TableItem, Double>, TableCell<TableItem, Double>> {
    @Override
    public TableCell<TableItem, Double> call(TableColumn<TableItem, Double> param) {
        DoubleStringConverter converter = new DoubleStringConverter();
        TextFieldTableCell<TableItem, Double> cell = new TextFieldTableCell<>(converter);
        return cell;
    }
}
