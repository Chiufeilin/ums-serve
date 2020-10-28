package cn.kyt.ums.utils;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanCopyUtils {
    public static final void copyWithoutNullProperties(Object src,Object dest){
        try {
            Map<String,String> properties = org.apache.commons.beanutils.BeanUtils.describe(src);

            for (String key : properties.keySet() ){
                if ("class".equals(key)){
                    continue;
                }
                Object v = PropertyUtils.getProperty(src,key);
                if (v!=null){
                    PropertyUtils.setProperty(dest,key,v);
                }
            }
        } catch (IllegalAccessException e) {
        } catch (InvocationTargetException e) {
        } catch (NoSuchMethodException e) {
        }
    }
}
