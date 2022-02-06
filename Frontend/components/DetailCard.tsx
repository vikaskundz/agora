import { Card, BackgroundImage, Text, Box, Flex, TextArea } from "pcln-design-system"
import Link from "next/link"
import styled from 'styled-components'

const StyledImage = styled(BackgroundImage)`
    background-size: contain;
    margin: 10px;
    background-color: white;
    border-radius: 10px;
    background-color: #0d1e31;
`
function DetailCard({ external_data }) {
    const metaDataKeys = Object.keys(external_data || {})
    return (
        <Box mx={2}>
            <Card
                boxShadowSize='xl'
                borderWidth={0}
                borderRadius={6}
                width='100%'
            >
                <Flex>
                    <Box width={2 / 3}>
                        <StyledImage
                            image={external_data?.image || '/no-image.jpeg'} height='600px'>
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
                                            {external_data[key]}
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
