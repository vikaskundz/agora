

package com.agora.botapi.validation;

import java.io.PrintStream;
import org.bouncycastle.jcajce.provider.digest.SHA3.Digest256;
import org.bouncycastle.jcajce.provider.digest.SHA3.DigestSHA3;
import org.bouncycastle.util.encoders.Hex;

public class EthereumAddressValidator {
    public EthereumAddressValidator() {
    }

    public static boolean isValidAddress(String addr) {
        String regex = "^0x[0-9a-f]{40}$";
        System.out.println("Incoming Address " + addr);
        return addr.matches(regex);
    }

    public static boolean isChecksumAddress(String addr) {
        System.out.println("Incoming Address " + addr);
        String regex = "^0x[0-9a-fA-F]{40}$";
        if (!addr.matches(regex)) {
            return false;
        } else {
            String subAddr = addr.substring(2);
            String subAddrLower = subAddr.toLowerCase();
            System.out.println("Fetched Original Address " + subAddrLower);
            DigestSHA3 digestSHA3 = new Digest256();
            digestSHA3.update(subAddrLower.getBytes());
            String digestMessage = Hex.toHexString(digestSHA3.digest());
            System.out.println("Hex String " + digestMessage);

            for(short i = 0; i < subAddr.length(); ++i) {
                if (subAddr.charAt(i) >= 'A' && subAddr.charAt(i) <= '[') {
                    PrintStream var10000 = System.out;
                    char var10001 = subAddr.charAt(i);
                    var10000.println("Position Upper " + var10001);
                    var10000 = System.out;
                    var10001 = digestMessage.charAt(i);
                    var10000.println("Position digest " + var10001);
                    String ss = Character.toString(digestMessage.charAt(i));
                    if (Integer.parseInt(ss, 16) <= 7) {
                        return false;
                    }
                }
            }

            return true;
        }
    }

    public static void main(String[] args) throws Exception {
        String input = "0x5144d13b1f50225067d353b26de50f241be10e89";
        if (isValidAddress(input)) {
            System.out.println("Valid Ethereum Address");
        } else if (isChecksumAddress(input)) {
            System.out.println("Valid Checksummed Ethereum Address");
        } else {
            System.out.println("Not A Valid Ethereum Address");
        }

    }
}
