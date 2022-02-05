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
  const { cAddress, tokenId } = req.query
  fetch(
    `https://api.nftport.xyz/v0/nfts/${cAddress}/${tokenId}?chain=polygon&refresh_metadata=false`,
    requestOptions
  )
    .then((response) => response.text())
    .then((result) => res.status(200).json(result))
    .catch((error) => res.status(200).json({ error }))
}
