package util;

import com.ross.model.ScoreTable;
import com.ross.model.TableItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Filter {
    public static ObservableList<TableItem> filter(ScoreTable scoreTable, String keyword) {
        ObservableList<TableItem> filteredTable = FXCollections.observableArrayList();
        for (TableItem item : scoreTable.getItems()) {
            if ((item.getNum() + item.getName() + item.getScore().toString()).contains(keyword))
                filteredTable.add(item);
        }
        return filteredTable;
    }
}
