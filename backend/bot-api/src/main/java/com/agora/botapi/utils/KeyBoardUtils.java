package com.agora.botapi.utils;



import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class KeyBoardUtils {
    public KeyBoardUtils() {
    }

    public static InlineKeyboardButton inlineKeyBoardButton(String buttonTxt, String callBackData) {
        InlineKeyboardButton inlineKeyboardButton = new InlineKeyboardButton();
        inlineKeyboardButton.setText(buttonTxt);
        inlineKeyboardButton.setCallbackData(callBackData);
        return inlineKeyboardButton;
    }
}