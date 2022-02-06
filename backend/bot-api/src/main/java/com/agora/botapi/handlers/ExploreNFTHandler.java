package com.agora.botapi.handlers;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
public class ExploreNFTHandler {
    public static final String EXPLORE_NFTS_OPTION = "Explore_NFTs";


    public SendMessage handle(Update update) {
        return null;
    }
}
