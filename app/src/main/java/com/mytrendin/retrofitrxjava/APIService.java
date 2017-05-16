package com.mytrendin.retrofitrxjava;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;


public interface APIService {


    @GET("/json")
    Observable<List<ProductModel>> getproductdata();

}

