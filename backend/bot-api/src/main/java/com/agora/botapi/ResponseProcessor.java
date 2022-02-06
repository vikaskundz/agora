package com.agora.botapi;

import com.agora.botapi.data.DataStore;
import com.agora.botapi.handlers.CounterfeitDetectNFTHandler;
import com.agora.botapi.handlers.ExploreNFTHandler;
import com.agora.botapi.handlers.MyProfileNFTHandler;
import com.agora.botapi.handlers.mint.MintNFTHandler;
import com.agora.botapi.utils.KeyBoardUtils;
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


@Component
public class ResponseProcessor {


    @Autowired
    private MintNFTHandler mintNFTHandler;
    @Autowired
    private CounterfeitDetectNFTHandler counterfeitDetectNFTHandler;

    public SendMessage process(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        String msg = update.getMessage().getText();
        if (DataStore.hasWalletRegistered(chatId)) {

            if (update.getMessage().getReplyToMessage() != null && (MintNFTHandler.ENTER_THE_URL_OF_IMAGE.equals(update.getMessage().getReplyToMessage().getText()) ||
                    MintNFTHandler.ENTER_THE_WALLET_ADDR_OF_YOUR_BUDDY.equals(update.getMessage().getReplyToMessage().getText()) ||
                    MintNFTHandler.ENTER_THE_NAME_OF_NFT.equals(update.getMessage().getReplyToMessage().getText()) ||
                    MintNFTHandler.ENTER_THE_DESCRIPTION_OF_NFT.equals(update.getMessage().getReplyToMessage().getText()))) {
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
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList();
        List<InlineKeyboardButton> rowInline1 = new ArrayList();
        rowInline1.add(KeyBoardUtils.inlineKeyBoardButton("My NFTs", MyProfileNFTHandler.MY_PROFILE_OPTION));
        rowInline1.add(KeyBoardUtils.inlineKeyBoardButton("Explore NFTs", ExploreNFTHandler.EXPLORE_NFTS_OPTION));
        rowsInline.add(rowInline1);
        List<InlineKeyboardButton> rowInline2 = new ArrayList();
        rowInline2.add(KeyBoardUtils.inlineKeyBoardButton("Mint NFTs", MintNFTHandler.MINT_NFTS_OPTION));
        rowInline2.add(KeyBoardUtils.inlineKeyBoardButton("Detect Counterfeit NFTs", CounterfeitDetectNFTHandler.DETECT_COUNTER_NFTS_OPTION));
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        rowsInline.add(rowInline2);
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