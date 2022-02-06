package com.agora.botapi.handlers.mint;

import com.agora.botapi.data.DataStore;
import com.agora.botapi.data.TokenInfo;
import com.agora.botapi.utils.KeyBoardUtils;
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

@Component
public class MintNFTHandler {

    private static final String MINT_NFTS_FOR_MY_SELF = "Mint_NFTs_For_My_Self";
    private static final String MINT_NFTS_FOR_OTHERS = "Mint_NFTs_For_Others";
    public static final String ENTER_THE_URL_OF_IMAGE = "Enter the URL of image";
    public static final String ENTER_THE_NAME_OF_NFT = "Enter the name of the NFT";
    public static final String ENTER_THE_DESCRIPTION_OF_NFT = "Lets give it a nice description!!... Enter a descriptionfor NFT";

    public static final String MINT_NFTS_OPTION = "Mint_NFTs";
    public static final String ENTER_THE_WALLET_ADDR_OF_YOUR_BUDDY = "Enter the wallet address of your buddy";


    public SendMessage handle(Update update) {


        if (update.getCallbackQuery() != null) {
            String callBackData = update.getCallbackQuery().getData();
            if (callBackData.contains(MINT_NFTS_FOR_MY_SELF)) {
                return customMsg(ENTER_THE_NAME_OF_NFT, update);
            }

            if (callBackData.contains(MINT_NFTS_FOR_OTHERS)) {
                return customMsg(ENTER_THE_WALLET_ADDR_OF_YOUR_BUDDY, update);
            }
        }

        String chatId = Optional.ofNullable(update.getMessage()).map(m -> m.getChat().getId().toString()).orElse(null);
        if (StringUtils.isNotBlank(chatId)) {
            String prevMsg = update.getMessage().getReplyToMessage().getText();

            if (ENTER_THE_WALLET_ADDR_OF_YOUR_BUDDY.equals(prevMsg)) {
                String walletAddr = DataStore.getWalletAddrOfUser(chatId);
                TokenInfo tokenInfo = getTokenInfo(chatId);
                tokenInfo.setBuddyWalletAddress(update.getMessage().getText());
                DataStore.addToken(walletAddr, tokenInfo);
                return customMsg(ENTER_THE_NAME_OF_NFT, update);
            }

            if (ENTER_THE_NAME_OF_NFT.equals(prevMsg)) {
                String walletAddr = DataStore.getWalletAddrOfUser(chatId);
                TokenInfo tokenInfo = getTokenInfo(walletAddr);
                tokenInfo.setName(update.getMessage().getText());
                DataStore.addToken(walletAddr, tokenInfo);
                return customMsg(ENTER_THE_DESCRIPTION_OF_NFT, update);
            }

            if (ENTER_THE_DESCRIPTION_OF_NFT.equals(prevMsg)) {
                String walletAddr = DataStore.getWalletAddrOfUser(chatId);
                TokenInfo tokenInfo = getTokenInfo(walletAddr);
                tokenInfo.setDescription(update.getMessage().getText());
                DataStore.addToken(walletAddr, tokenInfo);
                return customMsg(ENTER_THE_URL_OF_IMAGE, update);
            }

            if (ENTER_THE_URL_OF_IMAGE.equals(prevMsg)) {
                SendMessage sendMessage;
                TokenInfo mintedToken;
                String walletAddr = DataStore.getWalletAddrOfUser(chatId);
                TokenInfo tokenInfo = getTokenInfo(walletAddr);
                tokenInfo.setTokenUrl(update.getMessage().getText());

                DataStore.addToken(walletAddr, tokenInfo);
                if (StringUtils.isNotBlank(tokenInfo.getBuddyWalletAddress())) {
                    sendMessage  = customMsg(String.format("Minted the image with  name: %s desc: %s url: %s NOW  for address : %s", tokenInfo.getName(), tokenInfo.getDescription(), tokenInfo.getTokenUrl(), tokenInfo.getBuddyWalletAddress()), update);
                    //call REST service from here
                     mintedToken  =  restfulMint(tokenInfo.getName(), tokenInfo.getDescription(), tokenInfo.getTokenUrl(), tokenInfo.getBuddyWalletAddress());
                    return sendMessage;
                } else {
                    sendMessage  = customMsg(String.format("Minted the image with  name: %s desc: %s url: %s NOW  for address : %s", tokenInfo.getName(), tokenInfo.getDescription(), tokenInfo.getTokenUrl(), walletAddr), update);
                    //call REST service from here
                     mintedToken  =restfulMint(tokenInfo.getName(), tokenInfo.getDescription(), tokenInfo.getTokenUrl(), walletAddr);
                    return sendMessage;
                }

            }

        }


        return mintNFTDefault();


    }

    private TokenInfo restfulMint(String name, String description, String tokenUrl, String buddyWalletAddress) {

        TokenInfo tokenInfo = new TokenInfo(name,description,tokenUrl, null,null,buddyWalletAddress);

        //make actual rest call here and set contract Addr and tokenId
        System.out.println("REST CALL HERE");

        return tokenInfo;
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
        rowInline1.add(KeyBoardUtils.inlineKeyBoardButton("My Self", MINT_NFTS_FOR_MY_SELF));
        rowInline1.add(KeyBoardUtils.inlineKeyBoardButton("My Buddy", MINT_NFTS_FOR_OTHERS));
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList();
        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);
        message.setReplyMarkup(markupInline);


        return message;


    }

}
