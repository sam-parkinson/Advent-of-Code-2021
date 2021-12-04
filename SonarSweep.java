import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SonarSweep 
{
    private int increases;
    private int windowIncreases;
    private int[] measurements;

    public SonarSweep(String address)
    {
        this.measurements = makeMeasurements(address);
        this.increases = findIncreases();
        this.windowIncreases = findWindowIncreases();
    }

    // Getters
    public int getIncreases()
    {
        return this.increases;
    }

    public int getWindowIncreases()
    {
        return this.windowIncreases;
    }

    // Method that turns text input into array
    private int[] makeMeasurements(String address)
    {  
        ArrayList<Integer> measureList = new ArrayList();
        try
        {
            File file = new File(address);
            Scanner stdin = new Scanner(file);
            

            while (stdin.hasNext())
            {
                measureList.add(stdin.nextInt());
            }

            stdin.close();
         
        } catch (Exception e)
        {
            System.out.println(e);
        }

        int[] measurements = new int[measureList.toArray().length];

        for (int i = 0; i < measurements.length; i++)
        {
            measurements[i] = (int) measureList.get(i);
        }

        return measurements;
    }

    // Method that processes array
    private int findIncreases()
    {
        int increases = 0;
        for (int i = 0; i < measurements.length - 1; i++)
        {
            if (measurements[i + 1] > measurements[i])
                increases++;
        }
        return increases;
    }
    
    private int findWindowIncreases()
    {
        int windowIncreases = 0;
        
        for (int i = 0; i < measurements.length - 3; i++)
        {
            /*
            int a = 0, b = 0;

            for (int j = i; j < i + 3; j++)
                a += measurements[j];
            
            for (int j = i + 1; j < i + 4; j++)
                b += measurements[j];

            if (b > a)
                windowIncreases++;
            */
            if (measurements[i + 3] > measurements[i])
                windowIncreases++;
        }

        return windowIncreases;
    }
}