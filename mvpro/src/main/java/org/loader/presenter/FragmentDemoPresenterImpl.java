package org.loader.presenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.loader.helper.GenericHelper;
import org.loader.view.IView;

/**
 * [为实现Fragment 不会重新创，仿OuNews的 BaseFragment类中的onCreateView和onDestroyView写法]
 * Fragment作为Presenter的基类 <br />
 * Created by qibin on 2015/11/15.
 */
public class FragmentDemoPresenterImpl<T extends IView> extends Fragment implements IPresenter<T> {


    protected View fragmentRootView;
    protected T mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        create(savedInstanceState);
        try {

            if(fragmentRootView!=null)
            {
                return fragmentRootView;
            }

            mView = getViewClass().newInstance();
            fragmentRootView = mView.create(inflater, container);
            mView.created();
            mView.bindPresenter(this);
            mView.bindEvent();
            created(savedInstanceState);
            return fragmentRootView;
        } catch(Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewGroup parent = (ViewGroup) fragmentRootView.getParent();
        if (null != parent) {
            parent.removeView(fragmentRootView);
        }
    }
/**    
    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (mView == null) {
            try {
                mView = getViewClass().newInstance();
            } catch (java.lang.InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
*/

    @Override
    public Class<T> getViewClass() {
        return GenericHelper.getViewClass(getClass());
    }

    @Override
    public void create(Bundle savedInstance) {

    }

    @Override
    public void created(Bundle savedInstance) {

    }
}
