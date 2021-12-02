import java.io.File;
import java.util.Scanner;

public class SonarSweep
{
    private int increases;
    private int[] measurments;

    public SonarSweep(String address)
    {
        this.measurements = makeMeasurments(address);
        this.increases = findIncreases();
        // call method that turns text input into array
        // call method that turns array into total
    }

    // Getters
    public int getIncreases()
    {
        return this.increases;
    }

    // Method that turns text input into array
    private int[] makeMeasurments(String address)
    {
        File file = new File(address);
        Scanner stdin = new Scanner(file);
        stdin.close();
        return (0);
    }

    // Method that processes array
    private int findIncreases()
    {
        return this.measurments[0];
    }
}