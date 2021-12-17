public class Test {
    public static void main(String[] args) {
        CrabDrill test = new CrabDrill("inputs/test.txt");

        System.out.println(test.findShortestDistance());
        System.out.println(test.findCheapestDistance());
    }
}