package com.example.githubapifetcher.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.githubapifetcher.R;


public class FragmentRepoDescription extends Fragment {


    public FragmentRepoDescription() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_repo_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle bundle=getArguments();
        String htmlurl=bundle.getString("htmlurl");

        WebView myWebView = (WebView) view.findViewById(R.id.web_view);
        myWebView.loadUrl(htmlurl);


    }
}