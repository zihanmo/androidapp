package com.example.ypp;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class AddHelp extends AppCompatActivity {
    Button but1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_help);

        but1 = (Button) findViewById(R.id.button);



        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMainPage();
            }
        });
    }


    private void openMainPage() {
        Intent intent = new Intent(this, Activity.class);
        startActivity(intent);
    }
}
