package com.agora.botapi.handlers.mint;

import com.agora.botapi.data.DataStore;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

import static com.agora.botapi.util.KeyBoardUtils.inlineKeyBoardButton;

@Component
public class MintNFTHandler {

    private static final String MINT_NFTS_FOR_MY_SELF = "Mint_NFTs_For_My_Self";
    private static final String MINT_NFTS_FOR_MY_SELF_WITH_IMAGE_URL = "Mint_NFTs_For_My_Self_With_Image_Url";
    private static final String MINT_NFTS_FOR_MY_OTHERS = "Mint_NFTs_For_Others";
    private static final String ENTER_THE_URL_OF_IMAGE  = "Enter the URL of image ";


    public SendMessage handle(Update update, String action) {


        if (update.getCallbackQuery() != null) {
            String callBackData = update.getCallbackQuery().getData();
            if (callBackData.contains(MINT_NFTS_FOR_MY_SELF)) {
                return customMsg(ENTER_THE_URL_OF_IMAGE, update);
            }
        }

        if (ENTER_THE_URL_OF_IMAGE.equals(update.getMessage().getReplyToMessage())) {
            String walletAddr = DataStore.getWalletAddrOfUser(update.getMessage().getChatId().toString());
            return customMsg("Minting the image: " + update.getMessage().getText() + " NOW  for address" + walletAddr + "", update);
        }


        return mintNFTDefault();


    }

    public static SendMessage customMsg(String msg, Update update) {
        SendMessage message = new SendMessage();
        message.setText(msg);
        message.setReplyMarkup(new ForceReplyKeyboard(true));
        return message;
    }


    private SendMessage mintNFTDefault() {
        SendMessage message = new SendMessage();
        message.setText("Is this new NFT for you or for your Buddy?");
        List<InlineKeyboardButton> rowInline1 = new ArrayList();
        rowInline1.add(inlineKeyBoardButton("My Self", MINT_NFTS_FOR_MY_SELF));
        rowInline1.add(inlineKeyBoardButton("My Buddy", MINT_NFTS_FOR_MY_OTHERS));
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList();
        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);


        return message;


    }

}
