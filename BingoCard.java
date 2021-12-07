import java.util.ArrayList;

public class BingoCard
{
    private class BingoSquare
    {
        private int number;
        private boolean stamped;

        private BingoSquare(int number)
        {
            this.number = number;
            this.stamped = false;
        }

        private void stampSquare()
        {
            this.stamped = true;
        }
    }

    /*
        Okay, this looks like it uses a lot of nested for loops, sure.

        But they're bingo cards. They all have 25 squares. In a sense you can think
        of this as having an O(N) runtime, since the size of the input can be thought of as the
        number of numbers, not the number of bingo cards.

        Even if it is the number of bingo cards, I could make a case that it's still linear.

        I think.

        Hey, if you're reading this, what do you think? Leave a comment (?) on my webzone if you
        want a pizza roll (?)

        Wait. That's not the right thing.

        But I do think the size of the input is the number of numbers, not the number of bingo
        cards. And anyway, these need to be 2D arrays to properly account for the possibility
        of bingo cards that are bigger than 5x5. Which I've never seen in real life, but
        I'm young and my bingo-playing years are still decades ahead of me, when I've retired.

        Which, speaking of, if you're still reading this, and you want to hire me for some reason,
        reach out. No, really, I mean it. Email me, tell me what you think the runtime of this
        is, and let me know if you've got a position I'd be a good fit for.

        This is probably a better way to find a job than using LinkedIn.
    */

    private BingoSquare[][] squares;
    private boolean isWinner;

    public BingoCard(int[][] numbers)
    {
        this.squares = new BingoSquare[numbers.length][numbers[0].length];
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                squares[i][j] = new BingoSquare(numbers[i][j]);
            }
        }
        this.isWinner = false;
        // System.out.println(squares[0][0].number);
    }

    public int[] getUnstampedSquares()
    {
        
        ArrayList<Integer> unstampedAL = new ArrayList<Integer>();

        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                if (!squares[i][j].stamped)
                    unstampedAL.add(squares[i][j].number);
            }
        }

        int[] unstamped = new int[unstampedAL.size()];
        for (int i = 0; i < unstamped.length; i++)
            unstamped[i] = unstampedAL.get(i);

        return unstamped;
    }

    public boolean getWinner()
    {
        return this.isWinner;
    }

    public void stampCard(int number)
    {
        for (int i = 0; i < squares.length; i++) {
            for (int j = 0; j < squares[0].length; j++) {
                if (squares[i][j].number == number)
                    squares[i][j].stampSquare();
            }
        }

        isWinner = checkWinner();
    }

    private boolean checkWinner()
    {
        // check horizontals
        for (int i = 0; i < squares.length; i++) {
            int j = 0;
            while (j < squares[0].length && squares[i][j].stamped == true) {
                j++;
            }
            if (j == squares[0].length)
                return true;
            
        }

        // check verticals
        for (int j = 0; j < squares[0].length; j++) {
            int i = 0;
            while (i < squares.length && squares[i][j].stamped == true) {
                i++;
            }
            if (i == squares.length)
                return true;
        }

        return false;
    }
}

// haha nested for loops go brr