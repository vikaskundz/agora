//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.agora.botapi.handlers;

import com.agora.botapi.data.DataStore;
import com.agora.botapi.data.TokenInfo;
import java.util.List;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class MyProfileNFTHandler {
    public static final String MY_PROFILE_OPTION = "My_Profile";

    public MyProfileNFTHandler() {
    }

    public SendMessage handle(Update update) {
        SendMessage sendMessage = new SendMessage();
        String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
        List<TokenInfo> tokens = DataStore.retrieveMintedTokenInfo(chatId);
        sendMessage.setChatId(chatId);
        sendMessage.setText(tokens.toString());
        return sendMessage;
    }
}
