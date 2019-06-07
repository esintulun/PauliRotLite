package utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MyPagerAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentListe = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm) {

        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentListe.get(position);
    }

    @Override
    public int getCount() {

        return fragmentListe.size();
    }

    //ADD PAGE
    public void addFragment(Fragment fragmentKlasse) {

        fragmentListe.add(fragmentKlasse);
    }

    //set title

    @Override
    public CharSequence getPageTitle(int position) {
        String title = fragmentListe.get(position).toString();
        return title.toString();
    }
}