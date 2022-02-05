// @ts-check

/**
 * @type {import('next').NextConfig}
 **/
module.exports = {
  reactStrictMode: true,
  swcMinify: true,
  serverRuntimeConfig: {
    authApiKey: process.env.AUTH_API_KEY || '2267621f-d141-4838-a0d4-d2889ec809ab',
    contractAddress: process.env.CONTRACT_ADDRESS || '0x47c7ff137d7a6644a9a96f1d44f5a6f857d9023f',
  },
}
