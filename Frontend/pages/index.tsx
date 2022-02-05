import { useWeb3React } from "@web3-react/core";
import ETHBalance from "../components/ETHBalance";
import TokenBalance from "../components/TokenBalance";
import { Box, Text, Flex } from "pcln-design-system"
import Homepage from "../components/Homepage";
import Header from "../components/Header.tsx";


function Home() {
  const { account, library } = useWeb3React();

  const isConnected = typeof account === "string" && !!library;

  return (
    <Box p={2}>
      {isConnected ? <Homepage /> : (
          <Flex flexDirection="column">
          <Header/>
          <Text>Please Connect to Wallet</Text>
          </Flex>
        )}
    </Box>
  );
}

export default Home;
