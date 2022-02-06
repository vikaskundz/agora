package com.agora.botapi.handlers;

import com.agora.botapi.util.DataCodec;
import com.agora.botapi.util.WebClientProxy;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DetailsNFTHandler {

    @Autowired
    private WebClientProxy webClientProxy;

    public List<SendPhoto> handle(Update update) {
        List<SendPhoto> sendPhotoList = new ArrayList<>();
        String data = update.getCallbackQuery().getData();
        Map<String, String> dataMap = DataCodec.decode(data);
        String response = webClientProxy.sendAndReceive("/" + dataMap.get("c") + "/" + dataMap.get("t") + "/nftDetail");
        String tokenUrl = JsonPath.parse(response).read("$.nft_data.external_data.image", String.class);
        String name = JsonPath.parse(response).read("$.nft_data.external_data.name", String.class);
        String description = JsonPath.parse(response).read("$.nft_data.external_data.description", String.class);
        SendPhoto message = null;
        message = new SendPhoto();
        message.setChatId(update.getCallbackQuery().getMessage().getChatId().toString());
        message.setPhoto(new InputFile().setMedia(tokenUrl));
        message.setCaption("<b><i>NFT Name: </i></b>" + name + "\n<b><i>NFT Description: </i></b>" + description);
        message.setParseMode("HTML");
        sendPhotoList.add(message);
        return sendPhotoList;
    }
}
