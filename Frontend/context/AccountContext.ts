import React from 'react'

export interface AccountDetails {
  isConnected: boolean
  walletAddress: string
  setIsConnected: Function
  setWalletAddress: Function
  wallet: object
  setWallet: Function
}

const AccountContext = React.createContext({} as AccountDetails)
export default AccountContext
