package pl.sda.refactorapp.service;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

final class EvnHelper {

    public static void setEnvironmentVariables(Map<String, String> environments) throws Exception {
        final var classes = Collections.class.getDeclaredClasses();
        Map<String, String> env = System.getenv();
        for(final var clazz : classes) {
            if("java.util.Collections$UnmodifiableMap".equals(clazz.getName())) {
                Field field = clazz.getDeclaredField("m");
                field.setAccessible(true);
                Object obj = field.get(env);
                Map<String, String> map = (Map<String, String>) obj;
                map.clear();
                map.putAll(environments);
            }
        }
    }
}
