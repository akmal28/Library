package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;

public class Member {
    static TextField nameInput;
    static TextField emailInput;
    static TableView<MemberTable> tableM;

    public static Connection getConnection() throws SQLException{
        return DbConnection.getConnection();
    }

    public static ObservableList<MemberTable> getMember() throws SQLException{
        Statement stmt = DbConnection.getStatement();
        ObservableList<MemberTable> member = FXCollections.observableArrayList();

        ResultSet rs = stmt.executeQuery("SELECT * from member");

        while (rs.next()){
            member.add(new MemberTable(rs.getInt("member_id"), rs.getString("name"), rs.getString("email")));
        }

        return member;
    }

    public static TableView<MemberTable> tableMember() throws SQLException{
        TableColumn<MemberTable, Integer > memberIdColumn = new TableColumn("ID");
        memberIdColumn.setMinWidth(100);
        memberIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<MemberTable, String > memberNameColumn = new TableColumn("Nama");
        memberNameColumn.setMinWidth(200);
        memberNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<MemberTable, String> memberEmailColumn = new TableColumn("E-Mail");
        memberEmailColumn.setMinWidth(200);
        memberEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        tableM = new TableView();
        tableM.setItems(getMember());
        tableM.getColumns().addAll(memberIdColumn, memberNameColumn, memberEmailColumn);
        return tableM;
    }

    public static Scene memberScene(){
        Scene scene2;

        nameInput = new TextField();
        nameInput.setPromptText("Nama");
        emailInput = new TextField();
        emailInput.setPromptText("E-mail");

        Button btnAdd = new Button("Add");
        btnAdd.setOnAction(e -> {
            try {
                addMember();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        //Button btnRefresh = new Button("Refresh");

        HBox hBox = new HBox();
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(nameInput,emailInput, btnAdd);

        HBox foot = new HBox();
        foot.setPadding(new Insets(10,10,10,10));
        foot.setSpacing(10);
        foot.getChildren().addAll(Main.backBtn());
        foot.setAlignment(Pos.BOTTOM_RIGHT);

        VBox memberLayout = new VBox();
        try {
            memberLayout.getChildren().addAll(hBox, Member.tableMember(), foot);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        memberLayout.setPadding(new Insets(0,10,10,10));

        scene2 = new Scene(memberLayout, 800, 600);
        return scene2;
    }

    public static void addMember() throws SQLException{
        MemberTable newMember = new MemberTable();
        newMember.setName(nameInput.getText());
        newMember.setEmail(emailInput.getText());

        try {
            PreparedStatement posted = getConnection().prepareStatement("INSERT INTO member (name, email) VALUES (?, ?)");
            posted.setString(1, nameInput.getText());
            posted.setString(2, emailInput.getText());
            posted.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //ResultSet rs = stmt.executeQuery("INSERT INTO member (name, email) VALUES (?, ?)");

        tableM.getItems().add(newMember);
        nameInput.clear();
        emailInput.clear();
    }


}
