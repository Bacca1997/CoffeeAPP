package com.coffeshop.bacca.coffeeshop;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;




/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int numbersofcoffes=0;
    double price=1.20;
    ListView list;
    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int numeroOrine = 1;
    public static String URL = "matteobaccarin.ddns.net/api.php";
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.list);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String item = list.getItemAtPosition(position).toString();
                Log.i("MainActivity", "Selected = " + item);
            }
        });

    }

    /**
     * This method is called when the order button is clicked.
     */

    public void increment(View view) {
        numbersofcoffes+=1;
        display(numbersofcoffes);
    }
    public void decrement(View view) {
        if (numbersofcoffes >= 1) {
            numbersofcoffes-=1;
        }

        display(numbersofcoffes);
    }
    public void submitOrder(View view) {
        displayPrice(numbersofcoffes*price);
    }
    public void addItems(View v) {
        if(numbersofcoffes>0) {
            TextView tableTextView = (TextView) findViewById(R.id.table_text_view);
            listItems.add("Ordine n° " + (numeroOrine++) + ": " + numbersofcoffes + " Caffè al tavolo " + tableTextView.getText() + " " + String.format("%.2f", numbersofcoffes * price) + "€");
            adapter.notifyDataSetChanged();
    }else{
            Toast.makeText(MainActivity.this, "Inserisci una quantità maggiore di 0", Toast.LENGTH_LONG).show();
        }

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    private void displayPrice(double number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Chiudi")
                .setMessage("Sei sicuro di voler chiudere l'app? \nTutti i dati andranno persi")
                .setPositiveButton("Si", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

}
