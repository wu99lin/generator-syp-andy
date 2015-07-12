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

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;

import <%= packageName %>.R;
import <%= packageName %>.adapter.NotebookAdapter;
import <%= packageName %>.domain.NotebookData;
import <%= packageName %>.domain.SimpleBackPage;
import <%= packageName %>.ui.SimpleBackActivity;
import <%= packageName %>.ui.widget.KJDragGridView;
import <%= packageName %>.ui.widget.KJDragGridView.OnDeleteListener;
import <%= packageName %>.ui.widget.KJDragGridView.OnMoveListener;
import <%= packageName %>.utils.KJAnimations;
import org.kymjs.kjframe.KJDB;
import org.kymjs.kjframe.ui.BindView;

import java.util.List;

/**
 *
 * 便签列表界面
 *
 * @author kymjs (http://www.kymjs.com/)
 *
 */
public class NoteBookFragment extends TitleBarFragment implements
        OnItemClickListener {

    @BindView(id = R.id.frag_note_list)
    KJDragGridView mGrid;
    @BindView(id = R.id.frag_note_trash)
    ImageView mImgTrash;

    private List<NotebookData> datas;
    private NotebookAdapter adapter;
    private KJDB kjdb;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container,
            Bundle bundle) {
        View rootView = inflater.inflate(R.layout.fragment_note, container,
                false);
        return rootView;
    }

    @Override
    protected void setActionBarRes(ActionBarRes actionBarRes) {
        super.setActionBarRes(actionBarRes);
        actionBarRes.title = getString(R.string.sticies);
        actionBarRes.menuImageId = R.drawable.titlebar_add;
    }

    @Override
    protected void initData() {
        super.initData();
        kjdb = KJDB.create(outsideAty, true);
        datas = kjdb.findAll(NotebookData.class);
        adapter = new NotebookAdapter(outsideAty, datas);
    }

    @Override
    protected void initWidget(View parentView) {
        super.initWidget(parentView);
        mGrid.setAdapter(adapter);
        mGrid.setOnItemClickListener(this);
        mGrid.setTrashView(mImgTrash);
        mGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mGrid.setOnDeleteListener(new OnDeleteListener() {
            @Override
            public void onDelete(int position) {
                kjdb.delete(datas.get(position));
                datas.remove(position);
                adapter.refurbishData(datas);
            }
        });
        mGrid.setOnMoveListener(new OnMoveListener() {
            @Override
            public void startMove() {
                mImgTrash.startAnimation(KJAnimations.getTranslateAnimation(0,
                        0, mImgTrash.getTop(), 0, 500));
                mImgTrash.setVisibility(View.VISIBLE);
            }

            @Override
            public void finishMove() {
                mImgTrash.setVisibility(View.INVISIBLE);
                mImgTrash.startAnimation(KJAnimations.getTranslateAnimation(0,
                        0, 0, mImgTrash.getTop(), 500));
                if (adapter.getDataChange()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            datas = adapter.getDatas();
                            for (int i = 1; i < datas.size() + 1; i++) {
                                datas.get(i - 1).setId(i);
                                kjdb.update(datas.get(i - 1), "id=" + i);
                            }
                        }
                    }).start();
                }
            }

            @Override
            public void cancleMove() {}
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        datas = kjdb.findAll(NotebookData.class);
        adapter.refurbishData(datas);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
            long id) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(NoteEditFragment.NOTE_KEY, datas.get(position));
        SimpleBackActivity.postShowWith(outsideAty, SimpleBackPage.STICKY_EDIT,
                bundle);
    }

    @Override
    public void onMenuClick() {
        super.onMenuClick();
        SimpleBackActivity.postShowWith(outsideAty, SimpleBackPage.STICKY_EDIT);
    }
}
