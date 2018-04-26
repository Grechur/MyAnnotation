package com.good.zc.myannotation.anno;

import android.app.Activity;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Zc on 2018/4/23.
 */

public class ViewInjectUtils {
    public static final String TAG = "ViewInjectUtils";
    private static final String METHOD_SET_CONTENTVIEW = "setContentView";
    private static final String METHOD_FIND_VIEW_BY_ID = "findViewById";

    public static void inject(Activity activity)
    {

        injectContentView(activity);
        injectViews(activity);
        injectEvents(activity);
    }

    /**
     * 注入主布局文件
     * @param activity
     */
    private static void injectContentView(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if(contentView!=null){
            int contentLayoutId = contentView.value();
            try {
                Method method = clazz.getMethod(METHOD_SET_CONTENTVIEW,int.class);
                method.setAccessible(true);
                method.invoke(activity,contentLayoutId);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入所有的控件
     * @param activity
     */
    private static void injectViews(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            FindView findView = field.getAnnotation(FindView.class);
            if(findView != null){
                int viewId = findView.value();
                if(viewId!=-1){
                    try {
                        Method method = clazz.getMethod(METHOD_FIND_VIEW_BY_ID,int.class);
                        Object resView = method.invoke(activity,viewId);
                        field.setAccessible(true);
                        field.set(activity,resView);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    /**
     * 注入所有的事件
     *
     * @param activity
     */
    private static void injectEvents(Activity activity){
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getMethods();
        //遍历所有的方法
        for (Method method:methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation:annotations) {
                //拿到方法上的所有的注解
                Class<? extends Annotation> annotationType = annotation.annotationType();

                //如果设置为EventBase
                EventBase eventBase = annotationType.getAnnotation(EventBase.class);
                if(eventBase!=null){
                    //取出设置监听器的名称，监听器的类型，调用的方法名
                    String listenerSetter = eventBase.listenerSetter();
                    Class listenerType = eventBase.listenerType();
                    String methodeName = eventBase.methodName();
                    try {
                        //拿到Onclick注解中的value方法
                        Method aMethod = annotationType.getDeclaredMethod("value");
                        //拿到Onclick注解中的value方法
                        int[] viewIds = (int[]) aMethod.invoke(annotation,new  Object[]{});
                        //通过InvocationHandler设置代理
                        DynamicHandler handler = new DynamicHandler(activity);
                        handler.addMethod(methodeName,method);
                        Object listener = Proxy.newProxyInstance(listenerType.getClassLoader()
                                            ,new Class[]{listenerType}
                                            ,handler);
                        //遍历所有的View，设置事件
                        for (int viewId:viewIds) {
                            View view = activity.findViewById(viewId);
                            Method setEventListenerberMethod = view.getClass().getMethod(listenerSetter,listenerType);
                            setEventListenerberMethod.invoke(view,listener);
                        }

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
