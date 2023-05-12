package com.example.rupizza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * this controls the current order page of the application
 * @author Ramazan Azimov
 */
public class CurrentOrderActivity extends AppCompatActivity implements View.OnClickListener {

    ListView currentOrderList;
    ArrayList<String> currentOrder;
    ArrayAdapter orderAdapter;

    Button cancelOrder;
    Button placeOrder;
    TextView orderNumTxt;

    TextView subtotalTxt;
    TextView taxTxt;
    TextView totalTxt;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    /**
     * initializer for home page
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        setClickListeners();
        initializeItems();
        orderNumTxt = findViewById(R.id.orderNumTxt);
        orderNumTxt.setText(Integer.toString(HomeActivity.currentOrder.getOrderNum()));
        subtotalTxt = findViewById(R.id.subtotalTxt);
        taxTxt = findViewById(R.id.taxTxt);
        totalTxt = findViewById(R.id.totalTxt);
        setText();
    }

    /**
     * sets text for home page
     */
    public void setText()
    {
        double subtotal = HomeActivity.currentOrder.getSubtotal();
        double tax = HomeActivity.currentOrder.getSubtotal() * 0.06625;
        double total = subtotal + tax;
        subtotalTxt.setText(df.format(subtotal));
        taxTxt.setText(df.format(tax));
        totalTxt.setText(df.format(total));
    }

    /**
     * initializes current order list
     */
    private void initializeItems() {

        currentOrderList = findViewById(R.id.currentOrderList);
        currentOrder = new ArrayList<>(HomeActivity.currentOrder.getOrderList());
        orderAdapter = new ArrayAdapter(this, R.layout.list_item, R.id.textView, currentOrder);
        currentOrderList.setAdapter(orderAdapter);

        currentOrderList.setOnItemClickListener((parent, view, position, id) -> showRemovePizzaAlertDialog(position));
    }

    /**
     * remove pizza alert dialog
     * @param position item
     */
    private void showRemovePizzaAlertDialog(int position){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove Pizza");
        alert.setMessage("Do you want to remove this item from the order");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            HomeActivity.currentOrder.removePizza(position);
            currentOrder.remove(position);
            orderAdapter = new ArrayAdapter(CurrentOrderActivity.this,  R.layout.list_item, R.id.textView, currentOrder);
            currentOrderList.setAdapter(orderAdapter);
            setText();
            Toast.makeText(CurrentOrderActivity.this, "Pizza Removed", Toast.LENGTH_SHORT).show();
        });
        alert.setNegativeButton("No", (dialog, which) -> {
        });
        alert.create().show();
    }

    @Override
    /**
     * on click listener
     */
    public void onClick(View v) {
        if (v.getId() == R.id.homeImage || v.getId() == R.id.homeTxt)
            changeToHomeActivity();
        if (v.getId() == R.id.cartImage || v.getId() == R.id.cartTxt)
            changeToOrderActivity();
        if (v.getId() == R.id.storeImage || v.getId() == R.id.storeTxt)
            changeToStoreActivity();
        if (v.getId() == R.id.cancelOrderBtn)
            showCancelAlertDialog();
        if (v.getId() == R.id.placeOrderBtn)
            showOrderPLacedAlertDialog();
    }

    /**
     * order placed alert dialog
     */
    private void showOrderPLacedAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Place Order");
        alert.setMessage("Are you sure you want to place this this order?");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            Toast.makeText(CurrentOrderActivity.this, "Order Placed", Toast.LENGTH_SHORT).show();
            HomeActivity.addOrderToStore(HomeActivity.currentOrder);
            changeToHomeActivity();
        });
        alert.setNegativeButton("No", (dialog, which) -> {
        });
        alert.create().show();
    }

    /**
     * cancel order alert dialog
     */
    private void showCancelAlertDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Cancel");
        alert.setMessage("Are you sure you want to cancel this order?");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            Toast.makeText(CurrentOrderActivity.this, "Order Cancelled", Toast.LENGTH_SHORT).show();
            HomeActivity.currentOrder.removeAll();
            changeToHomeActivity();
        });
        alert.setNegativeButton("No", (dialog, which) -> {
        });
        alert.create().show();
    }

    /**
     * switch to current order activity
     */
    private void changeToOrderActivity() {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * switch to home screen activity
     */
    private void changeToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * switch to store order activity
     */
    private void changeToStoreActivity() {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }

    /**
     * sets on click listeners
     */
    private void setClickListeners()
    {
        cancelOrder = findViewById(R.id.cancelOrderBtn);
        cancelOrder.setOnClickListener(this);
        placeOrder = findViewById(R.id.placeOrderBtn);
        placeOrder.setOnClickListener(this);

        ImageView home = findViewById(R.id.homeImage);
        home.setOnClickListener(this);
        ImageView cart = findViewById(R.id.cartImage);
        cart.setOnClickListener(this);
        ImageView store = findViewById(R.id.storeImage);
        store.setOnClickListener(this);

        TextView hometxt = findViewById(R.id.homeTxt);
        hometxt.setOnClickListener(this);
        TextView carttxt = findViewById(R.id.cartTxt);
        carttxt.setOnClickListener(this);
        TextView storetxt = findViewById(R.id.storeTxt);
        storetxt.setOnClickListener(this);
    }
}