package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    public static class Position {
        public int x;
        public int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    /**
     * draw a row of hexagon in world.
     * @param world   2d array of TETile
     * @param p       specifies the lower left corner of the row.
     * @param t       the tile type of the row.
     * @param length  the length of the row.
     */
    private void drawRow(TETile[][] world, Position p, TETile t, int length) {
        for (int i = 0; i < length; i++) {
            world[p.x + i][p.y] = t;
        }
    }

    /**
     * 计算当前行基准点相较于row0的偏移量
     * @param whichRow    当前在第几行
     * @param size        hexagon的size
     * @return            偏移量
     */
    private int getOffset(int whichRow, int size) {
        if (whichRow < size) {
            return -whichRow;
        } else {
            return -(2 * size - 1 - whichRow);
        }
    }

    /**
     * 计算当前行的起始位置
     * @param p             row0的基准点
     * @param whichRow      当前在第几行
     * @param size          hexagon的size
     * @return              当前行的起始位置
     */
    private Position getRowStartPosition(Position p, int whichRow, int size) {
        int offsetX = getOffset(whichRow, size);
        return new Position(p.x + offsetX, p.y + whichRow);
    }

    /**
     * 计算当前行的长度
     * @param whichRow     当前在第几行
     * @param size         hexagon的size
     * @return             当前行的长度
     */
    private int getRowLength(int whichRow, int size) {
        if (whichRow < size) {
            return size + 2 * whichRow;
        } else {
            return size + 2 * (2 * size - 1 - whichRow);
        }
    }
    /**
     * draw a hexagon with side length s in world.
     * @param world   2d array of TETile
     * @param p       specifies the lower left corner of the hexagon.
     * @param t       the tile type of the hexagon.
     * @param side    the side length of the hexagon.
     */
    public void addHexagon (TETile[][] world, Position p, TETile t, int side) {
        // 检测p的合法性
        if (p.x < 0 || p.y < 0) {
            throw new IllegalArgumentException("The position is not valid.");
        }
        // 检测side的合法性
        if (side < 2) {
            throw new IllegalArgumentException("The side length is too small.");
        }
        for (int i = 0; i < 2 * side; i++) {
            int thisRowY = p.y + i;
            int thisRowX = p.x + getOffset(i, side);
            Position thisRowStart = new Position(thisRowX, thisRowY);
            drawRow(world, thisRowStart, t, getRowLength(i, side));
        }
    }
}
