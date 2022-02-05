import getConfig from 'next/config'

const { serverRuntimeConfig } = getConfig()

var myHeaders = new Headers()
myHeaders.append('Content-Type', 'application/json')
myHeaders.append('Authorization', serverRuntimeConfig.authApiKey)

export default function handler(req, res) {
  const { tokenUrl } = JSON.parse(req.body)
  var raw = JSON.stringify({
    url: tokenUrl,
    threshold: 0.5,
  })

  var requestOptions = {
    method: 'POST',
    headers: myHeaders,
    body: raw,
    //redirect: 'follow'
  }
  fetch('https://api.nftport.xyz/v0/duplicates/urls', requestOptions)
    .then((response) => response.text())
    .then((result) => res.status(200).json(result))
    .catch((error) => res.status(200).json({ error }))
}
