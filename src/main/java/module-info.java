module org.example.bibliotecafx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.naming;

    opens org.example.bibliotecafx.interfaz to javafx.fxml;
    exports org.example.bibliotecafx.interfaz;
}