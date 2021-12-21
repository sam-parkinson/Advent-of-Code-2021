import java.io.File;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class SyntaxScorer {
    private static final int[] SCORE = {3, 57, 1197, 25137};
    private static final String[] OPEN = {"(", "[", "{", "<"};
    private static final String[] CLOSE = {")", "]", "}", ">"};

    private String[][] lines;
    private int errorScore;

    public SyntaxScorer(String address) {
        processData(address);
        this.errorScore = findErrorScore();
    }

    public int getErrorScore() {
        return this.errorScore;
    }

    private void processData(String address) {
        ArrayList<String[]> lineArr = new ArrayList<String[]>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNext()) {
                lineArr.add(stdin.nextLine().split(""));
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        lines = new String[lineArr.size()][]; 
        lines = lineArr.toArray(lines);
    }

    private int findErrorScore() {
        int score = 0;

        for (int i = 0; i < lines.length; i++) {
            score += parseLineForError(lines[i]);
        }

        return score;
    }

    private int parseLineForError(String[] line) {
        ArrayDeque<String> stack = new ArrayDeque<String>();
        ArrayList<String> open = new ArrayList<>(Arrays.asList(OPEN));
        ArrayList<String> close = new ArrayList<>(Arrays.asList(CLOSE));

        for (int i = 0; i < line.length; i++) {
            if (open.contains(line[i])) {
                stack.push(line[i]);
            } else if (close.contains(line[i])) {
                if (open.indexOf(stack.peek()) == close.indexOf(line[i])) {
                    stack.pop();
                } else {
                    return SCORE[close.indexOf(line[i])];
                }
            }

        }

        return 0;
    }
    
}