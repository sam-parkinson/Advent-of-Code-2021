import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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
    private int aim;
    private Heading[] headings;

    public Submarine(String address)
    {
        this.x = 0;
        this.y = 0;
        this.aim = 0;
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
        reset();
        for (int i = 0; i < headings.length; i++)
        {
            switch (headings[i].direction)
            {
                case FORWARD:
                    x += headings[i].degree;
                    y += (headings[i].degree * aim);
                    break;
                case UP:
                    aim -= headings[i].degree;
                    break;
                case DOWN:
                    aim += headings[i].degree;
                    break;
            }
        }
    }

    public void updatePositionOld()
    {   
        reset();
        for (int i = 0; i < headings.length; i++)
        {
            switch (headings[i].direction)
            {
                case FORWARD:
                    x += headings[i].degree;
                    break;
                case UP:
                    y -= headings[i].degree;
                    break;
                case DOWN:
                    y += headings[i].degree;
                    break;
            }
        }
    }

    private void reset()
    {
        x = 0;
        y = 0;
        aim = 0;
    }

    private Heading[] makeHeadings(String address)
    {
        ArrayList<Heading> headingsAL = new ArrayList<Heading>();
        
        try
        {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNext())
            {
                headingsAL.add(formatHeading(stdin.nextLine()));
            }

            stdin.close();
        } catch (Exception e)
        {
            System.out.println(e);
        }

        Heading[] headings = new Heading[headingsAL.size()];
        headings = headingsAL.toArray(headings);

        return headings;
    }

    private Heading formatHeading(String str)
    {
        String[] strArr = str.split(" ");
        Direction dir;
        switch(strArr[0])
        {
            case "forward":
                dir = Direction.FORWARD;
                break;
            case "up":
                dir = Direction.UP;
                break;
            case "down":
            default:
                dir = Direction.DOWN;
                break;
        }
        int deg = Integer.parseInt(strArr[1]);

        return new Heading(dir, deg);
    }
}