import { sequence } from '0xsequence'
type WalletDataType = {
  _connectDetails: {connected: boolean}
  _wallet: any
}

async function connectWallet() {
  const walletData = {} as WalletDataType
  const _wallet = new sequence.Wallet('mainnet')
  const session = _wallet.getSession()
  if (!session?.walletContext) {
    const connectDetails = await _wallet.connect()
    walletData._connectDetails = connectDetails
  }
  walletData._wallet = _wallet
  console.log('walletData', walletData)
  return walletData
}

async function disconnectWallet() {
  const wallet = new sequence.Wallet('mainnet')
  const connectDetails = await wallet.disconnect()
  console.log('disconnectWallet', connectDetails)
  return connectDetails
}

async function getWalletAddress() {
  const wallet = await new sequence.Wallet('mainnet').getAddress()
  return wallet
}

export default { connectWallet, getWalletAddress, disconnectWallet }
