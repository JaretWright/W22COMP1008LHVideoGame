module com.example.w22comp1008lhvideogame {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.w22comp1008lhvideogame to javafx.fxml;
    exports com.example.w22comp1008lhvideogame;
    exports com.example.w22comp1008lhvideogame.sprites;
    opens com.example.w22comp1008lhvideogame.sprites to javafx.fxml;
}