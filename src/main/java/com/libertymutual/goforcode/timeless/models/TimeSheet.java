
package com.libertymutual.goforcode.timeless.models;


public class TimeSheet {

    private int iD;
    private String weekOf;
    private double mondayHours;
    private double tuesdayHours;
    private double wednesdayHours;
    private double thursdayHours;
    private double fridayHours;
    private boolean isComplete;
    
    public TimeSheet() {}
    
    public void setId(int id) {
        iD = id;
    }
    
    public void setWeekOf(String theWeek) {
        weekOf = theWeek;
    }
    
    
    public void setIsComplete(boolean complete) {
        isComplete = complete;
    }
    
    public int getId() {
        return iD;
    }
    
    public String getWeekOf() {
        return weekOf;
    }
    
    public boolean getIsComplete() {
        return isComplete;
    }

    /**
     * @return the mondayHours
     */
    public double getMondayHours() {
        return mondayHours;
    }

    /**
     * @return the tuesdayHours
     */
    public double getTuesdayHours() {
        return tuesdayHours;
    }

    /**
     * @return the wednesdayHours
     */
    public double getWednesdayHours() {
        return wednesdayHours;
    }

    /**
     * @return the thursdayHours
     */
    public double getThursdayHours() {
        return thursdayHours;
    }

    /**
     * @return the fridayHours
     */
    public double getFridayHours() {
        return fridayHours;
    }

    /**
     * @param mondayHours the mondayHours to set
     */
    public void setMondayHours(double mondayHours) {
        this.mondayHours = mondayHours;
    }

    /**
     * @param tuesdayHours the tuesdayHours to set
     */
    public void setTuesdayHours(double tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    /**
     * @param wednesdayHours the wednesdayHours to set
     */
    public void setWednesdayHours(double wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    /**
     * @param thursdayHours the thursdayHours to set
     */
    public void setThursdayHours(double thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    /**
     * @param fridayHours the fridayHours to set
     */
    public void setFridayHours(double fridayHours) {
        this.fridayHours = fridayHours;
    }
    
    public double getTotalHours() {
        return mondayHours + tuesdayHours + wednesdayHours + thursdayHours + fridayHours;
    }

    
}
