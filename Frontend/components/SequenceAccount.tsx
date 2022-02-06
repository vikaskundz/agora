import SequenceService from "../services/sequence.service";
import { useEffect, useState, useContext } from "react";
import { Button, Text, Flex, Box, IconButton } from "pcln-design-system";
import { formatEtherscanLink, shortenHex } from "../util";
import AccountContext from "../context/AccountContext";
import { ExitToApp } from "pcln-icons";

export default function Account() {
  const { isConnected, setIsConnected, walletAddress, setWalletAddress, wallet, setWallet } = useContext(AccountContext)
  //const { account } = useWeb3React();
  //const ENSName = useENSName(account);
  const [connectionDetails, setConnectionDetails] = useState(null)
  useEffect(async () => {
    // connectWallet()
    const _session = JSON.parse(localStorage.getItem('@sequence.session'));

    if(_session){
      const _walletContext = await SequenceService.getSession();
      // console.log('_walletContext',_walletContext);

      setIsConnected(true)
      setConnectionDetails({connected : true})
      setWallet(_walletContext)
      setWalletAddress(_walletContext.accountAddress)
      // setWalletAddress(_walletContext.getAddress())

    }


  }, [])

  async function connectWallet() {
    const { _wallet, _connectDetails } = await SequenceService.connectWallet(isConnected, setIsConnected, wallet)
    if (_wallet && _connectDetails?.connected) {
      // console.log('connectWallet',_wallet);

      const _wAddress = await _wallet.getAddress()
      setIsConnected(true)
      setConnectionDetails(_connectDetails)
      setWallet(_wallet)
      setWalletAddress(_wAddress)
    }

    return;
  }

  function disconnectWallet() {
    SequenceService.disconnectWallet().then(details => {
      localStorage.removeItem('@sequence.session');
      setIsConnected(false)
      setConnectionDetails({connected : false})
      setWallet(null)
      setWalletAddress(null)
      window.location.href = '/'
    })
  }

  return (
    <>
      {
        isConnected ? (
          <Flex flexDirection="row" alignItems="center" justifyContent="center">
            <Text  px={2}>Welcome,</Text> <a
              {...{
                href: formatEtherscanLink("Account", [Number(connectionDetails?.chainId), walletAddress]),
                target: "_blank",
                rel: "noopener noreferrer",
              }}
            >
              {walletAddress ? `${shortenHex(walletAddress, 6)}` : ''}
            </a>
          {/* <Button onClick={disconnectWallet} variation="subtle">signout</Button> */}
          <IconButton
            title="Sign out"
            onClick={disconnectWallet}
            mr={2}
            icon={<ExitToApp color="primary" ml={2} size={30} />}
          />
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
