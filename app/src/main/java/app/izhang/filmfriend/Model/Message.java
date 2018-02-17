package app.izhang.filmfriend.Model;

import java.util.Date;

/**
 * Created by ivanzhang on 2/17/18.
 * - Message model. Contains information about a message and the owner.
 */

public class Message {
    private String message; // message contents
    private String owner; // ID of the message owner
    private String ownerUsername; // Username of the message owner
    private Date date; // date when the message was posted
    private String groupId; // ID of the group that the message is in
}
