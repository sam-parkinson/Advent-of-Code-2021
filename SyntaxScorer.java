import java.io.File;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Scanner;

public class SyntaxScorer {
    private static final int[] ERROR_SCORE = {3, 57, 1197, 25137};
    private static final int[] FINISH_SCORE = {1, 2, 3, 4};
    private static final String[] OPEN = {"(", "[", "{", "<"};
    private static final String[] CLOSE = {")", "]", "}", ">"};

    private String[][] lines;
    private int errorScore;
    private Long[] incompleteScores;

    public SyntaxScorer(String address) {
        this.errorScore = 0;
        processData(address);
        parseData();
    }

    public int getErrorScore() {
        return this.errorScore;
    }

    public long getIncompleteScore() {
        return this.incompleteScores[incompleteScores.length / 2];
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

    private void parseData() {
        int lineScore = 0;
        ArrayDeque<String> stack = new ArrayDeque<String>();
        ArrayList<Long> complete = new ArrayList<Long>();

        for (int i = 0; i < lines.length; i++) {            
            lineScore = parseLine(lines[i], stack);
            if (lineScore == 0) {
                complete.add(completeStack(stack));
            } else {
                errorScore += lineScore;
            }
        }

        incompleteScores = new Long[complete.size()];
        incompleteScores = complete.toArray(incompleteScores);
        Arrays.sort(incompleteScores);
    }

    private int parseLine(String[] line, ArrayDeque<String> stack) {
        stack.clear();
        ArrayList<String> open = new ArrayList<>(Arrays.asList(OPEN));
        ArrayList<String> close = new ArrayList<>(Arrays.asList(CLOSE));

        for (int i = 0; i < line.length; i++) {
            if (open.contains(line[i])) {
                stack.push(line[i]);
            } else if (close.contains(line[i])) {
                if (open.indexOf(stack.peek()) == close.indexOf(line[i])) {
                    stack.pop();
                } else {
                    return ERROR_SCORE[close.indexOf(line[i])];
                }
            }
        }
        return 0;
    }

    private long completeStack(ArrayDeque<String> stack) {
        long score = 0;
        ArrayList<String> open = new ArrayList<>(Arrays.asList(OPEN));
        while (!stack.isEmpty()) {
            score *= 5;
            score += FINISH_SCORE[open.indexOf(stack.pop())];
        }
        return score;
    }
    
}