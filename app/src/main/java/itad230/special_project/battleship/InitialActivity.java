package itad230.special_project.battleship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InitialActivity extends AppCompatActivity {

    //   public final static int SHIP_SELECT_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_screen);
    }

    public void onPlayClick(View view){
        Intent intent = new Intent(this, UserGrid.class);
        startActivity(intent);
    }

    public void onExitClick(View view){
        finish();
    }
}