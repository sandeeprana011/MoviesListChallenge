package com.rana.movieslist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rana.movieslist.R;
import com.rana.movieslist.datastruct.Movie;
import com.whinc.widget.ratingbar.RatingBar;

import java.util.ArrayList;

/**
 * Created by sandeeprana on 15/10/16.
 * License is only applicable to individuals and non-profits
 * and that any for-profit company must
 * purchase a different license, and create
 * a second commercial license of your
 * choosing for companies
 */

public class AdapterRVMoview extends RecyclerView.Adapter<AdapterRVMoview.ViewHolder> {

    private final Context context;
    private final ArrayList<Movie> movies;

    public AdapterRVMoview(Context context, ArrayList<Movie> movies) {

        this.context = context;
        this.movies = movies;

    }


    @Override
    public AdapterRVMoview.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false);
        return new ViewHolder(v);
    }


//    @Override
//    public AdapterRVMoview.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movies, parent, false));
//    }

    @Override
    public void onBindViewHolder(final AdapterRVMoview.ViewHolder holder, int position) {
        Movie movie = this.movies.get(position);

        holder.tvTitle.setText(movie.getTitle());

        holder.tvGenre.setText(movie.getFormattedGenre());

        holder.tvReleaseYear.setText(movie.getFormattedYear());

        if (movie.getRating() >= 0) {
            holder.ratingBar.setCount(movie.getRating());
        } else {
            holder.ratingBar.setVisibility(View.INVISIBLE);
        }


        Glide
                .with(this.context)
                .load(movie.getImageUrl())
                .asBitmap()
                .centerCrop()
                .override(200, 200)
                .into(holder.avatarMovie);
//                .placeholder(R.drawable.avatar)
//                .into(new BitmapImageViewTarget(holder.avatarMovie) {
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        RoundedBitmapDrawable circularBitmapDrawable =
//                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                        circularBitmapDrawable.setCircular(true);
//                        holder.avatarMovie.setImageDrawable(circularBitmapDrawable);
//                    }
//                });
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView avatarMovie;
        TextView tvTitle, tvGenre, tvReleaseYear;
        RatingBar ratingBar;


        public ViewHolder(View itemView) {
            super(itemView);
            avatarMovie = (ImageView) itemView.findViewById(R.id.i_item_avatar);
            tvTitle = (TextView) itemView.findViewById(R.id.t_item_title);
            tvGenre = (TextView) itemView.findViewById(R.id.t_item_genre);
            tvReleaseYear = (TextView) itemView.findViewById(R.id.t_item_release);
            ratingBar = (RatingBar) itemView.findViewById(R.id.ratingBar);

        }
    }
}
