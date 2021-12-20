import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ArrayDeque;
import java.util.HashSet;
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
    private Integer[] basins;

    public DepthMap(String address) {
        processDepthData(address);
        findLowPoints();
        findBasins();
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

    private void findBasins() {
       // this populates the basins arr with the size of each basin,
       // then sorts it so largest basin is at index 0
        basins = new Integer[lowPoints.length];

        for (int i = 0; i < lowPoints.length; i++) {
            basins[i] = getBasinSize(lowPoints[i].x, lowPoints[i].y);
        }

        Arrays.sort(basins, (a,b) -> Integer.compare(b,a));        
    }

    private int getBasinSize(int a, int b) {
        int maxX = map.length - 1;
        int maxY = map[0].length - 1;
        
        ArrayList<int[]> basinPoints = new ArrayList<int[]>();
        ArrayDeque<int[]> nextPoints = new ArrayDeque<int[]>();
        
        nextPoints.add(new int[] {a, b});

        while (!nextPoints.isEmpty()) {
            int[] coords = nextPoints.remove();
            
            if (checkList(basinPoints, coords)) {
                basinPoints.add(coords);

                int x = coords[0], y = coords[1];

                if (x > 0 && map[x - 1][y] < 9) {
                    int[] up = {x - 1, y};  
                    nextPoints.add(up);
                }

                if (y > 0 && map[x][y - 1] < 9) {
                    int[] left = {x, y - 1};
                    nextPoints.add(left);  
                }

                if (x < maxX && map[x + 1][y] < 9) {
                    int[] down = {x + 1, y};   
                    nextPoints.add(down);                
                }

                if (y < maxY && map[x][y + 1] < 9) {
                    int[] right = {x, y + 1};
                    nextPoints.add(right);    
                }
            }
        }
        return basinPoints.size();
    }

    private boolean checkList(ArrayList<int[]> set, int[] coords) {        
        for (int i = 0; i < set.size(); i++) {
            if (coords[0] == set.get(i)[0] && coords[1] == set.get(i)[1])
                return false;
        }
        return true;
    }

    public int findTotalDanger() {
        int danger = 0;

        for (int i = 0; i < lowPoints.length; i++) {
            danger += lowPoints[i].dangerLevel;
        }
        
        return danger;
    }

    public int findDeepestBasins() {
        return (basins[0] * basins[1] * basins[2]);
    }
}