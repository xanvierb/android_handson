package nl.quintor.myhandsonapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main extends AppCompatActivity  {
    int buttonClicks = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button helloWorldButton = findViewById(R.id.button);
        helloWorldButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "Nu al " + buttonClicks++ +" keer geklikt.", Toast.LENGTH_SHORT).show();
                Intent myIntent = new Intent(Main.this, Main2Activity.class);
                //Optional parameters:
                //myIntent.putExtra("key", value);
                Main.this.startActivity(myIntent);
            }
        });
    }
}
