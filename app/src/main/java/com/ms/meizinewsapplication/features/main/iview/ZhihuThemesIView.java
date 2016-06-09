package com.ms.meizinewsapplication.features.main.iview;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hhl.library.FlowTagLayout;
import com.hhl.library.OnTagClickListener;
import com.ms.meizinewsapplication.R;
import com.ms.meizinewsapplication.features.base.utils.tool.ConstantData;
import com.ms.meizinewsapplication.features.base.utils.tool.DebugUtil;
import com.ms.meizinewsapplication.features.base.utils.tool.MyProfile;
import com.ms.meizinewsapplication.features.base.view.iview.RxBusRecyclerIView;
import com.ms.meizinewsapplication.features.main.adapter.ZhihuThemeTagAdapter;
import com.ms.meizinewsapplication.features.main.adapter.ZhihuThemesAdapter;
import com.ms.meizinewsapplication.features.main.json.zhihu_theme.Story;
import com.ms.mythemelibrary.lib.Colorful;

import java.util.ArrayList;
import java.util.List;

import me.corer.verticaldrawerlayout.VerticalDrawerLayout;

/**
 * Created by 啟成 on 2016/5/21.
 */
public class ZhihuThemesIView extends RxBusRecyclerIView {

    private ZhihuThemesAdapter zhihuThemesAdapter;
    private VerticalDrawerLayout vertical_drawer;
    private ImageView img_arrow;
    private FlowTagLayout flow_tag;
    private RelativeLayout rl_arrow;
    private TextView tv_arrow;
    private LinearLayout lin_drawer;

    private ZhihuThemeTagAdapter zhihuThemeTagAdapter;

    private int storyId = 0;
    private final int STORY_NUM = 3; //移动的位置

    @Override
    public int getLayoutId() {
        return R.layout.view_drawer_recycler;
    }

    @Override
    public void created() {
        super.created();
        vertical_drawer = findViewById(R.id.vertical_drawer);
        img_arrow = findViewById(R.id.img_arrow);
        flow_tag = findViewById(R.id.flow_tag);
        rl_arrow = findViewById(R.id.rl_arrow);
        tv_arrow = findViewById(R.id.tv_arrow);
        lin_drawer = findViewById(R.id.lin_drawer);
    }

    //TODO init========================================

    public void init(Activity activity) {
        initRecycler_list(activity);

        initZhihuThemeTagAdapter(activity);

        initColorful(activity);

        setTagAdapter();
        setOnTagClickListener();
        setDrawerListener();
        setImgArrowListener();

    }


    @Override
    public void initColorful(Activity activity) {
        this.activity = activity;
        initRecyclerViewSetter();
        mColorful = new Colorful.Builder(activity)
                .backgroundColor(lin_drawer, R.attr.root_view_bg)
                .backgroundColor(rl_arrow, R.attr.root_view_bg)
                .textColor(tv_arrow, R.attr.text_item_color)
                .setter(recyclerViewSetter) // 手动设置setter
                .create();

        isDay = MyProfile.getInstance(activity).getTheme().equals(ConstantData.MY_PROFILE_THEME_DAY);

        setTheme(isDay);
    }
    //TODO view ============================================

    private void initRecycler_list(final Activity activity) {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(activity);
        recycler_list.setLayoutManager(mLayoutManager);
        recycler_list.setItemAnimator(new DefaultItemAnimator());

        initAdapter(activity);
        setAdapter();
        addOnScrollListener(onScrollListener);
    }


    protected void initAdapter(Activity activity) {
        zhihuThemesAdapter = new ZhihuThemesAdapter(activity);
    }

    protected void setAdapter() {

        recycler_list.setAdapter(zhihuThemesAdapter);
    }

    protected void setDrawerListener() {
        vertical_drawer.setDrawerListener(simpleDrawerListener);
    }

    protected void setImgArrowListener() {
        rl_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isDrawerOpen();
            }
        });
    }

    protected void initZhihuThemeTagAdapter(Context context) {
        zhihuThemeTagAdapter = new ZhihuThemeTagAdapter(context);
    }

    protected void setTagAdapter() {
        flow_tag.setAdapter(zhihuThemeTagAdapter);
    }

    protected void setOnTagClickListener() {
        flow_tag.setOnTagClickListener(new OnTagClickListener() {
            @Override
            public void onItemClick(FlowTagLayout parent, View view, int position) {


                Story story = (Story) parent.getAdapter().getItem(position);

                smoothScrollToPosition(story.getId());
                setArrowText(story.getTitle());
                isDrawerOpen();
            }
        });
    }

    protected void smoothScrollToPosition(int position) {

        //最后一个可见Item的位置
        int lastVisibleItem = ((LinearLayoutManager) recycler_list.getLayoutManager()).findLastVisibleItemPosition();
        storyId = position;
        if (lastVisibleItem < storyId && storyId != 0) {
            storyId = storyId + STORY_NUM;
        }
        recycler_list.smoothScrollToPosition(storyId);
    }

    protected void setArrowText(String text) {
        tv_arrow.setText(text);
    }

    //TODO Model======================================================

    public void addAllData2QuickAdapter(ArrayList<Story> stories) {

        DebugUtil.debugLogD(zhihuThemesAdapter.getItemCount() + "++++RealPosition:" + stories.get(0).getTitle());
        zhihuThemesAdapter.addDatas(stories);
    }

    public Integer getItemCount() {
        return zhihuThemesAdapter.getItemCount();
    }

    public void addAllTagData(ArrayList<Story> stories) {
        DebugUtil.debugLogD(zhihuThemeTagAdapter.getCount() + "++++RealPosition:" + stories.get(stories.size() - 1).getTitle());
        zhihuThemeTagAdapter.onlyAddAll(stories);
    }

    public void addItemData(Story story) {
        DebugUtil.debugLogD(zhihuThemeTagAdapter.getCount() + "++++story:" + story.getTitle());
        zhihuThemeTagAdapter.addItem(story);
    }
    //TODO Listener================================================

    VerticalDrawerLayout.SimpleDrawerListener simpleDrawerListener = new VerticalDrawerLayout.SimpleDrawerListener() {
        @Override
        public void onDrawerSlide(View drawerView, float slideOffset) {
            super.onDrawerSlide(drawerView, slideOffset);
            img_arrow.setRotation(slideOffset * 180);
        }

        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            super.onDrawerClosed(drawerView);
        }

        @Override
        public void onDrawerStateChanged(int newState) {
            super.onDrawerStateChanged(newState);
        }

    };

    public boolean isDrawerOpen() {
        if (vertical_drawer.isDrawerOpen()) {
            vertical_drawer.closeDrawer();
            return false;
        } else {
            vertical_drawer.openDrawerView();
            return true;
        }
    }

    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

            if (newState != RecyclerView.SCROLL_STATE_IDLE) {
                return;
            }

            if (storyId == 0) {

                //最后一个可见Item的位置
                int lastVisibleItem = ((LinearLayoutManager) recycler_list.getLayoutManager()).findLastVisibleItemPosition();
                locationType(zhihuThemeTagAdapter.getData(), lastVisibleItem - STORY_NUM);
            }

            storyId = 0;
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            // dy>0 表示向下滑动

        }
    };

    // 当前位置的类型
    private void locationType(List<Story> storyList, int position) {

        for (Story story : storyList) {

            if (story.getId() > position) {
                continue;
            }
            setArrowText(story.getTitle());
        }

    }
}
