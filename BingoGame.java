import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BingoGame
{
    private int[] drawings;
    private BingoCard[] cards;

    public BingoGame(String address)
    {
        readInput(address);
    }

    private void readInput(String address)
    {
        ArrayList<BingoCard> cardsAL = new ArrayList<BingoCard>();
        ArrayList<int[]> cardAL = new ArrayList<int[]>();
        ArrayList<String> drawStrings = new ArrayList<String>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            drawStrings.addAll(Arrays.asList(stdin.nextLine().split(",")));

            while (stdin.hasNext()) {
                String line = stdin.nextLine();

                if (line.compareTo("") == 0) {
                    if (cardAL.size() != 0) {
                        int[][] cardArr = new int[cardAL.size()][];
                        cardArr = cardAL.toArray(cardArr);
                        cardsAL.add(new BingoCard(cardArr));
                    }
                }
                
                // if the line isn't a blank line: 
                // turn the line into an array of ints
                // add the array to the arrayList of ints

                
            }

            stdin.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        drawings = new int[drawStrings.size()];
        for (int i = 0; i < drawings.length; i++)
            drawings[i] = Integer.parseInt(drawStrings.get(i));

        // add the cards to their proper place in the class
    }
}