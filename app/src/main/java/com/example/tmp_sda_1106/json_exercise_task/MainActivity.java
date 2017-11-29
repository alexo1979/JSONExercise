package com.example.tmp_sda_1106.json_exercise_task;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




public class MainActivity extends AppCompatActivity {

    JSONArray jsonArray;
    JSONObject jsonbject;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        textView.setMovementMethod(new ScrollingMovementMethod());

        String coloursJSONstring = "{\n" +
                "  \"colors\": [\n" +
                "    {\n" +
                "      \"color\": \"black\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [255,255,255,1],\n" +
                "        \"hex\": \"#000\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"white\",\n" +
                "      \"category\": \"value\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [0,0,0,1],\n" +
                "        \"hex\": \"#FFF\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"red\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [255,0,0,1],\n" +
                "        \"hex\": \"#FF0\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"blue\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [0,0,255,1],\n" +
                "        \"hex\": \"#00F\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"yellow\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"primary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [255,255,0,1],\n" +
                "        \"hex\": \"#FF0\"\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"color\": \"green\",\n" +
                "      \"category\": \"hue\",\n" +
                "      \"type\": \"secondary\",\n" +
                "      \"code\": {\n" +
                "        \"rgba\": [0,255,0,1],\n" +
                "        \"hex\": \"#0F0\"\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        try {
            jsonbject = new JSONObject(coloursJSONstring);

            jsonArray = jsonbject.getJSONArray("colors");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void count(View view) throws JSONException {

        int countGreen = 0;

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            JSONObject code = object.getJSONObject("code");

            String rgba = code.getString("rgba");
            String[] values = rgba.split(",");

            String green = values[1];

            if (green.equals("255"))
                countGreen++;
        }

        String text =("The number of colors having green component equal to 255 is \n" + countGreen);
        textView.setText(text);

    }




    public void list (View view) throws JSONException {
        String colours = "";

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);

            JSONObject code = object.getJSONObject("code");
            String rgba = code.getString("rgba");
            String[] values = rgba.split(",");

            String green = values[1];

            if (green.equals("255")) {
                colours = colours.concat(object.getString("color"));
                colours = colours.concat(", ");
            }
        }

        String text =("The colors having green component equal to 255 are \n" + colours );

        textView.setText(text);

    }




    public void modify (View view) throws JSONException {

        JSONObject orange = new JSONObject();
        orange.put("color", "orange");
        orange.put("category", "hue");
        orange.put("type", "secondary");

        JSONObject code = new JSONObject();
        code.put("rgba", "[255,165,0,1]");
        code.put("hex", "#FA0");

        orange.put("code", code);

        jsonArray.put(orange);

        // textView.setText(jsonArray.toString());

        String text = "";

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            text = text.concat(object.toString());
            text = text.concat("\n\n");
        }


        textView.setText(text);

    }


}
