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
    private long chat_id;
    private String text_message;
    private LocalDateTime sending_time;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public String getText_message() {
        return text_message;
    }

    public void setText_message(String text_message) {
        this.text_message = text_message;
    }

    public LocalDateTime getSending_time() {
        return sending_time;
    }

    public void setSending_time(LocalDateTime sending_time) {
        this.sending_time = sending_time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificationTask that = (NotificationTask) o;
        return id == that.id && chat_id == that.chat_id && Objects.equals(text_message, that.text_message) && Objects.equals(sending_time, that.sending_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chat_id, text_message, sending_time);
    }

    @Override
    public String toString() {
        return "NotificationTask{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", text_message='" + text_message + '\'' +
                ", sending_time=" + sending_time +
                '}';
    }
}
