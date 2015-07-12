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
package <%= packageName %>.utils;

import android.content.Context;
import android.util.Log;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


import java.util.ArrayList;
import java.util.List;

/**
 * 解析工具类
 *
 * @author kymjs
 *
 */
public class Parser {

    public static <T> T xmlToBean(Class<T> type, String xml) {
        T data = null;
        try {
            XStream xStream = new XStream(new DomDriver("UTF-8"));
            xStream.processAnnotations(type);
            data = (T) xStream.fromXML(xml);
        } catch (Exception e) {
            try {
                data = type.newInstance();
            } catch (Exception ee) {
            } finally {
                Log.e("kymjs", "xml解析异常");
            }
        }
        return data;
    }

}
