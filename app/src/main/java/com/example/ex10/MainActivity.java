package com.example.ex10;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Main activity class that handles button clicks and UI interactions.
 */
public class MainActivity extends AppCompatActivity {

    Button button1;
    Button button2;
    Button button3;
    Button button4;

    Intent si;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState The saved instance state bundle.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
    }

    /**
     * Creates the options menu.
     * @param menu The menu to be created.
     * @return true if the menu is created successfully.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 250, "credits");
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Handles menu item selections.
     * @param menuItem The selected menu item.
     * @return true if the menu item is handled successfully.
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem menuItem) {
        String st = menuItem.getTitle().toString();
        if (st.equals("credits")) {
            si = new Intent(this, second_activity.class);
            startActivity(si);
        }
        return super.onOptionsItemSelected(menuItem);
    }

    /**
     * Handles button2 click event to display a multi-choice color selection dialog.
     * @param view The view that triggered the event.
     */
    public void btn2OnClick(View view) {
        final String[] colorNames = {"Red", "Yellow", "Blue"};
        final int[] colors = {Color.RED, Color.YELLOW, Color.BLUE};
        final boolean[] selectedColors = new boolean[colorNames.length];

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Choose Background Colors");
        adb.setMultiChoiceItems(colorNames, selectedColors, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                selectedColors[which] = isChecked;
            }
        });

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int mixedColor = mixSelectedColors(colors, selectedColors);
                getWindow().getDecorView().setBackgroundColor(mixedColor);
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        adb.show();
    }

    /**
     * Mixes the selected colors.
     * @param colors The array of available colors.
     * @param selectedColors The boolean array indicating selected colors.
     * @return The mixed color result.
     */
    public int mixSelectedColors(int[] colors, boolean[] selectedColors) {
        int r = 0, g = 0, b = 0, count = 0;

        for (int i = 0; i < selectedColors.length; i++) {
            if (selectedColors[i]) {
                r += Color.red(colors[i]);
                g += Color.green(colors[i]);
                b += Color.blue(colors[i]);
                count++;
            }
        }

        if (count == 0) return Color.WHITE;

        return Color.rgb(r / count, g / count, b / count);
    }

    /**
     * Handles button1 click event to display a single-choice color selection dialog.
     * @param view The view that triggered the event.
     */
    public void button1Click(View view) {
        final String[] colorNames = {"Red", "Yellow", "Blue"};
        final int[] colors = {Color.RED, Color.YELLOW, Color.BLUE};

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Choose a Background Color");
        adb.setItems(colorNames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getWindow().getDecorView().setBackgroundColor(colors[which]);
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        adb.show();
    }

    /**
     * Handles button3 click event to display an input dialog and show entered text in a Toast.
     * @param view The view that triggered the event.
     */
    public void btn3onClick(View view) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle("Enter Text");

        final EditText input = new EditText(this);
        adb.setView(input);

        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = input.getText().toString();
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });

        adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        adb.show();
    }

    /**
     * Handles button4 click event to reset the background color to white.
     * @param view The view that triggered the event.
     */
    public void btn4onClick(View view) {
        getWindow().getDecorView().setBackgroundColor(Color.WHITE);
    }
}
