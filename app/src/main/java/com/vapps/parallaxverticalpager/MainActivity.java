package com.vapps.parallaxverticalpager;

import android.app.Activity;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private VerticalParallaxPager verticalParallaxPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verticalParallaxPager = (VerticalParallaxPager) findViewById(R.id.pager_view);
        verticalParallaxPager.setOffscreenPageLimit(3);
        //For Showing some part of next and Prev page
        verticalParallaxPager.setClipToPadding(false);
        verticalParallaxPager.setPadding(0, 0, 0, 150);
        verticalParallaxPager.setPageMargin(convertPixelsToDp(-250, MainActivity.this));
        setUpViewPager();
    }

    public static int convertPixelsToDp(int px, Activity activity) {
        Resources resources = activity.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        return Math.round(px / (metrics.densityDpi / 160f));
    }

    private void setUpViewPager() {
        Adapter adapter = new Adapter(getSupportFragmentManager());


        IntroFirst introFirst = new IntroFirst();
        Bundle bundle = new Bundle();
        bundle.putInt("imageId", R.drawable.intro_first_);
        bundle.putInt("introId", R.string.intro_first);
        introFirst.setArguments(bundle);

        IntroFirst introSecond = new IntroFirst();
        Bundle bundle1 = new Bundle();
        bundle1.putInt("imageId", R.drawable.intro_second_);
        bundle1.putInt("introId", R.string.intro_second);
        introSecond.setArguments(bundle1);

        IntroFirst introThird = new IntroFirst();
        Bundle bundle2 = new Bundle();
        bundle2.putInt("imageId", R.drawable.intro_third_);
        bundle2.putInt("introId", R.string.intro_third);
        introThird.setArguments(bundle2);

        adapter.addFragment(introFirst);
        adapter.addFragment(introSecond);
        adapter.addFragment(introThird);
        verticalParallaxPager.setAdapter(adapter);

    }

    public static class IntroFirst extends Fragment {
        View viewRoot;
        ImageView imageView;
        TextView textViewDesc;
        int imageDrawableId;
        int textStringId;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            viewRoot = inflater.inflate(R.layout.intro_pager_view, container,
                    false);

            imageDrawableId = getArguments().getInt("imageId", R.drawable.intro_first_);
            textStringId = getArguments().getInt("introId");
            return viewRoot;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            imageView = (ImageView) viewRoot.findViewById(R.id.image_bg);
            imageView.setImageDrawable(getResources().getDrawable(imageDrawableId));
            textViewDesc = (TextView) viewRoot.findViewById(R.id.intro_desc);
            textViewDesc.setText(getResources().getString(textStringId));


        }
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment) {
            mFragments.add(fragment);

        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

    }
}
