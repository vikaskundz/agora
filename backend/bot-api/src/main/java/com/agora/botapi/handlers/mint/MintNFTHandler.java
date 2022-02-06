package com.agora.botapi.handlers.mint;

import com.agora.botapi.data.DataStore;
import com.agora.botapi.data.TokenInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ForceReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.agora.botapi.data.DataStore.getTokenInfo;
import static com.agora.botapi.util.KeyBoardUtils.inlineKeyBoardButton;

@Component
public class MintNFTHandler {

    private static final String MINT_NFTS_FOR_MY_SELF = "Mint_NFTs_For_My_Self";
    private static final String MINT_NFTS_FOR_MY_SELF_WITH_IMAGE_URL = "Mint_NFTs_For_My_Self_With_Image_Url";
    private static final String MINT_NFTS_FOR_MY_OTHERS = "Mint_NFTs_For_Others";
    public static final String ENTER_THE_URL_OF_IMAGE = "Enter the URL of image ";
    public static final String ENTER_THE_NAME_OF_NFT = "Enter the name of the NFT ";
    public static final String ENTER_THE_DESCRIPTION_OF_NFT = "Enter the URL of image ";


    public SendMessage handle(Update update) {


        if (update.getCallbackQuery() != null) {
            String callBackData = update.getCallbackQuery().getData();
            if (callBackData.contains(MINT_NFTS_FOR_MY_SELF)) {
                return customMsg(ENTER_THE_NAME_OF_NFT, update);
            }
        }

        String chatId = Optional.ofNullable(update.getMessage()).map(m ->m.getChat().getId().toString()).orElse(null);
        if (StringUtils.isNotBlank(chatId)) {
            if (ENTER_THE_URL_OF_IMAGE.equals(update.getMessage().getReplyToMessage())) {
                String walletAddr = DataStore.getWalletAddrOfUser(chatId);
                TokenInfo tokenInfo = getTokenInfo(walletAddr);
                return customMsg(String.format("Minting the image with  name: {} desc: {} url: {} NOW  for address :{}", tokenInfo.getName(), tokenInfo.getDescription(), tokenInfo.getTokenUrl(), walletAddr), update);
            }

            if (ENTER_THE_NAME_OF_NFT.equals(update.getMessage().getReplyToMessage())) {
                String walletAddr = DataStore.getWalletAddrOfUser(chatId);
                TokenInfo tokenInfo = getTokenInfo(walletAddr);
                tokenInfo.setName(update.getMessage().getText());
                return customMsg(ENTER_THE_DESCRIPTION_OF_NFT, update);
            }

            if (ENTER_THE_DESCRIPTION_OF_NFT.equals(update.getMessage().getReplyToMessage())) {
                String walletAddr = DataStore.getWalletAddrOfUser(chatId);
                TokenInfo tokenInfo = getTokenInfo(walletAddr);
                tokenInfo.setDescription(update.getMessage().getText());
                return customMsg(ENTER_THE_URL_OF_IMAGE, update);
            }
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
