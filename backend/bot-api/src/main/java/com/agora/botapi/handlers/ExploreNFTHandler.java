//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.agora.botapi.handlers;

import com.agora.botapi.util.SendPhotoUtil;
import com.agora.botapi.util.WebClientProxy;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Component
public class ExploreNFTHandler {
    public static final String EXPLORE_NFTS_OPTION = "Explore_NFTs";

    @Autowired
    private WebClientProxy webClientProxy;

    public List<SendPhoto> handle(Update update) {
        String apiResponse = webClientProxy.sendAndReceive("/fetchlist");
        List<String> cachedUrlList = JsonPath.parse(apiResponse).read("$.nfts[*].cached_file_url", List.class);
        List<String> tokenIdList = JsonPath.parse(apiResponse).read("$.nfts[*].token_id", List.class);
        List<String> contractList = JsonPath.parse(apiResponse).read("$.nfts[*].contract_address", List.class);
        return SendPhotoUtil.sendMessage(update, cachedUrlList, tokenIdList, contractList,null, true);
    }




}
