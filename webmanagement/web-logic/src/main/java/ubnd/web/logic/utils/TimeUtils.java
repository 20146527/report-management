package ubnd.web.logic.utils;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static Timestamp getCurrentTimestamp(){
        Date date = new Date();
        long time = date.getTime();
        Timestamp timestamp = new Timestamp(time);
        //out: T
        return timestamp;
    }

    public static String getCurrentTime(){
        Date date = new Date();
        long time = date.getTime();
        //out: 1569403189850
        return String.valueOf(time);
    }



    public static Timestamp getTimestampByStringLDT(String timeInput){
        String localDateTime = timeInput.replace("T", " ");
//        localDateTime += ":00";
        if(localDateTime.length()==16){
            localDateTime += ":00";
        }
        return Timestamp.valueOf(localDateTime);
    }

    public static Timestamp datetimePicker2Timestamp(String time){
        if(time.equals("")){
            return null;
        }else {
            String[] datetime = time.split(" ");
            String[] ddmmyyyy = datetime[0].split("/");
            String[] hhmm = datetime[1].split(":");
            String a = datetime[2];
            time = ddmmyyyy[2] + "-" + ddmmyyyy[1] + "-" + ddmmyyyy[0] + " ";
            int hh = Integer.parseInt(hhmm[0]);
            if(a.equals("PM") || a.equals("pm")){
                if(hh != 12){
                    hh += 12;
                }
                time += hh + ":" +hhmm[1]+":00";
            }else {
                if(hh == 12){
                    time += "00:"+hhmm[1]+":00";
                }else {
                    time += datetime[1] + ":00";
                }
            }
            return Timestamp.valueOf(time);
        }
    }

    public static String timestamp2DatetimePicker(String timestamp){
        String[] datetime = timestamp.split(" ");
        String[] yyyymmdd = datetime[0].split("-");
        String[] hhmmss = datetime[1].split(":");
        int hh = Integer.parseInt(hhmmss[0]);
        if(hh == 0){
            return yyyymmdd[2]+"/"+yyyymmdd[1]+"/"+yyyymmdd[0]+" " + "12:"+hhmmss[1] + " AM";
        }else if(hh >= 12){
            if(hh > 12){
                hh -= 12;
            }
            return yyyymmdd[2]+"/"+yyyymmdd[1]+"/"+yyyymmdd[0]+" " + hh+":"+hhmmss[1] + " PM";
        }else {
            return yyyymmdd[2]+"/"+yyyymmdd[1]+"/"+yyyymmdd[0]+" " + hh+":"+hhmmss[1] + " AM";
        }
    }
}
