import Head from "next/head";
import Link from "next/link";
// import Account from "../components/Account";
import { useContext } from "react";
import Account from "./SequenceAccount";
import useEagerConnect from "../hooks/useEagerConnect";
import {
  Banner,
  Flex,
  Heading,
  Button,
  Image,
  IconButton,
} from "pcln-design-system";
import { Chat } from "pcln-icons";
import styled from "styled-components";
const StyledAnchor = styled.a`
  text-decoration: none;
  color: inherit;
`;
import AccountContext from "../context/AccountContext";

function NavBar() {
  const { isConnected } = useContext(AccountContext)
  const triedToEagerConnect = useEagerConnect();
  return (
    <>
      <Head>
        <title>Agora - NFT Automation</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <header>
        <Flex
          alignItems="center"
          justifyContent="space-between"
          mx={2}
          px={2}
          style={{
            borderBottom: "1px solid rgba(235, 235, 235, 0.2)",
          }}
        >
          <Link href="/" passHref>
            <Flex>
              <StyledAnchor>
              <Image
                    width="80px"
                    alt="homepage"
                    src="/agora-logo.png"
                  ></Image>
                </StyledAnchor>
                <Heading.h5 mx={2}>NFT Automation</Heading.h5>
            </Flex>
            {/* <Flex>
                <StyledAnchor><Image width='100px' height='100px' alt='homepage' src='/Agora-logo-3.png'></Image></StyledAnchor>
                <Heading.h5 mx={2}>Nft Automation</Heading.h5>
              </Flex> */}
          </Link>

          <Flex alignItems="center">
            {/*
              <Button
                variation="outline"
                mx={2}
                onClick={() => {
                  window.open(
                    "https://app.unlock-protocol.com/checkout?redirectUri=http%3A%2F%2Flocalhost%3A3000&paywallConfig=%7B%22locks%22%3A%7B%220xE258bFffC07cA86A5860D8dc76EC33a416D3CfD0%22%3A%7B%22network%22%3A4%7D%7D%2C%22pessimistic%22%3Atrue%2C%22persistentCheckout%22%3Atrue%2C%22icon%22%3A%22https%3A%2F%2Flocksmith.unlock-protocol.com%2Flock%2F0xE258bFffC07cA86A5860D8dc76EC33a416D3CfD0%2Ficon%22%7D"
                  );
                }}
              >
                Be a TrueNFT member
                <Image
                  width="120px"
                  height="18px"
                  alt="homepage"
                  src="/Powered_by_Unlock.png"
                ></Image>
              </Button>
              */}
            {/*<Button
                variation="outline"
                mx={3}
                onClick={() => {
                  window.open(
                    " http://t.me/AgoriNFTBot"
                  );
              >
            }
                Telegram
              </Button>*/}
            {isConnected ? <IconButton

              title="Agora Bot"
              onClick={() => {
                window.open(
                  "https://t.me/agora_nft_bot"
                );
              }}
              mr={2}
              icon={<Chat color="primary" size={30} />}
            /> : ''}
            <Account />
          </Flex>
        </Flex>
      </header>
    </>
  );
}

export default NavBar;
