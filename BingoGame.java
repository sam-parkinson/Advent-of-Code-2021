import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class BingoGame
{
    private int[] drawings;
    private BingoCard[] cards;
    private int tracker;
    private int winners;

    public BingoGame(String address)
    {
        readInput(address);
        this.tracker = 0;
        this.winners = 0;
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
                        int[][] cardArr = new int[cardAL.size()][cardAL.size()];
                        cardArr = cardAL.toArray(cardArr);
                        cardsAL.add(new BingoCard(cardArr));
                        cardAL.clear();
                    }
                }

                else {
                    String[] lineSplit = line.trim().split("\\D+");
                    // System.out.println(lineSplit);
                    int[] cardLine = new int[lineSplit.length];
                    for (int i = 0; i < cardLine.length; i++) {
                        // if (lineSplit[i].compareTo("") != 0)
                            cardLine[i] = Integer.parseInt(lineSplit[i]);
                    }
                    cardAL.add(cardLine);
                }                    
            }

            stdin.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        drawings = new int[drawStrings.size()];
        for (int i = 0; i < drawings.length; i++)
            drawings[i] = Integer.parseInt(drawStrings.get(i));

        cards = new BingoCard[cardsAL.size()];
        cards = cardsAL.toArray(cards);
    }

    public BingoCard getFirstWinner()
    {
        boolean foundWinner = false;
        tracker = 0;

        while (foundWinner == false) {
            for (int j = 0; j < cards.length; j++) {
                cards[j].stampCard(drawings[tracker]);
            }

            for (int j = 0; j < cards.length; j++) {
                if (cards[j].getWinner() == true)
                    return cards[j];
            }

            tracker++;
        }

        return null;
    }

    public BingoCard getLastWinner()
    {
        winners = 0;
        tracker = 0;

        while (winners < cards.length - 1) {
            for (int j = 0; j < cards.length; j++) {
                if (cards[j].getWinner() == false) {
                    cards[j].stampCard(drawings[tracker]);
                    if (cards[j].getWinner() == true)
                        winners++;
                }            
            }

            tracker++;
        }

        int i = 0;
        while (cards[i].getWinner() == true)
            i++;

        while (cards[i].getWinner() == false) {
            cards[i].stampCard(drawings[tracker]);
            if (cards[i].getWinner() == true)
                return cards[i];
            
            tracker++;
        }

        return null;
    }

    public int getLastNumberCalled()
    {
        return drawings[tracker];
    }
}