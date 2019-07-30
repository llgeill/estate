package cn.stylefeng.guns.modular.estate.entity;

import java.util.Comparator;
import java.util.Map;

public class RenderComparator implements Comparator<Map<Object,Object>> {


    @Override
    public int compare(Map<Object, Object> o1, Map<Object, Object> o2) {
       if( (Integer)o1.get("total")> (Integer)o2.get("total")){
           return 1;
       }else if((Integer)o1.get("total")<(Integer)o2.get("total")){
           return -1;
       }else{
           return 0;
       }
    }
}
