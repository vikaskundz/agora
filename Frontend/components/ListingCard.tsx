import {
  Card,
  BackgroundImage,
  Text,
  Box,
  Flex,
  Heading,
  Button,
} from "pcln-design-system";
import styled from "styled-components";
import Link from "next/link";

const StyledImage = styled(BackgroundImage)`
  background-size: contain;
  background-color: black;
  border-radius: 10px;
`;

const StyledAnchor = styled.a`
  text-decoration: none;
  color: inherit;
`;

function ListingCard({
  token_id,
  contract_address,
  cached_file_url,
  chain,
  similarity,
  showMoreData,
}) {
  const sim = (similarity * 100).toFixed();
  const creator = "Satoshi Nakamoto";
  return (
    <Box mx={2} p={1}>
      <StyledAnchor>
        <Card
          style={{
            background: "#343444",
            cursor: "pointer",
            transform: "translateX(-10px)",
          }}
          borderWidth={0}
          borderRadius={20}
          width="100%"
        >
          <Flex flexDirection="column" alignItems="center" width="100%">
            <Box width={1} p={3}>
              <StyledImage image={cached_file_url} height="250px">
                {!cached_file_url && (
                  <Box ml={2} p={2}>
                    <Text align="center" color="white" bold>
                      No image
                    </Text>
                  </Box>
                )}
              </StyledImage>
            </Box>
            <Flex
              flexDirection="row"
              px={3}
              m={1}
              alignItems="center"
              justifyContent="space-between"
              width="100%"
            >
              <Box>
                <Text fontSize={0} style={{ color: "#68686e" }}>
                  Token Id
                </Text>
                {token_id.length > 10
                  ? token_id.slice(0, 10) + "..."
                  : token_id}
              </Box>
              <Box>
                <Link href={`/detail/${contract_address}/${token_id}`} passHref>
                  <Button
                    variation="outline"
                    py={2}
                    style={{
                      fontSize: ".7rem",
                    }}
                  >
                    Details
                  </Button>
                </Link>
              </Box>
            </Flex>
            <Flex
              flexDirection="row"
              px={3}
              pb={2}
              m={1}
              alignItems="end"
              justifyContent="space-between"
              width="100%"
            >
              <Box>
                <Text fontSize={0} style={{ color: "#68686e" }}>
                  Creator
                </Text>
                {creator.length > 10 ? creator.slice(0, 10) + "..." : creator}
              </Box>
              <Box>
                <Text fontSize={0} style={{ color: "#68686e" }}>
                  Current Bid
                </Text>
                <Text fontSize={2}>5.00 MATIC</Text>
              </Box>
            </Flex>
          </Flex>

          {/*<StyledImage image={cached_file_url} height="250px">
                {!cached_file_url && (
                  <Box ml={2} p={2}>
                    <Text align="center" color="white" bold>
                      No image
                    </Text>
                  </Box>
                )}
              </StyledImage>*/}

          {showMoreData && (
            <Flex
              justifyContent="center"
              alignItems="start"
              flexDirection="column"
            >
              <Flex m={2} alignItems="center">
                <Box>
                  <Text bold caps width={150}>
                    Similarity :
                  </Text>
                </Box>
                <Box>
                  <Text>{sim} %</Text>
                </Box>
              </Flex>
              <Flex m={2} alignItems="center">
                <Box>
                  <Text bold caps width={150}>
                    Chain :
                  </Text>
                </Box>
                <Box>
                  <Text>{chain}</Text>
                </Box>
              </Flex>
              <Flex m={2} alignItems="center">
                <Box>
                  <Text bold caps width={150}>
                    Contract Address:
                  </Text>
                </Box>
                <Box>
                  <Text fontSize={1} style={{ wordBreak: "break-all" }}>
                    {contract_address}
                  </Text>
                </Box>
              </Flex>
            </Flex>
          )}
        </Card>
      </StyledAnchor>
    </Box>
  );
}

export default ListingCard;
