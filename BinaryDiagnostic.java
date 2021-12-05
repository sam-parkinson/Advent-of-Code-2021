import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryDiagnostic
{
    private int gamma;
    private int epsilon;
    private int oxygen;
    private int carbonDioxide;
    private int[][] readings;
    private int[] commonBits;
    private int[] rareBits;

    public BinaryDiagnostic(String address)
    {
        this.readings = makeReadings(address);
        this.commonBits = findCommonBits(readings);
        this.rareBits = findRareBits(readings);
        this.gamma = findGamma();
        this.epsilon = findEpsilon();
        this.oxygen = findOxygen();
        this.carbonDioxide = findCarbonDioxide();
    }

    public int getGamma()
    {
        return this.gamma;
    }

    public int getEpsilon()
    {
        return this.epsilon;
    }

    public int getOxygen()
    {
        return this.oxygen;
    }

    public int getCarbonDioxide()
    {
        return this.carbonDioxide;
    }

    private int[][] makeReadings(String address)
    {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNext()) {
                lines.add(stdin.nextLine());
            }

            stdin.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        int[][] readings = new int[lines.size()][lines.get(0).length()];

        for (int i = 0; i < readings.length; i++) {
            String[] line = lines.get(i).split("");

            for (int j = 0; j < line.length; j++) {
                readings[i][j] = Integer.parseInt(line[j]);
            }
        }

        return readings;
    }

    private int[] findCommonBits(int[][] arr)
    {
        int[] commonBits = new int[arr[0].length];

        for (int i = 0; i < commonBits.length; i++) {
            commonBits[i] = findSingleCommonBit(i, arr);
        }

        return commonBits;
    }

    private int findSingleCommonBit(int i, int[][] arr)
    {
        int ones = 0;
        int zeros = 0;

        for (int j = 0; j < arr.length; j++) {
            if (arr[j][i] == 1)
                ones++;
            else
                zeros++;
        }

        return ones >= zeros ? 1 : 0;
    }

    private int[] findRareBits(int[][] arr) 
    {
        int[] rareBits = new int[arr[0].length];

        for (int i = 0; i < rareBits.length; i++) {
            rareBits[i] = findSingleRareBit(i, arr);
        }

        return rareBits;
    }

    private int findSingleRareBit(int i, int[][] arr)
    {
        int ones = 0;
        int zeros = 0;

        for (int j = 0; j < arr.length; j++) {
            if (arr[j][i] == 1)
                ones++;
            else
                zeros++;
        }

        return ones < zeros ? 1 : 0;
    }

    private int findGamma()
    {
        String binStr = "";
        
        for (int i = 0; i < commonBits.length; i++) {
            binStr += commonBits[i];
        }

        return Integer.parseInt(binStr, 2);
    }

    private int findEpsilon()
    {
        String binStr = "";
        
        for (int i = 0; i < rareBits.length; i++) {
            binStr += rareBits[i];
        }

        return Integer.parseInt(binStr, 2);
    }

    private int findOxygen()
    {
        int[][] oxyCopy = copyReadings();
        int i = 0;

        while (oxyCopy.length > 1) {
            int bit = findSingleCommonBit(i, oxyCopy);
            int tracker = 0;

            for (int j = 0; j < oxyCopy.length; j++) {
                if (oxyCopy[j][i] == bit)
                    tracker++;
            }

            int[][] paredOxyCopy = new int[tracker][readings[0].length];

            int oldPos = 0;
            int newPos = 0;

            while (newPos < tracker) {
                if (oxyCopy[oldPos][i] == bit) {
                    for (int j = 0; j < readings[0].length; j++) {
                        paredOxyCopy[newPos][j] = oxyCopy[oldPos][j];
                    }
                    newPos++;
                }
                oldPos++;
            }
            oxyCopy = paredOxyCopy;
            i++;
        }

        String oxyStr = "";

        for (int j = 0; j < oxyCopy[0].length; j++) {
            oxyStr += oxyCopy[0][j];
        }

        return Integer.parseInt(oxyStr, 2);
    }

    private int findCarbonDioxide()
    {
        int[][] cooCopy = copyReadings();
        int i = 0;

        while (cooCopy.length > 1) {
            int bit = findSingleRareBit(i, cooCopy);
            int tracker = 0;

            for (int j = 0; j < cooCopy.length; j++) {
                if (cooCopy[j][i] == bit)
                    tracker++;
            }

            int[][] paredCooCopy = new int[tracker][readings[0].length];

            int oldPos = 0;
            int newPos = 0;

            while (newPos < tracker) {
                if (cooCopy[oldPos][i] == bit) {
                    for (int j = 0; j < readings[0].length; j++) {
                        paredCooCopy[newPos][j] = cooCopy[oldPos][j];
                    }
                    newPos++;
                }
                oldPos++;
            }
            cooCopy = paredCooCopy;
            i++;
        }

        String cooStr = "";

        for (int j = 0; j < cooCopy[0].length; j++) {
            cooStr += cooCopy[0][j];
        }

        return Integer.parseInt(cooStr, 2);
    }

    private int[][] copyReadings()
    {
        int[][] copy = new int[readings.length][readings[0].length];
        for (int i = 0; i < readings.length; i++) {
            for (int j = 0; j < readings[0].length; j++) {
                copy[i][j] = readings[i][j];
            }
        }
        return copy;
    }
}