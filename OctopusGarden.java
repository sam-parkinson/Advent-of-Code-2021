import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class OctopusGarden {
    private class Octopus {
        private int energy;
        private boolean hasFlashed;

        private Octopus(int energy) {
            this.energy = energy;
            hasFlashed = false;
        }
    }
    
    private Octopus[][] garden;
    
    public OctopusGarden(String address) {
        seedGarden(address);
    }

    private void seedGarden(String address) {
        ArrayList<Octopus[]> lineArr = new ArrayList<Octopus[]>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNext()) {
                lineArr.add(parseLine(stdin.nextLine()));
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        garden = new Octopus[lineArr.size()][];
        garden = lineArr.toArray(garden);
    }

    private Octopus[] parseLine(String line) {
        ArrayList<Octopus> octArr = new ArrayList<Octopus>();

        String[] lineArr = line.split("");

        for (int i = 0; i < lineArr.length; i++) {
            octArr.add(new Octopus(Integer.parseInt(lineArr[i])));
        }

        Octopus[] arr = new Octopus[octArr.size()];
        arr = octArr.toArray(arr);

        return arr;
    }
}