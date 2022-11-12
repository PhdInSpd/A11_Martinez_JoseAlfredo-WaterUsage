/*
Name: Jose Alfredo Martinez
Email: chaver.yadim@gmail.com
Date: Nov 06 2022
Project Name: Water Usage
Course: CS17.11
Description: water usage application
*/
package edu.srjc.martinez.jose.a11_martinez_jose;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WaterUsageApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader(WaterUsageApplication.class.getResource("water-usage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1200, 800);
        stage.setTitle("Water Usage!");
        stage.setScene(scene);
//        stage.setFullScreen(true);
        stage.show();
    }

    public static void main(String[] args)
    {
        launch();
    }
}