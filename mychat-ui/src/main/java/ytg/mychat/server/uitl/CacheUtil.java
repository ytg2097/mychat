package ytg.mychat.server.uitl;

import ytg.mychat.server.view.chat.element.talk.Talk;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-19
 **/
public class CacheUtil {

    public static Map<String, Talk> talkMap = new ConcurrentHashMap<>(16);
}
