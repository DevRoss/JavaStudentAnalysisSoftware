package gui;

import javafx.scene.Node;

import java.io.File;

public class EditInterface extends MenuBorderPane{
    public EditInterface() {
        super();
    }

    public EditInterface(Node center) {
        super(center);
    }

    public EditInterface(Node center, Node top, Node right, Node bottom, Node left) {
        super(center, top, right, bottom, left);
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

    @Override
    public File getFile() {
        return super.getFile();
    }
}
