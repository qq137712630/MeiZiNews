package com.ms.meizinewsapplication.features.main.banner;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.holder.Holder;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.main.activity.MainMvpActivity;
import com.ms.meizinewsapplication.features.main.activity.ZhihuDetailActivity;
import com.ms.meizinewsapplication.features.main.json.TopStories;
import com.ms.meizinewsapplication.utils.tool.ImagerLoad;
import com.ms.meizinewsapplication.utils.tool.ZhiHuConstants;

/**
 * Created by 啟成 on 2016/3/8.
 */
public class ZhiHuTopBannerView implements Holder<TopStories> {
    private View view;
    @Override
    public View createView(Context context) {
        view = LayoutInflater.from(context).inflate(R.layout.card_item_big, null);
        return view;
    }

    @Override
    public void UpdateUI(final Context context, int position, final TopStories data) {
        final ImageView imageView = (ImageView) view.findViewById(R.id.story_img);
        TextView textView = (TextView) view.findViewById(R.id.story_title);

        ImagerLoad.load(context, data.getImage(), imageView);
        textView.setText(data.getTitle());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(context, ZhihuDetailActivity.class);
                intent.putExtra(ZhiHuConstants.ID, data.getId());

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {

                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(
                            (MainMvpActivity) context,
                            imageView,
                            context.getString(R.string.shared_img)
                    );
                    context.startActivity(intent, options.toBundle());

                } else {
                    //让新的Activity从一个小的范围扩大到全屏
                    ActivityOptionsCompat options = ActivityOptionsCompat.makeScaleUpAnimation(
                            imageView, imageView.getWidth() / 2,
                            imageView.getHeight() / 2, 0, 0
                    );
                    ActivityCompat.startActivity((MainMvpActivity) context, intent, options.toBundle());
                }

            }
        });

    }
}
