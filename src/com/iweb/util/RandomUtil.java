package com.iweb.util;

import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

public class RandomUtil {
    //返回start-end之间的10个不重复的随机整数
    public static Set<Integer> getRandoms(int start,int end) {
        Random ran = new Random();
        Set<Integer> set = new LinkedHashSet<>();
        while (set.size() != 10) {
          int num=(int)(start+(end-start+1)*Math.random());
            set.add(num);
        }
        return set;
    }

}
