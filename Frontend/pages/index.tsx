import { useWeb3React } from "@web3-react/core";
import ETHBalance from "../components/ETHBalance";
import TokenBalance from "../components/TokenBalance";
import { Box, Text, Flex } from "pcln-design-system"
import Homepage from "../components/Homepage";
import HeroBanner from "../components/HeroBanner";
import { sequence } from '0xsequence';
import AccountContext from "../context/AccountContext";
import { useContext } from "react";
import MarketListings from "../components/MarketListings";


function Home() {

  //const isConnected = typeof account === "string" && !!library;
  const { isConnected } = useContext(AccountContext)

  return (
    <Box p={2}>
      {isConnected ? <Homepage /> :
        <Flex flexDirection="column">
          <HeroBanner />
          <MarketListings />
        </Flex>
      }
    </Box>
  )
}

export default Home;
