/*
Name: Jose Alfredo Martinez
Email: chaver.yadim@gmail.com
Date: Nov 06 2022
Project Name: Water Usage
Course: CS17.11
Description: WaterDataPoint data model
*/
package edu.srjc.martinez.jose.a11_martinez_jose.DataModel;

public class WaterDataPoint
{
    //region fields
    final int DATE_FIELD = 0;
    final int USAGE_FIELD = 1;

    private int day;

    private int month;
    private int year;
    private float usage;
    //endregion

    //region setter and getters
    public int getDay()
    {
        return day;
    }

    public int getMonth()
    {
        return month;
    }

    public int getYear()
    {
        return year;
    }

    public float getUsage()
    {
        return usage;
    }

    private void setUsage(String inUsage)
    {
        usage = Float.parseFloat(inUsage);
    }

    private void setDate(String date) throws Exception
    {
        String[] fields = date.split("/", -1);
        if (fields.length < 3)
        {
            throw new Exception(String.format("WaterDataPoint.setDate: invalid number of fields for date: %s", date));
        }
        month = Integer.parseInt(fields[0]);
        day = Integer.parseInt(fields[1]);
        year = Integer.parseInt(fields[2]);

    }

    //endregion

    //region ctor
    public WaterDataPoint(String line) throws Exception
    {
        String[] fields = line.split(",", -1);

        if (fields.length < 2)
        {
            throw new Exception(String.format("WaterDataPoint: invalid number of fields for line: %s", line));
        }

        setDate(fields[DATE_FIELD]);
        setUsage(fields[USAGE_FIELD]);
    }
    //endregion

    //region public methods
    public boolean isSameMonthAndYear(WaterDataPoint p)
    {
        return p.getYear() == this.getYear() &&
                p.getMonth() == this.getMonth();
    }

    public String getDate()
    {
        return String.format("%04d/%02d/%02d", getYear(), getMonth(), getDay());
    }

    public String getDateYearMonth()
    {
        return String.format("%04d/%02d", getYear(), getMonth());
    }
    //endregion
}
