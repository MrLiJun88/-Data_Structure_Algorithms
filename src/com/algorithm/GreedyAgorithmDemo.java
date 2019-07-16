package com.algorithm;

import java.util.*;
/**
 * 实现贪婪算法
 * 算法实现：
 *  a) 遍历所有的广播电台, 找到一个覆盖了最多未覆盖的地区的电台(此电台可能包含一些已覆盖的地区，但没有关系）
 *  b) 将这个电台加入到一个集合中(比如ArrayList), 想办法把该电台覆盖的地区在下次比较时去掉。
 *  c) 重复第1步直到覆盖了全部的地区
 */
public class GreedyAgorithmDemo {
    public static void main(String[] args) {
        /**创建广播电台*/
        Map<String, Set<String>> broadcasts = new HashMap<>();
        Set<String> city1 = new HashSet<>();
        city1.add("北京");
        city1.add("上海");
        city1.add("天津");
        Set<String> city2 = new HashSet<>();
        city2.add("广州");
        city2.add("北京");
        city2.add("深圳");
        Set<String> city3 = new HashSet<>();
        city3.add("成都");
        city3.add("上海");
        city3.add("杭州");
        Set<String> city4 = new HashSet<>();
        city4.add("上海");
        city4.add("天津");
        Set<String> city5 = new HashSet<>();
        city5.add("杭州");
        city5.add("大连");
        broadcasts.put("k1",city1);
        broadcasts.put("k2",city2);
        broadcasts.put("k3",city3);
        broadcasts.put("k4",city4);
        broadcasts.put("k5",city5);

        Set<String> allAreas = new HashSet<>();
        /**将所有城市加入到 allAreas集合中*/
        for(Set<String> value:broadcasts.values()){
            for(String city : value){
                allAreas.add(city);
            }
        }
        /**创建一个集合，存放结果集合*/
        List<String> selects = new ArrayList<>();
        /**定义一个临时的集合，用于在遍历的过程中，存放遍历过程中电台覆盖的地区与allAreas的交集*/
        Set<String> tempSet = new HashSet<>();
        /**定义一个maxKey，保存在一次遍历中电台覆盖的最多key，将其加入到selects集合中*/
        String maxKey = null;
        while (allAreas.size() > 0){
            /**每次需要将maxKey置空*/
            maxKey = null;
            for(String key : broadcasts.keySet()){
                /**tempSet是一个临时集合，所以每次需要将内容清空*/
                tempSet.clear();
                /**取出当前k所覆盖的地区,即 K1	-> "北京", "上海", "天津"*/
                Set<String> citySet = broadcasts.get(key);
                tempSet.addAll(citySet);
                /**求出tempSet与allAreas的交集，并赋给tempSet*/
                tempSet.retainAll(allAreas);
                /***/
                boolean condition = tempSet.size() > 0 &&
                        (maxKey == null || tempSet.size() > broadcasts.get(maxKey).size());
                if(condition){
                    maxKey = key;
                }
            }
            if(null != maxKey){
                selects.add(maxKey);
                /**从allAreas集合中删除已经选择过的电台*/
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("最后选择的电台： " + selects);
    }
}
