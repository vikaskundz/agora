package com.agora.botapi.util;

import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SendPhotoUtil {
    public static List<SendPhoto> sendMessage(Update update, List<String> cachedUrlList, List<String> tokenIdList, List<String> contractList) {
        List<SendPhoto> sendMessages = new ArrayList<>();
        for (int i = 0; i < cachedUrlList.size(); i++) {
            SendPhoto message = new SendPhoto();
            message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
            if (cachedUrlList.get(i) == null) {
                continue;
            }
            message.setPhoto(new InputFile().setMedia(cachedUrlList.get(i)));
            InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
            List<InlineKeyboardButton> rowInline1 = new ArrayList();
            List<List<InlineKeyboardButton>> rowsInline = new ArrayList();
            InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
            inlineKeyboardButton.setText("Details");
            Map<String, String> data = new HashMap<>();
            data.put("t", tokenIdList.get(i));
            data.put("c", contractList.get(i));
            inlineKeyboardButton.setCallbackData("DETAILS_NFT?" + DataCodec.encode(data));
            rowInline1.add(inlineKeyboardButton);
            rowsInline.add(new ArrayList<>());
            rowsInline.add(rowInline1);
            rowsInline.add(new ArrayList<>());
            markupInline.setKeyboard(rowsInline);
            message.setReplyMarkup(markupInline);
            sendMessages.add(message);
        }
        return sendMessages;
    }
}
