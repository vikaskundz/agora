import SequenceService from "../services/sequence.service";
import { useEffect, useState } from "react";
import { Button, Text, Flex, Box} from "pcln-design-system";
import { formatEtherscanLink, shortenHex } from "../util";
import useENSName from "../hooks/useENSName";


export default function Account() {
  const [account,setAccount] = useState(null);
  const [walletAddress,setWalletAddress] = useState('');
  useEffect(() => {
    if(account && account.connected)
      getWalletAddress()

  },[account])

  const ENSName = useENSName(account);

  function connectWallet() {
    SequenceService.connectWallet().then(account => {
      console.log('account',account )
      account && account.connected ? setAccount(account) : setAccount(null)
    })
  }

  function disconnectWallet(){
    SequenceService.disconnectWallet().then(details => {
      console.log('disconnect',details);
      account && account.connected ? setAccount(details) : setAccount(null)
    })
  }

  function getWalletAddress() {
     SequenceService.getWalletAddress().then(walletAddress => {
       console.log('walletAddress',walletAddress);

      account && account.connected ? setWalletAddress(walletAddress) : setWalletAddress('')
     });
  }

  return (
    <>
      {
        account && account.connected ? (
          <Flex flexDirection="row" alignItems="center" justifyContent="center">
          <Text mx={2} px={2}>Welcome,</Text> <a
            {...{
              href: formatEtherscanLink("Account", [Number(account.chainId), walletAddress]),
              target: "_blank",
              rel: "noopener noreferrer",
            }}
          >
            {ENSName || `${shortenHex(walletAddress, 6)}`}
          </a>
          <Button onClick={disconnectWallet} variation="subtle">signout</Button>
          </Flex>
        ) : (
          <Button onClick={connectWallet}>
              Connect With Sequence
          </Button>
        )
      }
    </>
  )
}
