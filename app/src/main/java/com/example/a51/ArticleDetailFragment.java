package com.example.a51;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ArticleDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ArticleDetailFragment extends Fragment {

    private String title, content;
    private TextView articleTitle, articleContent;
    Button backButton;
    private int imageResource;
    private ImageView articleImage;
    RecyclerView newsArticles;

    public static ArticleDetailFragment newInstance(String title, String content, int imageResource) {
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("content", content);
        args.putInt("imageResource", imageResource);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article_detail, container, false);

        TextView articleTitle = view.findViewById(R.id.articleTitle);
        TextView articleContent = view.findViewById(R.id.articleContent);
        ImageView articleImage = view.findViewById(R.id.articleImage);
        Button backButton = view.findViewById(R.id.backButton);

        if (getArguments() != null) {
            title = getArguments().getString("title");
            content = getArguments().getString("content");
            imageResource = getArguments().getInt("imageResource");
            articleTitle.setText(title);
            articleContent.setText(content);
            articleImage.setImageResource(imageResource);

        }
        newsArticles = view.findViewById(R.id.newsArticles);


        List<NewsArticle> newsList = new ArrayList<>();
        newsList.add(new NewsArticle("Breaking News", "In a groundbreaking development, researchers at the University of Greenleaf have unveiled a revolutionary solar panel technology that boasts 50% higher efficiency than existing models. This innovation, dubbed 'SolarMax,' is expected to transform the renewable energy landscape by significantly reducing costs and increasing accessibility. Experts believe this breakthrough could play a pivotal role in combating climate change by accelerating the transition to cleaner energy sources. The team plans to collaborate with manufacturers to roll out SolarMax panels globally within the next two years.\"", R.drawable.venice));
        newsList.add(new NewsArticle("Tech Update", "In a bold leap forward, Apex Robotics has unveiled its latest creation: the Apex AI Companion, a revolutionary humanoid robot designed to seamlessly integrate into everyday life. This advanced machine features cutting-edge conversational AI, facial recognition capabilities, and adaptive learning algorithms, making it capable of providing personalized assistance, companionship, and even professional support. Industry experts have hailed this innovation as a significant step in bridging the gap between humans and AI. Apex Robotics plans to roll out these devices globally by the end of the year, promising to redefine how we interact with technology.", R.drawable.technology));
        NewsAdapter newsAdapter = new NewsAdapter(requireContext(), newsList);
        newsArticles.setLayoutManager(new LinearLayoutManager(requireContext()));
        newsArticles.setAdapter(newsAdapter);

        backButton.setOnClickListener(v -> requireActivity().getSupportFragmentManager().popBackStack());
        newsArticles.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        View articleItemView = getActivity().findViewById(R.id.article_item);
        View featuredArticleItemView = getActivity().findViewById(R.id.featured_article_item);


        if (articleItemView != null) {
            articleItemView.setVisibility(View.GONE);
        }
        if (featuredArticleItemView != null) {
            featuredArticleItemView.setVisibility(View.GONE);
        }

        newsArticles = getActivity().findViewById(R.id.newsArticles);

        if (newsArticles != null) {
            // Create and set data again
            List<NewsArticle> newsList = new ArrayList<>();
            newsList.add(new NewsArticle("Breaking News", "In a groundbreaking development, researchers at the University of Greenleaf have unveiled a revolutionary solar panel technology that boasts 50% higher efficiency than existing models. This innovation, dubbed 'SolarMax,' is expected to transform the renewable energy landscape by significantly reducing costs and increasing accessibility. Experts believe this breakthrough could play a pivotal role in combating climate change by accelerating the transition to cleaner energy sources. The team plans to collaborate with manufacturers to roll out SolarMax panels globally within the next two years.\"", R.drawable.venice));
            newsList.add(new NewsArticle("Tech Update", "In a bold leap forward, Apex Robotics has unveiled its latest creation: the Apex AI Companion, a revolutionary humanoid robot designed to seamlessly integrate into everyday life. This advanced machine features cutting-edge conversational AI, facial recognition capabilities, and adaptive learning algorithms, making it capable of providing personalized assistance, companionship, and even professional support. Industry experts have hailed this innovation as a significant step in bridging the gap between humans and AI. Apex Robotics plans to roll out these devices globally by the end of the year, promising to redefine how we interact with technology.", R.drawable.technology));

            // Set layout manager and adapter
            NewsAdapter newsAdapter = new NewsAdapter(requireContext(), newsList);
            newsArticles.setLayoutManager(new LinearLayoutManager(requireContext()));
            newsArticles.setAdapter(newsAdapter);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        View articleItemView = getActivity().findViewById(R.id.article_item);
        View featuredArticleItemView = getActivity().findViewById(R.id.featured_article_item);
        if (articleItemView != null) {
            articleItemView.setVisibility(View.VISIBLE);
        }
        if (featuredArticleItemView != null) {
            featuredArticleItemView.setVisibility(View.VISIBLE);
        }
    }




}