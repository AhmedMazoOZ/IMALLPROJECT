package totchi.apps.a.imall.HomePageModule;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import totchi.apps.a.imall.R;
import totchi.apps.a.imall.ResultOnePageModule.AccessoriesActivity;
import totchi.apps.a.imall.ResultOnePageModule.CafeActivity;
import totchi.apps.a.imall.ResultOnePageModule.FashionActivity;
import totchi.apps.a.imall.ResultOnePageModule.FoodActivity;

/**
 * Created by 3zoOz on 01/01/2018.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Category> categoryList;

    //getting the context and product list with constructor
    public CategoryAdapter(Context mCtx, List<Category> categoryList) {
        this.mCtx = mCtx;
        this.categoryList = categoryList;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_products, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        //getting the category of the specified position
        Category category = categoryList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(category.getTitle());
        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(category.getImage()));

    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }


    class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewTitle, textViewPrice;
        ImageView imageView;

        public CategoryViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);

            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int x = Integer.valueOf(getPosition());
            switch (x) {
                case (0):
                    mCtx.startActivity(new Intent(mCtx, FoodActivity.class));
                    break;
                case (1):
                    //mCtx.startActivity(new Intent(mCtx, CafeActivity.class));
                    break;
                case (2):
                    //mCtx.startActivity(new Intent(mCtx, AccessoriesActivity.class));
                    break;
                case (3):
                    //mCtx.startActivity(new Intent(mCtx, FashionActivity.class));
                    break;
                case (4):
                    break;
                case (5):
                    break;
                case (6):
                    break;
            }
        }
    }
}