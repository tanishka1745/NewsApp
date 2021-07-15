package com.techdecode.newsapp;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    String api="455a09ecdbc245bb9bbd0ea3d1d07975";
    ArrayList<ModalClass> modalClassArrayList;
    Adapter adapter;
    String country="in";
    private RecyclerView recyclerViewofhome;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View v= inflater.inflate(R.layout.homefragment,null);
      recyclerViewofhome=v.findViewById(R.id.recyclerviewofhome);
      modalClassArrayList= new ArrayList<>();
      recyclerViewofhome.setLayoutManager(new LinearLayoutManager(getContext()));
      adapter= new Adapter(getContext(),modalClassArrayList);
      recyclerViewofhome.setAdapter(adapter);

      findNews();
      return v;
    }

    private void findNews() {
        ApiUtilities.getApiInterface().getNews(country,100,api).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call, Response<mainNews> response) {
                modalClassArrayList.addAll(response.body().getArticles());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<mainNews> call, Throwable t) {

            }
        });
    }
}
