package com.agora.botapi;


import com.agora.botapi.handlers.mint.MintNFTHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class AghoraBot extends TelegramLongPollingBot {

    public String getBotUsername() {
        return "agora_nft_bot";
    }


    public String getBotToken() {
        return "5195191462:AAHRQj6d6gGml-8VXNeSm900_9tU4140HjA";
    }

    @Autowired
    private ResponseProcessor responseProcessor;


    @Autowired
    private MintNFTHandler mintNFTHandler;


    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = responseProcessor.process(update);
            sendMessage(message, update);
        } else if (update.hasCallbackQuery()) {
            String callBackData = update.getCallbackQuery().getData();

            if (callBackData.contains("Mint_NFT")) {
                SendMessage message = mintNFTHandler.handle(update, null);
                sendMessage(message, update);
            }

        }
    }

    private void sendMessage(SendMessage message,  Update update) {
        try {
            String chatId = update.getCallbackQuery() != null ? update.getCallbackQuery().getMessage().getChat().getId().toString() : update.getMessage().getChat().getId().toString();
            message.setChatId(chatId);
            execute(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}