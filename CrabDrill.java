import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class CrabDrill {
    private int[] crabShips;

    public CrabDrill(String address) {
        makeCrabShips(address);
    }

    private void makeCrabShips(String address) {
        String[] strArr = {};

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);
            strArr = stdin.nextLine().split(",");
            stdin.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        crabShips = new int[strArr.length];

        for (int i = 0; i < strArr.length; i++)
            crabShips[i] = Integer.parseInt(strArr[i]);

        Arrays.sort(crabShips);
    }

    public int findShortestDistance() {
        int median;
        int halfway = crabShips.length / 2;
        if (crabShips.length % 2 != 0) {
            median = crabShips[halfway];
        } else {
            median = (int) Math.ceil((double) (crabShips[halfway] + crabShips[halfway - 1]) / 2);
        }
        
        int fuel = 0;

        for (int i = 0; i < crabShips.length; i++) {
            fuel += Math.abs(median - crabShips[i]);
        }

        return fuel;
    }

    public int findCheapestDistance() {
        int sum = 0;

        for (int i = 0; i < crabShips.length; i++) {
            sum += crabShips[i];
        }

        int mean = (int) Math.round((double)sum / crabShips.length);
        
        int distance;
        int fuel = 0;

        for (int i = 0; i < crabShips.length; i++) {
            distance = Math.abs(mean - crabShips[i]);
            fuel += distance * (1 + distance) / 2;
        }

        return fuel;
    }
}

