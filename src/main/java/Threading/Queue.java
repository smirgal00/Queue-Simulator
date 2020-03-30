package Threading;

import Clients.Person;
import InputOutput.FileControl;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private BlockingQueue<Person> person;
    private AtomicInteger waitingTime;
    private boolean runCondition;
    private FileControl data;

    public Queue(boolean runCondition, FileControl data) {
        person = new LinkedBlockingDeque<>();
        this.runCondition = runCondition;
        this.data = data;
        waitingTime = new AtomicInteger(0);
    }

    @Override
    public void run() {
        while (runCondition) {
            try {
                Person current = person.take();
                int i = 0;
                while (current.getServiceTime() > 0) {
                    Thread.sleep(50);
                    String toWrite = "Queue " + Thread.currentThread().getName() + ": "
                            + "(" + current.getId()
                            + ", " + current.getArrivalTime()
                            + ", " + current.getServiceTime()
                            + ")";
                    data.writeFile(toWrite);
                    if (person.isEmpty()) {
                        data.writeFile("\n");
                    } else {
                        printClients(false);
                    }
                    Thread.sleep(1000);
                    person.remove(current);
                    current.substractWaitingTime();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void printClients(boolean cond) {
        if (cond == false) {
            for (Person p : person) {
                String toWrite = " (" + p.getId() + ", "
                        + p.getArrivalTime()
                        + ", " + p.getServiceTime()
                        + ")";
                data.writeFile(toWrite);
            }
            data.writeFile("\n");
        }
    }

    public void addPerson(Person temp) {
        this.person.add(temp);
        waitingTime.addAndGet(temp.getServiceTime());
        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getWaitingTime() {
        return waitingTime.get();
    }

    public int getPersonCount() {
        return person.size();
    }
}