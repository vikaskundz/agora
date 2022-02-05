import { Flex,Button, Heading } from "pcln-design-system"
import styled from "styled-components"
import MarketListings from "./MarketListings"
import YourTokens from "./YourTokens"

const StyledAnchor = styled.a`
    text-decoration: none;
    color: inherit;
`


function Header() {

    return (
      <Flex
        width="100%"
        height="80vh"
        flexDirection="column"
        justifyContent="center"
        alignItems="center"
        style={{
          backgroundImage:
            "linear-gradient(rgba(0,0,0,.4),rgba(0,0,0,.4)),linear-gradient(to right bottom,#4B50E6,#E250E5)",
          clipPath: "ellipse(100% 55% at 48% 44%)",
          backgroundAttachment : 'fixed'
        }}
      >
        <Heading.h1 fontWeight={100} letterSpacing="34px">AGORA</Heading.h1>
        <Heading.h2 fontWeight={300} letterSpacing="17px" mt={1}>Automation for NFT</Heading.h2>
        <Button  m={2}>
          Discover NFTs
        </Button>
      </Flex>
    )
}

export default Header;
