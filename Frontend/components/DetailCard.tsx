import { Card, BackgroundImage, Text, Box, Flex, TextArea } from "pcln-design-system"
import Link from "next/link"
import styled from 'styled-components'

const StyledImage = styled(BackgroundImage)`
    background-size: contain;
    margin: 10px;
    background-color: white;
    border-radius: 10px;
`
function DetailCard({ file_information, cached_file_url, metadata }) {
    const metaDataKeys = Object.keys(metadata || {})
    return (
        <Box mx={2}>
            <Card
                boxShadowSize='xl'
                borderWidth={1}
                borderRadius={6}
                width='100%'
            >
                <Flex>
                    <Box width={2 / 3}>
                        <StyledImage
                            image={cached_file_url} height='600px'>
                        </StyledImage>
                    </Box>
                    <Flex width={1 / 3} justifyContent='center' alignItems='start' flexDirection='column'>
                        {metaDataKeys.map((key, index) => {
                            return (
                                <Flex key={index} m={2} alignItems='center'>
                                    <Box>
                                        <Text bold caps width={150}>{key}:</Text>
                                    </Box>
                                    <Box>
                                        <Text style={{ wordBreak: 'break-all' }}>
                                            {metadata[key]}
                                        </Text>
                                    </Box>
                                </Flex>
                            )
                        })
                        }
                    </Flex>
                </Flex>
            </Card>
        </Box>
    )
}

export default DetailCard
