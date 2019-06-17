package cn.pedant.SweetAlert;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.StateListDrawable;
import android.view.View;

public class ViewUtils {

    static Drawable[] getDrawable(View view) {
        StateListDrawable drawable = (StateListDrawable) view.getBackground();
        DrawableContainer.DrawableContainerState dcs = (DrawableContainer.DrawableContainerState) drawable.getConstantState();
        if (dcs != null) {
            return dcs.getChildren();
        }
        return null;
    }

}