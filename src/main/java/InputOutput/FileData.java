package InputOutput;

public class FileData extends FileControl {
    private int clientNumber;
    private int queueNumber;
    private int simulationTime;
    private int minArrival, maxArrival;
    private int waitMin, waitMax;

    public FileData(String inPath, String outPath) {
        super(inPath, outPath);

        clientNumber = readLine();
        queueNumber = readLine();
        simulationTime = readLine();

        int[] val = readLineComma();
        minArrival = val[0];
        maxArrival = val[1];

        val = readLineComma();
        waitMin = val[0];
        waitMax = val[1];

    }

    public int getClientNumber() {
        return clientNumber;
    }

    public int getQueueNumber() {
        return queueNumber;
    }

    public int getSimulationTime() {
        return simulationTime;
    }

    public int getMinArrival() {
        return minArrival;
    }

    public int getMaxArrival() {
        return maxArrival;
    }

    public int getWaitMin() {
        return waitMin;
    }

    public int getWaitMax() {
        return waitMax;
    }
}
