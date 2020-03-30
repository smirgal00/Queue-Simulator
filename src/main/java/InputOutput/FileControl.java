package InputOutput;

import java.io.*;

public class FileControl {
    String inPath;
    String outPath;
    BufferedReader e;
    BufferedWriter wr;
    FileReader read;
    FileWriter write;

    public FileControl(String inPath, String outPath) {
        this.inPath = inPath;
        this.outPath = outPath;

        try{
            read = new FileReader(this.inPath);
            write = new FileWriter(this.outPath);
            e = new BufferedReader(read);
            wr = new BufferedWriter(write);
        } catch(Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public int readLine() {
        int nr = 0;

        try {
            nr = Integer.parseInt(e.readLine());
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        return nr;
    }

    public int[] readLineComma() {
        int[] val = new int[2];
        String[] vals = null;

        try {
            vals = e.readLine().split(",");
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        int len = 0;
        for(String x : vals) {
            val[len++] = Integer.parseInt(x);
        }

        return val;
    }

    public void writeFile(String toWrite) {
        try {
            wr.write(toWrite);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            this.wr.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
