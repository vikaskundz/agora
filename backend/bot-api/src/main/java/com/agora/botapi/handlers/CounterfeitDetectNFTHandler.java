package com.agora.botapi.handlers;

import com.agora.botapi.util.SendPhotoUtil;
import com.agora.botapi.util.WebClientProxy;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class CounterfeitDetectNFTHandler {

    @Autowired
    private WebClientProxy webClientProxy;

    public List<SendPhoto> handle(Update update) {
        String apiResponse = webClientProxy.sendAndReceiveForMint("/similarNfts",
                Map.of("tokenUrl","https://ipfs.io/ipfs/bafkreibtsi3nhctfhk22ncm5z3frci2szjrcn7x4roe7biq6nfl7iwfuqa"));
        List<String> cachedUrlList = JsonPath.parse(apiResponse).read("$.nfts[*].cached_file_url", List.class);
        List<String> tokenIdList = JsonPath.parse(apiResponse).read("$.nfts[*].token_id", List.class);
        List<String> contractList = JsonPath.parse(apiResponse).read("$.nfts[*].contract_address", List.class);
        List<String> captionList = new ArrayList<>();
        return SendPhotoUtil.sendMessage(update, cachedUrlList, tokenIdList, contractList);
    }




}
