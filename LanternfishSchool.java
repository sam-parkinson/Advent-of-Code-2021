import java.io.File;
import java.util.Scanner;

public class LanternfishSchool {

    private class Generation {
        private int timer;
        private long numberOfFish;

        private Generation(int timer) {
            this.timer = timer;
            this.numberOfFish = 0;
        }

    }

    private Generation[] generations;

    public LanternfishSchool(String address) {
        this.generations = new Generation[9]; 
        makeInitialGenerations(address);
    }

    private void makeInitialGenerations(String address) {
        String[] fishArr = {};

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            fishArr = stdin.nextLine().split(",");

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        for (int i = 0; i < generations.length; i++) {
            generations[i] = new Generation(i);
        }

        for (int i = 0; i < fishArr.length; i++) {
            generations[Integer.parseInt(fishArr[i])].numberOfFish++;
        }
    }

    public void generationTracker(int loops) {
        int parentTime;
        long parents;
        for (int i = 0; i < loops; i++) {
            parentTime = 0;
            parents = 0;
            for (int j = 0; j < generations.length; j++) {
                if (generations[j].timer == 0) {
                    parentTime = (j + 7) % generations.length;
                    parents = generations[j].numberOfFish;
                    generations[j].timer = 8;
                } else {
                    generations[j].timer--;
                }
            }
            generations[parentTime].numberOfFish += parents;
        }
    }

   public long fishCounter() {
       long fish = 0;
       for (int i = 0; i < generations.length; i++) {
           fish += generations[i].numberOfFish;
       }

       return fish;
   }
}