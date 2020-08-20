package com.wuguangxin.demo.umengsocial;

import android.content.Context;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.statistics.common.DeviceConfig;


/**
 * 友盟管理器
 * Created by wuguangxin on 17/5/11.
 */
public class UMengManager {

    // 修改为对应的APP申请的KEY
    private final static String APP_KEY = "586c9fae1061d2766e0000a7";

    /**
     * 初始化友盟SDK
     * @param context 上下文
     */
    public static void init(Context context){
        /*
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);

        /*
         * 注意: 即使您已经在AndroidManifest.xml中配置过appkey和channel值，也需要在App代码中调
         * 用初始化接口（如需要使用AndroidManifest.xml中配置好的appkey和channel值，
         * UMConfigure.init调用中appkey和channel参数请置为null）。
         * 最后个参数pushSecret：基础push业务时，才需要传，否则传null
         */
        UMConfigure.init(context, APP_KEY, "umeng", UMConfigure.DEVICE_TYPE_PHONE , null);

        /*
         * 首次使用【友盟+】统计SDK的用户选用AUTO模式采集页面数据，仅需执行SDK初始化函数后调用。
         * 但对于非Activity，需要手动调用：
         * public static void onPageStart(String viewName);
         * public static void onPageEnd(String viewName);
         * viewName：页面名称，一般用类名即可
         *
         * 详情：https://developer.umeng.com/docs/119267/detail/118588
         */
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO); // 自动采集
    }

    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    public static void onPause(Context context) {
        MobclickAgent.onPause(context);
    }

    public static String getTestDeviceInfo(Context context){
        try {
            if(context != null){
                return String.format("{\"device_id\":\"%s\",\"mac\":\"%s\"}",
                        DeviceConfig.getDeviceIdForGeneral(context),
                        DeviceConfig.getMac(context));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
