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
 *
 * 我爱看界面
 *
 * @author kymjs (http://www.kymjs.com/)
 *
 */
public class MineFragment extends TitleBarFragment {

  /*  @BindView(id = R.id.mine_tv_collect, click = true)
    private TextView mTvCollect;
    @BindView(id = R.id.mine_tv_join, click = true)
    private TextView mTvJoin;
    @BindView(id = R.id.mine_tv_other, click = true)
    private TextView mTvOther;
    @BindView(id = R.id.mine_rl_feedback, click = true)
    private RelativeLayout mRlFeedback;
    @BindView(id = R.id.mine_rl_about, click = true)
    private RelativeLayout mRlAbout;
    @BindView(id = R.id.mine_rl_push, click = true)
    private RelativeLayout mRlPush;
    @BindView(id = R.id.mine_rl_clean, click = true)
    private RelativeLayout mRlClean;
    @BindView(id = R.id.mine_rl_exit, click = true)
    private RelativeLayout mRlExit;
    @BindView(id = R.id.mine_tv_currentversion)
    private TextView mTvVersion;
    @BindView(id = R.id.mine_cbox_push)
    private CheckBox mCbox;

    private boolean isChanged = false; // box是否有改变

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View view = View.inflate(outsideAty, R.layout.frag_mine, null);
        return view;
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        actionBarRes.title = getString(R.string.app_name);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        mRlExit.setVisibility(View.GONE);
        mTvVersion.setText("当前版本" + SystemTool.getAppVersionName(outsideAty));
        mCbox.setChecked(PreferenceHelper.readBoolean(outsideAty,
                AppConfig.PUSH_SWITCH_FILE, AppConfig.PUSH_SWITCH_KEY));
        mCbox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                    boolean isChecked) {
                PreferenceHelper.write(outsideAty, AppConfig.PUSH_SWITCH_FILE,
                        AppConfig.PUSH_SWITCH_KEY, isChecked);
            }
        });
    }

    @Override
    protected void widgetClick(View v) {
        super.widgetClick(v);
        switch (v.getId()) {
        case R.id.mine_tv_collect:
            SimpleBackActivity.postShowWith(outsideAty, SimpleBackPage.COLLECT);
            break;
        case R.id.mine_tv_other:
            UIHelper.toBrowser(outsideAty, "http://www.aplesson.com/MtAndroid/");
            break;
        case R.id.mine_tv_join:
            doJoin("http://jq.qq.com/?_wv=1027&k=XblWhv");
            break;
        case R.id.mine_rl_push:
            isChanged = true;
            mCbox.setChecked(!mCbox.isChecked());
            break;
        case R.id.mine_rl_about:
            SimpleBackActivity.postShowWith(outsideAty, SimpleBackPage.ABOUT);
            break;
        case R.id.mine_rl_clean:
            KJAsyncTask.execute(new Runnable() {
                @Override
                public void run() {
                    File folder = FileUtils.getSaveFolder(AppConfig.saveFolder);
                    deleteFile(folder);
                }
            });
            ViewInject.toast("缓存正在后台清除");
            break;
        case R.id.mine_rl_feedback:
            doJoin("http://jq.qq.com/?_wv=1027&k=XblWhv");
            break;
        case R.id.mine_rl_exit:
            break;
        default:
            break;
        }
    }

    private void doJoin(String url) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


    private void deleteFile(File file) {
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    this.deleteFile(files[i]);
                }
            }
            file.delete();
        }
    }*/

    @Override
    protected View inflaterView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return null;
    }
}
