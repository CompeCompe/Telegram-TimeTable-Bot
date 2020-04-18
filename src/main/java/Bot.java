import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Bot extends TelegramLongPollingBot {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new Bot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButtons(sendMessage);
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
            switch (message.getText()) {
                case "Чт пн":
                    try {
                        sendMsg(message, WorkWithDB.connectionDB(1));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case "Нч вт":
                    try {
                        sendMsg(message, WorkWithDB.connectionDB(2));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case "Чт вт":
                    try {
                        sendMsg(message, WorkWithDB.connectionDB(3));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case "Нч ср":
                    try {
                        sendMsg(message, WorkWithDB.connectionDB(4));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case "Чтв":
                    try {
                        sendMsg(message, WorkWithDB.connectionDB(6));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case "Чт пт":
                    try {
                        sendMsg(message, WorkWithDB.connectionDB(7));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                case "Cб":
                    try {
                        sendMsg(message, WorkWithDB.connectionDB(8));
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    break;
                default:
                    sendMsg(message, "Пар нет");
            }
        }

    }

    public void setButtons(SendMessage sendMessage) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("Чт пн"));
        keyboardFirstRow.add(new KeyboardButton("Нч вт"));
        keyboardFirstRow.add(new KeyboardButton("Чт вт"));
        keyboardFirstRow.add(new KeyboardButton("Нч ср"));
        keyboardFirstRow.add(new KeyboardButton("Чтв"));
        keyboardFirstRow.add(new KeyboardButton("Чт пт"));
        keyboardFirstRow.add(new KeyboardButton("Cб"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

    public String getBotUsername() {
        return "TestBot";
    }

    public String getBotToken() {
        return "1109695794:AAELcKZmYjSil5WguFb54g-UR7swXXvVXG4";
    }
}
