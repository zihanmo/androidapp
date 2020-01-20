package com.example.ypp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;


public class Post extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pots_detail);

        ImageButton imageButton = findViewById(R.id.chattingquit);

        imageButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(Post.this, Main.class);
//                startActivity(intent);
                finish();
            }
        });
    }

}
