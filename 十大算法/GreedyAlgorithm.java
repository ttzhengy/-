package 十大算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        // 创建广播电台,放入到Map
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        // 将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        // 加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        // allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        for (Map.Entry<String, HashSet<String>> broadcast : broadcasts.entrySet()) {
            allAreas.addAll(broadcast.getValue());
        }

        // 创建ArrayList, 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();

        // 定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖地区的交集
        HashSet<String> tempSet = new HashSet<String>();

        // 定义给maxKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的key
        // 如果maxKey 不为null , 则会加入到 selects
        String maxKey = null;
        Integer maxCount = 0;
        Integer curCount = 0;
        while (!allAreas.isEmpty()){

            maxKey = null;
            maxCount = 0;

            // 遍历broadcasts，取出相应key
            for (String key : broadcasts.keySet()) {
                tempSet.clear();
                HashSet<String> areas = broadcasts.get(key);
                tempSet.addAll(areas);
                // 求当前站点与剩余地点的交集
                tempSet.retainAll(allAreas);

                curCount = tempSet.size();
                // 如果当前交集数量大于之前最大的交集数
                if (curCount > maxCount){
                    maxKey = key;
                    maxCount = curCount;
                }
            }

            if (maxKey!=null){
                selects.add(maxKey);
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }

        System.out.println("结果"+selects);
    }
}
