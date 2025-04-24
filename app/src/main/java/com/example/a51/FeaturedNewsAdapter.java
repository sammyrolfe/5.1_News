package com.example.a51;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeaturedNewsAdapter extends RecyclerView.Adapter<FeaturedNewsAdapter.FeaturedNewsViewHolder> {
    private List<FeaturedNewsArticle> featuredNewsList;
    private Context context;



    public FeaturedNewsAdapter(Context context, List<FeaturedNewsArticle> featuredNewsList) {
        this.context = context;
        this.featuredNewsList = featuredNewsList;
    }

    @NonNull
    @Override
    public FeaturedNewsAdapter.FeaturedNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.featured_article_item, parent, false);
        return new FeaturedNewsViewHolder(view);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull FeaturedNewsViewHolder holder, int position) {
        FeaturedNewsArticle article = featuredNewsList.get(position);

        holder.title.setText(article.getTitle());
        holder.content.setText(article.getContent());
        holder.image.setImageResource(article.getImageResource());

        holder.itemView.setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                v.getParent().requestDisallowInterceptTouchEvent(true); // Allow horizontal scrolling
                return true; // Consume the touch event
            }
            return false; // Let other events (like clicks) propagate
        });


        holder.itemView.setOnClickListener(v -> {
            ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(article.getTitle(), article.getContent(), article.getImageResource());


                AppCompatActivity activity = (AppCompatActivity) context;
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main, fragment)
                        .addToBackStack(null)
                        .commit();

        });
    }


    @Override
    public int getItemCount() {
        return featuredNewsList.size();
    }

    public static class FeaturedNewsViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView image;

        public FeaturedNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.featuredNewsTitle);
            content = itemView.findViewById(R.id.featuredNewsContent);
            image = itemView.findViewById(R.id.featuredNewsImage);

        }
    }
}
