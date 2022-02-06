import { sequence } from '0xsequence'
type WalletDataType = {
  _connectDetails: { connected: boolean }
  _wallet: any
}

async function connectWallet(isConnected, setIsConnected, wallet) {
  const walletData = {} as WalletDataType
  let _wallet = wallet
  const session = _wallet?.getSession()
  if(session?.accountAddress){
    setIsConnected(true)
  }
  else if(!isConnected) {
    _wallet = new sequence.Wallet('mainnet')
    const details = await _wallet.connect()
    walletData._connectDetails = details
    setIsConnected(details.connected)
  }
  walletData._wallet = _wallet
  // console.log('walletData', walletData)
  return walletData
}

async function disconnectWallet() {
  const wallet = new sequence.Wallet('mainnet')
  const connectDetails = await wallet.disconnect()
  console.log('disconnectWall/et', connectDetails)
  return connectDetails
}

async function getWalletAddress() {
  const wallet = await new sequence.Wallet('mainnet').getAddress()
  return wallet
}

async function getSession() {
  const wallet = await new sequence.Wallet('mainnet')
  return wallet.getSession()
}


export default { connectWallet, getWalletAddress, disconnectWallet, getSession }
