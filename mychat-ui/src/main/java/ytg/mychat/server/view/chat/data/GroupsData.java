package ytg.mychat.server.view.chat.data;

import lombok.Data;

/**
 * @description:
 * @author: yangtg
 * @create: 2021-01-19
 **/
@Data
public class GroupsData {

    private String groupId;

    private String groupName;

    private String groupHead;

    public GroupsData(String groupId, String groupName, String groupHead) {

        this.groupId = groupId;
        this.groupName = groupName;
        this.groupHead = groupHead;
    }
}
