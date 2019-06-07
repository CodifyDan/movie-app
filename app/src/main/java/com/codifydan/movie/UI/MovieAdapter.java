package com.codifydan.movie.UI;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codifydan.movie.Model.Movie;
import com.codifydan.movie.R;

import java.util.List;


public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.TaskItemViewHolder> {
    private List<Movie> mMovies;
    private ButtonClickListener buttonClickListener;
    private Context mContext;

    public interface ButtonClickListener {
        void onSelect(Movie movie);
    }

    public MovieAdapter(List<Movie> mMovies, ButtonClickListener buttonClickListener) {
        this.mMovies = mMovies;
        this.buttonClickListener = buttonClickListener;
    }

    @NonNull
    @Override
    public TaskItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_poster_card, viewGroup, false);
        mContext = context;
        return new TaskItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskItemViewHolder taskItemViewHolder, int i) {
        Movie movie = mMovies.get(i);
        String number = (i + 1) + mContext.getResources().getString(R.string.dot);
        taskItemViewHolder.mMovieNumber.setText(number);
        Glide.with(mContext).load("https://image.tmdb.org/t/p/original" + movie.getPoster()).into(taskItemViewHolder.mPosterView);
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class TaskItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mPosterView;
        TextView mMovieNumber;

        public TaskItemViewHolder(@NonNull View itemView) {
            super(itemView);
            mPosterView = (ImageView) itemView.findViewById(R.id.poster_image_view);
            mMovieNumber = itemView.findViewById(R.id.movie_number);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttonClickListener.onSelect(mMovies.get(getAdapterPosition()));
                }
            });
        }
    }
}
