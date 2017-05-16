package com.mytrendin.retrofitrxjava;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<ProductModel> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageLoaderConfiguration config=new ImageLoaderConfiguration.Builder(this).build();
        ImageLoader.getInstance().init(config);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        getProductData();
    }


    public void getProductData(){

        Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://api2.mytrendin.com")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                             .build();
        APIService apiService = retrofit.create(APIService.class);

        Observable<List<ProductModel>> observable = apiService.getproductdata().subscribeOn(Schedulers.newThread())
                                                    .observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(new Observer<List<ProductModel>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<ProductModel> products) {

                list = new ArrayList<>();
                for (int i =0;i<products.size();i++){

                    ProductModel productModel = new ProductModel();
                    productModel.setProductname(products.get(i).getProductname());
                    productModel.setImageurl(products.get(i).getImageurl());
                    productModel.setColor(products.get(i).getColor());
                    productModel.setPrice(products.get(i).getPrice());
                    productModel.setInstock(products.get(i).getInstock());
                    list.add(productModel);
                }

                RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list,ImageLoader.getInstance());
                RecyclerView.LayoutManager recyce = new GridLayoutManager(MainActivity.this,2);
                recyclerView.addItemDecoration(new GridSpacingdecoration(2, dpToPx(10), true));
                recyclerView.setLayoutManager(recyce);
                recyclerView.setItemAnimator( new DefaultItemAnimator());
                recyclerView.setAdapter(recyclerAdapter);

            }
        });
    }


    public class GridSpacingdecoration extends RecyclerView.ItemDecoration {

        private int span;
        private int space;
        private boolean include;

        public GridSpacingdecoration(int span, int space, boolean include) {
            this.span = span;
            this.space = space;
            this.include = include;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view);
            int column = position % span;

            if (include) {
                outRect.left = space - column * space / span;
                outRect.right = (column + 1) * space / span;

                if (position < span) {
                    outRect.top = space;
                }
                outRect.bottom = space;
            } else {
                outRect.left = column * space / span;
                outRect.right = space - (column + 1) * space / span;
                if (position >= span) {
                    outRect.top = space;
                }
            }
        }

    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
