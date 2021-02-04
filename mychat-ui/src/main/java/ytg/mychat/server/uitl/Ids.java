package ytg.mychat.server.uitl;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-19
 **/
public class Ids {

    public static class TalkId{

        public static String createTalkPaneId(String id){
            return "TalkElementId_createTalkPaneId_" + id;
        }

        public static String analysisTalkPaneId(String id){
            return id.split("_")[2];
        }

        public static String createInfoBoxListId(String id){
            return "TalkElementId_createInfoBoxListId_" + id;
        }

        public static String analysisInfoBoxListId(String id){
            return id.split("_")[2];
        }

        public static String createMsgDataId(String id){
            return "TalkElementId_createMsgDataId_" + id;
        }

        public static String analysisMsgDataId(String id){
            return "TalkElementId_MsgDataId_" + id;
        }

        public static String createMsgSketchId(String id) {
            return "ElementTalkId_createMsgSketchId_" + id;
        }

        public static String analysisMsgSketchId(String id) {
            return id.split("_")[2];
        }

        public static String createFriendGroupId(String id) {
            return "ElementTalkId_createFriendGroupId_" + id;
        }

        public static String analysisFriendGroupId(String id) {
            return id.split("_")[2];
        }


    }
}
