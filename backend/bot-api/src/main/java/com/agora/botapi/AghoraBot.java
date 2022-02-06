package com.agora.botapi;


import com.agora.botapi.handlers.CounterfeitDetectNFTHandler;
import com.agora.botapi.handlers.DetailsNFTHandler;
import com.agora.botapi.handlers.ExploreNFTHandler;
import com.agora.botapi.handlers.MyProfileNFTHandler;
import com.agora.botapi.handlers.mint.MintNFTHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

import static com.agora.botapi.handlers.ExploreNFTHandler.EXPLORE_NFTS_OPTION;
import static com.agora.botapi.handlers.MyProfileNFTHandler.MY_PROFILE_OPTION;
import static com.agora.botapi.handlers.mint.MintNFTHandler.MINT_NFTS_OPTION;

@Component
public class AghoraBot extends TelegramLongPollingBot {
    public String getBotUsername() {
        return "koli_manja_bot";
        //  return "agora_nft_bot";
    }


    public String getBotToken() {
        return "5256836230:AAHzXEltvka2f5xGQiO44JjYmF3Lt0AqvbQ";
        //return "5173952753:AAFhjz_hKjuVgcjuj8X8VhFQ7qCHLIhQQ0Y";
    }

    @Autowired
    private ResponseProcessor responseProcessor;


    @Autowired
    private MintNFTHandler mintNFTHandler;

    @Autowired
    private ExploreNFTHandler exploreNFTHandler;

    @Autowired
    private MyProfileNFTHandler profileNFTHandler;

    @Autowired
    private DetailsNFTHandler detailsNFTHandler;

    @Autowired
    private CounterfeitDetectNFTHandler counterfeitDetectNFTHandler;


    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = responseProcessor.process(update);
            sendMessage(message, update);
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();

            if (callBackData.contains(MINT_NFTS_OPTION)) {
                SendMessage message = mintNFTHandler.handle(update);
                sendMessage(message, update);
            }
            if (callBackData.contains(EXPLORE_NFTS_OPTION)) {
                List<SendPhoto> photos = exploreNFTHandler.handle(update);
                sendMessage(photos, update);
            }

            if (callBackData.contains("DETAILS_NFT")) {
                List<SendPhoto> photos  = detailsNFTHandler.handle(update);
                sendMessage(photos, update);
            }

            if (callBackData.contains(MY_PROFILE_OPTION)) {
                List<SendPhoto> photos = profileNFTHandler.handle(update);
                sendMessage(photos, update);
            }

            if (callBackData.contains("Detect_Counter_NFTs")) {
                List<SendPhoto> photos = counterfeitDetectNFTHandler.handle(update);
                sendMessage(photos, update);
            }

        }
    }

    private void sendMessage(SendMessage message, Update update) {
        try {
            String chatId = update.getCallbackQuery() != null ? update.getCallbackQuery().getMessage().getChat().getId().toString() : update.getMessage().getChat().getId().toString();
            message.setChatId(chatId);
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(List<SendPhoto> photos, Update update) {
        try {
            String chatId = update.getCallbackQuery() != null ? update.getCallbackQuery().getMessage().getChat().getId().toString() : update.getMessage().getChat().getId().toString();
            for (SendPhoto sendPhoto : photos) {
                sendPhoto.setChatId(chatId);
                execute(sendPhoto); // Sending our message object to user
            }
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}