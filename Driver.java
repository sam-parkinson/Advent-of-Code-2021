public class Driver
{
    public static void main(String[] args)
    {
        SonarSweep sweeper = new SonarSweep("inputs/day1.txt");
        Submarine submarine = new Submarine("inputs/day2.txt");
        BinaryDiagnostic diagnostic = new BinaryDiagnostic("inputs/day3.txt");
        BingoGame bingoGame = new BingoGame("inputs/day4.txt");
        
        System.out.println("Day 1 Problem 1: " + sweeper.getIncreases());
        System.out.println("Day 1 Problem 2: " + sweeper.getWindowIncreases());

        System.out.println();
        submarine.updatePositionOld();
        System.out.println("Day 2 Problem 1: " + (submarine.getX() * submarine.getY()));
        submarine.updatePosition();
        System.out.println("Day 2 Problem 2: " + (submarine.getX() * submarine.getY()));

        System.out.println();
        System.out.println("Day 3 Problem 1: " + (diagnostic.getGamma() * diagnostic.getEpsilon()));
        System.out.println("Day 3 Problem 2: " + (diagnostic.getOxygen() * diagnostic.getCarbonDioxide()));
    }
}
