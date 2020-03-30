package Clients;

public class Person {

    private int id;
    private int arrivalTime;
    private int serviceTime;

    public Person(int id, int arrivalTime, int serviceTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getId() {
        return id;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public String printData() {
        return ("(" + this.id + ", " + this.arrivalTime + ", " + this.serviceTime + ") ");
    }

    public void substractWaitingTime() {
        this.serviceTime -= 1;
    }

    public void setArrivalTime(int x) {
        this.arrivalTime = x;
    }
}
