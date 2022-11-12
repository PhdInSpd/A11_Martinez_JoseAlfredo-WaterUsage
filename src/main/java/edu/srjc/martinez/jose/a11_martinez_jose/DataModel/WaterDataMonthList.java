/*
Name: Jose Alfredo Martinez
Email: chaver.yadim@gmail.com
Date: Nov 06 2022
Project Name: Water Usage
Course: CS17.11
Description: WaterDataMonthList data model that stores ArrayList of months( ArrayList<WaterDataPoint )
*/
package edu.srjc.martinez.jose.a11_martinez_jose.DataModel;

import java.util.ArrayList;

public class WaterDataMonthList extends ArrayList<ArrayList<WaterDataPoint>>
{
    public WaterDataMonthList(WaterDataList list)
    {
        ArrayList<WaterDataPoint> month = new ArrayList<>();
        for (WaterDataPoint p : list)
        {
            if (month.size() == 0)
            {
                month.add(p);
            } else
            {
                WaterDataPoint lastP = month.get(month.size() - 1);
                if (p.isSameMonthAndYear(lastP))
                {
                    month.add(p);
                } else
                {
                    this.add(month);
                    month = new ArrayList<>();
                    month.add(p);
                }
            }
        }
        // add last month that was not processed by foreach loop
        if (month.size() > 0)
        {
            this.add(month);
        }
    }

    public String getMonthSummary(int monthIndex)
    {
        if (monthIndex < 0 || monthIndex >= size())
        {
            return null;
        }

        ArrayList<WaterDataPoint> monthData = get(monthIndex);
        String summary = String.format("%04d/%02d\nMin usage: %.1f\nMax usage: %.1f\nMean usage: %.1f",
                monthData.get(0).getYear(),
                monthData.get(0).getMonth(),
                getMinUsage(monthData),
                getMaxUsage(monthData),
                getMeanUsage(monthData));
        return summary;
    }

    static private float getSumUsage(ArrayList<WaterDataPoint> data)
    {
        float sum = 0;
        for (WaterDataPoint p : data)
        {
            sum += p.getUsage();
        }
        return sum;
    }

    static private float getMeanUsage(ArrayList<WaterDataPoint> data)
    {
        return getSumUsage(data) / (float) data.size();
    }

    static private float getMaxUsage(ArrayList<WaterDataPoint> data)
    {
        float max = data.get(0).getUsage();
        for (WaterDataPoint p : data)
        {
            max = Math.max(max, p.getUsage());
        }
        return max;
    }

    static private float getMinUsage(ArrayList<WaterDataPoint> data)
    {
        float min = data.get(0).getUsage();
        for (WaterDataPoint p : data)
        {
            min = Math.min(min, p.getUsage());
        }
        return min;
    }
}
