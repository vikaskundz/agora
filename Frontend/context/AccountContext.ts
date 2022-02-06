import React from 'react'

export interface AccountDetails {
  isConnected: boolean
  walletAddress: string
  setIsConnected: Function
  setWalletAddress: Function
}

const AccountContext = React.createContext({} as AccountDetails)
export default AccountContext
