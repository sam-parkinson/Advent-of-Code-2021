public class Test {
    public static void main(String[] args) {
        SyntaxScorer test = new SyntaxScorer("inputs/test.txt");

        System.out.println(test.getErrorScore());
        System.out.println(test.getIncompleteScore());
    }
}