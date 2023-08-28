package pro.sky.telegrambot.notificationService;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import org.springframework.stereotype.Service;

@Service
public class NotificationTaskService {
    private final TelegramBot telegramBot;

    public NotificationTaskService(TelegramBot telegramBot) {
        this.telegramBot = telegramBot;
    }

    public synchronized void sendMsg(Long chatId, String textMessage) {
        SendMessage sendMessage = new SendMessage(chatId, textMessage);
        SendResponse response = telegramBot.execute(sendMessage);
    }
}
