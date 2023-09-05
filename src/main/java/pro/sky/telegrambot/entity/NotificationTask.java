package pro.sky.telegrambot.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class NotificationTask {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private long chatId;
    private String textMessage;
    private LocalDateTime sendingTime;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getchatId() {
        return chatId;
    }

    public void setchatId(long chatId) {
        this.chatId = chatId;
    }

    public String gettextMessage() {
        return textMessage;
    }

    public void settextMessage(String textMessage) {
        this.textMessage = textMessage;
    }

    public LocalDateTime getsendingTime() {
        return sendingTime;
    }

    public void setsendingTime(LocalDateTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && chatId == that.chatId && Objects.equals(textMessage, that.textMessage) && Objects.equals(sendingTime, that.sendingTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, textMessage, sendingTime);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chatId=" + chatId +
                ", textMessage='" + textMessage + '\'' +
                ", sendingTime=" + sendingTime +
                '}';
    }
}
