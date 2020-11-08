package com.example.mymvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mymvvm.adapter.MovieListAdapter;
import com.example.mymvvm.model.MovieModel;
import com.example.mymvvm.viewModel.MovieListViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<MovieModel> movieModelList;
    private MovieListAdapter adapter;
    private MovieListViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final TextView noResult = findViewById(R.id.noResultTv);
        LinearLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter =  new MovieListAdapter(this, movieModelList);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        viewModel.getMoviesList().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null) {
                    movieModelList = movieModels;
                    adapter.setMovieList(movieModels);
                    noResult.setVisibility(View.GONE);

                }else {
                    noResult.setVisibility(View.VISIBLE);
                }
            }
        });
        viewModel.makeApiCall();
    }
}