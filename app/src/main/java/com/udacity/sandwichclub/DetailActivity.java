package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());


    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {

        TextView originalTextView = (TextView) findViewById(R.id.origin_tv);
        originalTextView.append(sandwich.getMainName());

        TextView alsoKnownAs = (TextView) findViewById(R.id.also_known_tv);
        for(int i=0;i<sandwich.getAlsoKnownAs().size();i++)
            alsoKnownAs.append(sandwich.getAlsoKnownAs().get(i));

        TextView placeOfOrigin = (TextView) findViewById(R.id.place_of_origin_tv);
            placeOfOrigin.append(sandwich.getPlaceOfOrigin());//

        TextView ingredients = (TextView) findViewById(R.id.ingredients_tv);
        for(int e=0;e<sandwich.getIngredients().size();e++)
        ingredients.append(sandwich.getIngredients().get(e)+" ");

        TextView description = (TextView) findViewById(R.id.description_tv);
            description.append(sandwich.getDescription());


    }
}
