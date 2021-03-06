package cn.chinapost.com.tinker.network;


import cn.chinapost.com.tinker.customtinker.module.BasePatch;
import cn.chinapost.com.tinker.network.listener.DisposeDataHandle;
import cn.chinapost.com.tinker.network.listener.DisposeDataListener;
import cn.chinapost.com.tinker.network.listener.DisposeDownloadListener;
import cn.chinapost.com.tinker.network.request.CommonRequest;
import cn.chinapost.com.tinker.network.request.RequestParams;

/**
 * Created by renzhiqiang on 17/4/24.
 *
 * @function 请求发送中心
 */
public class RequestCenter {

    //根据参数发送所有post请求
    public static void postRequest(String url, RequestParams params, DisposeDataListener listener, Class<?> clazz) {
        CommonOkHttpClient.get(CommonRequest.createGetRequest(url, params), new DisposeDataHandle(listener, clazz));
    }


    /**
     * 询问是否有patch可更新
     *
     * @param listener
     */
    public static void requestPatchUpdateInfo(DisposeDataListener listener) {
        RequestCenter.postRequest(HttpConstant.UPDATE_PATCH_URL, null, listener,
                BasePatch.class);

    }

    /**
     * 文件下载
     *
     * @param url
     * @param path
     * @param listener
     */
    public static void downloadFile(String url, String path, DisposeDownloadListener listener) {
        CommonOkHttpClient.downloadFile(CommonRequest.createGetRequest(url, null),
                new DisposeDataHandle(listener, path));
    }
}
