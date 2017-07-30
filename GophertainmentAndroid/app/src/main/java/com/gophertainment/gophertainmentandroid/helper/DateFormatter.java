package com.gophertainment.gophertainmentandroid.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dakshsharma on 7/29/17.
 */

public final class DateFormatter {
    private DateFormatter() {}

    public static String getReleaseDateFormat(String d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date             date;
        try {
            if (d != null)  {
                date = sdf.parse(d);
                sdf = new SimpleDateFormat("E, MMM d, yyyy");
                return sdf.format(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "--";
    }
}
