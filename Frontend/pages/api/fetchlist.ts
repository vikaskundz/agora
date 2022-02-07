import getConfig from 'next/config'

const { serverRuntimeConfig } = getConfig()

var myHeaders = new Headers()
myHeaders.append('Authorization', serverRuntimeConfig.authApiKey)

var requestOptions = {
  method: 'GET',
  headers: myHeaders,
  //redirect: 'follow'
}

export default function handler(req, res) {
  fetch(
    `https://api.nftport.xyz/v0/nfts/${serverRuntimeConfig.contractAddress}?chain=polygon&page_number=670&page_size=20&include=all&refresh_metadata=true`,
    requestOptions
  )
    .then((response) => response.text())
    .then((result: any) => {
      const duplicate = {}
      const jsonData = JSON.parse(result || {})
      const filData = jsonData?.nfts?.filter((nft) => {
        duplicate[nft.cached_file_url] =
          !duplicate[nft.cached_file_url] && nft.cached_file_url
        return duplicate[nft.cached_file_url] ? true : false
      })
      return res.status(200).json({ nfts: filData })
    })
    .catch((error) => res.status(200).json({ error }))
}
