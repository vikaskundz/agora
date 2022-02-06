package com.agora.botapi;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

public class MyAmazingBot extends TelegramLongPollingBot {

    public String getBotUsername() {
        return "agora_nft_bot";
    }


    public String getBotToken() {
        return "5173952753:AAFhjz_hKjuVgcjuj8X8VhFQ7qCHLIhQQ0Y";
    }


    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage();
            message.setText("You send /start");
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList();
            List<InlineKeyboardButton> rowInline1 = new ArrayList();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("My Profile");
            inlineKeyboardButton.setCallbackData("update_msg_text");
            rowInline1.add(inlineKeyboardButton);
            inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("Mint NFT");
            inlineKeyboardButton.setCallbackData("update_msg_text");
            rowInline1.add(inlineKeyboardButton);
            List<InlineKeyboardButton> rowInline2 = new ArrayList();
            inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("Update message text");
            inlineKeyboardButton.setCallbackData("update_msg_text");
            rowInline2.add(inlineKeyboardButton);
            inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("Update message text");
            inlineKeyboardButton.setCallbackData("update_msg_text");
            rowInline2.add(inlineKeyboardButton);

            rowsInline.add(rowInline1);
            rowsInline.add(rowInline2);
            // Set the keyboard to the markup
            // Add it to the message
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
            try {
                execute(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
            message.setChatId(update.getMessage().getChatId().toString());
            String command = update.getMessage().getText();
            message.setText(command);
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        } else if (update.hasCallbackQuery()) {
            // Set variables
            String call_data = update.getCallbackQuery().getData();
            long message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();

            if (call_data.equals("update_msg_text")) {
                String answer = "Updated message text";
                EditMessageText new_message = new EditMessageText();
                new_message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
                new_message.setMessageId(Long.valueOf(message_id).intValue());
                new_message.setText("<b><i>Test</i></b>");
                new_message.enableHtml(true);


                SendMediaGroup sendMediaGroup = new SendMediaGroup();
                sendMediaGroup.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
                List<InputMedia> inputMediaList = new ArrayList<InputMedia>();
                String[] data = {
                        "https://picsum.photos/id/0/5616/3744",
                        "https://picsum.photos/id/1/5616/3744",
                        "https://picsum.photos/id/10/2500/1667",
                        "https://picsum.photos/id/100/2500/1656",
                        "https://picsum.photos/id/1000/5626/3635",
                        "https://picsum.photos/id/1001/5616/3744",
                        "https://picsum.photos/id/1002/4312/2868",
                        "https://picsum.photos/id/1003/1181/1772",
                        "https://picsum.photos/id/1004/5616/3744",
                        "https://picsum.photos/id/1005/5760/3840",
                        "https://picsum.photos/id/1006/3000/2000",
                        "https://picsum.photos/id/1008/5616/3744",
                        "https://picsum.photos/id/1009/5000/7502",
                        "https://picsum.photos/id/101/2621/1747",
                        "https://picsum.photos/id/1010/5184/3456",
                        "https://picsum.photos/id/1011/5472/3648",
                        "https://picsum.photos/id/1012/3973/2639",
                        "https://picsum.photos/id/1013/4256/2832",
                        "https://picsum.photos/id/1014/6016/4000",
                        "https://picsum.photos/id/1015/6000/4000",
                        "https://picsum.photos/id/1016/3844/2563",
                        "https://picsum.photos/id/1018/3914/2935",
                        "https://picsum.photos/id/1019/5472/3648",
                        "https://picsum.photos/id/102/4320/3240",
                        "https://picsum.photos/id/1020/4288/2848",
                        "https://picsum.photos/id/1021/2048/1206",
                        "https://picsum.photos/id/1022/6000/3376",
                        "https://picsum.photos/id/1023/3955/2094",
                        "https://picsum.photos/id/1024/1920/1280",
                        "https://picsum.photos/id/1025/4951/3301"};

                for (int i = 0; i < 10; i++) {
                    SendPhoto message = null;
                    message = new SendPhoto();
                    message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
                    message.setPhoto(new InputFile().setMedia(data[i % 10]));
                    message.setCaption(data[i % 10]);
                    InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
                    List<InlineKeyboardButton> rowInline1 = new ArrayList();
                    List<List<InlineKeyboardButton>> rowsInline = new ArrayList();
                    InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
                    inlineKeyboardButton.setText("Details");
                    inlineKeyboardButton.setCallbackData("imageSelect");
                    rowInline1.add(inlineKeyboardButton);
                    rowsInline.add(rowInline1);
                    markupInline.setKeyboard(rowsInline);
                    message.setReplyMarkup(markupInline);
                    //sendMediaGroup.setMedias(inputMediaList);
                    try {
                        execute(message);
                        //execute(new_message);

                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
            } else if (call_data.equals("imageSelect")) {
                SendMessage sendMessage = new SendMessage();
                message_id = update.getCallbackQuery().getMessage().getMessageId();
                String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
                sendMessage.setChatId(chatId);
                sendMessage.setText("SAAKU MANEGE HOGU");
                try {
                    execute(sendMessage);
                    //execute(new_message);

                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

            }

        }
    }

}