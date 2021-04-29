package main;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class Demo {
    public static void main(String[] args) {

        ArrayList<EmployeeSchedule> allEmployeeSchedules = null;
        try {
            allEmployeeSchedules = Reader.readFromFile("test.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<Pair> pairs = EmployeeHelper.getAllPairsOfWorkers(allEmployeeSchedules);
        for (Pair p: pairs
             ) {
            long d = EmployeeHelper.setDaysTogether(p,allEmployeeSchedules);
            p.setTotalDays(d);
        }
        Collections.sort(pairs);
        System.out.println(pairs.get(0) + " are the employees worked for longest time in a team");
    }




}
