import { Box, Flex, Text, Button, Heading } from "pcln-design-system"
import Popover from 'pcln-popover'
import { useContext, useState } from "react"
import useSwr from 'swr'
import ListingCard from "./ListingCard"
import MintForm from "./MintForm"
import { Modal } from 'pcln-modal'
import { useWeb3React } from '@web3-react/core'
import Link from "next/link"
import AccountContext from "../context/AccountContext"

const fetcher = (url) => fetch(url).then((res) => res.json())

function YourTokens() {
    const { walletAddress } = useContext(AccountContext)
    const { data, error } = useSwr(walletAddress ? `/api/yourTokens?account=${walletAddress}` : null, fetcher)

    const [showForm, setShowForm] = useState(false)

    if (error) return <div>Failed to load users</div>
    if (!data?.nfts) return <div>Loading your tokens...</div>

    return (
        <>
            <Box id="mint_nft">
                <Flex justifyContent='space-between' mx={2}>
                    <Text bold my={2} fontSize={4}
                    style={{
                      backgroundImage : 'linear-gradient(216.56deg,#4B50E6 35.32%,#E250E5 94.32%)',
                      backgroundClip: 'text',
                      WebkitBackgroundClip : 'text',
                      color : 'transparent',
                    }}
                    > <span>Your Tokens</span>
                    </Text>

                    <Link href='/similarNft' passHref>
                        <Button  width={80}  size='large'>Mint</Button>
                    </Link>
                </Flex>
                <Flex wrap>
                    {data.nfts.map((nft, index) => (
                        <Box key={index} width={[1, 1 / 2, null, 1 / 3, 1 / 4]} my={3}>
                            <ListingCard {...nft}></ListingCard>
                        </Box>
                    ))}
                </Flex>
            </Box>
        </>
    )
}

export default YourTokens;
