package com.mytrendin.retrofitrxjava;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by csa on 16-May-17.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Myholder> {


    List<ProductModel> list;

    public RecyclerAdapter(List<ProductModel> list) {
        this.list = list;

    }

    @Override
    public Myholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Myholder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
