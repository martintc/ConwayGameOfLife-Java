package GUIComponents;

import core.Status;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public class GameOfLifeGridPane extends GridPane {

    public GameOfLifeGridPane (Status[][] board ) {
        setGridLinesVisible(true);
        setHgap(1);
        setVgap(1);
        setAlignment(Pos.CENTER);
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                this.add(new CellComponent(), c, r);
    }

    public GameOfLifeGridPane getGameOfLifeGridPane (Status[][] board) {
        for (int r = 0; r < board.length; r++)
            for (int c = 0; c < board[0].length; c++)
                this.add(new CellComponent(board[r][c]), c, r);
        return this;
    }

    public GameOfLifeGridPane update (Status[][] board) {
        this.getChildren().clear();
        return getGameOfLifeGridPane(board);
    }
    
}
