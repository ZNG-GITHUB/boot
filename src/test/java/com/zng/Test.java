package com.zng;

import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by John.Zhang on 2017/12/29.
 */
public class Test {
    public static void main(String[] args) {

        List<PaTime> timeList = new ArrayList<>();

        try {
            Date s = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-2");
            Date end = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-8");
            PaTime p1 = new PaTime(s,end,"1");
            timeList.add(p1);

            s = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-3");
            end = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-5");
            PaTime p2 = new PaTime(s,end,"2");
            timeList.add(p2);

            s = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-2");
            end = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-9");
            PaTime p3 = new PaTime(s,end,"3");
            timeList.add(p3);

            s = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-10");
            end = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-11");
            PaTime p4 = new PaTime(s,end,"2");
            timeList.add(p4);

            s = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-9");
            end = new SimpleDateFormat("yyyy-MM-dd").parse("2017-1-11");
            PaTime p5= new PaTime(s,end,"3");
            timeList.add(p5);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //按照开始时间进行排序
        Collections.sort(timeList, new Comparator<PaTime>() {
            @Override
            public int compare(PaTime o1, PaTime o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        });

        //先分类型去重
//        List<PaTime> newList = getDistint(timeList);

        //多类型整合
        List<PaTime> newList_ = getSum(timeList);

        for(PaTime p : newList_){
            System.out.println((p.getEndTime().getTime()-p.getStartTime().getTime())/86400+"--------"+p.getType());
        }

    }

    private static List<PaTime> getSum(List<PaTime> list) {

        Collections.sort(list, new Comparator<PaTime>() {
            @Override
            public int compare(PaTime o1, PaTime o2) {
                return o1.getStartTime().compareTo(o2.getStartTime());
            }
        });

        List<PaTime> timeList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(PaTime p : list){
                if(CollectionUtils.isEmpty(timeList)){
                    timeList.add(p);
                    continue;
                }
                List<PaTime> t = new ArrayList<>();
                for(PaTime pt : timeList){
                    if((pt.getStartTime().before(p.getStartTime()) || pt.getStartTime().equals(p.getStartTime())) && (pt.getEndTime().after(p.getEndTime()) || pt.getEndTime().equals(p.getEndTime()))){
                        if(pt.getStartTime().before(p.getStartTime())){
                            t.add(new PaTime(pt.getStartTime(),p.getStartTime(),pt.getType()));
                        }
                        t.add(p);
                        if(pt.getEndTime().after(p.getEndTime())){
                            t.add(new PaTime(p.getEndTime(),pt.getEndTime(),pt.getType()));
                        }
                        continue;
                    }

                    if((pt.getStartTime().before(p.getStartTime()) || pt.getStartTime().equals(p.getStartTime())) && pt.getEndTime().after(p.getStartTime()) && pt.getEndTime().before(p.getEndTime())){
                        if(pt.getStartTime().before(p.getStartTime())){
                            t.add(new PaTime(pt.getStartTime(),p.getStartTime(),pt.getType()));
                        }
                        t.add(p);
                        continue;
                    }

                    if(p.getStartTime().before(pt.getStartTime()) && pt.getStartTime().before(p.getEndTime()) && pt.getEndTime().after(p.getEndTime())){
                        t.add(new PaTime(p.getEndTime(),pt.getEndTime(),pt.getType()));
                        continue;
                    }

                    t.add(pt);
                }
                PaTime last = timeList.get(timeList.size()-1);
                if((p.getStartTime().after(last.getEndTime()) || p.getStartTime().equals(last.getEndTime()))){
                    t.add(p);
                }
                timeList = t;
            }
        }

        return timeList;
    }

    private static List<PaTime> getDistint(List<PaTime> timeList) {
        List<PaTime> list = new ArrayList<>();
        for(PaTime paTime : timeList){
            if(CollectionUtils.isEmpty(list)){
                list.add(paTime);
                continue;
            }

            for(PaTime p : list){
                if((paTime.getStartTime().before(p.getEndTime()) || paTime.getStartTime().equals(p.getEndTime())) && (paTime.getEndTime().after(p.getEndTime()) || paTime.getEndTime().equals(p.getEndTime()))){
                    p.setEndTime(paTime.getEndTime());
                    break;
                }
            }

            PaTime last = list.get(list.size()-1);
            if(paTime.getStartTime().after(last.getEndTime())){
                list.add(paTime);
            }
        }

        return  list;
    }
}
