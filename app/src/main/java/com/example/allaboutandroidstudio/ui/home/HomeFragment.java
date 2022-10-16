package com.example.allaboutandroidstudio.ui.home;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.allaboutandroidstudio.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final WebView homeWebView = binding.idwebViewhome;
        final ProgressBar loadingPB=binding.idPBLoading;
        homeWebView.loadUrl("https://rushi047.github.io/index.html#");     // home page url:(main url)
        homeWebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingPB.setVisibility(View.VISIBLE);
            }
                // for back button code start here.
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadingPB.setVisibility(View.GONE);
            }
        });
          homeWebView.setOnKeyListener(new View.OnKeyListener() {
              @Override
              public boolean onKey(View view, int i, KeyEvent event) {
                  if(event.getAction()==KeyEvent.ACTION_DOWN){
                      switch (i){

                          case KeyEvent.KEYCODE_BACK:
                              if(homeWebView.canGoBack()){
                                  homeWebView.goBack();
                              }
                      }

                  }
                  return false;
              }
          });
        // code stop here
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}