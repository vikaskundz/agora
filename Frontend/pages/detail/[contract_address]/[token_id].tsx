import { useWeb3React } from "@web3-react/core";
import { Box, Text } from "pcln-design-system"
import useSwr from 'swr'
import DetailCard from "../../../components/DetailCard"


const fetcher = (url) => fetch(url).then((res) => res.json())

function Detail(props) {
    const { contract_address, token_id } = props.params
    const { data, error } = useSwr(`/api/${contract_address}/${token_id}/nftDetail`, fetcher)

    if (error) return <div>Failed to load users</div>
    if (!data?.nft_data) return <div>Loading market listings...</div>
    return (
        <Box p={2}>
            <DetailCard {...data.nft_data}></DetailCard>
        </Box>
    );
}

export async function getServerSideProps({ params }) {
    return { props: { params } }
}

export default Detail;
