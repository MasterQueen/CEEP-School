package com.example.administrator.employmentplatform.com.example.administrator.MainView;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

/**
 * Created by 秞苏。 on 2018/3/17.
 */

public class MyscrollView extends ScrollView {
    public MyscrollView(Context context) {
        super(context);
    }

    public MyscrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyscrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    View view;

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            LinearLayout v = (LinearLayout) getChildAt(0);
            if (v != null) {
                for (int i = 0; i < v.getChildCount(); i++) {
                    if (v.getChildAt(i).getTag() != null && ((String) v.getChildAt(i).getTag()).equals("aaa")) {
                        view = v.getChildAt(i);
                        break;
                    }
                }
            }
        }
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if(getScrollY() >= view.getTop()){
            fixHead();
            canvas.save();
            canvas.translate(0,getScrollY());
            canvas.clipRect(0,0,view.getWidth(),view.getHeight());
            view.draw(canvas);
            canvas.restore();
        }else {
            resetHead();
        }
    }


    private OnFixHeadListener listener;

    private void fixHead() {
        if (listener != null) {
            listener.onFix();
        }
    }

    private void resetHead() {
        if (listener != null) {
            listener.onReset();
        }
    }

    public void setFixHeadListener(OnFixHeadListener listener) {
        this.listener = listener;
    }

    public interface OnFixHeadListener {
        void onFix();
        void onReset();
    }

}



