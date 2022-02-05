import { sequence } from "0xsequence";

async function connectWallet() {
  const wallet = new sequence.Wallet("mainnet");
  console.log('wallet',wallet);
  const connectDetails = await wallet.connect();
  console.log("connectWallet", connectDetails);
  return connectDetails;
}

async function disconnectWallet() {
  const wallet = new sequence.Wallet("mainnet");
  const connectDetails = await wallet.disconnect();
  console.log("disconnectWallet", connectDetails);
  return connectDetails;
}

async function getWalletAddress() {
  const wallet = await new sequence.Wallet("mainnet").getAddress();
  return wallet;
}

 export default {connectWallet,getWalletAddress,disconnectWallet}
