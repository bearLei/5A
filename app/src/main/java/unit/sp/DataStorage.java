package unit.sp;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Created by lenovo on 2018/4/26.
 * 具体的sp操作
 */

public class DataStorage {

    public static final String User_info = "UserInfo";
    public static final String Current_TopicId = "CurrentTopicId";//当前操作的商品id
    /**
     * 将一个Serializable对象保存到sp中. <br/>
     * 如果参数key为空, 则直接返回, 不进行任何操作.<br/>
     * 如果参数obj为null, 则会直接删掉sp中对应的key的条目.
     * <p/>
     */
    public static <T extends Serializable> void saveComObject(final String key, final T obj) {

            if (TextUtils.isEmpty(key)) {
                return;
            }

            if (null == obj) {
                DataStorageBase.remove(key);
                return;
            }
            try {
                String value = ObjectSerializer.serialize(obj);
                DataStorageBase.putString(key, value);
            } catch (Exception e) {

        }
    }


    /**
     * 从sp中取出key所对应的Serializable对象.
     */
    public static Serializable getComObject(String key) {
        if (TextUtils.isEmpty(key)) {
            return null;
        }

        String value = DataStorageBase.getString(key, "");
        if (TextUtils.isEmpty(value)) {
            return null;
        }

        try {
            return (Serializable) ObjectSerializer.deserialize(value);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getCurrentTopicId(){
        return DataStorageBase.getString(Current_TopicId,"");
    }

    public static void putCurrentTopicId(String topicId){
        DataStorageBase.putString(Current_TopicId,topicId);
    }

}
