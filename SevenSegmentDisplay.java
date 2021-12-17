import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SevenSegmentDisplay {
    private class SignalStream {
        private String[] input;
        private String[] output;

        private SignalStream(String[] input, String[] output) {
            this.input = input;
            this.output = output;
        }
    }

    private class IntegerStream {
        private int[] input;
        private int[] output;

    }

    private SignalStream[] data;

    public SevenSegmentDisplay(String address) {
        makeSignalStreams(address);
    }

    private void makeSignalStreams(String address) {
        ArrayList<String> lineArr = new ArrayList<String>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNext()) {
                lineArr.add(stdin.nextLine());
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        String[] line, input, output;

        data = new SignalStream[lineArr.size()];

        for (int i = 0; i < lineArr.size(); i++) {
            line = lineArr.get(i).split("\\|");
            input = line[0].trim().split(" ");
            output = line[1].trim().split(" ");

            data[i] = new SignalStream(input, output);
        }
    }

    public int simpleCounter() {
        int count = 0;

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < 4; j++) {
                switch (data[i].output[j].length()) {
                    case 2: // is 1
                    case 3: // is 7
                    case 4: // is 4
                    case 7: // is 8
                        count++;
                    default:
                        break;

                }
            }
        }
        
        return count;
    }

    private void processSignalStream() {
        // go through signal stream array
        /*
            This will need a static sort of positional mapping
             _
            |_|
            |_|

            becomes

             0
            123
            456


            For each signal stream:

            Look at the entire input
            Find one, four, seven, and eight
            
            Use one and seven to identify the letter at position zero

            Use one and six/nine/zero to identify the letter at position three
                (six and nine are both length 6, the one that does not have both of 1's chars
                 is six, the other nine)
            Process of elimination gets position six -- remaining letter in one

            Use four and six/nine/zero to identify the letter at position two
                (four, six, and nine share positions 1 and 6, and position three is known,
                 so the unknown number must be position 2)
            Process of elimination gets position one -- remaining letter in four

            Nine has only one unknown member, which must be position five
            Process of elimination gets position four

            Assign values to output    
        */
    }
}