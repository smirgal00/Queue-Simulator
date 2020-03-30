package Threading;

import Clients.Person;
import InputOutput.FileControl;

import java.util.ArrayList;

public class Scheduler {
    private ArrayList<Queue> queues;
    private ArrayList<Thread> threads;
    private int numberOfQueues;
    private FileControl data;

    public Scheduler(int numberOfQueues, FileControl data) {
        this.numberOfQueues = numberOfQueues;
        queues = new ArrayList<Queue>();
        threads = new ArrayList<Thread>();
        this.data = data;

        for(int i = 0; i < numberOfQueues; i++) {
            Queue q = new Queue(true, data);
            queues.add(q);
            threads.add(new Thread(q));
            threads.get(i).setName(String.valueOf(i + 1));
        }
        for(Thread t : threads) {
            t.start();
        }
    }

    public void placeInQueue(Person person) {
        int minTime = Integer.MAX_VALUE;
        for(Queue q : queues) {
            if(q.getWaitingTime() < minTime) {
                minTime = q.getWaitingTime();
            }
        }
        for(Queue q : queues) {
            if(q.getWaitingTime() == minTime) {
                q.addPerson(person);
                break;
            }
        }
    }

    public int getWaitingTime() {
        int max = 0;
        for(Queue q : queues) {
            max += q.getPersonCount();
        }

        return max;
    }
}
