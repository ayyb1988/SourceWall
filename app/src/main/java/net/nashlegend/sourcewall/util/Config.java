package net.nashlegend.sourcewall.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import net.nashlegend.sourcewall.AppApplication;
import net.nashlegend.sourcewall.R;

/**
 * Created by NashLegend on 2014/12/15 0015
 */
public class Config {

    public final static int ExitTapsGap = 1200;
    public final static String defaultDisplayName = "果的壳";

    public static boolean shouldLoadImage() {
        //略微有点耗时，最多可耗时3ms，最低0.3ms
        //可以监听网络状态变化，记录状态，而不是直接读ConnectivityManager和SharedPreference
        int mode = getImageLoadMode();
        boolean flag = true;
        switch (mode) {
            case Consts.MODE_ALWAYS_LOAD:
                flag = true;
                break;
            case Consts.MODE_NEVER_LOAD:
                flag = false;
                break;
            case Consts.MODE_LOAD_WHEN_WIFI:
                flag = isWifi();
                break;
        }
        return flag;
    }

    private static boolean isWifi() {
        ConnectivityManager manager = (ConnectivityManager) AppApplication.getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return info != null && info.getState() == NetworkInfo.State.CONNECTED;
    }

    public static int getImageLoadMode() {
        return SharedUtil.readInt(Consts.Key_Image_Load_Mode, Consts.MODE_ALWAYS_LOAD);
    }

    /**
     * 上传图片尺寸
     *
     * @return 返回图片最小边的尺寸限制
     */
    public static int getUploadImageSizeRestrict() {
        return 720;
    }

    /**
     * 返回尾巴，html格式
     *
     * @return html格式的尾巴，发贴时带，但是评论答案和评论问题不带
     */
    public static String getComplexReplyTail() {
        String tail = "";
        switch (SharedUtil.readInt(Consts.key_Use_Tail_Type, Consts.Type_Use_Default_Tail)) {
            case Consts.Type_Use_Default_Tail:
                tail = getDefaultComplexTail();
                break;
            case Consts.Type_Use_Phone_Tail:
                tail = getPhoneComplexTail();
                break;
            case Consts.Type_Use_Custom_Tail:
                tail = getParametricCustomComplexTail();
                break;
        }
        return tail;
    }

    /**
     * 返回默认尾巴
     *
     * @return 默认尾巴
     */
    private static String getDefaultComplexTail() {
        return "<p></p><p>来自 <a href=\"https://github.com/NashLegend/SourceWall/blob/master/README.md\" target=\"_blank\">" + defaultDisplayName + "</a></p>";
    }

    /**
     * 返回手机尾巴
     *
     * @return 手机尾巴
     */
    private static String getPhoneComplexTail() {
        String mTypeString = android.os.Build.MODEL == null ? AppApplication.getApplication().getString(R.string.unknown_phone)
                : android.os.Build.MODEL;
        return "<p></p><p>来自 <a href=\"https://github.com/NashLegend/SourceWall/blob/master/README.md\" target=\"_blank\">" + mTypeString + "</a></p>";
    }

    /**
     * 返回自定义参数{}尾巴
     *
     * @return 参数化的尾巴
     */
    public static String getParametricCustomComplexTail() {
        //bit##参数##
        String tail = SharedUtil.readString(Consts.key_Custom_Tail, "");
        if (!tail.trim().equals("")) {
            String reg = "##([^#]+)##";
            tail = tail.replaceAll(reg, "<a href=\"https://github.com/NashLegend/SourceWall/blob/master/README.md\" target=\"_blank\">$1</a>");
        }
        return "<p></p><p>" + tail + "</p>";
    }

    /**
     * 返回尾巴，UBB格式
     *
     * @return html格式的尾巴，评论、发贴、回答时都带，但是评论答案和评论问题不带
     */
    public static String getSimpleReplyTail() {
        String tail = "";
        switch (SharedUtil.readInt(Consts.key_Use_Tail_Type, Consts.Type_Use_Default_Tail)) {
            case Consts.Type_Use_Default_Tail:
                tail = getDefaultSimpleTail();
                break;
            case Consts.Type_Use_Phone_Tail:
                tail = getPhoneSimpleTail();
                break;
            case Consts.Type_Use_Custom_Tail:
                tail = getParametricCustomSimpleTail();
                break;
        }
        return tail;
    }

    /**
     * 返回手机尾巴
     *
     * @return 手机尾巴
     */
    private static String getPhoneSimpleTail() {
        String mTypeString = android.os.Build.MODEL == null ? AppApplication.getApplication().getString(R.string.unknown_phone)
                : android.os.Build.MODEL;
        return "\n\n[blockquote]来自 [url=https://github.com/NashLegend/SourceWall/blob/master/README.md]" + mTypeString + "[/url][/blockquote]";
    }


    /**
     * 返回默认尾巴
     *
     * @return 默认尾巴
     */
    private static String getDefaultSimpleTail() {
        return "\n\n[blockquote]来自 [url=https://github.com/NashLegend/SourceWall/blob/master/README.md]" + defaultDisplayName + "[/url][/blockquote]";
    }

    /**
     * 返回自定义参数{}尾巴
     *
     * @return 参数化的尾巴
     */
    public static String getParametricCustomSimpleTail() {
        String tail = SharedUtil.readString(Consts.key_Custom_Tail, "");
        if (!tail.trim().equals("")) {
            String reg = "##([^#]+)##";
            tail = tail.replaceAll(reg, "[url=https://github.com/NashLegend/SourceWall/blob/master/README.md]$1[/url]");
        }
        return "\n\n[blockquote]" + tail + "[/blockquote]";
    }

    public static String getDefaultPlainTail() {
        return "来自 " + defaultDisplayName;
    }

    public static String getPhonePlainTail() {
        String mTypeString = android.os.Build.MODEL == null ? AppApplication.getApplication().getString(R.string.unknown_phone)
                : android.os.Build.MODEL;
        return "来自 " + mTypeString;
    }
}
