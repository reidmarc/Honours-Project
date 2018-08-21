package reidmarc.student.napier.honoursproject;

public class Pattern
{
    private int patternNum;

    private int x1;
    private int y1;

    private int x2;
    private int y2;

    private int x3;
    private int y3;

    private int x4;
    private int y4;

    private int x5;
    private int y5;

    private int x6;
    private int y6;

    private int x7;
    private int y7;

    private int x8;
    private int y8;

    private int[][] xy;


    Pattern(int patternNum, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5, int x6, int y6, int x7, int y7, int x8, int y8)
    {
        this.patternNum = patternNum;

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;

        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;

        this.x5 = x5;
        this.y5 = y5;
        this.x6 = x6;
        this.y6 = y6;

        this.x7 = x7;
        this.y7 = y7;
        this.x8 = x8;
        this.y8 = y8;
    }


    public int[][] getArrayOfCoords()
    {
        xy = new int[8][2];

        xy[0][0] = x1;
        xy[0][1] = y1;

        xy[1][0] = x2;
        xy[1][1] = y2;

        xy[2][0] = x3;
        xy[2][1] = y3;

        xy[3][0] = x4;
        xy[3][1] = y4;

        xy[4][0] = x5;
        xy[4][1] = y5;

        xy[5][0] = x6;
        xy[5][1] = y6;

        xy[6][0] = x7;
        xy[6][1] = y7;

        xy[7][0] = x8;
        xy[7][1] = y8;

        return xy;
    }
}
