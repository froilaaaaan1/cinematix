package com.example.movies_ticketing_aquino_villaester_edp;

import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MovieLists extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_lists);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            getWindow().setDecorFitsSystemWindows(false);
        else {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            );
        }

        RecyclerView rView = findViewById(R.id.recycView);
        LinearSnapHelper linearSnapHelper = new LinearSnapHelper();
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false);
        linearSnapHelper.attachToRecyclerView(rView);
        rView.setLayoutManager(layoutManager);
        List<MovieItem> movieList = createMovieList();

        MovieAdapter adapter = new MovieAdapter(movieList, this);
        rView.setAdapter(adapter);

        rView.setLayoutManager(new LinearLayoutManager(this));

    }

    private List<MovieItem> createMovieList() {
        List<MovieItem> movies = new ArrayList<>();
        movies.add(new MovieItem(
                "My Neighbor Totoro - (1988)",
                R.drawable.my_neighbor_totoro_1988,
                R.drawable.my_neighbor_totoro_1988_blur,
                "My Neighbor Totoro",
                 "1988",
                "Hayao Miyazaki",
                "1hr 27Mins"));

        movies.add(new MovieItem(
                "Nausicaa of the Valley of the Wind - (1984)",
                R.drawable.nausicaa_of_the_valley_of_the_wind_1984,
                R.drawable.nausicaa_of_the_valley_of_the_wind_1984_blur,
                "Nausicaa of the Valley of the Wind",
                "1984",
                "Hayao Miyazaki",
                "127 Minutes"));

        movies.add(new MovieItem(
                "Castle in the Sky - (1986)",
                R.drawable.castle_in_the_sky_1986,
                R.drawable.castle_in_the_sky_1986_blur,
                "Castle in the Sky",
                "1986",
                "Hayao Miyazaki",
                "124 Minutes"));

        movies.add(new MovieItem(
                "Grave of the Fireflies - (1988)",
                R.drawable.grave_of_the_fireflies_1988,
                R.drawable.grave_of_the_fireflies_1988_blur,
                "Grave of the Fireflies",
                "1988",
                "Isao Takahata",
                "189 Minutes"));

        movies.add(new MovieItem(
                "Kiki's Delivery Services - (1989)",
                R.drawable.kikis_delivery_service,
                R.drawable.kikis_delivery_service_blur,
                "Kiki's Delivery Services",
                "1989",
                "Hayao Miyazaki",
                "102 Minutes"));

        movies.add(new MovieItem(
                "Only Yesterday - (1991)",
                R.drawable.only_yesterday_1991,
                R.drawable.only_yesterday_1991_blur,
                "Only Yesterday",
                "1991",
                "Isao Takahata",
                "118 Minutes"));

        movies.add(new MovieItem(
                "Porco Rossso - (1992)",
                R.drawable.porco_rosco_1992,
                R.drawable.porco_rosco_1992_blur,
                "Porco Rossso",
                "1992",
                "Hayao Miyazaki",
                "94 Minutes"));

        movies.add(new MovieItem(
                "Ocean Waves - (1993)",
                R.drawable.ocean_waves_1993,
                R.drawable.ocean_waves_1993_blur,
                "Ocean Waves",
                "1993",
                "Tomomi Mochizuki",
                "72 Minutes"));

        movies.add(new MovieItem(
                "The Boy and The Heron - (2023)",
                R.drawable.the_boy_and_the_heron_2023,
                R.drawable.the_boy_and_the_heron_2023_blur,
                "The Boy and The Heron",
                "2023",
                "Hayao Miyazaki",
                "124 Minutes"));

        movies.add(new MovieItem(
                "Whisper of the Heart - (1995)",
                R.drawable.whisper_of_the_heart_1995,
                R.drawable.whisper_of_the_heart_1995_blur,
                "Whisper of the Heart",
                "1995",
                "Yoshifumi Kondo",
                "111 Minutes"));

        movies.add(new MovieItem(
                "Princess Mononoke - (1997)",
                R.drawable.princess_monoke_1997,
                R.drawable.princess_monoke_1997_blur,
                "Princess Mononoke",
                "1997",
                "Hayao Miyazaki",
                "133 Minutes"));

        movies.add(new MovieItem(
                "My Neighbors the Yamadas - (1999)",
                R.drawable.my_neighbor_the_yamadas_1999,
                R.drawable.my_neighbor_the_yamadas_1999_blur,
                "My Neighbors the Yamadas",
                "1999",
                "Isao Takahata",
                "104 Minutes"));

        movies.add(new MovieItem(
                "Spirited Away - (2001)",
                R.drawable.spirited_away_2001,
                R.drawable.spirited_away_2001_blur,
                "Spirited Away",
                "2001",
                "Hayao Miyazaki",
                "125 Minutes"));

        movies.add(new MovieItem(
                "The Cat Returns - (2002)",
                R.drawable.the_cat_returns_2002,
                R.drawable.the_cat_returns_2002_blur,
                "The Cat Returns",
                "2002",
                "Hiroyuki Morita",
                "75 Minutes"));

        movies.add(new MovieItem(
                "Howl's Moving Castle - (2004)",
                R.drawable.howls_moving_castle_2004,
                R.drawable.howls_moving_castle_2004_blur,
                "Howl's Moving Castle",
                "2004",
                "Hayao Miyazaki",
                "119 Minutes"));

        movies.add(new MovieItem(
                "When Marnie was There - (2014)",
                R.drawable.when_marnie_was_there_2014,
                R.drawable.when_marnie_was_there_2014_blur,
                "When Marnie was There",
                "2014",
                "Hiromasa Yonebayashi",
                "103 Minutes"));

        movies.add(new MovieItem(
                "Ponyo - (2008)",
                R.drawable.ponyo_2008,
                R.drawable.ponyo_2008_blur,
                "Ponyo",
                "2008",
                "Hayao Miyazaki",
                "101 Minutes"));

        movies.add(new MovieItem(
                "The Secret World of Arrietty - (2010)",
                R.drawable.the_secret_world_of_arrietty_2010,
                R.drawable.the_secret_world_of_arrietty_2010_blur,
                "The Secret World of Arrietty",
                "2010",
                "Hiromasa Yonebayashi",
                "95 Minutes"));

        movies.add(new MovieItem(
                "From Up on Poppy Hill - (2011)",
                R.drawable.from_up_on_poppy_hill_2011,
                R.drawable.from_up_on_poppy_hill_2011_blur,
                "From Up on Poppy Hill",
                "2011",
                "Goro Miyazaki",
                "92 Minutes"));

        movies.add(new MovieItem(
                "The Wind Rises - (2013)",
                R.drawable.the_wind_rises_2013,
                R.drawable.the_wind_rises_2013_blur,
                "The Wind Rises",
                "2013",
                "Hayao Miyazaki",
                "126 Minutes"));

        return movies;
    }
}