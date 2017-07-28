package tic_tac_toe;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Pair;

/**
 * Created by morsalin on 7/25/17.
 */
public class TicTacToeController {
    private GamePlay gamePlay = new GamePlay();
    private final String PLAYER_TOKEN = "X";
    private final String CPU_TOKEN = "O";

    private final int BASE_ROW = 1;
    private final int BASE_COL = 0;

    @FXML
    private GridPane gridPane;
    @FXML
    private Label resultLabel;

    private final String BUTTON_IDENTIFIER = new Button().getClass().toString();

    public void onClickGameButtons(MouseEvent event) {
        Button clickedByPlayer = (Button)event.getSource();
        gamePlay.performPlayerMove(GridPane.getRowIndex(clickedByPlayer)-BASE_ROW,
                                    GridPane.getColumnIndex(clickedByPlayer)-BASE_COL);
        updateGameField(clickedByPlayer, PLAYER_TOKEN);

        if (!gamePlay.isResultDecided()) {
            Pair<Integer, Integer> cpuMove = gamePlay.performCPUMove();
            int row = cpuMove.getKey() + BASE_ROW;
            int col = cpuMove.getValue() + BASE_COL;
            int buttonIndex = (row * gridPane.getColumnConstraints().size()) + col;
            Button clickedByCPU = (Button)gridPane.getChildren().get(buttonIndex);
            updateGameField(clickedByCPU, CPU_TOKEN);

            if (gamePlay.isResultDecided()) {
                displayResult();
            }
        }
        else {
            displayResult();
        }
    }

    public void onClickResetButton(MouseEvent event) {
        int buttonCounts = 1;
        for (int i = 0; buttonCounts <= 9 && i < gridPane.getChildren().size(); i++) {
            if (isGameButton(gridPane.getChildren().get(i).getClass().toString())) {
                Button btn = (Button)gridPane.getChildren().get(i);
                btn.setDisable(false);
                btn.setText("");
                buttonCounts++;
            }
        }
        gamePlay.resetGrid();
        resultLabel.setText("");
    }

    private void displayResult() {
        disableGameField();
        resultLabel.setText(gamePlay.getResult());
    }

    private void disableGameField() {
        int buttonCounts = 1;
        for (int i = 0; buttonCounts <= 9 && i < gridPane.getChildren().size(); i++) {
            if (isGameButton(gridPane.getChildren().get(i).getClass().toString())) {
                Button btn = (Button)gridPane.getChildren().get(i);
                btn.setDisable(true);
                buttonCounts++;
            }
        }
    }

    private void updateGameField(Button button, String token) {
        if (token == PLAYER_TOKEN) button.setTextFill(Color.GREEN);
        else button.setTextFill(Color.RED);
        button.setText(token);
        button.setDisable(true);
    }

    private boolean isGameButton(String s) {
        return s.equals(BUTTON_IDENTIFIER);
    }
}
