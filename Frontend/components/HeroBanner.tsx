import { Flex, Button, Heading } from "pcln-design-system"
import styled from "styled-components"
import MarketListings from "./MarketListings"
import YourTokens from "./YourTokens"

const StyledAnchor = styled.a`
    text-decoration: none;
    color: inherit;
`


function HeroBanner() {

  return (
    <Flex
      width="100%"
      height={["30vh", "50vh"]}
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
      style={{
        backgroundImage:
          "linear-gradient(rgba(0,0,0,.4),rgba(0,0,0,.4)),linear-gradient(to right bottom,#4B50E6,#E250E5)",
        clipPath: "ellipse(100% 55% at 48% 44%)",
        backgroundAttachment: 'fixed'
      }}
    >
      <Heading.h1 letterSpacing={20}>AGORA</Heading.h1>
      <Heading.h2 letterSpacing={10} mt={1}>Automation for NFT</Heading.h2>
      <Flex>
        <Button m={2} width={150} onClick={() => {
          location.hash = "#market_place"
        }}>
          Discover NFTs
        </Button>
        <Button m={2} width={150} onClick={() => {
          location.href = "/similarNft"
        }}>
          Mint NFT
        </Button>
      </Flex>
    </Flex>
  )
}

export default HeroBanner;
