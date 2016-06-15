package com.chryscontreras.micochinito;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Chrysthoper on 13/06/2016.
 */
public class ToolbarView  extends RelativeLayout
{
    private ImageView firstImageView;
    private ImageView secondImageView;
    private TextView titleTextView;

    public ToolbarView(Context context)
    {
        super(context);
        init(context);
    }

    public ToolbarView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init(context);
    }

    public ToolbarView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(final Context context)
    {
        final View rootView = inflate(context, R.layout.toolbar, this);
        firstImageView = (ImageView) rootView.findViewById(R.id.view_actionbar_first);
        secondImageView = (ImageView) rootView.findViewById(R.id.view_actionbar_second);
        titleTextView = (TextView) rootView.findViewById(R.id.view_actionbar_title);
        Typeface myCustomFont = Typeface.createFromAsset(context.getAssets(), "fonts/Quicksand-Bold.otf");
        titleTextView.setTypeface(myCustomFont);

        Bus bus = App.getBus();
        bus.register(this); // Here we register this activity in bus.

        firstImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new FirstItemClicked());
            }
        });

        secondImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getBus().post(new SecondItemClicked());
            }
        });
    }

}

class FirstItemClicked
{
    public FirstItemClicked()
    {}
}

class SecondItemClicked
{
    public SecondItemClicked()
    {}
}