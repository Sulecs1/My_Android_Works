package com.suleakcay.chatprogrami;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAdapter extends FragmentPagerAdapter {
    public TabsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
//0 ise ->istekler fragmentı,1->mesajlar fragmentı,2->arkadaşlar fragmentının belirtir.


    public Fragment getItem(int position) {
        switch (position){
            case 0:
                RequestFragment requestFragment=new RequestFragment();
                return  requestFragment;
            case 1:
                chatFragment chatfragment=new chatFragment();
                return  chatfragment;
            case 2:
                FriendsFragment friendsFragment=new FriendsFragment();
                return  friendsFragment;
            default:
                return  null;
        }

    }

    @Override
    public int getCount() {  //kaç tane tabımız varsa o değeri döndürür

        return 3;  //yukarıda gözüktüğü gibi 3 tane tabımız var
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "İSTEKLER";
            case 1:
                return "MESAJLAR";
            case 2:
                return "ARKADAŞLAR";
            default:
                return  null;
        }

    }
}
