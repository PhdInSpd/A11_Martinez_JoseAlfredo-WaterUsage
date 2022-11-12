/*
Name: Jose Alfredo Martinez
Email: chaver.yadim@gmail.com
Date: Nov 06 2022
Project Name: Water Usage
Course: CS17.11
Description: WaterDataList data model
*/
package edu.srjc.martinez.jose.a11_martinez_jose.DataModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WaterDataList extends ArrayList<WaterDataPoint>
{
    public WaterDataList(File wdFile) throws FileNotFoundException
    {
        Scanner fIn = new Scanner(wdFile);

        String line;
        while (fIn.hasNextLine())
        {
            line = fIn.nextLine().trim();
            if (line.length() == 0 || line.startsWith("#"))
            {
                continue;
            }

            try
            {
                WaterDataPoint dp = new WaterDataPoint(line);
                this.add(dp);
            } catch (Exception e)
            {
                System.err.println(String.format("Error occurred on this line: %s", line));
            }
        }
        Collections.reverse(this);

    }
}
