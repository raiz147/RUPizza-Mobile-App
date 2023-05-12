package com.example.rupizza;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * this class controls the store orders page of the application
 */
public class StoreOrdersActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    ListView orderList;
    private ArrayList<String> order;
    ArrayAdapter orderAdapter;

    TextView orderTotalTxt;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    Button removeOrderBtn;
    Spinner spinner;

    @SuppressLint("MissingInflatedId")
    @Override
    /**
     * initializer of store order page
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        setClickListeners();
        setSpinner();
        setView();
        orderTotalTxt = findViewById(R.id.orderTotalTxt);
        if (HomeActivity.storeOrder.getSize() == 0)
        {
            orderTotalTxt.setText(R.string.zero);
        }
        removeOrderBtn = findViewById(R.id.removeOrderBtn);
        removeOrderBtn.setOnClickListener(this);

    }

    /**
     * sets text for the page
     * @param p postion
     */
    private void setText(int p){
        if (HomeActivity.storeOrder.getSize() == 0)
        {
            orderTotalTxt.setText(R.string.zero);
        }
        else{
            orderTotalTxt.setText(df.format(HomeActivity.storeOrder.getTotalPrice(p)));
        }
    }

    /**
     * sets listview of all the items in an order
     */
    private void setView(){
        orderList = findViewById(R.id.orderList);
        if (HomeActivity.storeOrder.getSize() == 0) {
            order = new ArrayList<>();
        }
        else {
            order = new ArrayList<>(HomeActivity.storeOrder.getOrderList(0));
        }
        orderAdapter = new ArrayAdapter(this, R.layout.list_item, R.id.textView, order);
        orderList.setAdapter(orderAdapter);
    }

    /**
     * sets spinner of order numbers
     */
    private void setSpinner()
    {
        ArrayList<String> orderNums = new ArrayList(HomeActivity.storeOrder.getOrderNums());
        spinner = findViewById(R.id.orderNumSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.spinner_view, R.id.textView2, orderNums);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    /**
     * on item selected listener for spinner
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        order = new ArrayList<>(HomeActivity.storeOrder.getOrderList(position));
        ArrayAdapter orderAdapter = new ArrayAdapter(this, R.layout.list_item, R.id.textView, order);
        orderList.setAdapter(orderAdapter);
        setText(position);
    }

    @Override
    /**
     * on nothing selected listener for spinner
     */
    public void onNothingSelected(AdapterView<?> parent) {

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
        if (v.getId() == R.id.removeOrderBtn){
            showRemoveOrderAlertDialog();
        }
    }

    /**
     * shows remove order alert dialog
     */
    private void showRemoveOrderAlertDialog(){
        String temp = String.valueOf(spinner.getSelectedItem());
        int orderNum = Integer.parseInt(temp);

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove Order");
        alert.setMessage("Do you want to remove this order?");
        alert.setPositiveButton("Yes", (dialog, which) -> {
            HomeActivity.storeOrder.removeThisOrder(orderNum);
            changeToStoreActivity();
            Toast.makeText(this, "Order Removed", Toast.LENGTH_SHORT).show();
        });
        alert.setNegativeButton("No", (dialog, which) -> {
        });
        alert.create().show();
    }

    /**
     * switches to current order activity
     */
    private void changeToOrderActivity() {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * switches to home activity
     */
    private void changeToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    /**
     * switches to store orders activity
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