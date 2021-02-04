package ytg.mychat.server.uitl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-19
 **/
public class DateUtil {

    static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date){

        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(formatter);
    }
}
