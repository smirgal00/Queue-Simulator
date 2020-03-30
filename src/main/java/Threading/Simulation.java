package Threading;

import Clients.Person;
import Clients.RandomClientGenerator;
import InputOutput.FileControl;
import InputOutput.FileData;

import java.util.*;

public class Simulation implements Runnable {
    private FileData x;
    private int simulationTime;
    private FileControl data;
    private Scheduler scheduler;
    private ArrayList<Person> persons;
    private int waitingTime;

    public Simulation(String in, String out, FileControl data) {
        x = new FileData(in, out);
        this.data = data;
        this.simulationTime = x.getSimulationTime();
        this.waitingTime = 0;

        persons = new ArrayList<Person>();
        generatePersons();

        scheduler = new Scheduler(x.getQueueNumber(), data);
    }

    private void generatePersons() {
        RandomClientGenerator rand = new RandomClientGenerator(
                x.getClientNumber(),
                x.getMinArrival(),
                x.getWaitMin(),
                x.getMaxArrival(),
                x.getWaitMax()
        );

        this.persons = rand.getPersons();
        this.persons.get(0).setArrivalTime(1);
        this.persons.get(1).setArrivalTime(2);
        this.persons.get(2).setArrivalTime(3);
        persons.sort(Comparator.comparingInt(Person::getArrivalTime));
    }

    @Override
    public void run() {
        int currentTime = 0;
        int waitingTime = 0;
        while(currentTime <= simulationTime) {
            data.writeFile("Time: " + currentTime + "\n");
            Iterator<Person> iterator = persons.iterator();

            while(iterator.hasNext()) {
                Person temp = iterator.next();
                if(temp.getArrivalTime() == currentTime) {
                    scheduler.placeInQueue(temp);
                    waitingTime += temp.getServiceTime();
                    iterator.remove();
                }
            }

            waitingTime += scheduler.getWaitingTime();

            data.writeFile("Waiting clients: ");
            for(Person person : persons) {
                data.writeFile(person.printData());
            }
            data.writeFile("\n");
            currentTime++;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        data.writeFile("Average waiting time: " + String.valueOf((waitingTime - 1.0) / x.getClientNumber()));
        data.closeFile();
        System.exit(1);
    }

    public static void main(String[] args) {
        FileControl data = new FileControl(args[0], args[1]);
        Simulation p = new Simulation(args[0], args[1], data);
        Thread t = new Thread(p);
        t.start();
    }
}