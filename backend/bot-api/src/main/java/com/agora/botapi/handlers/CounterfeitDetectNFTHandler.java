package com.agora.botapi.handlers;

import com.agora.botapi.handlers.mint.MintNFTHandler;
import com.agora.botapi.util.SendPhotoUtil;
import com.agora.botapi.util.WebClientProxy;
import com.jayway.jsonpath.JsonPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CounterfeitDetectNFTHandler {

    @Autowired
    private WebClientProxy webClientProxy;

    public static final String DETECT_COUNTER_NFTS_OPTION = "detect_counter_nfts";
    public static final String ENTER_THE_URL_OF_IMAGE_TO_CHECK_FOR_COUNTERFEIT = "Enter the URL of image to check for counterfeit";

    public List<SendPhoto> handlePhotos(Update update) {
        String tokenImageurl = update.getMessage().getText();
        String apiResponse = webClientProxy.sendAndReceiveForMint("/similarNfts",
                Map.of("tokenUrl",tokenImageurl));
        List<String> cachedUrlList = JsonPath.parse(apiResponse).read("$.similar_nfts[*].cached_file_url", List.class);
        List<Double> similarityList = JsonPath.parse(apiResponse).read("$.similar_nfts[*].similarity", List.class);
        List<Double> similarityPercList = similarityList.stream().map(d-> new BigDecimal(d*100).setScale(2, RoundingMode.CEILING).doubleValue()).collect(Collectors.toList());

        return SendPhotoUtil.sendMessage(update, cachedUrlList, null,null, similarityPercList, false);
    }

    public SendMessage handleMessage(Update update) {
        return MintNFTHandler.customMsg(ENTER_THE_URL_OF_IMAGE_TO_CHECK_FOR_COUNTERFEIT, update);
    }





}
