/*
Name: Sean Kirkpatrick
Email: skirkpatrick@santarosa.edu
Date: Nov 06 2022
Project Name: N/A
Course: CS17.11
Description: Utility to show popup in java fx charts
*/
package edu.srjc.martinez.jose.a11_martinez_jose.Utilities;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/* the way to change position of where this shows up was to use a different container than StackPane */
public class DataPopup extends AnchorPane
{
    private Label lblContent;

    public DataPopup(String content)
    {
        setPrefSize(10, 10);

        lblContent = formatContent(content);
        setTopAnchor(lblContent,0.0 );
//        setRightAnchor(lblContent,0.0 );
//        setLeftAnchor(lblContent,0.0 );

        lblContent.setVisible(false);
        getChildren().add(lblContent);

        setOnMouseEntered(mouseEvent -> {
            var source =  mouseEvent.getSource();
            Node node = getChildren().get(0);

            node.setVisible(true);
            double X  = mouseEvent.getX();
            double Y  = mouseEvent.getY();
            double sceneX  = mouseEvent.getSceneX();
            double sceneY = mouseEvent.getSceneY();
            double screenX = mouseEvent.getScreenX();
            double screenY = mouseEvent.getScreenY();
            System.out.println( String.format( "(X,Y): (%.1f,%.1f) screen (X,Y): (%.1f,%.1f) scene (X,Y): (%.1f,%.1f) node layout (X,Y): (%.1f,%.1f) node translation (X,Y): (%.1f,%.1f) bndsLocal (%.1f,%.1f,%.1f,%.1f)" ,
                                        X,Y,
                                        screenX, screenY,
                                        sceneX,sceneY,
                                        node.getLayoutX(), node.getLayoutY(),
                                        node.getTranslateX(), node.getTranslateY(),
                                        node.getBoundsInLocal().getMinX(),
                                        node.getBoundsInLocal().getMinY(),
                                        node.getBoundsInLocal().getMaxX(),
                                        node.getBoundsInLocal().getMaxY() ) );

// y and x location are not what I expect
//            node.setTranslateX( transX );
//            node.setTranslateY( transY );
//            node.setLayoutX( transX);
//            node.setLayoutY( transY);
// bar moves
//            setTranslateX( transX );
//            setTranslateY( transY );
            toFront();
            //node.toFront();
        });

        setOnMouseExited(mouseEvent -> {
            getChildren().get(0).setVisible(false);
        });
    }

    public Label formatContent(String content)
    {
        Label label = new Label(content);
        label.getStyleClass().addAll("default-color0", "char-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-border-color: black; -fx-border-width: 2px; -fx-background-color: white");
        label.setTextFill(Color.FIREBRICK);
        label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

        return label;
    }
}
