package Clients;

import java.util.ArrayList;
import java.util.Random;

public class RandomClientGenerator {
    private int nrOfClients;
    private int arrivalTimeMin;
    private int serviceTimeMin;
    private int arrivalTimeMax;
    private int serviceTimeMax;

    private ArrayList<Person> Persons;

    public RandomClientGenerator(int nrOfClients, int arrivalTimeMin, int serviceTimeMin, int arrivalTimeMax, int serviceTimeMax) {
        this.arrivalTimeMin = arrivalTimeMin;
        this.arrivalTimeMax = arrivalTimeMax;
        this.nrOfClients = nrOfClients;
        this.serviceTimeMin = serviceTimeMin;
        this.serviceTimeMax = serviceTimeMax;

        Persons = new ArrayList<Person>();

        for (int i = 0; i < nrOfClients; i++) {
            Person temp = new Person(
                    i + 1,
                    new Random().nextInt((arrivalTimeMax - arrivalTimeMin) + 1) + arrivalTimeMin,
                    new Random().nextInt((serviceTimeMax - serviceTimeMin) + 1) + serviceTimeMin
            );

            Persons.add(temp);


        }
    }

    public ArrayList<Person> getPersons() {
        return this.Persons;
    }

}
