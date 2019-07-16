package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        Sandwich sandwich= new Sandwich();

        try {
            JSONObject sandwichDetails = new JSONObject(json);

            JSONObject name = sandwichDetails.getJSONObject("name");
            sandwich.setMainName(name.getString("mainName"));
            Log.e("main name", name.getString("mainName"));
//**********************************************************************************************
            // put alsoKnownAS in a list then add this list to sandwich class
            // after i have get getJSONArray
            ArrayList<String> alsoKnownAsNames = new ArrayList<String>();
            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            for(int e=0;e<alsoKnownAsArray.length();e++)
                alsoKnownAsNames.add(alsoKnownAsArray.getString(e));

            sandwich.setAlsoKnownAs(alsoKnownAsNames);// add the list

//**********************************************************************************************

            sandwich.setPlaceOfOrigin(sandwichDetails.getString("placeOfOrigin"));

            sandwich.setDescription(sandwichDetails.getString("description"));

            sandwich.setImage(sandwichDetails.getString("image"));

//**********************************************************************************************
            // ingredients
            ArrayList <String> ingredientsList = new ArrayList<>();
            JSONArray ingredientsArray = sandwichDetails.getJSONArray("ingredients");
            for(int e=0;e<ingredientsArray.length();e++)
                ingredientsList.add(ingredientsArray.getString(e));

            sandwich.setIngredients(ingredientsList);
//**********************************************************************************************



        }catch (JSONException e)
        {
            e.printStackTrace();
        }

        return sandwich;
    }
}
