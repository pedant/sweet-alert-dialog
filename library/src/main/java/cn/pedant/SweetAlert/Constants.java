package cn.pedant.SweetAlert;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

public class Constants {
    //make bg a little bit darker
    public static final View.OnTouchListener FOCUS_TOUCH_LISTENER = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            Drawable drawable = v.getBackground();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_BUTTON_PRESS:

                    drawable.setColorFilter(0x20000000, PorterDuff.Mode.SRC_ATOP);
                    v.invalidate();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    drawable.clearColorFilter();
                    v.invalidate();
                    break;
            }
            return false;
        }
    };
}
