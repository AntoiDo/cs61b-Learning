package byog.Core;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;
import byog.lab5.HexWorld.Position;

import java.util.ArrayList;
import java.util.Random;

public class CreateWorld {
    private static final int WIDTH = 60;
    private static final int HEIGHT = 30;

    /**
     * add the walls around the cell to a list (i.e., "wall list").
     * @param world    the world
     * @param x        x coordinate of grass cell
     * @param y        y coordinate of grass cell
     * @param wallList the list of walls
     */
    private void addWallsToList(TETile[][] world, int x, int y, ArrayList<Position> wallList) {
        // 添加相邻的四个墙壁
        if(x - 1 >= 0 && world[x - 1][y] == Tileset.WALL) {
            wallList.add(new Position(x - 1, y));
        }
        if(x + 1 < WIDTH && world[x + 1][y] == Tileset.WALL) {
            wallList.add(new Position(x + 1, y));
        }
        if(y - 1 >= 0 && world[x][y - 1] == Tileset.WALL) {
            wallList.add(new Position(x, y - 1));
        }
        if(y + 1 < HEIGHT && world[x][y + 1] == Tileset.WALL) {
            wallList.add(new Position(x, y + 1));
        }
    }

    /**
     * generate the world
     * @param world
     * @note Tileset.WALL represents the wall, Tileset.GRASS represents room space
     */
    public void generateWorld(TETile[][] world) {
        // initialize tiles as wall meaning that no space is available
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.WALL;
            }
        }
        // randomly select a cell as the starting point of the maze generation
        // set it as a path cell (e.g., represented by TETiles.GRASS).
        // add the walls around the cell to a list (i.e., "wall list").
        ArrayList<Position> wallList = new ArrayList<>();
        Random random = new Random();
        int startX = random.nextInt(WIDTH);
        int startY = random.nextInt(HEIGHT);
        world[startX][startY] = Tileset.FLOOR;
        addWallsToList(world, startX, startY, wallList);

        /**
         * 循环处理墙壁列表：
         * 当墙壁列表非空时，随机从列表中选择一堵墙。
         * 设该墙壁的坐标为 (x, y)。
         * 检查墙的两侧相邻单元格：
         * 如果两侧单元格之间只有一个是路径，则打通该墙壁，将其改为路径。
         * 将新路径单元周围的墙壁（即新的潜在墙壁）加入到墙壁列表中。
         * 如果两侧都已经是路径，跳过这堵墙并从列表中移除。
         */
        while (!wallList.isEmpty()) {
            int wallIndex = random.nextInt(wallList.size());
            Position wall = wallList.get(wallIndex);
            int wallX = wall.x;
            int wallY = wall.y;
            int pathCount = 0;
            if(wallX - 1 >= 0 && world[wallX - 1][wallY] == Tileset.FLOOR) {
                pathCount++;
            }
            if(wallX + 1 < WIDTH && world[wallX + 1][wallY] == Tileset.FLOOR) {
                pathCount++;
            }
            if(wallY - 1 >= 0 && world[wallX][wallY - 1] == Tileset.FLOOR) {
                pathCount++;
            }
            if(wallY + 1 < HEIGHT && world[wallX][wallY + 1] == Tileset.FLOOR) {
                pathCount++;
            }
            if(pathCount == 1) {
                world[wallX][wallY] = Tileset.FLOOR;
                addWallsToList(world, wallX, wallY, wallList);
            }
            wallList.remove(wallIndex);
        }
    }
    public static void main(String[] args) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }

        CreateWorld createWorld = new CreateWorld();
        createWorld.generateWorld(world);

        // draws the world to the screen
        ter.renderFrame(world);
    }
}
