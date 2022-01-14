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
    private int hundredSteps;
    private int syncSteps;
    
    public OctopusGarden(String address) {
        seedGarden(address);
        flashCounter();
    }

    public int getHundredSteps() {
        return this.hundredSteps;
    }

    public int getSyncSteps() {
        return this.syncSteps;
    }

    private void flashCounter() {
        int hundredFlashes = 0;
        int stepFlashes = 0;
        boolean allFlashed = false;
        int i = 0;

        int octoCount = garden.length * garden[0].length;

        while (!allFlashed) {
            incrementOctopi();
            flashOctopi();
            stepFlashes = resetFlashes();

            if (stepFlashes == octoCount)
            {
                allFlashed = true;
            }

            if (i < 100) {
                hundredFlashes += stepFlashes;
            } else if (i == 100) {
                hundredSteps = hundredFlashes;
            }

            i++;
        }

        syncSteps = i;
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

    private void incrementOctopi() {
        for (int i = 0; i < garden.length; i++) {
            for (int j = 0; j < garden[i].length; j++) {
                garden[i][j].energy++;
            }
        }
    }

    private void flashOctopi() {
        // go through array, check to see if value over nine
        // if so, add one to each adjacent value, set flashed to true
        // check each adjacent value for condition flashed == false and value > 9
        // repeat process

        for (int i = 0; i < garden.length; i++) {
            for (int j = 0; j < garden[i].length; j++) {
                if (garden[i][j].energy > 9 && garden[i][j].hasFlashed == false) {
                    flashSurround(i, j);
                }
            }
        }
    }

    private int resetFlashes() {
        int flashed = 0;

        for (int i = 0; i < garden.length; i++) {
            for (int j = 0; j < garden[i].length; j++) {
                if (garden[i][j].hasFlashed == true) {
                    flashed++;
                    garden[i][j].hasFlashed = false;
                    garden[i][j].energy = 0;
                }
            }
        }
        return flashed;
    }

    private void flashSurround(int x, int y) {
        garden[x][y].hasFlashed = true;

        // this method will call itself
        // should have an exit condition: no surrounding octopus has both
        // an energy value of > 9 and flashed == false

        // go up left
        if (x > 0 && y > 0) {
            garden[x - 1][y - 1].energy++;
            if (garden[x - 1][y - 1].energy > 9 && !garden[x - 1][y - 1].hasFlashed) {
                flashSurround(x - 1, y - 1);
            }
        }
        
        // go up
        if (x > 0) {
            garden[x - 1][y].energy++;
            if (garden [x - 1][y].energy > 9 && !garden[x - 1][y].hasFlashed) {
                flashSurround(x - 1, y);
            }
        }

        // go up right
        if (x > 0 && y < garden[x].length - 1) {
            garden[x - 1][y + 1].energy++;
            if (garden[x - 1][y + 1].energy > 9 && !garden[x - 1][y + 1].hasFlashed) {
                flashSurround(x - 1, y + 1);
            }
        }

        // go left
        if (y > 0) {
            garden[x][y - 1].energy++;
            if (garden[x][y - 1].energy > 9 && ! garden[x][y - 1].hasFlashed) {
                flashSurround(x, y - 1);
            }
        }

        // go right
        if (y < garden[x].length - 1) {
            garden[x][y + 1].energy++;
            if (garden[x][y + 1].energy > 9 && !garden[x][y + 1].hasFlashed) {
                flashSurround(x, y + 1);
            }
        }

        // go down left
        if (x < garden.length - 1 && y > 0) {
            garden[x + 1][y - 1].energy++;
            if (garden[x + 1][y - 1].energy > 9 && !garden[x + 1][y - 1].hasFlashed) {
                flashSurround(x + 1, y - 1);
            }
        }

        // go down
        if (x < garden.length - 1) {
            garden[x + 1][y].energy++;
            if (garden[x + 1][y].energy > 9 && !garden[x + 1][y].hasFlashed) {
                flashSurround(x + 1, y);
            }
        }

        // go down right
        if (x < garden.length - 1 && y < garden[x].length - 1) {
            garden[x + 1][y + 1].energy++;
            if (garden[x + 1][y + 1].energy > 9 && !garden[x + 1][y + 1].hasFlashed) {
                flashSurround(x + 1, y + 1);
            }
        }
    }
}