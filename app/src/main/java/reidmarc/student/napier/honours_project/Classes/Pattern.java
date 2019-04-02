package reidmarc.student.napier.honours_project.Classes;

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

    private int x9;
    private int y9;

    private int x10;
    private int y10;

    private int x11;
    private int y11;

    private int x12;
    private int y12;

    private int x13;
    private int y13;

    private int x14;
    private int y14;

    private int x15;
    private int y15;

    private int x16;
    private int y16;

    private int x17;
    private int y17;

    private int x18;
    private int y18;

    private int x19;
    private int y19;

    private int x20;
    private int y20;

    private int x21;
    private int y21;

    private int[][] xy;


    // 9 dot constructor
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

        this.x9 = 1189;
        this.y9 = 100;
    }

    // 15 dot constructor
    Pattern(int patternNum, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5,
            int x6, int y6, int x7, int y7, int x8, int y8, int x9, int y9, int x10, int y10,
            int x11, int y11, int x12, int y12, int x13, int y13, int x14, int y14)
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

        this.x9 = x9;
        this.y9 = y9;

        this.x10 = x10;
        this.y10 = y10;

        this.x11 = x11;
        this.y11 = y11;

        this.x12 = x12;
        this.y12 = y12;

        this.x13 = x13;
        this.y13 = y13;

        this.x14 = x14;
        this.y14 = y14;

        this.x15 = 1189;
        this.y15 = 100;
    }

    // 21 dot constructor
    Pattern(int patternNum, int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4, int x5, int y5,
            int x6, int y6, int x7, int y7, int x8, int y8, int x9, int y9, int x10, int y10,
            int x11, int y11, int x12, int y12, int x13, int y13, int x14, int y14, int x15, int y15,
            int x16, int y16, int x17, int y17, int x18, int y18, int x19, int y19, int x20, int y20)
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

        this.x9 = x9;
        this.y9 = y9;

        this.x10 = x10;
        this.y10 = y10;

        this.x11 = x11;
        this.y11 = y11;

        this.x12 = x12;
        this.y12 = y12;

        this.x13 = x13;
        this.y13 = y13;

        this.x14 = x14;
        this.y14 = y14;

        this.x15 = x15;
        this.y15 = y15;

        this.x16 = x16;
        this.y16 = y16;

        this.x17 = x17;
        this.y17 = y17;

        this.x18 = x18;
        this.y18 = y18;

        this.x19 = x19;
        this.y19 = y19;

        this.x20 = x20;
        this.y20 = y20;

        this.x21 = 1189;
        this.y21 = 100;
    }



    public int[][] getArrayOfCoords()
    {
        // Creates the array depending on amount of pattern points to store
        if (patternNum >= 7)
        {
            xy = new int[21][2];
        }
        else if (patternNum <= 4)
        {
            xy = new int[9][2];
        }
        else
        {
            xy = new int[15][2];
        }


        // All patterns have atleast 9 points
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

        xy[8][0] = x9;
        xy[8][1] = y9;


        if (xy.length >= 14)
        {
            xy[9][0] = x10;
            xy[9][1] = y10;

            xy[10][0] = x11;
            xy[10][1] = y11;

            xy[11][0] = x12;
            xy[11][1] = y12;

            xy[12][0] = x13;
            xy[12][1] = y13;

            xy[13][0] = x14;
            xy[13][1] = y14;

            xy[14][0] = x15;
            xy[14][1] = y15;
        }

        if (xy.length >= 20)
        {
            xy[15][0] = x16;
            xy[15][1] = y16;

            xy[16][0] = x17;
            xy[16][1] = y17;

            xy[17][0] = x18;
            xy[17][1] = y18;

            xy[18][0] = x19;
            xy[18][1] = y19;

            xy[19][0] = x20;
            xy[19][1] = y20;

            xy[20][0] = x21;
            xy[20][1] = y21;
        }

        return xy;
    }
}
