package com.changgou.goods.controller;

import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("apple", "cat", "banana", "dog");
       /* Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.compareTo(b);
            }
        });*/
      /*  Collections.sort(names,(String a,String b)  -> {
            return b.compareTo(a);
        });*/
//      Collections.sort(names,(a,b)->b.compareTo(a));
//      System.out.println(names);

        String[] arrayA = new String[] { "1", "2", "3", "4"};
        String[] arrayB = new String[] { "3", "4", "5", "6" };
        List<String> listA = Arrays.asList(arrayA);
        List<String> listB = Arrays.asList(arrayB);

        /*System.out.println(CollectionUtils.union(listA,listB));
        System.out.println(CollectionUtils.intersection(listA,listB));
        System.out.println(CollectionUtils.disjunction(listA,listB));
        System.out.println(CollectionUtils.subtract(listA,listB));*/


        List<String> collect = listA.stream().filter(item -> listB.contains(item)).collect(Collectors.toList());
        List<String> collect1 = listA.stream().filter(item -> !listB.contains(item)).collect(Collectors.toList());
        System.out.println(collect);
        System.out.println(collect1);

        //并集
        List<String> listA1 = listA.parallelStream().collect(Collectors.toList());
        List<String> listB2 = listB.parallelStream().collect(Collectors.toList());

        listA1.addAll(listB2);

        //去重
        List<String> list = listA1.stream().distinct().collect(Collectors.toList());
        System.out.println(list);



    }

}
