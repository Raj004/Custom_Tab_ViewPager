package com.example.customtabviewpager;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
//    private int[] tabIcons = {
//            R.mipmap.cross,
//            R.mipmap.fb,
//            R.mipmap.twitter
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setSelectedTabIndicatorHeight(4);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view=tab.getCustomView();
                TextView text;
                if(tab.getPosition()==0){
                    text=(TextView)view.findViewById(R.id.tab_one);
                }else if(tab.getPosition()==1){
                    text=(TextView)view.findViewById(R.id.tab_two);
                }else{
                    text=(TextView)view.findViewById(R.id.tab_three);

                }
                text.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorAccent));
                view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view=tab.getCustomView();
                TextView text;
                if(tab.getPosition()==0){
                    text=(TextView)view.findViewById(R.id.tab_one);
                }else if(tab.getPosition()==1){
                    text=(TextView)view.findViewById(R.id.tab_two);

                }else{
                    text=(TextView)view.findViewById(R.id.tab_three);

                }
                text.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
                view.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.btn_custom));//mActivity.getDrawable(R.drawable.btn_custom));

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setupTabIcons();
    }

    private void setupTabIcons() {
        View tabOne=(View) LayoutInflater.from(getApplicationContext()).inflate(R.layout.one_tab,null);
        TextView tab_one = (TextView) tabOne.findViewById(R.id.tab_one);

        View tabTwo=(View)LayoutInflater.from(getApplicationContext()).inflate(R.layout.two_tab,null);
        TextView tab_two = (TextView) tabTwo.findViewById(R.id.tab_two);

        View tabThree=(View)LayoutInflater.from(getApplicationContext()).inflate(R.layout.three_tab,null);
        TextView tab_three = (TextView) tabThree.findViewById(R.id.tab_three);

        tabLayout.getTabAt(0).setCustomView(R.layout.one_tab);
        tabLayout.getTabAt(1).setCustomView(R.layout.two_tab).select();
        tabLayout.getTabAt(2).setCustomView(R.layout.three_tab);
//        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
//        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
//        tabLayout.getTabAt(2).setIcon(tabIcons[2]).select();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new OneFragment(), "");
        adapter.addFrag(new TwoFragment(), "");
        adapter.addFrag(new ThreeFragment(), "");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}