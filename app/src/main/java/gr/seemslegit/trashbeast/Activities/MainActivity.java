package gr.seemslegit.trashbeast.Activities;


import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;

import gr.seemslegit.trashbeast.Controllers.ViewPagerAdapter;
import gr.seemslegit.trashbeast.Controllers.ZoomOutPageTransformer;
import gr.seemslegit.trashbeast.Fragments.BottomSheetFragment;
import gr.seemslegit.trashbeast.Fragments.MapFragment;
import gr.seemslegit.trashbeast.Fragments.SettingsFragment;
import gr.seemslegit.trashbeast.R;

/**
 * Controls  the fragments.
 */
public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    LinearLayout layoutBottomSheet;
    BottomSheetBehavior sheetBehavior;
    Button btBottomSheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetViews();
       // showBottomSheetDialogFragment();
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void SetViews() {
        viewPager = (ViewPager) findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MapFragment(), "Map");
        adapter.addFragment(new SettingsFragment(), "Statistics");
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        layoutBottomSheet = findViewById(R.id.bottom_sheet);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    /**
     * Bottom sheet dialog TODO: SHOW ON CALLBACK
     */
    public void showBottomSheetDialogFragment() {
        BottomSheetFragment bottomSheetFragment = new BottomSheetFragment();
        bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
    }


}