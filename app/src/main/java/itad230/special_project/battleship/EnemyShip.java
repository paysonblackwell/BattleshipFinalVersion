package itad230.special_project.battleship;

import android.os.*;
import android.util.Log;

import java.io.Serializable;
import java.util.Random;

public class EnemyShip implements Serializable {

    private static final String TAG = EnemyGrid.class.getSimpleName();

    private String name;
    private int size;
    private boolean isDestroyed;
    //int[][] grid = UserGrid.enemyGrid;

    //---  PUBLIC METHODS CALLED IN THE ACTIVITY  -----------------------
    public EnemyShip(String name, int size) {
        this.name = name;
        this.size = size;
        isDestroyed = false;
    }

    protected EnemyShip(Parcel in) {
        name = in.readString();
        size = in.readInt();
        isDestroyed = in.readByte() != 0;
    }


    public String getName() {
        return (name);
    }

    public int getSize() {
        return size;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void hitShip() {
        if (size == 1) {
            size--;
            isDestroyed = true;
            return;
        } else {
            size--;
        }
    }


    //---  PRIVATE METHODS NOT CALLED IN THE ACTIVITY  -----------------------
    private boolean isEmptySpace(int[][] grid, int x, int y, int orientation) {

        boolean result = false;
        boolean tmp = false;
        boolean tmp2 = false;

        switch (orientation) {
            //------ Horizontal ------
            case 0:
                //------------------------------------------------------------
                if (x == 0 && y == 0) {
                    if ((grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5) &&
                            (grid[(x+size)][(y+1)] == -1) || (grid[(x+size)][(y+1)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) &&
                                (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    if ((grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5) &&
                            (grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) && (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    if ((grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5) &&
                            (grid[(x-size)][(y+1)] == -1 || grid[(x-size)][(y+1)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) && (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    if ((grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5) &&
                            (grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) && (grid[x][y] == -1)) { tmp2 = true;}
                        else { return false; }
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    if ((grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5) &&
                            (grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5) &&
                            (grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5)&&
                                (grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) && (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    if ((grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5) &&
                            (grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5) &&
                            (grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                (grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) && (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x -= 1;

                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    if (x < size) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                (grid[(x-1)][(y+1)] == -1 || grid[(x-1)][(y+1)] == -5) &&
                                (grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5) &&
                                (grid[(x+size)][(y+1)] == -1 || grid[(x+size)][(y+1)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                        }
                    } else {
                        if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                (grid[(x+1)][(y+1)] == -1 || grid[(x+1)][(y+1)] == -5) &&
                                (grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5) &&
                                (grid[(x-size)][(y+1)] == -1 || grid[(x-size)][(y+1)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    if (x < size) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                (grid[(x-1)][(y-1)] == -1 || grid[(x-1)][(y-1)] == -5) &&
                                (grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5) &&
                                (grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x += 1;
                        }
                    } else {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                (grid[(x-1)][(y-1)] == -1 || grid[(x-1)][(y-1)] == -5) &&
                                (grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5) &&
                                (grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else {
                    if (x < size) {
                        if ((grid[(x-1)][(y+1)] == -1 || grid[(x-1)][(y+1)] == -5) &&
                                (grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                (grid[(x-1)][(y-1)] == -1  || grid[(x-1)][(y-1)] == -5) &&
                                (grid[(x+size)][(y+1)] == -1 || grid[(x+size)][(y+1)] == -5) &&
                                (grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5) &&
                                (grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) &&
                                    (grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x += 1;
                        }
                    } else {
                        if ((grid[(x+1)][(y+1)] == -1 || grid[(x+1)][(y+1)] == -5) &&
                                (grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                (grid[(x+1)][(y-1)] == -1 || grid[(x+1)][(y-1)] == -5) &&
                                (grid[(x-size)][(y+1)] == -1 || grid[(x-size)][(y+1)] == -5) &&
                                (grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5) &&
                                (grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) &&
                                    (grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                if (tmp && tmp2) { result = true; }
                break;

            //--------- Vertical ---------
            case 1:
                //------------------------------------------------------------
                if (x == 0 && y == 0) {
                    if ((grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5) &&
                            (grid[(x+1)][(y+size)] == -1 || grid[(x+1)][(y+size)] == -5)) { tmp = true;}
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                grid[x][y] == -1) { tmp2 = true;}
                        else { return false; }
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    if ((grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                grid[x][y] == -1) { tmp2 = true;}
                        else { return false; }
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    if ((grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5) &&
                            (grid[(x+1)][(y-size)] == -1 || grid[(x+1)][(y-size)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                grid[x][y] == -1) { tmp2 = true; }
                        else { return false; }
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    if ((grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5) &&
                            (grid[(x-1)][(y-size)] == -1 || grid[(x-1)][(y-size)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                grid[x][y] == -1) { tmp2 = true; }
                        else { return false; }
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    if ((grid[(x-1)][(y-size)] == -1 || grid[(x-1)][(y-size)] == -5) &&
                            (grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5) &&
                            (grid[(x+1)][(y-size)] == -1 || grid[(x+1)][(y-size)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                (grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                grid[x][y] == -1) { tmp2 = true; }
                        else { return false; }
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    if ((grid[(x-1)][(y+size)] == -1 || grid[(x-1)][(y+size)] == -5) &&
                            (grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5) &&
                            (grid[(x+1)][(y+size)] == -1 || grid[(x+1)][(y+size)] == -5)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                (grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                grid[x][y] == -1) { tmp2 = true; }
                        else { return false; }
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    if (y < size) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                (grid[(x+1)][(y-1)] == -1 || grid[(x+1)][(y-1)] == -5) &&
                                (grid[(x)][(y+size)] == -1 || grid[(x)][(y+size)] == -5) &&
                                (grid[(x+1)][(y+size)] == -1 || grid[(x+1)][(y+size)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y += 1;
                        }
                    } else {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                (grid[(x+1)][(y-1)] == -1 || grid[(x+1)][(y-1)] == -5) &&
                                (grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5) &&
                                (grid[(x+1)][(y-size)] == -1 || grid[(x+1)][(y-size)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    if (y < size) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                (grid[(x-1)][(y-1)] == -1 || grid[(x-1)][(y-1)] == -5) &&
                                (grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5) &&
                                (grid[(x-1)][(y+size)] == -1 || grid[(x-1)][(y+size)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y += 1;
                        }
                    } else {
                        if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) &&
                                (grid[(x-1)][(y+1)] == -1 || grid[(x-1)][(y+1)] == -5) &&
                                (grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5) &&
                                (grid[(x-1)][(y-size)] == -1 || grid[(x-1)][(y-size)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else {
                    if (y < size) {
                        if ((grid[(x-1)][(y-1)] == -1 || grid[(x-1)][(y-1)] == -5) &&
                                (grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5) &&
                                (grid[(x+1)][(y-1)] == -1 || grid[(x+1)][(y-1)] == -5) &&
                                (grid[(x-1)][(y+size)] == -1 || grid[(x-1)][(y+size)] == -5) &&
                                (grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5) &&
                                (grid[(x+1)][(y+size)] == -1 || grid[(x+1)][(y+size)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                    (grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y += 1;
                        }
                    } else {
                        if ((grid[(x-1)][(y+1)] == -1 || grid[(x-1)][(y+1)] == -5) &&
                                (grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5) &&
                                (grid[(x+1)][(y+1)] == -1 || grid[(x+1)][(y+1)] == -5) &&
                                (grid[(x-1)][(y-size)] == -1 || grid[(x-1)][(y-size)] == -5) &&
                                (grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5) &&
                                (grid[(x+1)][(y-size)] == -1 || grid[(x+1)][(y-size)] == -5)) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5) &&
                                    (grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                if (tmp && tmp2) { result = true; }
                break;
        }
        return result;
    }

    //--- PUBLIC METHOD THAT PLACES SHIP ON THE GRID ------------------------
    public void placeShip(int[][] grid, int shipId) {

        Random rand = new Random();
        int x = (int) rand.nextInt(10);
        int y = (int) rand.nextInt(10);
        int orientation = (int) (Math.random() * 2);

        while (!isEmptySpace(grid, x, y, orientation)) {
            x = (int) rand.nextInt(10);
            y = (int) rand.nextInt(10);
            orientation = (int) (Math.random() * 2);
        }

        Log.d(TAG, "----------------------------------");
        Log.d(TAG, "Starting point: (" + x + ")(" + y + "), orientation: " + orientation);

        // Place ship on the enemyGrid based on the orientation
        switch (orientation) {
            //----- Horizontal -----
            case 0:
                //------------------------------------------------------------
                if (x == 0 && y == 0) {
                    grid[(x+size)][y] = -5;
                    grid[(x+size)][(y+1)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y+1)] = -5;
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    grid[(x+size)][y] = -5;
                    grid[(x+size)][(y-1)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] = -5;
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    grid[(x-size)][y] = -5;
                    grid[(x-size)][(y-1)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] = -5;
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    grid[(x-size)][y] = -5;
                    grid[(x-size)][(y+1)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y+1)] = -5;
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    grid[(x+size)][(y-1)] = -5;
                    grid[(x+size)][y] = -5;
                    grid[(x+size)][(y+1)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] = -5;
                        grid[x][(y+1)] = -5;
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    grid[(x-size)][(y-1)] = -5;
                    grid[(x-size)][y] = -5;
                    grid[(x-size)][(y+1)] = -5;
                    for (int slot = 0; slot < size; slot++){
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] = -5;
                        grid[x][(y+1)] = -5;
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    if (x < size) {
                        grid[(x-1)][(y+1)] = -5;
                        grid[(x-1)][(y)] = -5;
                        grid[(x+size)][y] = -5;
                        grid[(x+size)][(y+1)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] = -5;
                            x += 1;
                        }
                    } else {
                        grid[(x-size)][y] = -5;
                        grid[(x-size)][(y+1)] = -5;
                        grid[(x+1)][y] = -5;
                        grid[(x+1)][(y+1)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] = -5;
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    if (x < size) {
                        grid[(x-1)][y] = -5;
                        grid[(x-1)][(y-1)] = -5;
                        grid[(x+size)][y] = -5;
                        grid[(x+size)][(y-1)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y-1)] = -5;
                            x += 1;
                        }
                    } else {
                        grid[(x+1)][y] = -5;
                        grid[(x+1)][(y-1)] = -5;
                        grid[(x-size)][y] = -5;
                        grid[(x-size)][(y-1)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y-1)] = -5;
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else {
                    if (x < size) {
                        grid[(x-1)][(y+1)] = -5;
                        grid[(x-1)][y] = -5;
                        grid[(x-1)][(y-1)] = -5;
                        grid[(x+size)][(y+1)] = -5;
                        grid[(x+size)][y] = -5;
                        grid[(x+size)][(y-1)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] = -5;
                            grid[x][(y-1)] = -5;
                            x += 1;
                        }
                    } else {
                        grid[(x+1)][(y+1)] = -5;
                        grid[(x+1)][y] = -5;
                        grid[(x+1)][(y-1)] = -5;
                        grid[(x-size)][(y+1)] = -5;
                        grid[(x-size)][y] = -5;
                        grid[(x-size)][(y-1)] = -5;

                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] = -5;
                            grid[x][(y-1)] = -5;
                            x -= 1;
                        }
                    }
                }
                break;
            //------------------------------------------------------------
            //----- Vertical ----
            case 1:
                //------------------------------------------------------------
                if (x == 0 && y == 0) {
                    grid[x][(y+size)] = -5;
                    grid[(x+1)][(y+size)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x+1)][y] = -5;
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    grid[x][(y-size)] = -5;
                    grid[(x+1)][(y-size)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x+1)][y] = -5;
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    grid[x][(y-size)] = -5;
                    grid[(x-1)][(y-size)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] = -5;
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    grid[x][(y+size)] = -5;
                    grid[(x-1)][(y+size)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] = -5;
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    if (y < size) {
                        grid[x][(y+size)] = -5;
                        grid[(x+1)][(y+size)] = -5;
                        grid[x][(y-1)] = -5;
                        grid[(x+1)][(y-1)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x+1)][y] = -5;
                            y += 1;
                        }
                    } else {
                        grid[x][(y-size)] = -5;
                        grid[(x+1)][(y-size)] = -5;
                        grid[x][(y+1)] = -5;
                        grid[(x+1)][(y+1)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x+1)][y] = -5;
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    grid[(x-1)][(y+size)] = -5;
                    grid[x][(y+size)] = -5;
                    grid[(x+1)][(y+size)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] = -5;
                        grid[(x+1)][y] = -5;
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    if (y < size) {
                        grid[x][(y-1)] = -5;
                        grid[(x-1)][(y-1)] = -5;
                        grid[x][(y+size)] = -5;
                        grid[(x-1)][(y+size)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] = -5;
                            y += 1;
                        }
                    } else {
                        grid[x][(y+1)] = -5;
                        grid[(x-1)][(y+1)] = -5;
                        grid[x][(y-size)] = -5;
                        grid[(x-1)][(y-size)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] = -5;
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    grid[(x-1)][(y-size)] = -5;
                    grid[x][(y-size)] = -5;
                    grid[(x+1)][(y-size)] = -5;
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] = -5;
                        grid[(x+1)][y] = -5;
                        y -= 1;
                    }

                }
                //------------------------------------------------------------
                else {
                    if (y < size) {
                        grid[(x-1)][(y-1)] = -5;
                        grid[x][(y-1)] = -5;
                        grid[(x+1)][(y-1)] = -5;
                        grid[(x-1)][(y+size)] = -5;
                        grid[x][(y+size)] = -5;
                        grid[(x+1)][(y+size)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] = -5;
                            grid[(x+1)][y] = -5;
                            y += 1;
                        }
                    } else {
                        grid[(x-1)][(y+1)] = -5;
                        grid[x][(y+1)] = -5;
                        grid[(x+1)][(y+1)] = -5;
                        grid[(x-1)][(y-size)] = -5;
                        grid[x][(y-size)] = -5;
                        grid[(x+1)][(y-size)] = -5;
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: enemyGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] = -5;
                            grid[(x+1)][y] = -5;
                            y -= 1;
                        }
                    }
                }
                break;
        }
    }
}
