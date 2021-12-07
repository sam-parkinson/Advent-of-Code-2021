import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class VentMapper
{
    private class VentCluster
    {
        private int x1, y1, x2, y2;

        private VentCluster(int x1, int y1, int x2, int y2)
        {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private VentCluster[] vents;
    private int dimension;
    private int[][] straightLineVentMap;
    private int[][] fullVentMap;

    public VentMapper(String address)
    {
        this.dimension = 0;
        makeVentClusters(address);
        makeStraightLineVentMap();
        makeFullVentMap();
    }

    public int straightLineDangers(int x) {
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (straightLineVentMap[i][j] >= x)
                    count++;
            }
        }

        return count;
    }

    public int fullDangers(int x) {
        int count = 0;
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (fullVentMap[i][j] >= x)
                    count++;
            }
        }

        return count;
    }

    private void makeVentClusters(String address)
    {
        ArrayList<VentCluster> ventAL = new ArrayList<VentCluster>();

        try {
            File file = new File(address);
            Scanner stdin = new Scanner(file);

            while (stdin.hasNext()) {
                ventAL.add(makeVent(stdin.nextLine()));
            }

            stdin.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        vents = new VentCluster[ventAL.size()];
        vents = ventAL.toArray(vents);
    }

    private VentCluster makeVent(String str) {
        String[] strArr = str.split("\\D+");
        int[] arr = new int[4];

        for (int i = 0; i < 4; i++)
        {
            arr[i] = Integer.parseInt(strArr[i]);
            if (arr[i] > dimension)
                dimension = arr[i];
        }

        dimension++;
        return new VentCluster(arr[0], arr[1], arr[2], arr[3]);
    }

    private void makeStraightLineVentMap() {
        straightLineVentMap = new int[dimension][dimension];
        for (int i = 0; i < vents.length; i++) {
            VentCluster vent = vents[i];
            if (vent.x1 == vent.x2) 
                straightLineY(vent);

            if (vent.y1 == vent.y2) 
                straightLineX(vent);        
        }
    }

    private void makeFullVentMap() {
        fullVentMap = new int[dimension][dimension];
        for (int i = 0; i < vents.length; i++) {
            VentCluster vent = vents[i];
            if (vent.x1 == vent.x2) 
                fullY(vent);

            if (vent.y1 == vent.y2) 
                fullX(vent);

            if ((vent.y1 < vent.y2 && vent.x1 < vent.x2) ||
                    vent.y2 < vent.y1 && vent.x2 < vent.x1)
                downwardDiagonal(vent);  

            if ((vent.y1 < vent.y2 && vent.x1 > vent.x2) ||
                vent.y2 < vent.y1 && vent.x2 > vent.x1)
                upwardDiagonal(vent);    
        }
    }

    private void straightLineX(VentCluster vent) {
        int start = vent.x1 > vent.x2 ? vent.x2 : vent.x1;
        int end = vent.x2 > vent.x1 ? vent.x2 : vent.x1;

        for (int i = start; i <= end; i++) {
            straightLineVentMap[i][vent.y1]++;
        }
    }

    private void straightLineY(VentCluster vent) {
        int start = vent.y1 > vent.y2 ? vent.y2 : vent.y1;
        int end = vent.y2 > vent.y1 ? vent.y2 : vent.y1;

        for (int i = start; i <= end; i++) {
            straightLineVentMap[vent.x1][i]++;
        }
    }

    private void fullX(VentCluster vent) {
        int start = vent.x1 > vent.x2 ? vent.x2 : vent.x1;
        int end = vent.x2 > vent.x1 ? vent.x2 : vent.x1;

        for (int i = start; i <= end; i++) {
            fullVentMap[i][vent.y1]++;
        }
    }

    private void fullY(VentCluster vent) {
        int start = vent.y1 > vent.y2 ? vent.y2 : vent.y1;
        int end = vent.y2 > vent.y1 ? vent.y2 : vent.y1;

        for (int i = start; i <= end; i++) {
            fullVentMap[vent.x1][i]++;
        }
    }

    private void downwardDiagonal(VentCluster vent) {
        int startx = vent.x1 < vent.x2 ? vent.x1 : vent.x2;
        int starty = vent.y1 < vent.y2 ? vent.y1 : vent.y2;
        int endx = vent.x1 > vent.x2 ? vent.x1 : vent.x2;
        // int endy = vent.y1 > vent.y2 ? vent.y1 : vent.y2;

        int j = starty;
        for (int i = startx; i <= endx; i++) {
            fullVentMap[i][j]++;
            j++;
        }
    }

    private void upwardDiagonal(VentCluster vent) {
        int startx = vent.x1 < vent.x2 ? vent.x1 : vent.x2;
        int starty = vent.y1 > vent.y2 ? vent.y1 : vent.y2;
        int endx = vent.x1 > vent.x2 ? vent.x1 : vent.x2;
        // int endy = vent.y1 < vent.y2 ? vent.y1 : vent.y2;

        int j = starty;
        for (int i = startx; i <= endx; i++) {
            fullVentMap[i][j]++;
            j--;
        }
    }
}