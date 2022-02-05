import { Card, BackgroundImage, Text, Box, Flex, TextArea } from "pcln-design-system"
import styled from 'styled-components'
import MintForm from "./MintForm"
import { useState } from "react"
import ListingCard from "./ListingCard"

const StyledImage = styled(BackgroundImage)`
    background-size: contain;
    margin: 10px;
    background-color: '#343444';
    border-radius: 10px;
`
function MintNft() {
    const [imageUrl, setImageUrl] = useState('')
    const [data, setData] = useState(null)
    const handlePreview = (e) => {
        setImageUrl(e.target.value)
    }
    const handleReview = (tokenUrl) => {
        fetch('/api/similarNfts', {
            method: 'post',
            body: JSON.stringify({
                tokenUrl
            })
        })
            .then(response => response.json())
            .then((resData) => {
                setData(resData)
            })
    }

    const submitMint = () => {

    }

    return (
        <Box mx={2}>
            <Card
                boxShadowSize='xl'
                borderWidth={1}
                borderRadius={6}
                width='100%'

            >
                <Flex
                >
                    <Box width={1 / 2}>
                        <StyledImage
                            image={imageUrl} height='500px'>
                        </StyledImage>
                    </Box>
                    <MintForm handlePreview={handlePreview} handleReview={handleReview} submitMint={submitMint}></MintForm>
                </Flex>
                {data?.is_similar &&
                    <Box mt={4} mx={4}>
                        <Text bold m={2} fontSize={4}>Similar NFTs <Text.span fontSize={2} fontWeight={1} italic>(AI-enabled counterfeit NFT detection)</Text.span></Text>
                        <Flex wrap>
                            {data?.similar_nfts.map((nft, index) => (
                                <Box key={index} width={[1, 1 / 2, null, 1 / 3, null]} my={3}>
                                    <ListingCard {...nft} showMoreData={true}></ListingCard>
                                </Box>
                            ))}
                        </Flex>
                    </Box>
                }
            </Card>
        </Box>
    )
}

export default MintNft
