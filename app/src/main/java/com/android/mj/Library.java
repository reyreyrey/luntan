package com.android.mj;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.mj.chat.CustomUserProvider;
import com.android.mj.exception.CrashHandler;
import com.android.mj.model.Post;
import com.android.mj.model.PostSave;
import com.android.mj.model.SystemNotify;
import com.android.mj.model.SystemNotifyReadHistory;
import com.android.mj.model.UserModel;
import com.android.mj.tools.LogUtil;
import com.android.mj.tools.activity_manager.ActivityManager;
import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.PushService;
import com.avos.avoscloud.im.v2.AVIMClient;
import com.avos.avoscloud.im.v2.AVIMException;
import com.avos.avoscloud.im.v2.callback.AVIMClientCallback;

import java.util.List;

import cn.bmob.v3.Bmob;
import cn.jpush.android.api.JPushInterface;
import cn.leancloud.chatkit.LCChatKit;

/**
 * author: Rea.X
 * date: 2017/12/2.
 */
//阿凡达数：rey666888    666888aa
    //https://www.avatardata.cn/UserCenter/My
public class Library {

    private static Application application;
    private static boolean isDebug;

    public static boolean isDebug() {
        return isDebug;
    }

    public static Application get() {
        return application;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }

    private static final String APP_ID = "1J7dxpxtmBe9BKL59e7v3d3p-gzGzoHsz";
    private static final String APP_KEY = "QXU1cpfBzN53LWNOjyHUlngP";

    public static void init(Application application, String bmobkey, boolean isDebug) {
        Library.application = application;
        Library.isDebug = isDebug;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(application);
        JPushInterface.setDebugMode(BuildConfig.DEBUG);
        JPushInterface.init(application);
        if (!TextUtils.isEmpty(bmobkey))
            Bmob.initialize(application, bmobkey);
        regModels();
        AVUser.alwaysUseSubUserClass(UserModel.class);
        LCChatKit.getInstance().setProfileProvider(CustomUserProvider.getInstance());
        LCChatKit.getInstance().init(application, APP_ID, APP_KEY);
        AVOSCloud.initialize(application, APP_ID, APP_KEY);
        AVIMClient.setAutoOpen(false);
        AVOSCloud.setDebugLogEnabled(BuildConfig.DEBUG);
        ActivityManager.startWatcher(application);
//        queryRuntimeConfig();
        loginChat(null);
    }

    private static void regModels() {
        AVUser.registerSubclass(UserModel.class);
        AVObject.registerSubclass(SystemNotify.class);
        AVObject.registerSubclass(SystemNotifyReadHistory.class);
        AVObject.registerSubclass(Post.class);
        AVObject.registerSubclass(PostSave.class);
    }

    private static boolean isLoginChat = false;

    public static boolean isLoginChat() {
        return isLoginChat;
    }

    public static boolean loginChat(final AVIMClientCallback callback) {
        if (isLoginChat) return false;
        AVUser avUser = AVUser.getCurrentUser();
        if (avUser == null) return false;
        LCChatKit.getInstance().open(avUser.getObjectId(), new AVIMClientCallback() {
            @Override
            public void done(AVIMClient avimClient, AVIMException e) {
                if (callback != null) {
                    callback.done(avimClient, e);
                }
                if (null == e) {
                    isLoginChat = true;
                } else {
                    isLoginChat = false;
                }
            }
        });
        return true;
    }

    //检查配置是否过期，过期就不再让使用了
    private static void queryRuntimeConfig() {
        AVQuery<AVObject> avquery = new AVQuery<>("RuntimeConfig");
        avquery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e != null) return;
                if (list != null && list.size() != 0) {
                    for (AVObject config : list) {
                        String packagename = config.getString("packagename");
                        boolean isenable = config.getBoolean("isenable");
                        LogUtil.e("packagename::" + packagename + " isenable:" + isenable);
                        if (packagename.equalsIgnoreCase(application.getPackageName()) && !isenable) {
                            Library.setIsTimeout(true);
                            break;
                        }
                    }
                }
            }
        });
    }

    private static boolean isTimeout;

    public static boolean isTimeout() {
        return isTimeout;
    }

    public static void setIsTimeout(boolean isTimeout) {
        Library.isTimeout = isTimeout;
    }
}
