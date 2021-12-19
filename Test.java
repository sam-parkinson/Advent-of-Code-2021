public class Test {
    public static void main(String[] args) {
        DepthMap test = new DepthMap("inputs/test.txt");

        System.out.println(test.findTotalDanger());
    }
}