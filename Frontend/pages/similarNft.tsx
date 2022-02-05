import { useWeb3React } from "@web3-react/core";
import { Box, Text } from "pcln-design-system"
import useSwr from 'swr'
import MintForm from "../components/MintForm";
import MintNft from "../components/MintNft"


const fetcher = (url) => fetch(url).then((res) => res.json())

function SimilarNft(props) {
    return (
        <Box p={2}>
            <MintNft></MintNft>
        </Box>
    );
}

export default SimilarNft;
