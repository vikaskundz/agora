import { Box } from "pcln-design-system"
import styled from "styled-components"
import MarketListings from "./MarketListings"
import YourTokens from "./YourTokens"
import Header from "./Header.tsx";

const StyledAnchor = styled.a`
    text-decoration: none;
    color: inherit;
`


function Homepage() {

    return (
        <Box mx={3}>
            <Header></Header>
            <MarketListings mb={3}></MarketListings>
            <YourTokens></YourTokens>
        </Box>
    )
}

export default Homepage;
