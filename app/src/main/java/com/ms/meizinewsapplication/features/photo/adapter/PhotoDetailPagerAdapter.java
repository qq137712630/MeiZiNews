package com.ms.meizinewsapplication.features.photo.adapter;

import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.pojo.ImgItem;
import com.ms.meizinewsapplication.features.base.utils.tool.ImagerLoad;
import com.test.basepageradapterlibrary.basepager.BasePagerAdapterHelper;
import com.test.basepageradapterlibrary.basepager.QuickPagerAdapter;

import java.util.List;

import uk.co.senab.photoview.PhotoView;

/**
 * Created by 啟成 on 2016/3/18.
 */
public class PhotoDetailPagerAdapter extends QuickPagerAdapter<ImgItem> {
    public PhotoDetailPagerAdapter(List<String> strPageTitles, List<Integer> layoutResIds) {
        super(strPageTitles, layoutResIds);
    }

    public PhotoDetailPagerAdapter(List<ImgItem> data, List<String> strPageTitles, List<Integer> layoutResIds) {
        super(data, strPageTitles, layoutResIds);
    }

    @Override
    protected void convert(BasePagerAdapterHelper helper, ImgItem item, int position) {
        PhotoView photo_view = (PhotoView) helper.getView(R.id.photo_view);

        final String kpic = item.getImgUrl();
        if (kpic.contains("gif")) {
            ImagerLoad.load(helper.itemView.getContext(), item.getImgUrl(), photo_view);

            photo_view.setZoomable(false);
        } else {
            ImagerLoad.load(helper.itemView.getContext(), item.getImgUrl(), photo_view);
            photo_view.setZoomable(true);
        }

    }
}
