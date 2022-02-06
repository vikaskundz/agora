import { Box } from "pcln-design-system"
import styled from "styled-components"
import MarketListings from "./MarketListings"
import YourTokens from "./YourTokens"
import HeroBanner from "./HeroBanner";

const StyledAnchor = styled.a`
    text-decoration: none;
    color: inherit;
`


function Homepage() {

    return (
        <Box>
            <HeroBanner />
            <YourTokens />
            <MarketListings />
        </Box>
    )
}

export default Homepage;
