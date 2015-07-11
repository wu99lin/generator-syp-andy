/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package <%= packageName %>.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 主界面博客模块
 *
 * @author kymjs (http://www.kymjs.com/)
 * @since 2015-3
 *
 */
public class BlogFragment extends TitleBarFragment {
    @Override
    protected View inflaterView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return null;
    }
//    public static final String TAG = BlogFragment.class.getSimpleName();
//
//    @BindView(id = R.id.empty_layout)
//    private EmptyLayout mEmptyLayout;
//    @BindView(id = R.id.blog_swiperefreshlayout)
//    private PullToRefreshList mRefreshLayout;
//    private ListView mList;
//
//    private Main aty;
//    private KJHttp kjh;
//    private BlogAdapter adapter;
//
//    private final String MY_BLOG_HOST = "http://www.kymjs.com/api/json_blog_list";
//    private String cache;
//
//    @Override
//    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
//            Bundle bundle) {
//        aty = (Main) getActivity();
//        return View.inflate(aty, R.layout.frag_blog, null);
//    }
//
//    @Override
//    protected void setActionBarRes(ActionBarRes actionBarRes) {
//        actionBarRes.title = getString(R.string.app_name);
//    }
//
//    @Override
//    protected void initData() {
//        super.initData();
//        HttpConfig config = new HttpConfig();
//        int hour = StringUtils.toInt(StringUtils.getDataTime("HH"), 0);
//        if (hour > 12 && hour < 22) {
//            config.cacheTime = 10;
//        } else {
//            config.cacheTime = 300;
//        }
//        config.useDelayCache = true;
//        kjh = new KJHttp(config);
//    }
//
//    @Override
//    protected void initWidget(View parentView) {
//        super.initWidget(parentView);
//        mEmptyLayout.setOnLayoutClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mEmptyLayout.setErrorType(EmptyLayout.NETWORK_LOADING);
//                refresh();
//            }
//        });
//        listViewPreference();
//        fillUI();
//    }
//
//    /**
//     * 初始化ListView样式
//     */
//    private void listViewPreference() {
//        mList = mRefreshLayout.getRefreshView();
//        mList.setDivider(new ColorDrawable(android.R.color.transparent));
//        mList.setOverscrollFooter(null);
//        mList.setOverscrollHeader(null);
//        mList.setOverScrollMode(ListView.OVER_SCROLL_NEVER);
//        mRefreshLayout.setPullLoadEnabled(true);
//        ((FooterLoadingLayout) mRefreshLayout.getFooterLoadingLayout())
//                .setNoMoreDataText("学习不可贪多哦~");
//
//        mList.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                    int position, long id) {
//                UIHelper.toBrowser(aty, adapter.getItem(position).getUrl());
//            }
//        });
//
//        mRefreshLayout.setOnRefreshListener(new OnRefreshListener<ListView>() {
//            @Override
//            public void onPullDownToRefresh(
//                    PullToRefreshBase<ListView> refreshView) {
//                refresh();
//            }
//
//            @Override
//            public void onPullUpToRefresh(
//                    PullToRefreshBase<ListView> refreshView) {
//                mRefreshLayout.setHasMoreData(false);
//            }
//        });
//    }
//
//    private void fillUI() {
//        cache = kjh.getStringCache(MY_BLOG_HOST);
//        if (!StringUtils.isEmpty(cache)) {
//            List<Blog> datas = Parser.getBlogList(cache);
//            adapter = new BlogAdapter(mList, datas);
//            mList.setAdapter(adapter);
//            mEmptyLayout.dismiss();
//        }
//        refresh();
//    }
//
//    private void refresh() {
//        kjh.get(MY_BLOG_HOST, new HttpCallBack() {
//            @Override
//            public void onSuccess(String t) {
//                super.onSuccess(t);
//                KJLoger.debug("博客列表：" + t);
//                if (t != null) {
//                    List<Blog> datas = Parser.getBlogList(t);
//                    adapter = new BlogAdapter(mList, datas);
//                    mList.setAdapter(adapter);
//                }
//                mEmptyLayout.dismiss();
//            }
//
//            @Override
//            public void onFailure(int errorNo, String strMsg) {
//                super.onFailure(errorNo, strMsg);
//                if (adapter != null && adapter.getCount() > 0) {
//                    return;
//                } else {
//                    mEmptyLayout.setErrorType(EmptyLayout.NODATA);
//                }
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                mRefreshLayout.onPullDownRefreshComplete();
//                mRefreshLayout.onPullUpRefreshComplete();
//            }
//        });
//    }
}