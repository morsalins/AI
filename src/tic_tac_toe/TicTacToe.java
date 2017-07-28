package tic_tac_toe;/**
 * Created by morsalin on 7/19/17.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("tictactoeUI.fxml"));
        primaryStage.setTitle("Tic Tac Tow");
        primaryStage.setScene(new Scene(root, 500, 500));
        primaryStage.show();
    }
}
