package ug.co.lion.lionmobile;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eriq on 10/28/2015.
 */
public class ProductGridAdapter extends RecyclerView.Adapter<ProductGridAdapter.ViewHolder> {

    List<ProductItem> mItems;
    Context mContext;

    public ProductGridAdapter(Context contexts) {
        super();

        mContext = contexts;
        mItems = new ArrayList<ProductItem>();
        ProductItem productItem = new ProductItem();
        productItem.setName("Property");
        productItem.setThumbnail(R.drawable.property);
        mItems.add(productItem);
        productItem = new ProductItem();
        productItem.setName("Motor Insurance");
        productItem.setThumbnail(R.drawable.motor);
        mItems.add(productItem);
        productItem = new ProductItem();
        productItem.setName("Agriculture");
        productItem.setThumbnail(R.drawable.agric);
        mItems.add(productItem);
        productItem = new ProductItem();
        productItem.setName("Personal Line");
        productItem.setThumbnail(R.drawable.personal);
        mItems.add(productItem);

        productItem = new ProductItem();
        productItem.setName("Fraud");
        productItem.setThumbnail(R.drawable.fraud);
        mItems.add(productItem);
        productItem = new ProductItem();
        productItem.setName("Marine");
        productItem.setThumbnail(R.drawable.marine);
        mItems.add(productItem);
        productItem = new ProductItem();
        productItem.setName("Engineering Risks");
        productItem.setThumbnail(R.drawable.engineering);
        mItems.add(productItem);

        productItem = new ProductItem();
        productItem.setName("Liability");
        productItem.setThumbnail(R.drawable.liability);
        mItems.add(productItem);
    }


    @Override
    public ProductGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grid_item_products, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductGridAdapter.ViewHolder holder, int position) {
        ProductItem product = mItems.get(position);
        holder.tvPdtName.setText(product.getName());
        holder.imgThumbnail.setImageResource(product.getThumbnail());

        holder.setClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick) {
                    Toast.makeText(mContext, "LOng#" + position, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mContext, "#" + position, Toast.LENGTH_SHORT).show();
                }


                final String[] motorOptions = {"Motor Comprehensive Cover", "Motor Traders' Insurance",
                        "Motor Fire and Theft Insurance Cover", "Motor Third (3rd) Party Insurance Policy"};

                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                builder.setTitle("Motor Options");
                builder.setItems(motorOptions, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(mContext, "you touched" + which, Toast.LENGTH_LONG).show();

                        Intent i = new Intent(mContext, MotorDetail.class);
                        mContext.startActivity(i);
                    }
                });

                Dialog d = builder.create();
                d.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public ImageView imgThumbnail;
        public TextView tvPdtName;
        private ItemClickListener clickListener;

        public ViewHolder(View itemView) {
            super(itemView);
            imgThumbnail = (ImageView) itemView.findViewById(R.id.img_thumbnail);
            tvPdtName = (TextView) itemView.findViewById(R.id.tv_pdt_name);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void setClickListener(ItemClickListener itemClickListener) {
            this.clickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view, getPosition(), false);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onClick(view, getPosition(), true);
            return true;
        }
    }
}
