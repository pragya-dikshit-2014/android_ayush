/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/
package com.example.android.justjava;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;


import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        } else
            Toast.makeText(getApplicationContext(), "Sorry too many coffee", Toast.LENGTH_SHORT).show();
    }

    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        } else {
            Toast.makeText(getApplicationContext(), "Soryy you need to add minimum of 1 cofees!", Toast.LENGTH_SHORT).show();
        }
    }

    public void submitOrder(View view) {

        EditText name = (EditText) findViewById(R.id.name);
        String copyName = name.getText().toString();
        CheckBox hasWhippedCream = (CheckBox) findViewById(R.id.WhippedCream);
        boolean isWhipped = hasWhippedCream.isChecked();
        CheckBox Choc = (CheckBox) findViewById(R.id.Chocolate);
        boolean isChocolate = Choc.isChecked();
        int x = CalculatePrize(isWhipped, isChocolate);
        String y = createOrderSummary(x, isWhipped, isChocolate, copyName);
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "java for " + copyName);
        intent.putExtra(Intent.EXTRA_TEXT, y);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }

    public int CalculatePrize(boolean whip, boolean choc) {
        int price = quantity * 5;
        if (whip)
            price = price + 1;
        if (choc)
            price = price + 2;

        return price;
    }

    public String createOrderSummary(int price, boolean check, boolean check1, String name) {
        String str;
        str = "Name:" + name + "\nWhipppedCream?" + check + "\nChocolate?" + check1 + "\nQuantity:5\nTotal:" + price + "\nThank You";
        return str;
    }

    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
}