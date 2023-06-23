module emsi.ma.clientschats {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens emsi.ma.clientschats to javafx.fxml;
    exports emsi.ma.clientschats;
}