package com.mytrendin.retrofitrxjava;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by csa on 16-May-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Myholder> {


    List<ProductModel> list;

    ImageLoader imageLoader;
    public RecyclerAdapter(List<ProductModel> list, ImageLoader imageLoader) {
        this.list = list;
        this.imageLoader=imageLoader;
    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.show,parent,false);
        Myholder myHolder = new Myholder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {

        ProductModel product = list.get(position);
        holder.pname.setText(product.getProductname());
        holder.pcolor.setText(product.getColor());
        String image1 = product.getImageurl();
        imageLoader.displayImage(image1, holder.pimage);
        holder.price.setText("INR "+product.getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Myholder extends RecyclerView.ViewHolder{
        ImageView pimage;
        TextView pname,pcolor,price;

        public Myholder(View itemView) {
            super(itemView);
            pimage = (ImageView) itemView.findViewById(R.id.productimage);
            pname = (TextView) itemView.findViewById(R.id.name);
            pcolor = (TextView) itemView.findViewById(R.id.color);
            price = (TextView) itemView.findViewById(R.id.price);
        }
    }
}
