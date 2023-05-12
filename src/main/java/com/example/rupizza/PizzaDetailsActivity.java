package com.example.rupizza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;

/**
 * this controls the pizza details page
 * you can select the size and toppings here and etc.
 * @author Ramazan Azimov
 */
public class PizzaDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    private CheckBox sausage;
    private CheckBox pepperoni;
    private CheckBox pepper;
    private CheckBox onion;
    private CheckBox mushroom;
    private CheckBox provolone;
    private CheckBox cheddar;
    private CheckBox beef;
    private CheckBox ham;
    private CheckBox bbqchicken;
    private CheckBox bacon;
    private CheckBox pineapple;
    private CheckBox olive;
    private RadioGroup g1;
    private RadioButton smallbtn;
    private RadioButton mediumtn;
    private RadioButton largebtn;

    private final PizzaFactory pizzaFactoryCH = new ChicagoPizza();
    private final PizzaFactory pizzaFactoryNY = new NYPizza();
    private Pizza buildYourOwnPizza;
    private Pizza deluxePizza;
    private Pizza bbqChickenPizza;
    private Pizza meatzzaPizza;
    private boolean byo;
    private boolean del;
    private boolean bbqc;
    private boolean meat;

    private TextView pricevalueTxt;

    private int count = 0;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    @Override
    /**
     * page initializer
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);
        initializeCB();
        String name = getIntent().getStringExtra("NAME");
        int image = getIntent().getIntExtra("IMAGE", 0);
        TextView txt = findViewById(R.id.pizzaNameTxt);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) ImageView img = findViewById(R.id.pizzaImageOrderView);
        txt.setText(name);
        img.setImageResource(image);
        String pizzaName = name.substring(0, name.indexOf("|"));
        String pizzaStyle = name.substring(name.indexOf("|") + 2);
        String crust = getCrust(pizzaName, pizzaStyle);
        TextView crustName = findViewById(R.id.crusttxt2);
        crustName.setText(crust);
        setClickListeners();
        setRadioButtons(pizzaName);
    }

    /**
     * checks if checkboxes are checked
     * changes price accordingly
     * @param view view
     */
    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        if (view.getId() == R.id.sausageCB || view.getId() == R.id.pepperoniCB || view.getId() == R.id.pepperCB || view.getId() == R.id.onionCB ||
                view.getId() == R.id.mushroomCB || view.getId() == R.id.provoloneCB || view.getId() == R.id.cheddarCB || view.getId() == R.id.beefCB ||
                view.getId() == R.id.hamCB || view.getId() == R.id.bbqchickenCB || view.getId() == R.id.baconCB || view.getId() == R.id.pineappleCB ||
                view.getId() == R.id.olivesCB)   {
            if (checked)
                count += 1;
            else
                count -= 1;
            if (smallbtn.isChecked())
                pricevalueTxt.setText(df.format(8.99 + count * 1.59));
            else if (mediumtn.isChecked())
                pricevalueTxt.setText(df.format(10.99 + count * 1.59));
            else if (largebtn.isChecked())
                pricevalueTxt.setText(df.format(12.99 + count * 1.59));
        }
        disable7Toppings();
    }

    /**
     * disables toppings when max amount is reached
     */
    private void disable7Toppings() {
        if (count == 7) {
            Toast.makeText(this, "Topping Limit Reached", Toast.LENGTH_SHORT).show();
            sausage.setEnabled(sausage.isChecked());
            pepperoni.setEnabled(pepperoni.isChecked());
            pepper.setEnabled(pepper.isChecked());
            onion.setEnabled(onion.isChecked());
            mushroom.setEnabled(mushroom.isChecked());
            provolone.setEnabled(provolone.isChecked());
            cheddar.setEnabled(cheddar.isChecked());
            beef.setEnabled(beef.isChecked());
            ham.setEnabled(ham.isChecked());
            bbqchicken.setEnabled(bbqchicken.isChecked());
            bacon.setEnabled(bacon.isChecked());
            pineapple.setEnabled(pineapple.isChecked());
            olive.setEnabled(olive.isChecked());
        }
        else
        {
            sausage.setEnabled(true);
            pepperoni.setEnabled(true);
            pepper.setEnabled(true);
            onion.setEnabled(true);
            mushroom.setEnabled(true);
            provolone.setEnabled(true);
            cheddar.setEnabled(true);
            beef.setEnabled(true);
            ham.setEnabled(true);
            bbqchicken.setEnabled(true);
            bacon.setEnabled(true);
            pineapple.setEnabled(true);
            olive.setEnabled(true);
        }
    }

    /**
     * sets radio buttons
     * @param pizzaName pizza name
     */
    private void setRadioButtons(String pizzaName){
        g1.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.smallbtn){
                if (pizzaName.toLowerCase().compareTo("deluxe ") == 0) {
                    pricevalueTxt.setText(R.string.smDeluxe);
                }
                if (pizzaName.toLowerCase().compareTo("bbq chicken ") == 0) {
                    pricevalueTxt.setText(R.string.sbbq);
                }
                if (pizzaName.toLowerCase().compareTo("meatzza ") == 0) {
                    pricevalueTxt.setText(R.string.mbbq);
                }
                if (pizzaName.toLowerCase().compareTo("build your own ") == 0) {
                    pricevalueTxt.setText(df.format(8.99 + count * 1.59));
                }

            }
            if (checkedId == R.id.mediumbtn){
                if (pizzaName.toLowerCase().compareTo("deluxe ") == 0) {
                    pricevalueTxt.setText(R.string.medDeluxe);
                }
                if (pizzaName.toLowerCase().compareTo("bbq chicken ") == 0) {
                    pricevalueTxt.setText(R.string.mbbq);
                }
                if (pizzaName.toLowerCase().compareTo("meatzza ") == 0) {
                    pricevalueTxt.setText(R.string.lbbq);
                }
                if (pizzaName.toLowerCase().compareTo("build your own ") == 0) {
                    pricevalueTxt.setText(df.format(10.99 + count * 1.59));
                }
            }
            if (checkedId == R.id.largebtn){
                if (pizzaName.toLowerCase().compareTo("deluxe ") == 0) {
                    pricevalueTxt.setText(R.string.lgDeluxe);
                }
                if (pizzaName.toLowerCase().compareTo("bbq chicken ") == 0) {
                    pricevalueTxt.setText(R.string.lbbq);
                }
                if (pizzaName.toLowerCase().compareTo("meatzza ") == 0) {
                    pricevalueTxt.setText(R.string.lmeatzza);
                }
                if (pizzaName.toLowerCase().compareTo("build your own ") == 0) {
                    pricevalueTxt.setText(df.format(12.99 + count * 1.59));
                }
            }
        });
    }

    /**
     * sets toppings for deluxe pizza
     */
    private void selectDeluxeToppings(){
        sausage.setChecked(true);
        pepperoni.setChecked(true);
        pepper.setChecked(true);
        onion.setChecked(true);
        mushroom.setChecked(true);
    }

    /**
     * sets toppings for BBQ Chicken pizza
     */
    private void selectBBQToppings(){
        bbqchicken.setChecked(true);
        pepper.setChecked(true);
        provolone.setChecked(true);
        cheddar.setChecked(true);
    }

    /**
     * sets toppings for Meatzza Pizza
     */
    private void selectMeatzzaToppings(){
        sausage.setChecked(true);
        pepperoni.setChecked(true);
        beef.setChecked(true);
        ham.setChecked(true);
    }

    /**
     * disables all toppings checkboxes
     */
    private void disableAllCB(){
        sausage.setEnabled(false);
        pepperoni.setEnabled(false);
        pepper.setEnabled(false);
        onion.setEnabled(false);
        mushroom.setEnabled(false);
        provolone.setEnabled(false);
        cheddar.setEnabled(false);
        beef.setEnabled(false);
        ham.setEnabled(false);
        bbqchicken.setEnabled(false);
        bacon.setEnabled(false);
        pineapple.setEnabled(false);
        olive.setEnabled(false);
    }

    /**
     * initializes checkboxes
     */
    private void initializeCB(){
        pricevalueTxt = findViewById(R.id.priceValuetxt);
        g1 = findViewById(R.id.radioGroup);
        Button addToOrder = findViewById(R.id.addToOrderbtn);
        addToOrder.setOnClickListener(this);
        smallbtn = findViewById(R.id.smallbtn);
        mediumtn = findViewById(R.id.mediumbtn);
        largebtn = findViewById(R.id.largebtn);
        sausage = findViewById(R.id.sausageCB);
        sausage.setOnClickListener(this::onCheckboxClicked);
        pepperoni = findViewById(R.id.pepperoniCB);
        pepperoni.setOnClickListener(this::onCheckboxClicked);
        pepper = findViewById(R.id.pepperCB);
        pepper.setOnClickListener(this::onCheckboxClicked);
        onion = findViewById(R.id.onionCB);
        onion.setOnClickListener(this::onCheckboxClicked);
        mushroom = findViewById(R.id.mushroomCB);
        mushroom.setOnClickListener(this::onCheckboxClicked);
        provolone = findViewById(R.id.provoloneCB);
        provolone.setOnClickListener(this::onCheckboxClicked);
        cheddar = findViewById(R.id.cheddarCB);
        cheddar.setOnClickListener(this::onCheckboxClicked);
        beef = findViewById(R.id.beefCB);
        beef.setOnClickListener(this::onCheckboxClicked);
        ham = findViewById(R.id.hamCB);
        ham.setOnClickListener(this::onCheckboxClicked);
        bbqchicken = findViewById(R.id.bbqchickenCB);
        bbqchicken.setOnClickListener(this::onCheckboxClicked);
        bacon = findViewById(R.id.baconCB);
        bacon.setOnClickListener(this::onCheckboxClicked);
        pineapple = findViewById(R.id.pineappleCB);
        pineapple.setOnClickListener(this::onCheckboxClicked);
        olive = findViewById(R.id.olivesCB);
        olive.setOnClickListener(this::onCheckboxClicked);
    }

    /**
     * gets crust based on pizza type
     * @param pizzaName pizza name
     * @param pizzaStyle pizza style
     * @return crust
     */
    private String getCrust(String pizzaName, String pizzaStyle) {
        String result = null;

        if (pizzaName.toLowerCase().compareTo("deluxe ") == 0) {
            disableAllCB();
            selectDeluxeToppings();
            pricevalueTxt.setText(R.string.smDeluxe);
            del = true;
            if (pizzaStyle.toLowerCase().compareTo("new york style") == 0) {
                result = "Brooklyn";
                deluxePizza = pizzaFactoryNY.createDeluxe();
            }else if (pizzaStyle.toLowerCase().compareTo("chicago style") == 0) {
                result = "Deep Dish";
                deluxePizza = pizzaFactoryCH.createDeluxe();
            }
        }
        else if (pizzaName.toLowerCase().compareTo("bbq chicken ") == 0) {
            disableAllCB();
            selectBBQToppings();
            pricevalueTxt.setText(R.string.sbbq);
            bbqc = true;
            if (pizzaStyle.toLowerCase().compareTo("new york style") == 0) {
                result = "Thin";
                bbqChickenPizza = pizzaFactoryNY.createBBQChicken();
            }else if (pizzaStyle.toLowerCase().compareTo("chicago style") == 0) {
                result = "Pan";
                bbqChickenPizza = pizzaFactoryCH.createBBQChicken();
            }
        }
        else if (pizzaName.toLowerCase().compareTo("meatzza ") == 0) {
            disableAllCB();
            selectMeatzzaToppings();
            pricevalueTxt.setText(R.string.mbbq);
            meat = true;
            if (pizzaStyle.toLowerCase().compareTo("new york style") == 0) {
                result = "Hand Tossed";
                meatzzaPizza = pizzaFactoryNY.createMeatzza();
            }else if (pizzaStyle.toLowerCase().compareTo("chicago style") == 0) {
                result = "Stuffed";
                meatzzaPizza = pizzaFactoryCH.createMeatzza();
            }
        }
        else if (pizzaName.toLowerCase().compareTo("build your own ") == 0) {
            pricevalueTxt.setText(R.string.sbyo);
            byo = true;
            if (pizzaStyle.toLowerCase().compareTo("new york style") == 0) {
                result = "Hand Tossed";
                buildYourOwnPizza = pizzaFactoryNY.createBuildYourOwn();
            }else if (pizzaStyle.toLowerCase().compareTo("chicago style") == 0) {
                result = "Pan";
                buildYourOwnPizza = pizzaFactoryCH.createBuildYourOwn();
            }
        }
        return result;
    }

    /**
     * adds selected toppings to pizza
     */
    private void addSelectedToppings() {
        if (sausage.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Sausage"));
        }
        if(pepperoni.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Pepperoni"));
        }
        if (pepper.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Green Pepper"));
        }
        if(onion.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Onion"));
        }
        if (mushroom.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Mushroom"));
        }
        if(provolone.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Provolone"));
        }
        if(cheddar.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Cheddar"));
        }
        if (beef.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Beef"));
        }
        if(ham.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Ham"));
        }
        if(bbqchicken.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("BBQ Chicken"));
        }
        if (bacon.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Bacon"));
        }
        if(pineapple.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Pineapple"));
        }
        if(olive.isChecked()) {
            buildYourOwnPizza.addToppings(new Topping("Olives"));
        }
    }

    /**
     * shows alert dialog when adding to cart
     * @param v view
     */
    public void showAlertDialog(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Add to Cart");
            alert.setMessage("Are you sure you want to add this item to cart?");
            alert.setPositiveButton("Yes", (dialog, which) -> {
                String size = "";
                if (smallbtn.isChecked())
                    size = "Small";
                else if (mediumtn.isChecked())
                    size = "Medium";
                else if (largebtn.isChecked())
                    size = "Large";
                if (byo) {
                    buildYourOwnPizza.setSize(size);
                    addSelectedToppings();
                    HomeActivity.addPizzaToOrder(buildYourOwnPizza);
                }
                else if (del) {
                    deluxePizza.setSize(size);
                    HomeActivity.addPizzaToOrder(deluxePizza);
                }
                else if (meat) {
                    meatzzaPizza.setSize(size);
                    HomeActivity.addPizzaToOrder(meatzzaPizza);
                }
                else if (bbqc) {
                    bbqChickenPizza.setSize(size);
                    HomeActivity.addPizzaToOrder(bbqChickenPizza);
                }
                Toast.makeText(PizzaDetailsActivity.this, "Added to Order", Toast.LENGTH_SHORT).show();
                changeToHomeActivity();
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
        if (v.getId() == R.id.addToOrderbtn)
            showAlertDialog(v);
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