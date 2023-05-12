package com.example.rupizza;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * recycler view adapter class
 * @author Ramazan Azimov
 */
public class pizzaRecViewAdapter extends RecyclerView.Adapter<pizzaRecViewAdapter.ViewHolder> {

    private RecyclerViewInterface recyclerViewInterface;

    private ArrayList<PizzaItem> pizzas = new ArrayList<>();

    /**
     * default constructor
     */
    public pizzaRecViewAdapter() {

    }

    @NonNull
    @Override
    /**
     * initial view holder
     */
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pizza_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    /**
     * on bind viewholder
     */
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(pizzas.get(position).getName());
        holder.pizzaImage.setImageResource(pizzas.get(position).getImageUrl());

    }

    @Override
    /**
     * returns item count
     */
    public int getItemCount() {
        return pizzas.size();
    }

    /**
     * sets pizzas
     * @param pizzas
     */
    public void setPizzas(ArrayList<PizzaItem> pizzas) {
        this.pizzas = pizzas;
        notifyDataSetChanged();
    }

    /**
     * sets recycler view interface
     * @param recyclerViewInterface
     */
    public void setRecyclerViewInterface(RecyclerViewInterface recyclerViewInterface) {
        this.recyclerViewInterface = recyclerViewInterface;
    }

    /**
     * View Holder class
     */
    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView txtName;
        private ImageView pizzaImage;

        /**
         * constructor
         * @param itemView view
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            pizzaImage = itemView.findViewById(R.id.pizzaImage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                /**
                 * on click listener
                 */
                public void onClick(View v) {
                    if (recyclerViewInterface != null) {
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
