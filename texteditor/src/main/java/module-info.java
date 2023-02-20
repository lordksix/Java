module pasquel.texteditor {
    requires org.fxmisc.richtext;
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens pasquel.texteditor to javafx.fxml;
    exports pasquel.texteditor;

}
