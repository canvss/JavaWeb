package canvs.ssm.utils;


import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {
    public static Date dateFormat(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parse = simpleDateFormat.parse(date);
        return new Date(parse.getTime());
    }

    public static boolean isEmpty(String str) {
        return !((str == null) || (str.equals("")));
    }
}
