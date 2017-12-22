package gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import

import java.io.File;

/**
 * 带菜单栏及其功能的基础模板
 */
public class MenuBorderPane extends BorderPane {

    static private MenuBar menuBar = new MenuBar();
    static private Menu fileMenu = new Menu("文件");
    static private MenuItem openMenuItem = new MenuItem("打开学生列表");
    static private MenuItem exitMenuItem = new MenuItem("退出");
    public static File file = new File("2010级网络工程10w.txt");

    public MenuBorderPane() {
        super();
        initMenu();
    }

    public MenuBorderPane(Node center) {
        super(center);
        initMenu();
    }

    public MenuBorderPane(Node center, Node top, Node right, Node bottom, Node left) {
        super(center, top, right, bottom, left);
        initMenu();
    }

    private void initMenu() {
        exitMenuItem.setOnAction(event -> Platform.exit());
        openMenuItem.setOnAction(new ChooseFileHandler());
        fileMenu.getItems().addAll(openMenuItem, exitMenuItem);
        menuBar.getMenus().add(fileMenu);
        this.setTop(menuBar);
    }

    class ChooseFileHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            FileChooser fileChooser = new FileChooser();
            // 格式过滤器
            fileChooser.getExtensionFilters().add(
                    new FileChooser.ExtensionFilter("TXT", "*.txt")
            );
            fileChooser.setInitialDirectory(new File("").getAbsoluteFile());
            file = fileChooser.showOpenDialog(new Stage());

            // Start a new scene


        }
    }

    public File getFile() {
        return file;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
