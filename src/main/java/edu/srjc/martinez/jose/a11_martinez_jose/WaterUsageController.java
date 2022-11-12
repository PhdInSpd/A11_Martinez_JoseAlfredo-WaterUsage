/*
Name: Jose Alfredo Martinez
Email: chaver.yadim@gmail.com
Date: Nov 06 2022
Project Name: Water Usage
Course: CS17.11
Description: water usage controller
*/
package edu.srjc.martinez.jose.a11_martinez_jose;

import edu.srjc.martinez.jose.a11_martinez_jose.DataModel.WaterDataList;
import edu.srjc.martinez.jose.a11_martinez_jose.DataModel.WaterDataMonthList;
import edu.srjc.martinez.jose.a11_martinez_jose.DataModel.WaterDataPoint;
import edu.srjc.martinez.jose.a11_martinez_jose.Utilities.DataPopup;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class WaterUsageController implements Initializable
{
    //region fields
    WaterDataMonthList monthList;
    @FXML
    private BarChart<?, ?> barChart;
    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;


    @FXML
    private Button btnNext;

    @FXML
    private Button btnPrevious;

    @FXML
    private MenuItem mnuFileOpen;

    @FXML
    private MenuItem mnuHelpAbout;

    @FXML
    private MenuItem mnuItemExit;
    int currentMonth;
    //endregion

    //region event handlers
    @FXML
    void btnNext_Click(ActionEvent event)
    {
        if (monthList == null)
        {
            updateChangeMonthEnable();
            return;
        }
        currentMonth++;
        updateChangeMonthEnable();
        setUpChart();
    }


    @FXML
    void btnPrevious_Click(ActionEvent event)
    {
        if (monthList == null || monthList.size() == 0)
        {
            updateChangeMonthEnable();
            return;
        }
        currentMonth--;
        updateChangeMonthEnable();
        setUpChart();
    }

    @FXML
    void mnuFileOpen_Click(ActionEvent event)
    {

    }

    @FXML
    void mnuHelpAbout_Click(ActionEvent event)
    {

    }

    @FXML
    void mnuItemExit_Click(ActionEvent event)
    {
        Platform.exit();
    }
    //endregion


    //region private and public methods
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        try
        {
            WaterDataList dataList = new WaterDataList(new File("DailyWaterData.csv"));
            monthList = new WaterDataMonthList(dataList);
            currentMonth = 0;
            updateChangeMonthEnable();
            setUpChart();

        } catch (Exception e)
        {
            System.err.println(e.getMessage());
            return;
        }
    }

    private void setUpChart()
    {
        ArrayList<WaterDataPoint> month = monthList.get(currentMonth);

        barChart.getData().clear();
        if (month == null || month.size() == 0)
        {
            return;
        }

        //CategoryAxis xAxis = new CategoryAxis();
        //NumberAxis yAxis = new NumberAxis();

        barChart.setTitle("Water Usage");
        xAxis.setLabel("Date");
        yAxis.setLabel("Usage");

        XYChart.Series monthSeries = new XYChart.Series();
        monthSeries.setName("Usage for " + month.get(0).getDateYearMonth());

        String summary = monthList.getMonthSummary(currentMonth);
        for (WaterDataPoint p : month)
        {
            XYChart.Data<String, Float> dataPoint = new XYChart.Data(p.getDate(), p.getUsage());

            String popup = String.format("usage: %.1f\n%s", p.getUsage(), summary);
            dataPoint.setNode(new DataPopup(popup));
            monthSeries.getData().add(dataPoint);
        }

        barChart.getData().add(monthSeries);
    }

    private void updateChangeMonthEnable()
    {

        if (monthList == null || monthList.size() == 0)
        {
            btnPrevious.setDisable(true);
            btnNext.setDisable(true);
            return;
        }
        int maxMonth = (monthList.size() - 1);
        btnPrevious.setDisable(currentMonth == 0);
        btnNext.setDisable(currentMonth >= maxMonth);
    }

    //endregion

}