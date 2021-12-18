public class Driver
{
    public static void main(String[] args)
    {
        SonarSweep sweeper = new SonarSweep("inputs/day1.txt");
        Submarine submarine = new Submarine("inputs/day2.txt");
        BinaryDiagnostic diagnostic = new BinaryDiagnostic("inputs/day3.txt");
        BingoGame bingoGameOne = new BingoGame("inputs/day4.txt");
        BingoGame bingoGameTwo = new BingoGame("inputs/day4.txt");
        VentMapper ventMapper = new VentMapper("inputs/day5.txt");
        LanternfishSchool fishSchool = new LanternfishSchool("inputs/day6.txt");
        CrabDrill crabDrill = new CrabDrill("inputs/day7.txt");
        SevenSegmentDisplay display = new SevenSegmentDisplay("inputs/day8.txt");
        
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

        System.out.println();
        BingoCard firstWinner = bingoGameOne.getFirstWinner();
        int[] scoreArr = firstWinner.getUnstampedSquares();
        int score1 = 0;
        for (int i = 0; i < scoreArr.length; i++) {
            score1 += scoreArr[i];
        }
        BingoCard secondWinner = bingoGameTwo.getLastWinner();
        scoreArr = secondWinner.getUnstampedSquares();
        int score2 = 0;
        for (int i = 0; i < scoreArr.length; i++) {
            score2 += scoreArr[i];
        }
        System.out.println("Day 4 Problem 1: " + (score1 * bingoGameOne.getLastNumberCalled()));
        System.out.println("Day 4 Problem 2: " + (score2 * bingoGameTwo.getLastNumberCalled()));

        System.out.println();
        System.out.println("Day 5 Problem 1: " + ventMapper.straightLineDangers(2));
        System.out.println("Day 5 Problem 2: " + ventMapper.fullDangers(2));

        fishSchool.generationTracker(80);
        System.out.println();
        System.out.println("Day 6 Problem 1: " + fishSchool.fishCounter());
        fishSchool.generationTracker(176);
        System.out.println("Day 6 Problem 2: " + fishSchool.fishCounter());

        System.out.println();
        System.out.println("Day 7 Problem 1: " + crabDrill.findShortestDistance());
        System.out.println("Day 7 Problem 2: " + crabDrill.findCheapestDistance());

        System.out.println();
        System.out.println("Day 8 Problem 1: " + display.simpleCounter());
        System.out.println("Day 8 Problem 2: " + display.processSignalStream());

    }
}
