import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
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

    public int processSignalStream() {
        class Disambiguate {
            static String five(String str, HashMap<String, Integer> pairs) {
                String[] strArr = str.split("");

                int[] intArr = new int[strArr.length];
                convertStr(strArr, intArr, pairs); 
                if (intArr[3] == 4) {
                    return "2";
                } else if (intArr[1] == 2) {
                    return "3";
                } else {
                    return "5";
                }
            }

            static String six(String str, HashMap<String, Integer> pairs) {
                String[] strArr = str.split("");

                int[] intArr = new int[strArr.length]; 
                convertStr(strArr, intArr, pairs); 
                if (intArr[3] == 3) {
                    return "9";
                } else if (intArr[2] == 3) {
                    return "6";
                } else {
                    return "0";
                }
            }

            static void convertStr(String[] strArr, int[] intArr, HashMap<String, Integer> pairs) {
                for (int i = 0; i < strArr.length; i++) {
                    intArr[i] = pairs.get(strArr[i]);
                }

                Arrays.sort(intArr);
            }
        }

        HashMap<String, Integer> pairs = new HashMap<String, Integer>();
        prepHashMap(pairs);
        
        int sum = 0;

        for (int i = 0; i < data.length; i++) {
            resetHashMap(pairs);

            // input processing
            String[] sortedStr = new String[data[i].input.length];
            for (int j = 0; j < data[i].input.length; j++) {
                sortedStr[j] = data[i].input[j];
            }

            Arrays.sort(sortedStr, (a, b) -> Integer.compare(a.length(), b.length()));

            String[] oneStr = sortedStr[0].split("");
            String[] sevenStr = sortedStr[1].split("");
            String[] fourStr = sortedStr[2].split("");
            String[] eightStr = sortedStr[9].split("");

            for (int j = 0; j < sevenStr.length; j++) {
                if (!Arrays.asList(oneStr).contains(sevenStr[j])) {
                    pairs.put(sevenStr[j], 0);
                }
            }

            String[][] zeroSixNine = new String[3][6];
            
            for (int j = 0; j < 3; j++) {
                zeroSixNine[j] = sortedStr[j + 6].split("");
            }

            for (int j = 0; j < oneStr.length; j++) {
                for (int k = 0; k < zeroSixNine.length; k++) {
                    if (!Arrays.asList(zeroSixNine[k]).contains(oneStr[j])) {
                        pairs.put(oneStr[j], 2);
                        pairs.put(oneStr[(j+1) % 2], 5);
                    }
                }
            }

            for (int j = 0; j < fourStr.length; j++) {
                for (int k = 0; k < zeroSixNine.length; k++) {
                    if (!Arrays.asList(zeroSixNine[k]).contains(fourStr[j])
                        && pairs.get(fourStr[j]) == null) {
                            pairs.put(fourStr[j], 3);
                        }
                }
            }

            for (int j = 0; j < fourStr.length; j++) {
                if (pairs.get(fourStr[j]) == null) {
                    pairs.put(fourStr[j], 1);
                }
            }

            for (int j = 0; j < eightStr.length; j++) {
                for (int k = 0; k < zeroSixNine.length; k++) {
                    if (!Arrays.asList(zeroSixNine[k]).contains(eightStr[j])
                        && pairs.get(eightStr[j]) == null) {
                            pairs.put(eightStr[j], 4);
                    }
                }
            }

            for (int j = 0; j < eightStr.length; j++) {
                if (pairs.get(eightStr[j]) == null) {
                    pairs.put(eightStr[j], 6);
                }
            }

            // output processing 
            String outputStr = "";

            for (int j = 0; j < data[i].output.length; j++) {
                String digit = data[i].output[j];
                
                switch (digit.length()) {
                    case 2:
                        outputStr += "1";
                        break;
                    case 3:
                        outputStr += "7";
                        break;
                    case 4:
                        outputStr += "4";
                        break;
                    case 5:
                        outputStr += Disambiguate.five(digit, pairs);
                        break;
                    case 6:
                        outputStr += Disambiguate.six(digit, pairs);
                        break;
                    case 7:
                        outputStr += "8";
                        break;
                }
            }

            sum += Integer.parseInt(outputStr);
        }

        return sum;
    }

    private void prepHashMap(HashMap<String, Integer> pairs) {
        String[] arr = {"a", "b", "c", "d", "e", "f", "g"};

        for (int i = 0; i < arr.length; i++) {
            pairs.put(arr[i], null);
        }
    }

    private void resetHashMap(HashMap<String, Integer> pairs) {
        for (String k : pairs.keySet()) {
            pairs.put(k, null);
        }
    }

    
}

/*
     0
    1 2
     3
    4 5
     6

    0: 0, 1, 2, 4, 5, 6
    1: 2, 6
    2: 0, 2, 3, 4, 6
    3: 0, 2, 3, 5, 6
    4: 1, 2, 3, 5
    5: 0, 1, 3, 5, 6
    6: 0, 1, 3, 4, 5, 6
    7: 0, 2, 5
    8: 0, 1, 2, 3, 4, 5, 6
    9: 0, 1, 2, 3, 5, 6
*/