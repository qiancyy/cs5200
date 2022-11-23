package HouseSelling.Model;

import java.util.Date;

//TODO from to should be Buyer and Seller
public class Message {
  private int messageId;
  private Date sendTime;
  private String content;
  private User from;
  private User to;

  public Message(int messageId, Date sendTime, String content, User fromUser, User toUser) {
    this.messageId = messageId;
    this.sendTime = sendTime;
    this.content = content;
    this.from = fromUser;
    this.to = toUser;
  }

  @Override
  public String toString() {
    return "Message{" +
        "messageId=" + messageId +
        ", sendTime=" + sendTime +
        ", content='" + content + '\'' +
        ", fromUser=" + from +
        ", toUser=" + to +
        '}';
  }

  public int getMessageId() {
    return messageId;
  }

  public void setMessageId(int messageId) {
    this.messageId = messageId;
  }

  public Date getSendTime() {
    return sendTime;
  }

  public void setSendTime(Date sendTime) {
    this.sendTime = sendTime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public User getFrom() {
    return from;
  }

  public void setFrom(User from) {
    this.from = from;
  }

  public User getTo() {
    return to;
  }

  public void setTo(User to) {
    this.to = to;
  }
}
