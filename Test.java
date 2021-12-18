public class Test {
    public static void main(String[] args) {
        SevenSegmentDisplay test = new SevenSegmentDisplay("inputs/test.txt");

        System.out.println(test.simpleCounter());
        System.out.println(test.processSignalStream());
    }
}