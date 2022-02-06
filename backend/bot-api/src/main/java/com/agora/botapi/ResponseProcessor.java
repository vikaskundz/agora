package com.agora.botapi;

import com.agora.botapi.data.DataStore;
import com.agora.botapi.handlers.mint.MintNFTHandler;
import com.agora.botapi.validation.EthereumAddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.agora.botapi.util.KeyBoardUtils.inlineKeyBoardButton;

@Component
public class ResponseProcessor {


    @Autowired
    private MintNFTHandler mintNFTHandler;

    public SendMessage process(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String msg = update.getMessage().getText();
        if (DataStore.hasWalletRegistered(chatId)) {

            if (MintNFTHandler.ENTER_THE_URL_OF_IMAGE.equals(update.getMessage().getReplyToMessage()) ||
                    MintNFTHandler.ENTER_THE_NAME_OF_NFT.equals(update.getMessage().getReplyToMessage()) ||
                    MintNFTHandler.ENTER_THE_DESCRIPTION_OF_NFT.equals(update.getMessage().getReplyToMessage())) {
                return mintNFTHandler.handle(update);
            }

            /** REGISTERED USER**/
            return mainMenuMsg(update);

        } else if (EthereumAddressValidator.isValidAddress(msg.toLowerCase().trim())) {
            /** REGISTER**/
            DataStore.registerWallet(chatId, msg);
            return mainMenuMsg(update);
        } else {
            return defaultMsg(update);
        }
    }

    private SendMessage mainMenuMsg(Update update) {
        SendMessage message = new SendMessage();
        message.setText("Main Menu");

        List<InlineKeyboardButton> rowInline1 = new ArrayList();
        rowInline1.add(inlineKeyBoardButton("My Profile", "My_Profile"));
        rowInline1.add(inlineKeyBoardButton("Explore NFTs", "Explore_NFTs"));
        rowInline1.add(inlineKeyBoardButton("Mint NFTs", "Mint_NFTs"));
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList();
        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);

        return message;
    }


    public static SendMessage defaultMsg(Update update) {
        SendMessage message = new SendMessage();
        Chat chat = update.getMessage().getChat();
        message.setText("HOWDY... " + chat.getFirstName() + " " + chat.getLastName() + "!!!Please add your valid wallet address to be a real Aghora");
        return message;
    }


    private SendMessage letsGo(Update update) {
        SendMessage message = new SendMessage();
        message.setText("letsGo");
        return message;
    }
}
