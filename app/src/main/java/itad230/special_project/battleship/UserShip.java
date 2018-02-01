package itad230.special_project.battleship;

import android.os.*;
import android.util.Log;

import java.io.Serializable;

public class UserShip implements Serializable {

    private static final String TAG = UserGrid.class.getSimpleName();

    private String name;
    private int size;
    private boolean isDestroyed;
    //int[][] grid = UserGrid.userGrid;
    private boolean shipButton = false;

    //Chanaged
    private int starty;
    private int startx;
    private int startOr;

    //---  PUBLIC METHODS CALLED IN THE ACTIVITY  -----------------------
    public UserShip(String name, int size) {
        this.name = name;
        this.size = size;
        isDestroyed = false;
    }

    protected UserShip(Parcel in) {
        name = in.readString();
        size = in.readInt();
        isDestroyed = in.readByte() != 0;
    }

    public boolean getBoolean()//HERE
    {
        return shipButton;
    }

    public void setBoolean(boolean x)//HERE
    {
        shipButton = x;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void hitShip() {
        if (size != 0) {
            size -= 1;
        } else {
            isDestroyed = true;
            return;
        }
    }

    public int getStartOr(){
        return startOr;
    }

    private boolean isEmptySpot(int[][] grid, int x, int y, int plusx, int plusy){

        if(x+plusx >= 10 || y+plusy >= 10)
        {
            return false;
        }

        if(grid[(x+plusx)][(y+plusy)] == -20 || grid[(x+plusx)][(y+plusy)] == -40 || grid[(x+plusx)][(y+plusy)] == -60 || grid[(x+plusx)][(y+plusy)] == -80 || grid[(x+plusx)][(y+plusy)] == -100)
        {
            return true;
        }
        return false;
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
                    if ((grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5 || isEmptySpot(grid, x,y,size,0)) &&
                            (grid[(x+size)][(y+1)] == -1) || (grid[(x+size)][(y+1)] == -5) || isEmptySpot(grid, x,y,size,1)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) &&
                                (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    if ((grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5 || isEmptySpot(grid, x,y,size,0)) &&
                            (grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5 || isEmptySpot(grid, x,y,size,-1))) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) && (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    if ((grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5 || isEmptySpot(grid, x,y,(size* -1),0)) &&
                            (grid[(x-size)][(y+1)] == -1 || grid[(x-size)][(y+1)] == -5 || isEmptySpot(grid, x,y,(size* -1),1)) ) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) && (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    if ((grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5 || isEmptySpot(grid, x,y,(size* -1),0)) &&
                            (grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5) || isEmptySpot(grid, x,y,(size* -1),-1)) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) && (grid[x][y] == -1)) { tmp2 = true;}
                        else { return false; }
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    if ((grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5 || isEmptySpot(grid, x,y,(size),-1)) &&
                            (grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5 || isEmptySpot(grid, x,y,(size),0)) &&
                            (grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5 || isEmptySpot(grid, x,y,(size),-1))) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1))&&
                                (grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) && (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    if ((grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5 || isEmptySpot(grid, x,y,(size* -1),-1)) &&
                            (grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5 || isEmptySpot(grid, x,y,(size* -1),0)) &&
                            (grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5 || isEmptySpot(grid, x,y,(size* -1),-1))) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) &&
                                (grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) && (grid[x][y] == -1)) { tmp2 = true; }
                        else { return false; }
                        x -= 1;

                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    if (x < size) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,(-1),0)) &&
                                (grid[(x-1)][(y+1)] == -1 || grid[(x-1)][(y+1)] == -5 || isEmptySpot(grid, x,y,(-1),1)) &&
                                (grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5 || isEmptySpot(grid, x,y,(size),0)) &&
                                (grid[(x+size)][(y+1)] == -1 || grid[(x+size)][(y+1)] == -5 || isEmptySpot(grid, x,y,(size),1))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                        }
                    } else {
                        if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,1,0)) &&
                                (grid[(x+1)][(y+1)] == -1 || grid[(x+1)][(y+1)] == -5 || isEmptySpot(grid, x,y,1,1)) &&
                                (grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5 || isEmptySpot(grid, x,y,(size* -1),0)) &&
                                (grid[(x-size)][(y+1)] == -1 || grid[(x-size)][(y+1)] == -5 || isEmptySpot(grid, x,y,(size* -1),1))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    if (x < size) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,(-1),0)) &&
                                (grid[(x-1)][(y-1)] == -1 || grid[(x-1)][(y-1)] == -5|| isEmptySpot(grid, x,y,(-1),-1)) &&
                                (grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5 || isEmptySpot(grid, x,y,(size),0)) &&
                                (grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5 || isEmptySpot(grid, x,y,(size),-1))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x += 1;
                        }
                    } else {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,(-1),0)) &&
                                (grid[(x-1)][(y-1)] == -1 || grid[(x-1)][(y-1)] == -5|| isEmptySpot(grid, x,y,(-1),-1)) &&
                                (grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5 || isEmptySpot(grid, x,y,(size* -1),0)) &&
                                (grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5 || isEmptySpot(grid, x,y,(size* -1),-1))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else {
                    if (x < size) {
                        if ((grid[(x-1)][(y+1)] == -1 || grid[(x-1)][(y+1)] == -5 || isEmptySpot(grid, x,y,(-1),1)) &&
                                (grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,(-1),0)) &&
                                (grid[(x-1)][(y-1)] == -1  || grid[(x-1)][(y-1)] == -5 || isEmptySpot(grid, x,y,(-1),-1)) &&
                                (grid[(x+size)][(y+1)] == -1 || grid[(x+size)][(y+1)] == -5 || isEmptySpot(grid, x,y,(size),1)) &&
                                (grid[(x+size)][y] == -1 || grid[(x+size)][y] == -5 || isEmptySpot(grid, x,y,(size),0)) &&
                                (grid[(x+size)][(y-1)] == -1 || grid[(x+size)][(y-1)] == -5 || isEmptySpot(grid, x,y,(size),-1))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,(0),1)) &&
                                    (grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            x += 1;
                        }
                    } else {
                        if ((grid[(x+1)][(y+1)] == -1 || grid[(x+1)][(y+1)] == -5 || isEmptySpot(grid, x,y,(1),1)) &&
                                (grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,(1),0)) &&
                                (grid[(x+1)][(y-1)] == -1 || grid[(x+1)][(y-1)] == -5 || isEmptySpot(grid, x,y,(1),-1)) &&
                                (grid[(x-size)][(y+1)] == -1 || grid[(x-size)][(y+1)] == -5 || isEmptySpot(grid, x,y,(size* -1),1)) &&
                                (grid[(x-size)][y] == -1 || grid[(x-size)][y] == -5 || isEmptySpot(grid, x,y,(size* -1),0)) &&
                                (grid[(x-size)][(y-1)] == -1 || grid[(x-size)][(y-1)] == -5 || isEmptySpot(grid, x,y,(size* -1),-1))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) &&
                                    (grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) &&
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
                    if ((grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5 || isEmptySpot(grid, x,y,0,size)) &&
                            (grid[(x+1)][(y+size)] == -1 || grid[(x+1)][(y+size)] == -5 || isEmptySpot(grid, x,y,(1),size))) { tmp = true;}
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,(1),0)) &&
                                grid[x][y] == -1) { tmp2 = true;}
                        else { return false; }
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    if ((grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5 || isEmptySpot(grid, x,y,0,size))) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,(-1),0)) &&
                                grid[x][y] == -1) { tmp2 = true;}
                        else { return false; }
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    if ((grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5 || isEmptySpot(grid, x,y,0,(size*-1))) &&
                            (grid[(x+1)][(y-size)] == -1 || grid[(x+1)][(y-size)] == -5 || isEmptySpot(grid, x,y,1,(size*-1)))) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,1,0)) &&
                                grid[x][y] == -1) { tmp2 = true; }
                        else { return false; }
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    if ((grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5 || isEmptySpot(grid, x,y,0,(size*-1))) &&
                            (grid[(x-1)][(y-size)] == -1 || grid[(x-1)][(y-size)] == -5 || isEmptySpot(grid, x,y,-1,(size*-1)))) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,-1,0)) &&
                                grid[x][y] == -1) { tmp2 = true; }
                        else { return false; }
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    if ((grid[(x-1)][(y-size)] == -1 || grid[(x-1)][(y-size)] == -5 || isEmptySpot(grid, x,y,-1,(size*-1))) &&
                            (grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5 || isEmptySpot(grid, x,y,0,(size*-1))) &&
                            (grid[(x+1)][(y-size)] == -1 || grid[(x+1)][(y-size)] == -5 || isEmptySpot(grid, x,y,1,(size*-1)))) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,-1,0)) &&
                                (grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,1,0)) &&
                                grid[x][y] == -1) { tmp2 = true; }
                        else { return false; }
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    if ((grid[(x-1)][(y+size)] == -1 || grid[(x-1)][(y+size)] == -5 || isEmptySpot(grid, x,y,-1,(size))) &&
                            (grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5 || isEmptySpot(grid, x,y,0,(size))) &&
                            (grid[(x+1)][(y+size)] == -1 || grid[(x+1)][(y+size)] == -5) || isEmptySpot(grid, x,y,1,(size))) { tmp = true; }
                    for (int slot = 0; slot < size; slot++) {
                        if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,-1,0)) &&
                                (grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,1,0)) &&
                                grid[x][y] == -1) { tmp2 = true; }
                        else { return false; }
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    if (y < size) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,(-1))) &&
                                (grid[(x+1)][(y-1)] == -1 || grid[(x+1)][(y-1)] == -5 || isEmptySpot(grid, x,y,1,-1)) &&
                                (grid[(x)][(y+size)] == -1 || grid[(x)][(y+size)] == -5 || isEmptySpot(grid, x,y,0,(size))) &&
                                (grid[(x+1)][(y+size)] == -1 || grid[(x+1)][(y+size)] == -5) || isEmptySpot(grid, x,y,1,(size))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,1,0)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y += 1;
                        }
                    } else {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) &&
                                (grid[(x+1)][(y-1)] == -1 || grid[(x+1)][(y-1)] == -5 || isEmptySpot(grid, x,y,1,-1)) &&
                                (grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5 || isEmptySpot(grid, x,y,0,(size*-1))) &&
                                (grid[(x+1)][(y-size)] == -1 || grid[(x+1)][(y-size)] == -5 || isEmptySpot(grid, x,y,1,(size*-1)))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,1,0)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    if (y < size) {
                        if ((grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) &&
                                (grid[(x-1)][(y-1)] == -1 || grid[(x-1)][(y-1)] == -5 || isEmptySpot(grid, x,y,-1,-1)) &&
                                (grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5 || isEmptySpot(grid, x,y,0,size)) &&
                                (grid[(x-1)][(y+size)] == -1 || grid[(x-1)][(y+size)] == -5 || isEmptySpot(grid, x,y,-1,size))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,-1,0)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y += 1;
                        }
                    } else {
                        if ((grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) &&
                                (grid[(x-1)][(y+1)] == -1 || grid[(x-1)][(y+1)] == -5 || isEmptySpot(grid, x,y,1,1)) &&
                                (grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5 || isEmptySpot(grid, x,y,0,(size*-1))) &&
                                (grid[(x-1)][(y-size)] == -1 || grid[(x-1)][(y-size)] == -5 || isEmptySpot(grid, x,y,-1,(size*-1)))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,-1,0)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else {
                    if (y < size) {
                        if ((grid[(x-1)][(y-1)] == -1 || grid[(x-1)][(y-1)] == -5 || isEmptySpot(grid, x,y,-1,-1)) &&
                                (grid[x][(y-1)] == -1 || grid[x][(y-1)] == -5 || isEmptySpot(grid, x,y,0,-1)) &&
                                (grid[(x+1)][(y-1)] == -1 || grid[(x+1)][(y-1)] == -5 || isEmptySpot(grid, x,y,1,-1)) &&
                                (grid[(x-1)][(y+size)] == -1 || grid[(x-1)][(y+size)] == -5 || isEmptySpot(grid, x,y,-1,size)) &&
                                (grid[x][(y+size)] == -1 || grid[x][(y+size)] == -5 || isEmptySpot(grid, x,y,0,size)) &&
                                (grid[(x+1)][(y+size)] == -1 || grid[(x+1)][(y+size)] == -5 || isEmptySpot(grid, x,y,1,size))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,1,0)) &&
                                    (grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,-1,0)) &&
                                    grid[x][y] == -1) { tmp2 = true; }
                            else { return false; }
                            y += 1;
                        }
                    } else {
                        if ((grid[(x-1)][(y+1)] == -1 || grid[(x-1)][(y+1)] == -5 || isEmptySpot(grid, x,y,-1,1)) &&
                                (grid[x][(y+1)] == -1 || grid[x][(y+1)] == -5 || isEmptySpot(grid, x,y,0,1)) &&
                                (grid[(x+1)][(y+1)] == -1 || grid[(x+1)][(y+1)] == -5 || isEmptySpot(grid, x,y,1,1)) &&
                                (grid[(x-1)][(y-size)] == -1 || grid[(x-1)][(y-size)] == -5 || isEmptySpot(grid, x,y,-1,(size*-1))) &&
                                (grid[x][(y-size)] == -1 || grid[x][(y-size)] == -5 || isEmptySpot(grid, x,y,0,(size*-1))) &&
                                (grid[(x+1)][(y-size)] == -1 || grid[(x+1)][(y-size)] == -5 || isEmptySpot(grid, x,y,1,(size*-1)))) { tmp = true; }
                        for (int slot = 0; slot < size; slot++) {
                            if ((grid[(x-1)][y] == -1 || grid[(x-1)][y] == -5 || isEmptySpot(grid, x,y,-1,0)) &&
                                    (grid[(x+1)][y] == -1 || grid[(x+1)][y] == -5 || isEmptySpot(grid, x,y,1,0)) &&
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

        int x = (int) (Math.random() * 9);
        int y = (int) (Math.random() * 9);
        int orientation = (int) (Math.random() * 2);

        while (!isEmptySpace(grid, x, y, orientation)) {
            x = (int) (Math.random() * 9);
            y = (int) (Math.random() * 9);
            orientation = (int) (Math.random() * 2);
        }

        Log.d(TAG, "----------------------------------");
        Log.d(TAG, "Starting point: (" + x + ")(" + y + "), orientation: " + orientation);

        //changed
        startx = x;
        starty = y;
        startOr = orientation;

        // Place ship on the userGrid
        switch (orientation) {
            //----- Horizontal -----
            case 0:

                //------------------------------------------------------------
                if (x == 0 && y == 0) {
                    grid[(x+size)][y] = (shipId*-20);
                    grid[(x+size)][(y+1)] = (shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y+1)] = (shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    grid[(x+size)][y] = (shipId*-20);
                    grid[(x+size)][(y-1)] = (shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] = (shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    grid[(x-size)][y] = (shipId*-20);
                    grid[(x-size)][(y-1)] = (shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] = (shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    grid[(x-size)][y] = (shipId*-20);
                    grid[(x-size)][(y+1)] = (shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y+1)] = (shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    grid[(x+size)][(y-1)] =(shipId*-20);
                    grid[(x+size)][y] =(shipId*-20);
                    grid[(x+size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    grid[(x-size)][(y-1)] =(shipId*-20);
                    grid[(x-size)][y] =(shipId*-20);
                    grid[(x-size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++){
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y+1)] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y-1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y-1)] =(shipId*-20);
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[(x+size)][(y+1)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            grid[x][(y-1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-size)][(y+1)] =(shipId*-20);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y-1)] =(shipId*-20);

                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            grid[x][(y-1)] =(shipId*-20);
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
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x+1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x+1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x+1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x+1)][y] =(shipId*-20);
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x-1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x-1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    if (y < size) {
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x+1)][(y+size)] =(shipId*-20);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x+1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x+1)][(y-size)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x+1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    grid[(x-1)][(y+size)] =(shipId*-20);
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x+1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    if (y < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x-1)][(y+size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x-1)][(y-size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    grid[(x-1)][(y-size)] =(shipId*-20);
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x+1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        y -= 1;
                    }

                }
                //------------------------------------------------------------
                else {
                    if (y < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "],orientation=" +orientation);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-1)][(y+size)] =(shipId*-20);
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x+1)][(y+size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            grid[(x+1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y-size)] =(shipId*-20);
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x+1)][(y-size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            grid[(x+1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                break;
        }
        //----  End of Switch  -------------------------
        /*for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COLUMN; j++) {
                newGrid[i][j] = userGrid[i][j];
            }
        }*/

    }

    public void placeShipHere(int[][] grid, int x, int y, int shipId) {

        int orientation = (int) (Math.random() * 2);

        if (!isEmptySpace(grid, x, y, orientation)) {
            return;
        }

        Log.d(TAG, "----------------------------------");
        Log.d(TAG, "Starting point: (" + x + ")(" + y + "), orientation: " + orientation);

        //changed
        startx = x;
        starty = y;
        startOr = orientation;

        // Place ship on the userGrid
        switch (orientation) {
            //----- Horizontal -----
            case 0:
                //------------------------------------------------------------
                if (x == 0 && y == 0) {
                    grid[(x+size)][y] =(shipId*-20);
                    grid[(x+size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y+1)] =(shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    grid[(x+size)][y] =(shipId*-20);
                    grid[(x+size)][(y-1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    grid[(x-size)][y] =(shipId*-20);
                    grid[(x-size)][(y-1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    grid[(x-size)][y] =(shipId*-20);
                    grid[(x-size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y+1)] =(shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    grid[(x+size)][(y-1)] =(shipId*-20);
                    grid[(x+size)][y] =(shipId*-20);
                    grid[(x+size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    grid[(x-size)][(y-1)] =(shipId*-20);
                    grid[(x-size)][y] =(shipId*-20);
                    grid[(x-size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++){
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y+1)] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y-1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y-1)] =(shipId*-20);
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[(x+size)][(y+1)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            grid[x][(y-1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-size)][(y+1)] =(shipId*-20);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y-1)] =(shipId*-20);

                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            grid[x][(y-1)] =(shipId*-20);
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
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x+1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x+1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x+1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x+1)][y] =(shipId*-20);
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x-1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x-1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    if (y < size) {
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x+1)][(y+size)] =(shipId*-20);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x+1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x+1)][(y-size)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x+1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    grid[(x-1)][(y+size)] =(shipId*-20);
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x+1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    if (y < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x-1)][(y+size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x-1)][(y-size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    grid[(x-1)][(y-size)] =(shipId*-20);
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x+1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        y -= 1;
                    }

                }
                //------------------------------------------------------------
                else {
                    if (y < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "],orientation=" +orientation);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-1)][(y+size)] =(shipId*-20);
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x+1)][(y+size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            grid[(x+1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y-size)] =(shipId*-20);
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x+1)][(y-size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            grid[(x+1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                break;
        }
        //----  End of Switch  -------------------------
    }

    public void placeShipThisWay(int[][] grid, int x, int y, int or, int shipId){
        Log.d(TAG, "----------------------------------");
        Log.d(TAG, "Starting point: (" + x + ")(" + y + "), orientation: " + or);
        // Place ship on the userGrid
        switch (or) {
            //----- Horizontal -----
            case 0:
                //------------------------------------------------------------
                if (x == 0 && y == 0) {
                    grid[(x+size)][y] =(shipId*-20);
                    grid[(x+size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y+1)] =(shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    grid[(x+size)][y] =(shipId*-20);
                    grid[(x+size)][(y-1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    grid[(x-size)][y] =(shipId*-20);
                    grid[(x-size)][(y-1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    grid[(x-size)][y] =(shipId*-20);
                    grid[(x-size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y+1)] =(shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    grid[(x+size)][(y-1)] =(shipId*-20);
                    grid[(x+size)][y] =(shipId*-20);
                    grid[(x+size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        x += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    grid[(x-size)][(y-1)] =(shipId*-20);
                    grid[(x-size)][y] =(shipId*-20);
                    grid[(x-size)][(y+1)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++){
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[x][(y-1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        x -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y+1)] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y-1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y-1)] =(shipId*-20);
                            x -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else {
                    if (x < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[(x+size)][(y+1)] =(shipId*-20);
                        grid[(x+size)][y] =(shipId*-20);
                        grid[(x+size)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            grid[x][(y-1)] =(shipId*-20);
                            x += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-size)][(y+1)] =(shipId*-20);
                        grid[(x-size)][y] =(shipId*-20);
                        grid[(x-size)][(y-1)] =(shipId*-20);

                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[x][(y+1)] =(shipId*-20);
                            grid[x][(y-1)] =(shipId*-20);
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
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x+1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x+1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0 && y == 9) {
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x+1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x+1)][y] =(shipId*-20);
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 9) {
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x-1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        y -= 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9 && y == 0) {
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x-1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 0) {
                    if (y < size) {
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x+1)][(y+size)] =(shipId*-20);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x+1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x+1)][(y-size)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x+1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 0) {
                    grid[(x-1)][(y+size)] =(shipId*-20);
                    grid[x][(y+size)] =(shipId*-20);
                    grid[(x+1)][(y+size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        y += 1;
                    }
                }
                //------------------------------------------------------------
                else if (x == 9) {
                    if (y < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x-1)][(y+size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "], orientation=" +orientation);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x-1)][(y-size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                //------------------------------------------------------------
                else if (y == 9) {
                    grid[(x-1)][(y-size)] =(shipId*-20);
                    grid[x][(y-size)] =(shipId*-20);
                    grid[(x+1)][(y-size)] =(shipId*-20);
                    for (int slot = 0; slot < size; slot++) {
                        grid[x][y] = shipId;
                        Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                        grid[(x-1)][y] =(shipId*-20);
                        grid[(x+1)][y] =(shipId*-20);
                        y -= 1;
                    }

                }
                //------------------------------------------------------------
                else {
                    if (y < size) {
                        //Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "],orientation=" +orientation);
                        grid[(x-1)][(y-1)] =(shipId*-20);
                        grid[x][(y-1)] =(shipId*-20);
                        grid[(x+1)][(y-1)] =(shipId*-20);
                        grid[(x-1)][(y+size)] =(shipId*-20);
                        grid[x][(y+size)] =(shipId*-20);
                        grid[(x+1)][(y+size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            grid[(x+1)][y] =(shipId*-20);
                            y += 1;
                        }
                    } else {
                        grid[(x-1)][(y+1)] =(shipId*-20);
                        grid[x][(y+1)] =(shipId*-20);
                        grid[(x+1)][(y+1)] =(shipId*-20);
                        grid[(x-1)][(y-size)] =(shipId*-20);
                        grid[x][(y-size)] =(shipId*-20);
                        grid[(x+1)][(y-size)] =(shipId*-20);
                        for (int slot = 0; slot < size; slot++) {
                            grid[x][y] = shipId;
                            Log.d(TAG, "Starting point: userGrid[" + x + "][" + y + "] = ship (" + size + ")");
                            grid[(x-1)][y] =(shipId*-20);
                            grid[(x+1)][y] =(shipId*-20);
                            y -= 1;
                        }
                    }
                }
                break;
        }
    }

    public void tapToRotate(int[][] grid, int shipId){
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(grid[i][j] == shipId || grid[i][j] == (shipId*-20))
                {
                    grid[i][j] = -1;
                }
            }
        }
        int newOr = startOr == 0? 1: 0;
        boolean empty = isEmptySpace(grid, startx, starty, newOr);
        if (!empty){
            placeShipThisWay(grid, startx, starty, startOr, shipId);
            return;
        }
        startOr = newOr;
        placeShipThisWay(grid, startx, starty, newOr, shipId);
    }
}
