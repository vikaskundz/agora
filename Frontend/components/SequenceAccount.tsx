import SequenceService from "../services/sequence.service";
import { useEffect, useState, useContext } from "react";
import { Button, Text, Flex, Box } from "pcln-design-system";
import { formatEtherscanLink, shortenHex } from "../util";
import AccountContext from "../context/AccountContext";


export default function Account() {
  const { isConnected, setIsConnected, walletAddress, setWalletAddress } = useContext(AccountContext)
  //const { account } = useWeb3React();
  //const ENSName = useENSName(account);
  const [connectionDetails, setConnectionDetails] = useState(null)
  const [wallet, setWallet] = useState(null)
  //isConnected && walletAddress && connectWallet()

  async function connectWallet() {
    const { _wallet, _connectDetails } = await SequenceService.connectWallet()
    if (_wallet) {
      const _wAddress = await _wallet.getAddress()
      setIsConnected(true)
      setConnectionDetails(_connectDetails)
      setWallet(_wallet)
      setWalletAddress(_wAddress)
    }
  }

  function disconnectWallet() {
    SequenceService.disconnectWallet().then(details => {
      console.log('disconnect', details);
      setIsConnected(false)
    })
  }

  return (
    <>
      {
        isConnected ? (
          <Flex flexDirection="row" alignItems="center" justifyContent="center">
            <Text mx={2} px={2}>Welcome,</Text> <a
              {...{
                href: formatEtherscanLink("Account", [Number(connectionDetails?.chainId), walletAddress]),
                target: "_blank",
                rel: "noopener noreferrer",
              }}
            >
              {walletAddress || `${shortenHex(walletAddress, 6)}`}
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
