package com.example.rupizza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * this controls the home page of the application
 * @author Ramazan Azimov
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener, RecyclerViewInterface{


    private ArrayList<PizzaItem> pizzas;
    public static int orderNum = 0;
    public static Order currentOrder = new Order();
    public static StoreOrder storeOrder = new StoreOrder();

    /**
     * adds pizza to current order
     * @param p pizza
     */
    public static void addPizzaToOrder(Pizza p){
        currentOrder.add(p);
    }

    /**
     * adds order to the store orders
     * @param o order
     */
    public static void addOrderToStore(Order o){
        storeOrder.add(o);
        currentOrder = new Order();
        orderNum += 1;
        currentOrder.setOrderNum(orderNum);
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
    }

    @Override
    /**
     * initializes home screen
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        setPizzaRecyclerView();
        setClickListeners();
    }

    @Override
    /**
     * on item click listnener for recycler view
     */
    public void onItemClick(int position){
        changeToPizzaDetails(position);
    }

    /**
     * switch view to pizza details
     * @param position recycler view item
     */
    private void changeToPizzaDetails(int position) {
        Intent intent = new Intent(HomeActivity.this, PizzaDetailsActivity.class);
        intent.putExtra("NAME", pizzas.get(position).getName());
        intent.putExtra("IMAGE", pizzas.get(position).getImageUrl());
        startActivity(intent);
    }

    /**
     * sets recycler view
     */
    private void setPizzaRecyclerView() {
        RecyclerView pizzaRecyclerView = findViewById(R.id.pizzaRecyclerView);

        pizzas = new ArrayList<>();
        pizzas.add(new PizzaItem("Deluxe | New York Style", R.drawable.deluxepizza));
        pizzas.add(new PizzaItem("Deluxe | Chicago Style", R.drawable.deluxepizza));
        pizzas.add(new PizzaItem("BBQ Chicken | New York Style", R.drawable.bbqchicken));
        pizzas.add(new PizzaItem("BBQ Chicken | Chicago Style", R.drawable.bbqchicken));
        pizzas.add(new PizzaItem("Meatzza | New York Style", R.drawable.meatzza));
        pizzas.add(new PizzaItem("Meatzza | Chicago Style", R.drawable.meatzza));
        pizzas.add(new PizzaItem("Build Your Own | New York Style", R.drawable.custompizza));
        pizzas.add(new PizzaItem("Build Your Own | Chicago Style", R.drawable.custompizza));

        pizzaRecViewAdapter adapter = new pizzaRecViewAdapter();
        adapter.setPizzas(pizzas);
        adapter.setRecyclerViewInterface(this);

        pizzaRecyclerView.setAdapter(adapter);
        pizzaRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    /**
     * change to current order activity
     */
    private void changeToOrderActivity() {
        Intent intent = new Intent(this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    /**
     * change to store orders activity
     */
    private void changeToStoreActivity() {
        Intent intent = new Intent(this, StoreOrdersActivity.class);
        startActivity(intent);
    }

    /**
     * change to home activity
     */
    private void changeToHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
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