package itad230.special_project.battleship;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.*;
import android.widget.*;

// TODO: create cell class and each button is a cell object
public class EnemyGrid extends AppCompatActivity {

    // ---  FINAL MEMBER VARIABLES -----------------------------------------------------------------
    private static final String TAG = EnemyGrid.class.getSimpleName();

    // --- OTHER MEMBER VARIABLES  -----------------------------------------------------------------
    protected int[][] enemyGrid = new int[UserGrid.NUM_ROW][UserGrid.NUM_COLUMN];
    private EnemyShip[] enemyShipList = new EnemyShip[UserGrid.NUM_SHIPS];

    private final Button[][] enemyButtonArray = new Button[UserGrid.NUM_ROW][UserGrid.NUM_COLUMN];
    private static boolean gameOver = false;


    // ---  ON_CREATE FUNCTIONS (CALLED ONES)  -----------------------------------------------------
    //TODO: getIntent and retrieve enemyGrid from intent
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enemy_screen);
        initButtonArray();
        hideBtnText();

        enemyGrid = (int[][]) getIntent().getSerializableExtra("enemyGrid");
        enemyShipList = (EnemyShip[]) getIntent().getSerializableExtra("enemyShipList");

        setGridColors();
        if (checkGameStatus()) {
            TextView tv = (TextView) findViewById(R.id.message);
            tv.setText("CONGRATILATIONS!!! YOU WON! WOOHOO!!!");
        }
    }

    private void initButtonArray() {
        enemyButtonArray[0][0] = (Button) findViewById(R.id.btn00);
        enemyButtonArray[0][1] = (Button) findViewById(R.id.btn01);
        enemyButtonArray[0][2] = (Button) findViewById(R.id.btn02);
        enemyButtonArray[0][3] = (Button) findViewById(R.id.btn03);
        enemyButtonArray[0][4] = (Button) findViewById(R.id.btn04);
        enemyButtonArray[0][5] = (Button) findViewById(R.id.btn05);
        enemyButtonArray[0][6] = (Button) findViewById(R.id.btn06);
        enemyButtonArray[0][7] = (Button) findViewById(R.id.btn07);
        enemyButtonArray[0][8] = (Button) findViewById(R.id.btn08);
        enemyButtonArray[0][9] = (Button) findViewById(R.id.btn09);
        enemyButtonArray[1][0] = (Button) findViewById(R.id.btn10);
        enemyButtonArray[1][1] = (Button) findViewById(R.id.btn11);
        enemyButtonArray[1][2] = (Button) findViewById(R.id.btn12);
        enemyButtonArray[1][3] = (Button) findViewById(R.id.btn13);
        enemyButtonArray[1][4] = (Button) findViewById(R.id.btn14);
        enemyButtonArray[1][5] = (Button) findViewById(R.id.btn15);
        enemyButtonArray[1][6] = (Button) findViewById(R.id.btn16);
        enemyButtonArray[1][7] = (Button) findViewById(R.id.btn17);
        enemyButtonArray[1][8] = (Button) findViewById(R.id.btn18);
        enemyButtonArray[1][9] = (Button) findViewById(R.id.btn19);
        enemyButtonArray[2][0] = (Button) findViewById(R.id.btn20);
        enemyButtonArray[2][1] = (Button) findViewById(R.id.btn21);
        enemyButtonArray[2][2] = (Button) findViewById(R.id.btn22);
        enemyButtonArray[2][3] = (Button) findViewById(R.id.btn23);
        enemyButtonArray[2][4] = (Button) findViewById(R.id.btn24);
        enemyButtonArray[2][5] = (Button) findViewById(R.id.btn25);
        enemyButtonArray[2][6] = (Button) findViewById(R.id.btn26);
        enemyButtonArray[2][7] = (Button) findViewById(R.id.btn27);
        enemyButtonArray[2][8] = (Button) findViewById(R.id.btn28);
        enemyButtonArray[2][9] = (Button) findViewById(R.id.btn29);
        enemyButtonArray[3][0] = (Button) findViewById(R.id.btn30);
        enemyButtonArray[3][1] = (Button) findViewById(R.id.btn31);
        enemyButtonArray[3][2] = (Button) findViewById(R.id.btn32);
        enemyButtonArray[3][3] = (Button) findViewById(R.id.btn33);
        enemyButtonArray[3][4] = (Button) findViewById(R.id.btn34);
        enemyButtonArray[3][5] = (Button) findViewById(R.id.btn35);
        enemyButtonArray[3][6] = (Button) findViewById(R.id.btn36);
        enemyButtonArray[3][7] = (Button) findViewById(R.id.btn37);
        enemyButtonArray[3][8] = (Button) findViewById(R.id.btn38);
        enemyButtonArray[3][9] = (Button) findViewById(R.id.btn39);
        enemyButtonArray[4][0] = (Button) findViewById(R.id.btn40);
        enemyButtonArray[4][1] = (Button) findViewById(R.id.btn41);
        enemyButtonArray[4][2] = (Button) findViewById(R.id.btn42);
        enemyButtonArray[4][3] = (Button) findViewById(R.id.btn43);
        enemyButtonArray[4][4] = (Button) findViewById(R.id.btn44);
        enemyButtonArray[4][5] = (Button) findViewById(R.id.btn45);
        enemyButtonArray[4][6] = (Button) findViewById(R.id.btn46);
        enemyButtonArray[4][7] = (Button) findViewById(R.id.btn47);
        enemyButtonArray[4][8] = (Button) findViewById(R.id.btn48);
        enemyButtonArray[4][9] = (Button) findViewById(R.id.btn49);
        enemyButtonArray[5][0] = (Button) findViewById(R.id.btn50);
        enemyButtonArray[5][1] = (Button) findViewById(R.id.btn51);
        enemyButtonArray[5][2] = (Button) findViewById(R.id.btn52);
        enemyButtonArray[5][3] = (Button) findViewById(R.id.btn53);
        enemyButtonArray[5][4] = (Button) findViewById(R.id.btn54);
        enemyButtonArray[5][5] = (Button) findViewById(R.id.btn55);
        enemyButtonArray[5][6] = (Button) findViewById(R.id.btn56);
        enemyButtonArray[5][7] = (Button) findViewById(R.id.btn57);
        enemyButtonArray[5][8] = (Button) findViewById(R.id.btn58);
        enemyButtonArray[5][9] = (Button) findViewById(R.id.btn59);
        enemyButtonArray[6][0] = (Button) findViewById(R.id.btn60);
        enemyButtonArray[6][1] = (Button) findViewById(R.id.btn61);
        enemyButtonArray[6][2] = (Button) findViewById(R.id.btn62);
        enemyButtonArray[6][3] = (Button) findViewById(R.id.btn63);
        enemyButtonArray[6][4] = (Button) findViewById(R.id.btn64);
        enemyButtonArray[6][5] = (Button) findViewById(R.id.btn65);
        enemyButtonArray[6][6] = (Button) findViewById(R.id.btn66);
        enemyButtonArray[6][7] = (Button) findViewById(R.id.btn67);
        enemyButtonArray[6][8] = (Button) findViewById(R.id.btn68);
        enemyButtonArray[6][9] = (Button) findViewById(R.id.btn69);
        enemyButtonArray[7][0] = (Button) findViewById(R.id.btn70);
        enemyButtonArray[7][1] = (Button) findViewById(R.id.btn71);
        enemyButtonArray[7][2] = (Button) findViewById(R.id.btn72);
        enemyButtonArray[7][3] = (Button) findViewById(R.id.btn73);
        enemyButtonArray[7][4] = (Button) findViewById(R.id.btn74);
        enemyButtonArray[7][5] = (Button) findViewById(R.id.btn75);
        enemyButtonArray[7][6] = (Button) findViewById(R.id.btn76);
        enemyButtonArray[7][7] = (Button) findViewById(R.id.btn77);
        enemyButtonArray[7][8] = (Button) findViewById(R.id.btn78);
        enemyButtonArray[7][9] = (Button) findViewById(R.id.btn79);
        enemyButtonArray[8][0] = (Button) findViewById(R.id.btn80);
        enemyButtonArray[8][1] = (Button) findViewById(R.id.btn81);
        enemyButtonArray[8][2] = (Button) findViewById(R.id.btn82);
        enemyButtonArray[8][3] = (Button) findViewById(R.id.btn83);
        enemyButtonArray[8][4] = (Button) findViewById(R.id.btn84);
        enemyButtonArray[8][5] = (Button) findViewById(R.id.btn85);
        enemyButtonArray[8][6] = (Button) findViewById(R.id.btn86);
        enemyButtonArray[8][7] = (Button) findViewById(R.id.btn87);
        enemyButtonArray[8][8] = (Button) findViewById(R.id.btn88);
        enemyButtonArray[8][9] = (Button) findViewById(R.id.btn89);
        enemyButtonArray[9][0] = (Button) findViewById(R.id.btn90);
        enemyButtonArray[9][1] = (Button) findViewById(R.id.btn91);
        enemyButtonArray[9][2] = (Button) findViewById(R.id.btn92);
        enemyButtonArray[9][3] = (Button) findViewById(R.id.btn93);
        enemyButtonArray[9][4] = (Button) findViewById(R.id.btn94);
        enemyButtonArray[9][5] = (Button) findViewById(R.id.btn95);
        enemyButtonArray[9][6] = (Button) findViewById(R.id.btn96);
        enemyButtonArray[9][7] = (Button) findViewById(R.id.btn97);
        enemyButtonArray[9][8] = (Button) findViewById(R.id.btn98);
        enemyButtonArray[9][9] = (Button) findViewById(R.id.btn99);
    }

    private void hideBtnText() {
        for (int i = 0; i < UserGrid.NUM_ROW; i++) {
            for (int j = 0; j < UserGrid.NUM_COLUMN; j++) {
                enemyButtonArray[i][j].setText("");
            }
        }
    }

    private void returnToUserGrid() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO: return to calling activity
                Intent intent = new Intent(EnemyGrid.this, UserGrid.class);
                intent.putExtra("enemyGrid", enemyGrid);
                intent.putExtra("enemyShipList", enemyShipList);
                setResult(RESULT_OK, intent);
                finish();
            }
        }, 2500);
    }

    // ---  OTHER MEMBER FUNCTIONS  ----------------------------------------------------------------
    public void gridCellPressed(View v) {
        String[] tag = v.getTag().toString().split("(?<=\\d)(?=\\d)");
        int i = Integer.parseInt(tag[0]);
        int j = Integer.parseInt(tag[1]);

        if (enemyGrid[i][j] == -1 || enemyGrid[i][j] == -5) {
            displayFeedback("Luck next time");
            enemyGrid[i][j] = 0;
            singleColorChange(v, i, j);
            returnToUserGrid();
        }

        else if (enemyGrid[i][j] == 1) {
            String name = enemyShipList[0].getName();
            if (enemyShipList[0].getSize() == 1) {
                enemyShipList[0].hitShip();
                if (enemyShipList[0].isDestroyed()) {
                    displayFeedback("You sank enemy's " + name);
                } else {
                    displayFeedback("Nice shot!");
                }
            } else {
                enemyShipList[0].hitShip();
                displayFeedback("Nice shot!");
            }
            enemyGrid[i][j] = -10;
            singleColorChange(v, i, j);
        }

        else if (enemyGrid[i][j] == 2) {
            String name = enemyShipList[1].getName();
            if (enemyShipList[1].getSize() == 1) {
                enemyShipList[1].hitShip();
                if (enemyShipList[1].isDestroyed()) {
                    displayFeedback("You sank enemy's " + name);
                } else {
                    displayFeedback("Nice shot!");
                }
            } else {
                enemyShipList[1].hitShip();
                displayFeedback("Nice shot!");
            }
            enemyGrid[i][j] = -10;
            singleColorChange(v, i, j);
        }

        else if (enemyGrid[i][j] == 3) {
            String name = enemyShipList[2].getName();
            if (enemyShipList[2].getSize() == 1) {
                enemyShipList[2].hitShip();
                if (enemyShipList[2].isDestroyed()) {
                    displayFeedback("You sank enemy's " + name);
                } else {
                    displayFeedback("Nice shot!");
                }
            } else {
                enemyShipList[2].hitShip();
                displayFeedback("Nice shot!");
            }
            enemyGrid[i][j] = -10;
            singleColorChange(v, i, j);
        }

        else if (enemyGrid[i][j] == 4) {
            String name = enemyShipList[3].getName();
            if (enemyShipList[3].getSize() == 1) {
                enemyShipList[3].hitShip();
                if (enemyShipList[3].isDestroyed()) {
                    displayFeedback("You sank enemy's " + name);
                } else {
                    displayFeedback("Nice shot!");
                }
            } else {
                enemyShipList[3].hitShip();
                displayFeedback("Nice shot!");
            }
            enemyGrid[i][j] = -10;
            singleColorChange(v, i, j);
        }

        else if (enemyGrid[i][j] == 5) {
            String name = enemyShipList[4].getName();
            if (enemyShipList[4].getSize() == 1) {
                enemyShipList[4].hitShip();
                if (enemyShipList[4].isDestroyed()) {
                    displayFeedback("You sank enemy's " + name);
                } else {
                    displayFeedback("Nice shot!");
                }
            } else {
                enemyShipList[4].hitShip();
                displayFeedback("Nice shot!");
            }
            enemyGrid[i][j] = -10;
            singleColorChange(v, i, j);
        }

        if (checkGameStatus()) {
            TextView tv = (TextView) findViewById(R.id.message);
            tv.setText("CONGRATILATIONS!!! YOU WON! WOOHOO!!!");
        }
    }

    private void singleColorChange(View v, int i, int j) {
        if (enemyGrid[i][j] == 0)
            v.setBackgroundResource(R.drawable.miss_cell);

        if (enemyGrid[i][j] == -10)
            v.setBackgroundResource(R.drawable.hit_cell);
    }

    private void displayFeedback(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    private void setGridColors() {
        for (int i = 0; i < UserGrid.NUM_ROW; i++) {
            for (int j = 0; j < UserGrid.NUM_COLUMN; j++) {

                if (enemyGrid[i][j] == 0) {
                    enemyButtonArray[i][j].setBackgroundResource(R.drawable.miss_cell);
                }

                // TEST PURPOSE
                else if (enemyGrid[i][j] > 0) {
                    enemyButtonArray[i][j].setBackgroundResource(R.drawable.test_place_ships);
                }

                // TEST PURPOSE
                /*if (enemyGrid[i][j] == -5) {
                    enemyButtonArray[i][j].setBackgroundResource(R.drawable.test_not_available_space);
                }*/

                else if (enemyGrid[i][j] == -1 || enemyGrid[i][j] == -5) {
                    enemyButtonArray[i][j].setBackgroundResource(R.drawable.init_cell);
                }

                else if (enemyGrid[i][j] == -10) {
                    enemyButtonArray[i][j].setBackgroundResource(R.drawable.hit_cell);
                }

            }
        }
    }

    public void onExitClick(View view){
        setResult(RESULT_CANCELED);
        finish();
    }

    private boolean checkGameStatus() {
        int count = 0;
        for (EnemyShip ship : enemyShipList) {
            if (ship.isDestroyed()) {
                count += 1;
            }
        }

        if (count == 5) { return true; }
        else { return false; }
    }
}

