 package com.example.a51;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<NewsArticle> newsList;
    private Context context;

    public NewsAdapter(Context context, List<NewsArticle> newsList) {
        this.context = context;
        this.newsList = newsList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.article_item, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsArticle article = newsList.get(position);

        holder.title.setText(article.getTitle());

        String content = article.getContent();
        if (content.length() > 20) {
            holder.content.setText(content.substring(0, 20) + "...");
        } else {
            holder.content.setText(content);
        }


        holder.title.setText(article.getTitle());

        holder.image.setImageResource(article.getImageResource());


        holder.itemView.setOnClickListener(v -> {
            ArticleDetailFragment fragment = ArticleDetailFragment.newInstance(
                    article.getTitle(),
                    article.getContent(),
                    article.getImageResource()
            );

            if (context instanceof AppCompatActivity) {
                AppCompatActivity activity = (AppCompatActivity) context;
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;
        ImageView image;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.newsTitle);
            content = itemView.findViewById(R.id.newsContent);
            image = itemView.findViewById(R.id.newsImage);
        }
    }
}