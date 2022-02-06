package com.agora.botapi.data;

import java.util.HashMap;
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

}
