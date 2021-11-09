package com.dddd.questionnaireportal.common.util.dateJava;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateHelper {

    public static Date currentDatePlusOneDay() {
        return new Date(new Date().getTime() + TimeUnit.DAYS.toMillis(1));
    }

}
