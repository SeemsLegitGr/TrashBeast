package gr.seemslegit.trashbeast.Controllers;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.mapbox.mapboxsdk.maps.MapView;

public class ScrollMapView extends MapView {

    public ScrollMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        /**
         * Request all parents to relinquish the touch events
         */
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }
}