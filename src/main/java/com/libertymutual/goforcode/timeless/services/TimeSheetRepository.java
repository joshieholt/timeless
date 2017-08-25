
package com.libertymutual.goforcode.timeless.services;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.libertymutual.goforcode.timeless.models.TimeSheet;

@Service
public class TimeSheetRepository {
    
    private int nextID = 1;
    
    public TimeSheet getCurrentTimeSheet() {
        List<TimeSheet> list = getAll();
        for (TimeSheet item : list) {
            if (!item.getIsComplete()) {
                return item;
            }
        }
        return null;
    }
    public List<TimeSheet> getAll() {
//        System.out.println("starting getAll in repo");
        List<TimeSheet> list = new ArrayList<TimeSheet>();
//        System.out.println("initialized List in getAll in repo");
        try (Reader in = new FileReader("timesheets.csv")) {
//            System.out.println("in try block in getAll in repo");
            Iterable<CSVRecord> records = null;
            records = CSVFormat.DEFAULT.parse(in);
            
            for (CSVRecord record : records) {
//                System.out.println("in for block in getAll in repo");
                TimeSheet timeSheet = new TimeSheet();
                timeSheet.setId(Integer.parseInt(record.get(0)));
                timeSheet.setWeekOf(record.get(1));
//                System.out.println("set id and weekOf");
                timeSheet.setMondayHours(Double.parseDouble(record.get(2)));
//                System.out.println("set mon hours");
                timeSheet.setTuesdayHours(Double.parseDouble(record.get(3)));
//                System.out.println("set tues hours");
                timeSheet.setWednesdayHours(Double.parseDouble(record.get(4)));
//                System.out.println("set wed hours");
                timeSheet.setThursdayHours(Double.parseDouble(record.get(5)));
//                System.out.println("set thu hours");
                timeSheet.setFridayHours(Double.parseDouble(record.get(6)));
//                System.out.println("set fri hours");
                timeSheet.setIsComplete(Boolean.parseBoolean(record.get(7)));
//                System.out.println("set complete");
                list.add(timeSheet);
//                System.out.println("added list to timeSheet");
                nextID = timeSheet.getId() + 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("Hit FileNotFoundException in getAll() function.");
        } catch (IOException e) {
            System.out.println("Hit IOException in getAll() function.");
        }
        return list;
    }

    public int getNextId() {
        return nextID;
    }
    public void create(TimeSheet item) {
        String[] stringArray = new String[8];
//        item.setId(nextID);
//        item.setIsComplete(false);
        nextID += 1;
        
        Writer out = null;
        try {
            out = new FileWriter("timesheets.csv", true);
        } catch (IOException e) {
            System.out.println("Hit IOException in create() function.");
        }
        try {
            stringArray[0] = (Integer.toString(item.getId()));
            stringArray[1] = (item.getWeekOf());
            stringArray[2] = (Double.toString(item.getMondayHours()));
            stringArray[3] = (Double.toString(item.getTuesdayHours()));
            stringArray[4] = (Double.toString(item.getWednesdayHours()));
            stringArray[5] = (Double.toString(item.getThursdayHours()));
            stringArray[6] = (Double.toString(item.getFridayHours()));
            stringArray[7] = (Boolean.toString(item.getIsComplete()));
            CSVPrinter printer = CSVFormat.DEFAULT.print(out);
            
            printer.printRecord(stringArray);
            out.close();
        } catch (IOException e) {
            System.out.println("Hit IOException in create() function.");
        }
    }
    
    public void submit(TimeSheet item) {
        item.setIsComplete(true);
        create(item);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
