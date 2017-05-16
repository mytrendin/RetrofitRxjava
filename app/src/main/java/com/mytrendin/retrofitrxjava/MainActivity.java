package com.mytrendin.retrofitrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            public void onNext(List<ProductModel> productModels) {

                for (int i =0;i<productModels.size();i++){

                   Log.i("Hello",""+productModels.get(i).getProductname());

                }

            }
        });
    }



}
