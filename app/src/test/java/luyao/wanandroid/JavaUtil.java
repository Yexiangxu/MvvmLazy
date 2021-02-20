package luyao.wanandroid;

import java.util.HashMap;
import java.util.Map;

public class JavaUtil {
    public static void testmap(String var0, String var1, HashMap<String, Object> var2) {
        Map<String,Object> map= new HashMap<>();
        map.put("en", var0);
        map.put("sub_en", var1);
        map.put("kv", var2);
        System.out.println("map="+map.toString());
    }
}
