import { Web3ReactProvider } from "@web3-react/core";
import type { AppProps } from "next/app";
import getLibrary from "../getLibrary";
import "../styles/globals.css";
import { ThemeProvider, Banner, Flex } from "pcln-design-system";
import NavBar from "../components/NavBar";
import Link from "next/link";
import AccountContext, { AccountDetails } from "../context/AccountContext";
import { useState } from 'react'

function NextWeb3App({ Component, pageProps }: AppProps) {
  const [isConnected, setIsConnected] = useState(false)
  const [walletAddress, setWalletAddress] = useState('')
  const [wallet, setWallet] = useState(null)

  return (
    <Web3ReactProvider getLibrary={getLibrary}>
      <AccountContext.Provider value={{
        isConnected,
        setIsConnected,
        setWalletAddress,
        walletAddress,
        wallet, setWallet

      } as AccountDetails}>
        <ThemeProvider
          theme={{
            palette: {
              primary: {
                base: "rgb(81 66 252)",
                dark: '#3b30b8'
              },

            },
            componentStyles: {
              Button: {
                secondary: {
                  primary: {
                    backgroundImage: 'linear-gradient(216.56deg,#4B50E6 15.32%,#E250E5 94.32%)',
                    borderRadius: '500px',

                    "&:hover": {
                      backgroundImage: 'linear-gradient(216.56deg,#4B50E6 35.32%,#E250E5 94.32%)',
                      boxShadow: "0 0 0 0 white",
                      transition: ".2s ease-in-out",

                    },
                  }
                },
                outline: {
                  primary: {
                    backgroundColor:
                      "transparent",
                    border: "2px solid rgb(81 66 252)",
                    borderRadius: '500px',
                    boxShadow: '0 0 0 0 white',
                    transform: "scale(1)",
                    "&:hover": {
                      backgroundColor:
                        "rgb(81 66 252)",
                      color: "#14141f",
                      boxShadow: "0 0 0 0 white",
                      transform: "scale(1.1)",
                      transition: ".2s ease-in-out",
                    },
                  },

                },
                fill: {
                  primary: {
                    backgroundColor:
                      "rgb(81 66 252)",
                    borderRadius: '500px',
                    boxShadow: '0 0 0 0 white',
                    transform: "scale(1)",
                    border: "0px",
                    "&:hover": {
                      backgroundColor:
                        "rgb(81 66 252)",
                      color: "#14141f",
                      boxShadow: "0 0 0 0 white",
                      transform: "scale(1.1)",
                      transition: ".2s ease-in-out",
                    },
                  },

                },
                subtle: {
                  primary: {
                    backgroundColor:
                      "transparent",
                    borderRadius: '500px',
                    boxShadow: '0 0 0 0 white',
                    border: "0px",
                    "&:hover": {
                      backgroundColor:
                        "transparent",
                      boxShadow: "0 0 0 0 white",
                    },
                  },

                }
              }
            },
          }}
        >
          <NavBar />
          <Component {...pageProps} />
        </ThemeProvider>
      </AccountContext.Provider>
    </Web3ReactProvider>
  );
}

export default NextWeb3App;
