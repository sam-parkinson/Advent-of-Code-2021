import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryDiagnostic
{
    private int gamma;
    private int epsilon;
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
        // for oxygen and co2 readings create copy of readings array in each method before reducing
        // reduce using common and rare bits respectively
        // do not need entire common bits function, just similar method for each helper
    }

    public int getGamma()
    {
        return this.gamma;
    }

    public int getEpsilon()
    {
        return this.epsilon;
    }

    private int[][] makeReadings(String address)
    {
        ArrayList<String> lines = new ArrayList<String>();
        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNext())
            {
                lines.add(stdin.nextLine());
            }

            stdin.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        int[][] readings = new int[lines.size()][lines.get(0).length()];

        for (int i = 0; i < readings.length; i++)
        {
            String[] line = lines.get(i).split("");

            for (int j = 0; j < line.length; j++)
            {
                readings[i][j] = Integer.parseInt(line[j]);
            }
        }

        return readings;
    }

    private int[] findCommonBits(int[][] arr)
    {
        int[] commonBits = new int[arr[0].length];

        for (int i = 0; i < commonBits.length; i++) {
            int ones = 0;
            int zeros = 0;

            for (int j = 0; j < arr.length; j++)
            {
                if (arr[j][i] == 1)
                    ones++;
                else
                    zeros++;
            }

            commonBits[i] = ones >= zeros ? 1 : 0;
        }

        return commonBits;
    }

    private int[] findRareBits(int[][] arr) {
        int[] rareBits = new int[arr[0].length];
        for (int i = 0; i < rareBits.length; i++) {
            int ones = 0;
            int zeros = 0;

            for (int j = 0; j < arr.length; j++)
            {
                if (arr[j][i] == 1)
                    ones++;
                else
                    zeros++;
            }

            rareBits[i] = ones < zeros ? 1 : 0;
        }

        return rareBits;
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

        return 0;
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