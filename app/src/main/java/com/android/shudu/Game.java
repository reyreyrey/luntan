package com.android.shudu;

/**
 * Created by Administrator on 2018/2/28 0028.
 */

public class Game {
    //数独初始化数据的基础
    private final String str =
                    "360000000" +
                    "004230800" +
                    "000004200" +
                    "070460003" +
                    "820000014" +
                    "500013020" +
                    "001900000" +
                    "007048300" +
                    "000000045";
    private int sudoku[] = new int[9*9];
    //用于存储每个单元格已经不可用的数据
    private int used[][][] = new int[9][9][];

    public Game(){
        sudoku = fromPuzzleString(str);
        calculateAllUsedTiles();
    }
    //根据九宫格当中的坐标，返回该坐标所应该填写的数字
    private int getTitle(int x,int y){
        return sudoku[y * 9 + x];
    }
    public String getTitleString(int x,int y){
        int v = getTitle(x,y);
        if (v == 0){
            return "";
        }else {
            return String.valueOf(v);
        }
    }
    //根据一个字符串数据，生成一个整形数组
    protected int[] fromPuzzleString(String str){
        int[] sudo = new int[str.length()];
        for (int i = 0; i < sudo.length; i++) {
            sudo[i] = str.charAt(i) - '0';
        }
        return sudo;
    }
    //计算某一个单元格当中已经不可用的数据
    public int[] calculateUsedTiles(int x,int y){
        int[] c = new int[9];
        for (int i = 0; i < 9; i++) {
            if (i == y)
                continue;
            int t = getTitle(x,i);
            if (t != 0)
                c[t - 1] = t;
        }
        for (int i = 0; i < 9; i++) {
            if (i == x)
                continue;
            int t = getTitle(i,y);
            if (t != 0)
                c[t - 1] = t;
        }
        int startx = (x / 3)* 3;
        int starty = (y / 3) * 3;
        for (int i = startx; i < startx  + 3; i++) {
            for (int j = starty; j < starty + 3; j++) {
                if (i == x && j == y)
                    continue;
                int t = getTitle(i,j);
                if (t != 0)
                    c[t - 1] = t;
            }
        }
        int nused = 0;
        for (int t:c) {
            if (t != 0)
                nused++;
        }
        int c1[] = new int[nused];
        nused = 0;
        for (int t:c) {
            if (t != 0)
            c1[nused++] = t;
        }
        return c1;
    }
    public void calculateAllUsedTiles(){
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                used[x][y] = calculateUsedTiles(x,y);
            }
        }
    }
    //取出某一单元格当中已经不可用的数据
    public int[] getUsedTilesByCoor(int x,int y){
        return used[x][y];
    }
    protected boolean setTileIfValid(int x,int y,int value){
        int tiles[] = getUsedTiles(x,y);
        if (value != 0){
            for (int tile:tiles) {
                if (tile == value)
                    return false;
            }
        }
        setTile(x,y,value);
        calculateAllUsedTiles();
        return true;
    }

    private void setTile(int x, int y, int value) {
        sudoku[y * 9 + x] = value;
    }

    private int[] getUsedTiles(int x, int y) {
        return used[x][y];
    }
}
