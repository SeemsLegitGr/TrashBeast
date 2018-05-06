package gr.seemslegit.trashbeast.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import gr.seemslegit.trashbeast.Controllers.RecyclerViewAdapter;
import gr.seemslegit.trashbeast.Models.Village;
import gr.seemslegit.trashbeast.R;

/**
 * UI to inform the user  about the results of the backend algorith calculations
 */
public class BottomSheetFragment extends BottomSheetDialogFragment {
    List<Village> villages;

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @SuppressLint("ValidFragment")
    public BottomSheetFragment(List<Village> villages) {
        this.villages = villages;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            switch (newState) {
                case BottomSheetBehavior.STATE_HIDDEN:
                    Log.w("SHEET", "HIDDEN");
                    break;
                case BottomSheetBehavior.STATE_EXPANDED: {
                    Log.w("SHEET", "expanded");

                    break;
                }
                case BottomSheetBehavior.STATE_COLLAPSED: {
                    Log.w("SHEET", "collapsed");
                    break;
                }
                case BottomSheetBehavior.STATE_DRAGGING:
                    Log.w("SHEET", "Dragging");
                    break;
                case BottomSheetBehavior.STATE_SETTLING:
                    Log.w("SHEET", "Settiling");
                    break;
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            Log.w("SHEET", "Sliiiidiin");
        }
    };

    @SuppressLint("RestrictedApi")
    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.results_bottom_sheet, null);
        RecyclerView rvList = contentView.findViewById(R.id.rvList);
        rvList.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(contentView.getContext());
        rvList.setLayoutManager(layoutManager);

        RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(villages);
        rvList.setAdapter(recyclerViewAdapter);

        dialog.setContentView(contentView);

        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        CoordinatorLayout.Behavior behavior = params.getBehavior();
        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

}