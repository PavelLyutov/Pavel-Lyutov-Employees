package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reader {


    public static ArrayList<EmployeeSchedule> readFromFile(String fileName) throws Exception {

        StringToDateUtil helper=new StringToDateUtil();;
        ArrayList<EmployeeSchedule> allEmployeeSchedules = new ArrayList<>(); // employees information

        File f = new File(fileName);
        try (Scanner sc = new Scanner(f);){
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                String[] values = line.split(", ");
                int id;
                int projId;
                if(tryParse(values[0])==null){
                    //the id in the file is not a int
                    continue; // we ll skip this employee information
                }else{
                     id = Integer.parseInt(values[0]);
                }
                if(tryParse(values[1])==null){
                    //the id in the file is not a int
                    continue; // we ll skip this employee information
                }else {
                     projId =Integer.parseInt(values[1]);
                }
                LocalDate start;
                LocalDate end;

                if(helper.stringToDate(values[2])== null){
                    System.out.println("Invalid date format for worker with id : " + id + " on project " + projId);
                    continue;
                }
                start=helper.stringToDate(values[2]).toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();;

                if(values[3].equalsIgnoreCase("NULL")) {
                    end = LocalDate.now();
                }else{
                    if(helper.stringToDate(values[3])==null) {
                        System.out.println("Invalid date format for worker with id : " + id + " on project " + projId);
                        continue;
                    }else{
                        end = helper.stringToDate(values[3]).toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();;
                    }
                }

                EmployeeSchedule em = new EmployeeSchedule(id,projId,start,end);
                allEmployeeSchedules.add(em);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allEmployeeSchedules;
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
