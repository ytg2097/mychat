package ytg.mychat.server.view.chat.data;

import lombok.Getter;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-19
 **/
@Getter
public class RemindCount {

    private int count = 0;


    public int incrementAndGet(){

        ++count;
        return count;
    }
}
