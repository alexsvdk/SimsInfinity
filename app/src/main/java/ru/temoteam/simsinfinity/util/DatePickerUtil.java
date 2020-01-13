package ru.temoteam.simsinfinity.util;

import android.icu.util.Calendar;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DatePickerUtil {

    public final static SimpleDateFormat
            d = new SimpleDateFormat("dd"),
            m = new SimpleDateFormat("MM"),
            y = new SimpleDateFormat("yyyy");

    public static long datePicker2Date(DatePicker datePicker){

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                    0, 0, 0);
            return calendar.getTimeInMillis();
        }else {
            java.util.Calendar calendar = java.util.Calendar.getInstance();
            calendar.set(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth(),
                    0, 0, 0);
            return calendar.getTimeInMillis();
        }

    }

    public static void updateDP(DatePicker datePicker, Date date){
        datePicker.updateDate(Integer.parseInt(y.format(date)),
                Integer.parseInt(m.format(date))-1,
                Integer.parseInt(d.format(date)));
    }
}
