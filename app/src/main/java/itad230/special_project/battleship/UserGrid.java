package itad230.special_project.battleship;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.*;
import android.view.View;
import java.util.Random;

public class UserGrid extends AppCompatActivity {

    private static final String TAG = UserGrid.class.getSimpleName();

    protected static final int NUM_ROW = 10;
    protected static final int NUM_COLUMN = 10;
    protected static final int NUM_SHIPS = 5;

    private int[][] userGrid = new int[NUM_ROW][NUM_COLUMN];
    private int[][] enemyGrid = new int[NUM_ROW][NUM_COLUMN];

    private final Button[][] userButtonArray = new Button[NUM_ROW][NUM_COLUMN];

    private UserShip[] userShipList = new UserShip[NUM_SHIPS];
    private EnemyShip[] enemyShipList = new EnemyShip[NUM_SHIPS];

    public final static int PLAY_CODE = 1;
    public static boolean again = false;
    public static boolean duringPlay = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_grid);
        initButtonArray();
        hideBtnText();

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("userGrid")) {
                userGrid = (int[][]) savedInstanceState.getSerializable("userGrid");
            } else {
                initUserGrid();
            }

            if (savedInstanceState.containsKey("enemyGrid")) {
                enemyGrid = (int[][]) savedInstanceState.getSerializable("enemyGrid");
            } else {
                initEnemyGrid();
                placeEnemyShipsOnGrid();
            }

            if (savedInstanceState.containsKey("userShipList")) {
                userShipList = (UserShip[]) savedInstanceState.getSerializable("userShipList");
            } else {
                initUserShips();
            }

            if (savedInstanceState.containsKey("enemyShipList")) {
                enemyShipList = (EnemyShip[]) savedInstanceState.getSerializable("enemyShipList");
            } else {
                initEnemyShips();
            }
        } else {
            initUserGrid();
            initUserShips();
            initEnemyGrid();
            initEnemyShips();
            placeEnemyShipsOnGrid();
        }
        hideBtnText();
        setGridColors();
    }

    public void onExitClick(View view){
        finish();
    }

    //Here is where it calls Enemy Screen the first time
    public void onPlayGameClick(View view){
        if(checkShipPlacement())
        {
            ImageButton placeRandomB = (ImageButton) findViewById(R.id.placeRandomB);
            placeRandomB.setVisibility(View.GONE);
            ImageButton resetB = (ImageButton) findViewById(R.id.resetB);
            resetB.setVisibility(View.GONE);
            ImageButton playB = (ImageButton) findViewById(R.id.playB);
            playB.setVisibility(View.GONE);

            //TODO: add enemy grid to intent
            Intent intent = new Intent(this, EnemyGrid.class);
            intent.putExtra("enemyGrid", enemyGrid);
            intent.putExtra("enemyShipList", enemyShipList);
            startActivityForResult(intent, PLAY_CODE);
        }
        else
        {
            displayFeedback("Please place all your ships first");
        }

    }

    private boolean checkShipPlacement() {
        ImageButton airCButton = (ImageButton) findViewById(R.id.airCB);
        ImageButton battleButton = (ImageButton) findViewById(R.id.battleB);
        ImageButton subButton = (ImageButton) findViewById(R.id.subB);
        ImageButton cruiserButton = (ImageButton) findViewById(R.id.cruiserB);
        ImageButton patrolButton = (ImageButton) findViewById(R.id.patrolB);
        if(airCButton.isShown() && battleButton.isShown() && subButton.isShown() && cruiserButton.isShown() && patrolButton.isShown())
        {
            return false;
        }
        else
        {
            again = false;
            return true;
        }
    }

    public void computerHit() {
        // The Computer is still dumb,
        int rnum1;
        int rnum2;
        Random rand = new Random();
        do {
            rnum1 = rand.nextInt(10);
            rnum2 = rand.nextInt(10);
        } while (userGrid[rnum1][rnum2] == -10 || userGrid[rnum1][rnum2] == 0);

        Button btn = userButtonArray[rnum1][rnum2];

        //Log.d(TAG, "----Computer's move-----");
        //Log.d(TAG, "userGrid[" + rnum1 + "][" + rnum2 + "] = " + userGrid[rnum1][rnum2]);
        if (userGrid[rnum1][rnum2] == -1 || userGrid[rnum1][rnum2] == -5) {
            displayFeedback("Computer has missed!");
            userGrid[rnum1][rnum2] = 0;
            again = false;
            singleColorChange(btn, rnum1, rnum2);
        }
        if (userGrid[rnum1][rnum2] >= 2 && userGrid[rnum1][rnum2] <= 5) {
            userGrid[rnum1][rnum2] = -10;
            displayFeedback("Computer has hit!");
            again = true;
            singleColorChange(btn, rnum1, rnum2);
            checkShips();
        }

        //singleColorChange(btn, rnum1, rnum2);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putSerializable("userGrid", userGrid);
        savedInstanceState.putSerializable("enemyGrid", enemyGrid);
        savedInstanceState.putSerializable("userShipList", userShipList);
        savedInstanceState.putSerializable("enemyShipList", enemyShipList);
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreIntanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        TextView tv = (TextView) findViewById(R.id.header);
        tv.setText("Your grid");

        userGrid = (int[][]) savedInstanceState.getSerializable("userGrid");
        userShipList = (UserShip[]) savedInstanceState.getSerializable("userShipList");
        enemyGrid = (int[][]) savedInstanceState.getSerializable("enemyGrid");
        enemyShipList = (EnemyShip[]) savedInstanceState.getSerializable("enemyShipList");

        hideBtnText();
        setGridColors();
        computerHit();


        if(checkShips()) // Call this to see if Computer won
        {
            displayFeedback("Computer Won");
        }
        else
        {
            timeOutActivityCall(); // Go back to Enemy Screen if computer didn't win
        }
    }

    private boolean checkShips() {
        boolean gameOver = false;
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COLUMN; j++) {
                if (userGrid[i][j] == 2) {
                    gameOver = false;
                    break;
                }
                if (userGrid[i][j] == 3) {
                    gameOver = false;
                    break;
                }
                if (userGrid[i][j] == 4) {
                    gameOver = false;
                    break;
                }
                if (userGrid[i][j] == 5) {
                    gameOver = false;
                    break;
                }
            }
        }
        return gameOver;
    }

    private void initUserShips() {
        userShipList[0] = new UserShip("Aircraft carrier", 5);
        userShipList[1] = new UserShip("Battleship", 4);
        userShipList[2] = new UserShip("Submarine", 3);
        userShipList[3] = new UserShip("Cruiser", 3);
        userShipList[4] = new UserShip("Patrol", 2);
    }

    private void initEnemyShips() {
        enemyShipList[0] = new EnemyShip("Aircraft carrier", 5);    // id = 1
        enemyShipList[1] = new EnemyShip("Battleship", 4);          // id = 2
        enemyShipList[2] = new EnemyShip("Submarine", 3);           // id = 3
        enemyShipList[3] = new EnemyShip("Cruiser", 3);             // id = 4
        enemyShipList[4] = new EnemyShip("Patrol", 2);              // id = 5
        duringPlay = false;
    }

    private void initUserGrid() {
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COLUMN; j++) {
                userGrid[i][j] = -1;
            }
        }
    }

    private void initEnemyGrid() {
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COLUMN; j++) {
                enemyGrid[i][j] = -1;
            }
        }
    }

    private void placeEnemyShipsOnGrid() {
        for (int i = 0; i < enemyShipList.length; i++) {
            enemyShipList[i].placeShip(enemyGrid, (i+1));
        }
    }

    private void initButtonArray() {
        userButtonArray[0][0] = (Button) findViewById(R.id.btn00);
        userButtonArray[0][1] = (Button) findViewById(R.id.btn01);
        userButtonArray[0][2] = (Button) findViewById(R.id.btn02);
        userButtonArray[0][3] = (Button) findViewById(R.id.btn03);
        userButtonArray[0][4] = (Button) findViewById(R.id.btn04);
        userButtonArray[0][5] = (Button) findViewById(R.id.btn05);
        userButtonArray[0][6] = (Button) findViewById(R.id.btn06);
        userButtonArray[0][7] = (Button) findViewById(R.id.btn07);
        userButtonArray[0][8] = (Button) findViewById(R.id.btn08);
        userButtonArray[0][9] = (Button) findViewById(R.id.btn09);
        userButtonArray[1][0] = (Button) findViewById(R.id.btn10);
        userButtonArray[1][1] = (Button) findViewById(R.id.btn11);
        userButtonArray[1][2] = (Button) findViewById(R.id.btn12);
        userButtonArray[1][3] = (Button) findViewById(R.id.btn13);
        userButtonArray[1][4] = (Button) findViewById(R.id.btn14);
        userButtonArray[1][5] = (Button) findViewById(R.id.btn15);
        userButtonArray[1][6] = (Button) findViewById(R.id.btn16);
        userButtonArray[1][7] = (Button) findViewById(R.id.btn17);
        userButtonArray[1][8] = (Button) findViewById(R.id.btn18);
        userButtonArray[1][9] = (Button) findViewById(R.id.btn19);
        userButtonArray[2][0] = (Button) findViewById(R.id.btn20);
        userButtonArray[2][1] = (Button) findViewById(R.id.btn21);
        userButtonArray[2][2] = (Button) findViewById(R.id.btn22);
        userButtonArray[2][3] = (Button) findViewById(R.id.btn23);
        userButtonArray[2][4] = (Button) findViewById(R.id.btn24);
        userButtonArray[2][5] = (Button) findViewById(R.id.btn25);
        userButtonArray[2][6] = (Button) findViewById(R.id.btn26);
        userButtonArray[2][7] = (Button) findViewById(R.id.btn27);
        userButtonArray[2][8] = (Button) findViewById(R.id.btn28);
        userButtonArray[2][9] = (Button) findViewById(R.id.btn29);
        userButtonArray[3][0] = (Button) findViewById(R.id.btn30);
        userButtonArray[3][1] = (Button) findViewById(R.id.btn31);
        userButtonArray[3][2] = (Button) findViewById(R.id.btn32);
        userButtonArray[3][3] = (Button) findViewById(R.id.btn33);
        userButtonArray[3][4] = (Button) findViewById(R.id.btn34);
        userButtonArray[3][5] = (Button) findViewById(R.id.btn35);
        userButtonArray[3][6] = (Button) findViewById(R.id.btn36);
        userButtonArray[3][7] = (Button) findViewById(R.id.btn37);
        userButtonArray[3][8] = (Button) findViewById(R.id.btn38);
        userButtonArray[3][9] = (Button) findViewById(R.id.btn39);
        userButtonArray[4][0] = (Button) findViewById(R.id.btn40);
        userButtonArray[4][1] = (Button) findViewById(R.id.btn41);
        userButtonArray[4][2] = (Button) findViewById(R.id.btn42);
        userButtonArray[4][3] = (Button) findViewById(R.id.btn43);
        userButtonArray[4][4] = (Button) findViewById(R.id.btn44);
        userButtonArray[4][5] = (Button) findViewById(R.id.btn45);
        userButtonArray[4][6] = (Button) findViewById(R.id.btn46);
        userButtonArray[4][7] = (Button) findViewById(R.id.btn47);
        userButtonArray[4][8] = (Button) findViewById(R.id.btn48);
        userButtonArray[4][9] = (Button) findViewById(R.id.btn49);
        userButtonArray[5][0] = (Button) findViewById(R.id.btn50);
        userButtonArray[5][1] = (Button) findViewById(R.id.btn51);
        userButtonArray[5][2] = (Button) findViewById(R.id.btn52);
        userButtonArray[5][3] = (Button) findViewById(R.id.btn53);
        userButtonArray[5][4] = (Button) findViewById(R.id.btn54);
        userButtonArray[5][5] = (Button) findViewById(R.id.btn55);
        userButtonArray[5][6] = (Button) findViewById(R.id.btn56);
        userButtonArray[5][7] = (Button) findViewById(R.id.btn57);
        userButtonArray[5][8] = (Button) findViewById(R.id.btn58);
        userButtonArray[5][9] = (Button) findViewById(R.id.btn59);
        userButtonArray[6][0] = (Button) findViewById(R.id.btn60);
        userButtonArray[6][1] = (Button) findViewById(R.id.btn61);
        userButtonArray[6][2] = (Button) findViewById(R.id.btn62);
        userButtonArray[6][3] = (Button) findViewById(R.id.btn63);
        userButtonArray[6][4] = (Button) findViewById(R.id.btn64);
        userButtonArray[6][5] = (Button) findViewById(R.id.btn65);
        userButtonArray[6][6] = (Button) findViewById(R.id.btn66);
        userButtonArray[6][7] = (Button) findViewById(R.id.btn67);
        userButtonArray[6][8] = (Button) findViewById(R.id.btn68);
        userButtonArray[6][9] = (Button) findViewById(R.id.btn69);
        userButtonArray[7][0] = (Button) findViewById(R.id.btn70);
        userButtonArray[7][1] = (Button) findViewById(R.id.btn71);
        userButtonArray[7][2] = (Button) findViewById(R.id.btn72);
        userButtonArray[7][3] = (Button) findViewById(R.id.btn73);
        userButtonArray[7][4] = (Button) findViewById(R.id.btn74);
        userButtonArray[7][5] = (Button) findViewById(R.id.btn75);
        userButtonArray[7][6] = (Button) findViewById(R.id.btn76);
        userButtonArray[7][7] = (Button) findViewById(R.id.btn77);
        userButtonArray[7][8] = (Button) findViewById(R.id.btn78);
        userButtonArray[7][9] = (Button) findViewById(R.id.btn79);
        userButtonArray[8][0] = (Button) findViewById(R.id.btn80);
        userButtonArray[8][1] = (Button) findViewById(R.id.btn81);
        userButtonArray[8][2] = (Button) findViewById(R.id.btn82);
        userButtonArray[8][3] = (Button) findViewById(R.id.btn83);
        userButtonArray[8][4] = (Button) findViewById(R.id.btn84);
        userButtonArray[8][5] = (Button) findViewById(R.id.btn85);
        userButtonArray[8][6] = (Button) findViewById(R.id.btn86);
        userButtonArray[8][7] = (Button) findViewById(R.id.btn87);
        userButtonArray[8][8] = (Button) findViewById(R.id.btn88);
        userButtonArray[8][9] = (Button) findViewById(R.id.btn89);
        userButtonArray[9][0] = (Button) findViewById(R.id.btn90);
        userButtonArray[9][1] = (Button) findViewById(R.id.btn91);
        userButtonArray[9][2] = (Button) findViewById(R.id.btn92);
        userButtonArray[9][3] = (Button) findViewById(R.id.btn93);
        userButtonArray[9][4] = (Button) findViewById(R.id.btn94);
        userButtonArray[9][5] = (Button) findViewById(R.id.btn95);
        userButtonArray[9][6] = (Button) findViewById(R.id.btn96);
        userButtonArray[9][7] = (Button) findViewById(R.id.btn97);
        userButtonArray[9][8] = (Button) findViewById(R.id.btn98);
        userButtonArray[9][9] = (Button) findViewById(R.id.btn99);
    }

    public void gridCellPressed(View v) {
        String[] tag = v.getTag().toString().split("(?<=\\d)(?=\\d)");
        int i = Integer.parseInt(tag[0]);
        int j = Integer.parseInt(tag[1]);

        if(!duringPlay) {
            int shipOr;
            int newShipOr;

            if(userGrid[i][j] == 1) {
                shipOr = userShipList[0].getStartOr();
                userShipList[0].tapToRotate(userGrid, 1);
                newShipOr = userShipList[0].getStartOr();
                if(shipOr == newShipOr)
                {
                    displayFeedback("Ship Cannot Rotate");
                }
            }

            else if(userGrid[i][j] == 2) {
                shipOr = userShipList[1].getStartOr();
                userShipList[1].tapToRotate(userGrid, 2);
                newShipOr = userShipList[1].getStartOr();
                if(shipOr == newShipOr)
                {
                    displayFeedback("Ship Cannot Rotate");
                }
            }

            else if(userGrid[i][j] == 3) {
                shipOr = userShipList[2].getStartOr();
                userShipList[2].tapToRotate(userGrid, 3);
                newShipOr = userShipList[2].getStartOr();
                if(shipOr == newShipOr)
                {
                    displayFeedback("Ship Cannot Rotate");
                }
            }

            else if(userGrid[i][j] == 4) {
                shipOr = userShipList[3].getStartOr();
                userShipList[3].tapToRotate(userGrid, 4);
                newShipOr = userShipList[3].getStartOr();
                if(shipOr == newShipOr)
                {
                    displayFeedback("Ship Cannot Rotate");
                }
            }

            else if(userGrid[i][j] == 5) {
                shipOr = userShipList[4].getStartOr();
                userShipList[4].tapToRotate(userGrid, 5);
                newShipOr = userShipList[4].getStartOr();
                if(shipOr == newShipOr)
                {
                    displayFeedback("Ship Cannot Rotate");
                }
            }
            setGridColors();
        }

        if(userShipList[0].getBoolean())
        {
            placeAirC(v, i, j);
        }
        else if(userShipList[1].getBoolean())
        {
            placeBattle(v, i, j);
        }
        else if(userShipList[2].getBoolean())
        {
            placeSub(v, i, j);
        }
        else if(userShipList[3].getBoolean())
        {
            placeCruiser(v, i, j);
        }
        else if(userShipList[4].getBoolean())
        {
            placePatrol(v, i, j);
        }
        else
        {
            if(!duringPlay)
            {
                if(userGrid[i][j] == 1)
                {
                    userShipList[0].tapToRotate(userGrid, 1);
                }
                else if(userGrid[i][j] == 2)
                {
                    userShipList[1].tapToRotate(userGrid, 2);
                }
                else if(userGrid[i][j] == 3)
                {
                    userShipList[2].tapToRotate(userGrid, 3);
                }
                else if(userGrid[i][j] == 4)
                {
                    userShipList[3].tapToRotate(userGrid, 4);
                }
                else if(userGrid[i][j] == 5)
                {
                    userShipList[4].tapToRotate(userGrid, 5);
                }
                setGridColors();
            }
        }
    }

    private void singleColorChange(View v, int i, int j) {
        if (userGrid[i][j] == 0 || userGrid[i][j] == -5 || userGrid[i][j] == -20 || userGrid[i][j] == -40 || userGrid[i][j] == -60 || userGrid[i][j] == -80|| userGrid[i][j] == -100)
            v.setBackgroundResource(R.drawable.miss_cell);

        if (userGrid[i][j] == -10) { v.setBackgroundResource(R.drawable.hit_cell); }
        //else { v.setBackgroundResource(R.drawable.miss_cell); }
    }

    //Gets called by onActivityResult
    private void timeOutActivityCall() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(UserGrid.this, EnemyGrid.class);
                intent.putExtra("enemyGrid", enemyGrid);
                intent.putExtra("enemyShipList", enemyShipList);
                startActivityForResult(intent, 1);
            }
        }, 4000);
    }

    //Here is where it goes when it is called/returns from Enemy Screen
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        TextView tv = (TextView) findViewById(R.id.header);
        tv.setText("Your grid");
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                enemyGrid = (int[][]) data.getSerializableExtra("enemyGrid");
                enemyShipList = (EnemyShip[]) data.getSerializableExtra("enemyShipList");

                computerHit();

                while(again)
                {
                   computerHit();
                }

                if(checkShips()) // Call this to see if Computer won
                {
                    displayFeedback("Computer Won");
                }
                else
                {
                    timeOutActivityCall(); // Go back to Enemy Screen if computer didn't win
                }
            } else if (resultCode == RESULT_CANCELED) {
                this.finish();
            } else { // Result was a failure
                displayFeedback("Returned from Testing Failing");
            }
        }
    }

    private void setGridColors() {
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COLUMN; j++) {

                if (userGrid[i][j] == 0) {
                    userButtonArray[i][j].setBackgroundResource(R.drawable.miss_cell);
                }

                if (userGrid[i][j] == 1 || userGrid[i][j] == 2 || userGrid[i][j] == 3 || userGrid[i][j] == 4 || userGrid[i][j] == 5) {
                    userButtonArray[i][j].setBackgroundResource(R.drawable.test_place_ships);
                }

                if (userGrid[i][j] == -1 || userGrid[i][j] == -5 || userGrid[i][j] == -20 || userGrid[i][j] == -40 || userGrid[i][j] == -60 || userGrid[i][j] == -80|| userGrid[i][j] == -100) {
                    userButtonArray[i][j].setBackgroundResource(R.drawable.init_cell);
                }

                if (userGrid[i][j] == -10) {
                    userButtonArray[i][j].setBackgroundResource(R.drawable.hit_cell);
                }

            }
        }
    }

    private void hideBtnText() {
        for (int i = 0; i < NUM_ROW; i++) {
            for (int j = 0; j < NUM_COLUMN; j++) {
                userButtonArray[i][j].setText("");
            }
        }
    }

    private void displayFeedback(String message) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }

    public void resetGrid(View view) {
        initUserGrid();
        initUserShips();
        initButtonArray();
        setGridColors();
        resetShips(view);

    }

    public void placeShipsRandom(View v) {
        resetGrid(v);
        for (int i = 0; i < userShipList.length; i++) {
            userShipList[i].placeShip(userGrid, (i+1));
        }
        ImageButton airCButton = (ImageButton) findViewById(R.id.airCB);
        airCButton.setClickable(false);
        airCButton.setVisibility(View.GONE);

        ImageButton battleButton = (ImageButton) findViewById(R.id.battleB);
        battleButton.setClickable(false);
        battleButton.setVisibility(View.GONE);

        ImageButton subButton = (ImageButton) findViewById(R.id.subB);
        subButton.setClickable(false);
        subButton.setVisibility(View.GONE);

        ImageButton cruiserButton = (ImageButton) findViewById(R.id.cruiserB);
        cruiserButton.setClickable(false);
        cruiserButton.setVisibility(View.GONE);

        ImageButton patrolButton = (ImageButton) findViewById(R.id.patrolB);
        patrolButton.setClickable(false);
        patrolButton.setVisibility(View.GONE);

        setGridColors();
    }

    public void resetShips(View v) {
        resetShipsBools(v);

        ImageButton airCButton = (ImageButton) findViewById(R.id.airCB);
        airCButton.setClickable(true);
        airCButton.setVisibility(View.VISIBLE);

        ImageButton battleButton = (ImageButton) findViewById(R.id.battleB);
        battleButton.setClickable(true);
        battleButton.setVisibility(View.VISIBLE);

        ImageButton subButton = (ImageButton) findViewById(R.id.subB);
        subButton.setClickable(true);
        subButton.setVisibility(View.VISIBLE);

        ImageButton cruiserButton = (ImageButton) findViewById(R.id.cruiserB);
        cruiserButton.setClickable(true);
        cruiserButton.setVisibility(View.VISIBLE);

        ImageButton patrolButton = (ImageButton) findViewById(R.id.patrolB);
        patrolButton.setClickable(true);
        patrolButton.setVisibility(View.VISIBLE);
    }

    public void resetShipsBools(View v){
        userShipList[0].setBoolean(false);
        userShipList[1].setBoolean(false);
        userShipList[2].setBoolean(false);
        userShipList[3].setBoolean(false);
        userShipList[4].setBoolean(false);
    }

    public void placeAirC(View v, int x, int y){
        resetShipsBools(v);
        userShipList[0].placeShipHere(userGrid, x, y, 1);
        if(userGrid[x][y] != 1)
        {
            displayFeedback("Ships cannot collide. Try another cell.");
        }
        else
        {
            setGridColors();
            userShipList[0].setBoolean(false);
            ImageButton airCButton = (ImageButton) findViewById(R.id.airCB);
            airCButton.setClickable(false);
            airCButton.setVisibility(View.GONE);
        }
    }

    public void placeBattle(View v, int x, int y){
        userShipList[1].placeShipHere(userGrid, x, y, 2);
        resetShipsBools(v);
        if(userGrid[x][y] != 2)
        {
            displayFeedback("Ships cannot collide. Try another cell.");
        }
        else
        {
            setGridColors();
            userShipList[1].setBoolean(false);
            ImageButton battleButton = (ImageButton) findViewById(R.id.battleB);
            battleButton.setClickable(false);
            battleButton.setVisibility(View.GONE);
        }
    }

    public void placeSub(View v, int x, int y){
        userShipList[2].placeShipHere(userGrid, x, y, 3);
        resetShipsBools(v);
        if(userGrid[x][y] != 3)
        {
            displayFeedback("Ships cannot collide. Try another cell.");
        }
        else
        {
            setGridColors();
            userShipList[2].setBoolean(false);
            ImageButton subButton = (ImageButton) findViewById(R.id.subB);
            subButton.setClickable(false);
            subButton.setVisibility(View.GONE);

        }
    }

    public void placeCruiser(View v, int x, int y){
        userShipList[3].placeShipHere(userGrid, x, y, 4);
        resetShipsBools(v);
        if(userGrid[x][y] != 4)
        {
            displayFeedback("Ships cannot collide. Try another cell.");
        }
        else
        {
            setGridColors();
            userShipList[3].setBoolean(false);
            ImageButton cruiserButton = (ImageButton) findViewById(R.id.cruiserB);
            cruiserButton.setClickable(false);
            cruiserButton.setVisibility(View.GONE);
        }
    }

    public void placePatrol(View v, int x, int y) {
        userShipList[4].placeShipHere(userGrid, x, y, 5);
        resetShipsBools(v);
        if(userGrid[x][y] != 5)
        {
            displayFeedback("Ships cannot collide. Try another cell.");
        }
        else
        {
            setGridColors();
            userShipList[4].setBoolean(false);
            ImageButton patrolButton = (ImageButton) findViewById(R.id.patrolB);
            patrolButton.setClickable(false);
            patrolButton.setVisibility(View.GONE);
        }
    }

    public void placeAirCBoolean(View v) {
        if(userShipList[0].getBoolean()) {
            userShipList[0].setBoolean(false);
        }
        else {
            userShipList[0].setBoolean(true);
        }
    }

    public void placeBattleBoolean(View v) {
        if(userShipList[1].getBoolean()) {
            userShipList[1].setBoolean(false);
        }
        else {
            userShipList[1].setBoolean(true);
        }
    }

    public void placeSubBoolean(View v) {
        if(userShipList[2].getBoolean()) {
            userShipList[2].setBoolean(false);
        }
        else {
            userShipList[2].setBoolean(true);
        }
    }

    public void placeCruiserBoolean(View v) {
        if(userShipList[3].getBoolean()) {
            userShipList[3].setBoolean(false);
        }
        else {
            userShipList[3].setBoolean(true);
        }
    }

    public void placePatrolBoolean(View v) {
        if(userShipList[4].getBoolean()) {
            userShipList[4].setBoolean(false);
        }
        else {
            userShipList[4].setBoolean(true);
        }
    }
}

