import { Box, Flex, Text } from "pcln-design-system";
import useSwr from "swr";
import ListingCard from "./ListingCard";

const fetcher = (url) => fetch(url).then((res) => res.json());

function MarketListings(props) {
  const { data, error } = useSwr("/api/fetchlist", fetcher);

  if (error) return <div>Failed to load users</div>;
  if (!data?.nfts) return <div>Loading market listings...</div>;

  return (
    <Box mb={4} p={2} id="market_place">
      <Text bold m={2} fontSize={4}>
        <span
        style={{
          backgroundImage : 'linear-gradient(216.56deg,#4B50E6 35.32%,#E250E5 94.32%)',
          backgroundClip: 'text',
          WebkitBackgroundClip : 'text',
          color : 'transparent',
        }}
        >MarketPlace Listings</span>
      </Text>
      <Flex wrap>
        {data.nfts.map((nft, index) => (
          <Box key={index} width={[1, 1 / 2, null, 1 / 3, 1 / 4]} my={3}>
            <ListingCard {...nft}></ListingCard>
          </Box>
        ))}
      </Flex>
    </Box>
  );
}

export default MarketListings;
