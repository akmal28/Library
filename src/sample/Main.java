package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class Main extends Application {

    static Stage window;
    static Scene scene;
    Scene scene1;
    TableView table;

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setTitle("Perpustakaan");

        Label label = new Label("Selamat datang di Perpustakaan!");

        Button btn1 = new Button("Peminjaman Buku");
        Button btn2 = new Button("Daftar Buku");
        Button btn3 = new Button("Data Member");
        Button btn4 = new Button("Daftar Denda");

        GridPane layout = new GridPane();
        layout.setAlignment(Pos.CENTER);
        layout.addRow(0, btn1, btn2);
        layout.addRow(1, btn3, btn4);
        layout.setHgap(10);
        layout.setVgap(10);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label,layout);
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        btn1.setOnAction(e -> {
            try {
                window.setScene(peminjamanScene());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        btn2.setOnAction(e -> window.setScene(Book.bookScene()));
        btn3.setOnAction(e -> window.setScene(Member.memberScene()));
        btn4.setOnAction(e -> {
            try {
                window.setScene(Denda.dendaScene());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        scene = new Scene(vBox, 300, 250);

        window.setScene(scene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Button backBtn(){
        Button btnBack = new Button("Kembali");
        btnBack.setOnAction(e -> window.setScene(scene));
        return btnBack;
    }

    public static Scene peminjamanScene() throws SQLException {
        Scene scene1;
        Button btn1 = new Button("Daftar Peminjaman");
        btn1.setOnAction(e -> {
            try {
                window.setScene(Peminjaman.peminjamanScene());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        Button btn10 = new Button("Peminjaman Baru");
        btn10.setOnAction(e -> {
            try {
                window.setScene(PeminjamanBuku.pinjamBukuScene());
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });

        GridPane gPane = new GridPane();
        gPane.setVgap(10);
        gPane.setHgap(10);
        gPane.addRow(0, btn1, btn10);
        gPane.addRow(1, backBtn());
        gPane.setAlignment(Pos.CENTER);

        scene1 = new Scene(gPane, 300, 250);
        return scene1;
    }

}
