package main;

public class Pair implements Comparable<Pair>{
    int idWorker1;
    int idWorker2;
    long totalDays;

    public Pair(int idWorker1, int idWorker2) {
        this.idWorker1 = idWorker1;
        this.idWorker2 = idWorker2;
        this.totalDays =0;
    }

    public void setTotalDays(long totalDays) {
        this.totalDays = totalDays;
    }

    public long getTotalDays() {
        return totalDays;
    }

    public int getIdWorker1() {
        return idWorker1;
    }

    public int getIdWorker2() {
        return idWorker2;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "idWorker1=" + idWorker1 +
                ", idWorker2=" + idWorker2 +
                ", totalDays=" + totalDays +
                '}';
    }

    @Override
    public int compareTo(Pair o) {
        int x;
        if(this.getTotalDays()>o.getTotalDays()){
            x= -1;
        }else{
            if(this.getTotalDays()==o.getTotalDays()){
                x= 0;
            }else{
                x= 1;
            }
        }
        return x;
    }
}
