package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.entity.NotificationTask;
import pro.sky.telegrambot.notificationService.NotificationTaskService;
import pro.sky.telegrambot.repository.NotificationTaskRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private final Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final NotificationTaskRepository notificationTaskRepository;
    private final NotificationTaskService notificationTaskService;

    private final TelegramBot telegramBot;


    public TelegramBotUpdatesListener(NotificationTaskRepository notificationTaskRepository, NotificationTaskService notificationTaskService, TelegramBot telegramBot) {
        this.notificationTaskRepository = notificationTaskRepository;
        this.notificationTaskService = notificationTaskService;
        this.telegramBot = telegramBot;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {

            logger.info("Processing update: {}", update);

            Long chatId = update.message().chat().id();
            String textMessage = update.message().text();
            NotificationTask newEntry = new NotificationTask();
            Pattern pattern = Pattern.compile("([0-9\\.\\:\\s]{16})(\\s)([\\W+]+)");

            if (textMessage.contains("/start")) {
                notificationTaskService.sendMsg(chatId, "Привет, я бот-напоминание. Для корректной работы мне необходимо писать напоминания в формате: 01.01.2022 20:00 Сделать домашнюю работу");
            } else {
                Matcher matcher = pattern.matcher(textMessage);
                if (matcher.matches()) {
                    String date = matcher.group(1);
                    String item = matcher.group(3);
                    LocalDateTime dateToLocalDateTime = LocalDateTime.parse(date,
                            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));

                    newEntry.settextMessage(item);
                    newEntry.setsendingTime(dateToLocalDateTime);
                    newEntry.setchatId(chatId);
                    System.out.println(chatId);

                    notificationTaskRepository.save(newEntry);
                } else {
                    notificationTaskService.sendMsg(chatId, "Неверный формат. Пример: 01.01.2022 20:00 Сделать домашнюю работу");
                }
            }
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }
}
