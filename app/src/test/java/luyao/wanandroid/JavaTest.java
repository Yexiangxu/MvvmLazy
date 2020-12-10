package luyao.wanandroid;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class JavaTest {
    @Test
    public void main()   {
        List<String> resportList = new ArrayList<>();
        resportList.add("unlock_screen_video_xfc_home");
        resportList.add(String.valueOf(0));
        resportList.add("");
        resportList.add(String.valueOf(1233));
        commonReport(resportList);


    }
    private static void commonReport(List<String> params){
        if(params == null || params.size() < 4){
            return;
        }
        if(params.size() == 5){
            System.out.println("params1="+params);
            params.add(0, "commercialization");//type
            params.add(2, null);//taskid
            params.add(3, null);//state
            if(params.size() == 8){//当前肯定是8个，过滤垃圾数据，理论上不会发生
            }
        } else {
            //{type, action, taskid, state, p1, p2, sceneId, p4}
            params.add(0, "commercialization");//type
            params.add(2, null);//taskid
            params.add(3, null);//state     bug
            params.add(7, null);//p4预留字段
            if (params.size() == 8) {//当前肯定是8个，过滤垃圾数据，理论上不会发生
                System.out.println("params3="+params);

            }
        }
    }
}
