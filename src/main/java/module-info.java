module sjf.os_project {
    requires javafx.controls;
    requires javafx.fxml;


    opens sjf.os_project to javafx.fxml;
    exports sjf.os_project;
}