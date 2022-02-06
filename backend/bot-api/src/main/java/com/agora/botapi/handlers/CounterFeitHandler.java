package com.agora.botapi.handlers;

import com.agora.botapi.data.DataStore;
import com.agora.botapi.handlers.mint.MintNFTHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Component
public class CounterFeitHandler {


    private static final String ENTER_COUNTERFEIT_MSG = "Enter the CounterFeit URL";

    public SendMessage handle(Update update) {
        if (update.getCallbackQuery() != null) {
            String callBackData = update.getCallbackQuery().getData();
            if (callBackData.contains("counterfeit")) {
                return MintNFTHandler.customMsg(ENTER_COUNTERFEIT_MSG, update);
            }
        }

        String chatId = Optional.ofNullable(update.getMessage()).map(m -> m.getChat().getId().toString()).orElse(null);
        if (StringUtils.isNotBlank(chatId)) {
            String prevMsg = update.getMessage().getReplyToMessage().getText();

            if (ENTER_COUNTERFEIT_MSG.equals(prevMsg)) {
                String walletAddr = DataStore.getWalletAddrOfUser(chatId);
                return null;// customMsg(ENTER_THE_NAME_OF_NFT, update);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        double p = 0.9981275200843811;

        double perc = new BigDecimal(p*100).setScale(2, RoundingMode.CEILING).doubleValue();
        System.out.println(perc);
    }
}


