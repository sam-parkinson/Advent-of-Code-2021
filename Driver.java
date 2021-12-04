public class Driver
{
    public static void main(String[] args)
    {
        SonarSweep sweeper = new SonarSweep("inputs/day1.txt");
        
        System.out.println("Day 1 Problem 1: " + sweeper.getIncreases());
        System.out.println("Day 1 Problem 2: " + sweeper.getWindowIncreases());
    }
}
