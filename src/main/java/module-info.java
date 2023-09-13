module com.example.memorization_mastery {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires java.sql;

    opens com.example.memorization_mastery to javafx.fxml;
    exports com.example.memorization_mastery;
}