import getConfig from 'next/config'

const { serverRuntimeConfig } = getConfig()

var myHeaders = new Headers()
myHeaders.append('Authorization', serverRuntimeConfig.authApiKey)

var requestOptions = {
  method: 'GET',
  //redirect: 'follow'
}

export default function handler(req, res) {
  const { cAddress, tokenId } = req.query
  fetch(
    `https://api.covalenthq.com/v1/137/tokens/${cAddress}/nft_metadata/${tokenId}/?key=${serverRuntimeConfig.covalentApiKey}`,
    requestOptions
  )
    .then((response) => response.text())
    .then((result: any) => {
      const jsonData = JSON.parse(result|| {})
      const nft_data = jsonData?.data?.items[0]?.nft_data[0] || {}
      return res.status(200).json({nft_data})
    })
    .catch((error) => res.status(200).json({ error }))
}
