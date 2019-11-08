package br.com.mh3d.leitorqrcodeatividade.utils;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.TimeZone;

import java.text.ParseException;
import java.util.Date;


public final class DateFormatUtils {
    /**
     * get datetime
     * */
    public  String getUnixDateNow() {
        Long tsLong = (System.currentTimeMillis()/1000);
        String unixTime = tsLong.toString();
        return unixTime;
    }

    public  String convertUnixStringToDateString(String unixStamp){
        if(unixStamp != null) {
            long unixSeconds = Integer.parseInt(unixStamp);
            Date date = new Date(unixSeconds * 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd - HH:mm:ss");
            sdf.setTimeZone(TimeZone.getTimeZone("GMT-3"));
            String dateString = sdf.format(date);
            return dateString;
        } else return null;
    }

    public  String convertDateStringtoUnixString(String dateString){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(TimeZone.getTimeZone("GMT-3"));
        try {
            Date dt = formatter.parse(dateString);
            String epoch = String.valueOf(dt.getTime()/1000);
            return epoch;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


}
