package com.dddd.questionnaireportal.common.util.date;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHelper {

    public static Date cuurentDatePlusOneDay(){
        return new Date(new Date().getTime() + TimeUnit.DAYS.toMillis( 1 ));
    }

}
