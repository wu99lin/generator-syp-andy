package <%= packageName %>.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import <%= packageName %>.R;
import <%= packageName %>.ui.TitleBarActivity;
import <%= packageName %>.ui.widget.KJScrollView;

import org.kymjs.kjframe.KJBitmap;
import org.kymjs.kjframe.ui.BindView;


public class ContentFragment extends TitleBarFragment {


    @BindView(id = R.id.find_root)
    private KJScrollView rootView;

    @BindView(id = R.id.find_plugin_1, click = true)
    private TextView mTvTweet;
    @BindView(id = R.id.find_plugin_2, click = true)
    private TextView mTvTodayMessage;
    @BindView(id = R.id.find_plugin_3, click = true)
    private TextView mTvJokeList;
    @BindView(id = R.id.find_plugin_4, click = true)
    private TextView mTvActive;
    @BindView(id = R.id.find_plugin_5, click = true)
    private TextView mTvFollow;
    @BindView(id = R.id.find_plugin_6, click = true)
    private TextView mTvSticky;

    KJBitmap kjb = new KJBitmap();

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
                                Bundle bundle) {
        View view = View.inflate(getActivity(), R.layout.fragment_content, null);
        return view;
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        actionBarRes.title = getString(R.string.app_name);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);

        rootView.setOnViewTopPullListener(new KJScrollView.OnViewTopPull() {
            @Override
            public void onPull() {
                if (outsideAty instanceof TitleBarActivity) {
                    outsideAty.getCurtainView().expand();
                }
            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
//        switch (v.getId()) {
//            case R.id.find_plugin_1:
//                SimpleBackActivity.postShowWith(outsideAty,
//                        SimpleBackPage.OSC_TWEET_LIST);
//                break;
//            case R.id.find_plugin_2:
//                SimpleBackActivity.postShowWith(outsideAty, SimpleBackPage.COMMENT);
//                break;
//            case R.id.find_plugin_3:
//                SimpleBackActivity.postShowWith(outsideAty,
//                        SimpleBackPage.OSC_BLOG_LIST);
//                break;
//            case R.id.find_plugin_4:
//                SimpleBackActivity.postShowWith(outsideAty,
//                        SimpleBackPage.OSC_ACTIVE);
//                break;
//            case R.id.find_plugin_5:
//                SimpleBackActivity.postShowWith(outsideAty,
//                        SimpleBackPage.BLOG_AUTHOR);
//                break;
//            case R.id.find_plugin_6:
//                SimpleBackActivity.postShowWith(outsideAty, SimpleBackPage.STICKY);
//                break;
//            case R.id.find_img_head:
//                int id = UIHelper.getUser(outsideAty).getUid();
//                if (id == 2332925 || id == 1) {
//                    outsideAty.showActivity(outsideAty, Login.class);
//                }
//                break;
//            default:
//                break;
//        }
    }
}
