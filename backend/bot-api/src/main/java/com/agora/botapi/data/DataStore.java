package com.agora.botapi.data;


import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStore {

    private static final Map<String, String> walletData = new HashMap<>();//chatId,walletAddr


    public static boolean hasWalletRegistered(String chatId) {
        return walletData.containsKey(chatId);
    }

    public static void registerWallet(String chatId, String walletAddr) {
        walletData.put(chatId, walletAddr);
    }

    public static void updateWallet(String chatId, String walletAddr) {
        walletData.put(chatId, walletAddr);
    }

    public static String getWalletAddrOfUser(String chatId) {
        return walletData.get(chatId);
    }


    private static final Map<String, TokenInfo> mintProcessData = new HashMap<>();//chatId,TokenInfo

    public static TokenInfo getTokenInfo(String walletAddr) {
        TokenInfo token = mintProcessData.get(walletAddr);
        if (token == null) {
            token = new TokenInfo();
        }
        return token;
    }

    public static void addToken(String walletAddr, TokenInfo tokenInfo) {
        mintProcessData.put(walletAddr, tokenInfo);
    }

    private static final Map<String, List<TokenInfo>> mintedTokens = new HashMap<>();//chatId,TokenInfoList

    public static void saveMintedTokenInfo(String chatId, TokenInfo tokenInfo) {
        if (CollectionUtils.isNotEmpty(mintedTokens.get(chatId))) {
            mintedTokens.get(chatId).add(tokenInfo);
        } else {
            List<TokenInfo> list = new ArrayList<>();
            list.add(tokenInfo);
            mintedTokens.put(chatId, list);
        }
    }


    public static List<TokenInfo> retrieveMintedTokenInfo(String chatId) {
        if (CollectionUtils.isNotEmpty(mintedTokens.get(chatId))) {
            return mintedTokens.get(chatId);
        }
        return null;
    }


}
