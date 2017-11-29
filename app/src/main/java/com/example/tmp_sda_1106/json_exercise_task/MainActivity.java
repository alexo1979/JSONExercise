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

        String coloursJSONstring = "{" +
                "       \"colors\":" + "[" +
                "{" +
                " \"color\" :   \"black\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"primary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [255,255,255,1]," +
                " \"hex\" : \"#000\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" : \"white´\"," +
                " \"category\" : \"value\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [0,0,0,1]," +
                " \"hex\" : \"#FFF\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" :   \"red\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"primary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [255,0,0,1]," +
                " \"hex\" : \"#FF0\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" :   \"blue\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"primary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [0,0,255,1]," +
                " \"hex\" : \"#00F\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" :   \"yellow\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"primary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [255,255,0,1]," +
                " \"hex\" : \"#FF0\"" +
                "}" +
                "}," +
                "{" +
                " \"color\" :   \"green\"," +
                " \"category\" : \"hue\"," +
                " \"type\" : \"secondary\"," +
                " \"code\" : " + "{" +
                " \"rgba\" : [0,255,0,1]," +
                " \"hex\" : \"#0F0\"" +
                "}" +
                "}" +
                "]" +
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
            text = text.concat("\n");
        }


        textView.setText(text);

    }


}
