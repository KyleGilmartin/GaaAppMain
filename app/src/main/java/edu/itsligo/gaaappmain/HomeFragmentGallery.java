package edu.itsligo.gaaappmain;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;


public class HomeFragmentGallery extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_home_gallery, container, false);

        ImageSlider imageSlider = v.findViewById(R.id.slider);

        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel("https://pbs.twimg.com/media/DxmRWtvWsAAaRWV.jpg", "IT Sligo vs IT Tralee"));
        slideModels.add(new SlideModel("https://pbs.twimg.com/media/DpF3u-xW0AA6KAa.jpg", "IT Sligo's Hurlers vs GMIT"));
        slideModels.add(new SlideModel("https://old.itsligo.ie/files/2016/02/Hurlerstrophiesweb27022016.jpg", "Hurlers Win Fergal Maher Cup"));
        slideModels.add(new SlideModel("https://img2.thejournal.ie/inline/1933493/original/?width=630&version=1933493", "IT Sligo's Eunan Doherty"));
        slideModels.add(new SlideModel("https://pbs.twimg.com/media/Dy171AiXgAEucA-.jpg", "IT Sligo's Ladies"));
        imageSlider.setImageList(slideModels, true);

        return v;
    }


}