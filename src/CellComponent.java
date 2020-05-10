package GUIComponents;

import core.Status;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;

public class CellComponent extends Rectangle {

    public CellComponent () {
        this.setHeight(10);
        this.setWidth(10);
        this.setFill(Color.GREY);
    }
    

    public CellComponent (Status status) {
        this.setHeight(10);
        this.setWidth(10);
        if (status == Status.ALIVE)
            this.setFill(Color.RED);
        else
            this.setFill(Color.GREY);
    }
    
}
