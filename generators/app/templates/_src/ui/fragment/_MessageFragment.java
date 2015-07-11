/*
 * Copyright (c) 2015, �����֣�����ƽ��.
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

import org.kymjs.blog.R;


public class MessageFragment extends TitleBarFragment {

    @Override
    protected View inflaterView(final LayoutInflater inflater, final ViewGroup container, final Bundle bundle) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }






}
