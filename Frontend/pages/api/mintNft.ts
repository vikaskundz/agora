import getConfig from 'next/config'

const { serverRuntimeConfig } = getConfig()

var myHeaders = new Headers()
myHeaders.append('Content-Type', 'application/json')
myHeaders.append('Authorization', serverRuntimeConfig.authApiKey)

export default function handler(req, res) {
  const { tokenUrl, nftName, nftDesc, account } = JSON.parse(req.body)
  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: JSON.stringify({
      chain: 'polygon',
      name: nftName,
      description: nftDesc,
      file_url: tokenUrl,
      mint_to_address: account,
    }),
    // redirect: 'follow',
  }
  fetch('https://api.nftport.xyz/v0/mints/easy/urls', requestOptions)
    .then((response) => response.text())
    .then((result) => {
      console.log(result)
      res.status(200).json(result)
    })
    .catch((error) => res.status(200).json({ error }))
}
