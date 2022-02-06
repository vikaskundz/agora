package com.agora.botapi.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenInfo {

    private String name;
    private String description;
    private String tokenUrl;
    private String contractAddr;
    private String tokenId;
    private String buddyWalletAddress;

}