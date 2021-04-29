package main;

import java.util.ArrayList;
import java.util.HashSet;

import static java.time.temporal.ChronoUnit.DAYS;

public class EmployeeHelper {
    public static ArrayList<Pair> getAllPairsOfWorkers(ArrayList<EmployeeSchedule> a){
        HashSet<Integer> allEmployees = new HashSet<>(); // here we gone have all different employees id
        for (EmployeeSchedule es:a
        ) {
            allEmployees.add(es.getEmpId());
        }
        ArrayList<Integer> allEmployList = new ArrayList<>(allEmployees);
        ArrayList<Pair> allPairs = new ArrayList<>();
        for(int i = 0 ; i < allEmployList.size(); i ++){
            for(int j = i+1 ; j < allEmployList.size(); j ++){
                Pair p = new Pair(allEmployList.get(i),allEmployList.get(j));
                allPairs.add(p);
            }
        }
        return allPairs;
    }


    public static ArrayList<Integer> getAllProjects(ArrayList<EmployeeSchedule> a){
        HashSet<Integer> allProjects = new HashSet<>(); // here we gone have all different employees id
        for (EmployeeSchedule es:a
        ) {
            allProjects.add(es.getProjectId());
        }
        ArrayList<Integer> allProjectsList = new ArrayList<>(allProjects);
        return allProjectsList;

    }


    public static long setDaysTogether(Pair p,ArrayList<EmployeeSchedule> all){
        EmployeeSchedule e1;
        EmployeeSchedule e2;
        long days=0;
        for (Integer i:getAllProjects(all)
        ) {
            e1=null;
            e2=null;
            for (EmployeeSchedule e : all
            ) {
                if (e.getEmpId() == p.idWorker1 && e.getProjectId()==i) {
                    e1 = e;
                }
                if (e.getEmpId() == p.idWorker2 && e.getProjectId()==i) {
                    e2 = e;
                }
            }
            if(e1 != null && e2!=null){
                //the pair of workers had worked on the same project
                if(e1.getDateFrom().isBefore(e2.getDateFrom())){
                    if(e1.getDateTo().isBefore(e2.getDateTo())){
                        long daysBetween = DAYS.between(e2.getDateFrom(), e1.getDateTo());
                        days +=daysBetween;
                    }else{
                        long daysBetween = DAYS.between(e2.getDateFrom(), e2.getDateTo());
                        days +=daysBetween;
                    }
                }else{
                    if(e1.getDateTo().isBefore(e2.getDateTo())){
                        long daysBetween = DAYS.between(e1.getDateFrom(), e1.getDateTo());
                        days +=daysBetween;
                    }else{
                        long daysBetween = DAYS.between(e1.getDateFrom(), e2.getDateTo());
                        days +=daysBetween;
                    }

                }

            }
        }
        if(days<0){
            days = 0;
        }
        return days;

    }
}
