package com.example.a51;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView newsArticles;
    RecyclerView featuredNewsArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        featuredNewsArticles = findViewById(R.id.featuredNewsArticles);
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(
                this, LinearLayoutManager.HORIZONTAL, false);

        List<FeaturedNewsArticle> featuredNewsList = new ArrayList<>();
        featuredNewsList.add(new FeaturedNewsArticle("Breaking News", "Sydney skies lit up tonight with an extraordinary display of vibrant, multicolored lights that left residents in awe and sparked a wave of speculation. The phenomenon, described as a series of pulsating beams and swirling patterns, appeared suddenly at 2:15 a.m. and lasted for approximately 15 minutes. Social media has erupted with theories ranging from an atmospheric anomaly to a rare meteorological event—or even extraterrestrial activity. Local authorities and scientists are investigating the cause, urging the public to remain calm as more information is expected to emerge in the coming hours.", R.drawable.desk ));
        featuredNewsList.add(new FeaturedNewsArticle("Tech Update", "In an extraordinary discovery, wildlife enthusiasts in New South Wales have spotted a rare koala with striking golden fur in a eucalyptus tree near Port Macquarie. The unique coloration, thought to be the result of a genetic mutation, has drawn both scientists and admirers to the area. While the koala appears healthy, experts are urging the public to maintain a safe distance to ensure its well-being. Conservation groups are celebrating the sighting as a symbol of hope for the survival of the species amidst ongoing environmental challenges. Could this golden koala become a new icon for wildlife preservation efforts in Australia? ", R.drawable.moon));
        for (FeaturedNewsArticle article : featuredNewsList) {
            if (article.getTitle() != null && !article.getTitle().isEmpty() &&
                    article.getContent() != null && !article.getContent().isEmpty()) {
                Toast.makeText(this, "Title: " + article.getTitle() + "\nContent: " + article.getContent(), Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Invalid article found!", Toast.LENGTH_SHORT).show();
            }
        }
        featuredNewsArticles.setLayoutManager(horizontalLayoutManager);
        FeaturedNewsAdapter featuredNewsAdapter = new FeaturedNewsAdapter(this, featuredNewsList);
        featuredNewsArticles.setAdapter(featuredNewsAdapter);

        newsArticles = findViewById(R.id.newsArticles);

        List<NewsArticle> newsList = new ArrayList<>();
        newsList.add(new NewsArticle("Breaking News", "A stray roof tile from the iconic Sydney Opera House has captured the imagination of social media users after being discovered washed ashore at Bondi Beach. The white ceramic tile, bearing the signature gleam and curvature of the Opera House’s iconic sails, has sparked debates among locals about its origins and how it ended up in the surf. Engineers are investigating whether the tile was dislodged during recent storms, while art enthusiasts are calling it a piece of “accidental conceptual art.” One resident has already launched an online petition to have the tile displayed in a local gallery, citing its cultural and historical value. Could this be the unlikeliest celebrity Sydney has seen in years? ", R.drawable.venice));
        newsList.add(new NewsArticle("Tech Update", "Breaking News: AI-Powered Robot Makes Breakthrough in Deep-Sea Exploration\n" +
                "In a groundbreaking moment for technology and science, an autonomous AI-powered robot has successfully completed a 7,000-meter dive into the Mariana Trench, capturing high-definition footage of previously undiscovered marine species. The robot, designed with advanced machine learning capabilities and pressure-resistant materials, navigated the trench’s harsh conditions with remarkable precision, collecting valuable data that could reshape our understanding of deep-sea ecosystems. Scientists hail this achievement as a new frontier in environmental research, while robotics enthusiasts are celebrating the blend of innovation and exploration. Could this be the start of a new era in AI-led scientific discovery?\n", R.drawable.technology));

        NewsAdapter newsAdapter = new NewsAdapter(this, newsList);
        newsArticles.setLayoutManager(new LinearLayoutManager(this));
        newsArticles.setAdapter(newsAdapter);
    }




}