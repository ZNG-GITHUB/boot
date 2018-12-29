package com.zng.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.Date;

/**
 * @author John.Zhang
 * @version 1.0
 * @date 2018/12/29
 */
public class DateUtil {

    private static Logger log = LoggerFactory.getLogger(DateUtil.class);

    public static Integer timeBetween(Date start, Date end) {
        return timeBetween(start,end,DateType.SECOND,true);
    }

    public static Integer timeBetween(Date start, Date end, DateType type, Boolean isUp) {

        if(start == null || end == null){
            log.error("Comparing dates cannot be empty!");
            return 0;
        }

        if(type == null){
            log.error("Comparing type cannot be empty!");
            return 0;
        }

        Calendar s = Calendar.getInstance();
        s.setTime(start);

        Calendar e = Calendar.getInstance();
        e.setTime(end);

        Integer between = 0;
        switch (type){
            case SECOND:
                BigDecimal num_s = new BigDecimal(end.getTime() - start.getTime()).divide(new BigDecimal(1000),4,RoundingMode.HALF_UP);
                if(isUp){
                    num_s = num_s.setScale(0, RoundingMode.HALF_UP);
                }
                between = num_s.intValue();
                break;
            case MINUTE:
                BigDecimal num_m = new BigDecimal(end.getTime() - start.getTime()).divide(new BigDecimal(1000*60),4,RoundingMode.HALF_UP);
                if(isUp){
                    num_m = num_m.setScale(0, RoundingMode.HALF_UP);
                }
                between = num_m.intValue();
                break;

        }

        return between;
    }
}
