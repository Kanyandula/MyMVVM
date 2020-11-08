package com.example.mymvvm.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.mymvvm.MainActivity;
import com.example.mymvvm.R;
import com.example.mymvvm.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MyViewHolder> {
    private Context context;
    private List<MovieModel> movieList;

    public  MovieListAdapter(Context context, List<MovieModel> movieList){
        this.context = context;
        this.movieList = movieList;
    }
    public void setMovieList(List<MovieModel > movieList){
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view  = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MyViewHolder holder, int position) {
        holder.tvTitle.setText(this.movieList.get(position).getTitle().toString());
        Glide.with(context)
                .load(this.movieList.get(position).getImage())
                .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView);

//        int width = Resources.getSystem().getDisplayMetrics().widthPixels;
//        Picasso.get()
//                .load(this.movieList.get(position).getImage())
//                .resize(width, width * 2/3)
//                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (this.movieList != null){
            return  this.movieList.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        ImageView imageView;
        public MyViewHolder(View itemView){
            super(itemView);
            tvTitle = (TextView)itemView.findViewById(R.id.titleView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);


        }

    }
}
