package com.example.chicagotour;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chicagotour.fences.FenceData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {

    TextView name;
    TextView location;
    TextView description;
    ImageView buildingImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.buildingName);
        location = findViewById(R.id.locTxt);
        description = findViewById(R.id.detailsTxt);
        buildingImg = findViewById(R.id.buildingImg);

        if(getIntent().hasExtra("DATA")){

            FenceData fData = (FenceData)getIntent().getSerializableExtra("DATA");
            name.setText(fData.getId());
            location.setText(fData.getAddress());
            description.setText(fData.getDescription());

            Picasso.get().load(fData.getImage()).into(buildingImg, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError(Exception e) {

                }
            });

        }

    }
}