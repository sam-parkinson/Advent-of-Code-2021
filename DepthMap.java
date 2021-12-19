import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DepthMap {
    private class LowPoint {
        private int height, dangerLevel, x, y;
        
        private LowPoint(int height, int x, int y) {
            this.height = height; 
            this.dangerLevel = height + 1;
            this.x = x;
            this.y = y;
        }
    }

    private int[][] map;
    private LowPoint[] lowPoints;

    public DepthMap(String address) {
        processDepthData(address);
        findLowPoints();
    }

    private void processDepthData(String address) {
        ArrayList<String> lineArr = new ArrayList<String>();
        String[] line;

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

        map = new int[lineArr.size()][lineArr.get(1).length()];

        for (int i = 0; i < map.length; i++) {
            line = lineArr.get(i).split("");

            for (int j = 0; j < line.length; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
    }

    private void findLowPoints() {
        ArrayList<LowPoint> lpList = new ArrayList<LowPoint>();

        boolean flag;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                flag = true;

                if (i > 0 && map[i - 1][j] <= map[i][j]) {
                    flag = false;
                }

                if (j > 0 && map[i][j - 1] <= map[i][j]) {
                    flag = false;
                }

                if (i < map.length - 1 && map[i + 1][j] <= map[i][j]) {
                    flag = false;
                }

                if (j < map[0].length - 1 && map[i][j + 1] <= map[i][j]) {
                    flag = false;
                }
                
                if (flag == true) {
                    lpList.add(new LowPoint(map[i][j], i, j));
                }
            }
        }

        lowPoints = new LowPoint[lpList.size()];
        lowPoints = lpList.toArray(lowPoints);
    }

    public int findTotalDanger() {
        int danger = 0;

        for (int i = 0; i < lowPoints.length; i++) {
            danger += lowPoints[i].dangerLevel;
        }
        
        return danger;
    }
}