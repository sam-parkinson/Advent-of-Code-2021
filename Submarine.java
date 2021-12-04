public class Submarine
{
    // This probably isn't strictly necessary, input shouldn't be malicious
    // but I just think they're neat dot jpg
    private enum Direction {
        FORWARD, UP, DOWN
    }

    private class Heading
    {
        Direction direction;
        int degree;

        private Heading(Direction dir, int deg)
        {
            this.direction = dir;
            this.degree = deg;
        }
    }

    private int x;
    private int y;
    private Heading[] headings;

    public Submarine(String address)
    {
        this.x = 0;
        this.y = 0;
        this.headings = makeHeadings(address);
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public void updatePosition()
    {
        // Go through headings, process each one accordingly
    }

    private Heading[] makeHeadings(String address)
    {
        Heading[] headings = {new Heading(Direction.UP, 1)};

        return headings;
    }
}