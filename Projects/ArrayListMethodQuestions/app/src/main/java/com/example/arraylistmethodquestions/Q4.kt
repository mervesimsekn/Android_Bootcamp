package com.example.arraylistmethodquestions

data class CoinResponse(
    val name: String,
    val coinId: String,
    val hashingAlgorithm: String?,
    val description: String,
    val image: String,
    val currentPrice: Float,
    val priceChangePercentage24h: Float
)

data class CoinUI(
    val name: String,
    val coinId: String,
    val description: String,
    val image: String
)

fun main() {

    val coinList = arrayListOf(
        CoinResponse(
            name = "Bitcoin",
            coinId = "BTC",
            hashingAlgorithm = "SHA-256",
            description = "Bitcoin is a decentralized digital currency, without a central bank or single administrator.",
            image = "https://example.com/bitcoin.png",
            currentPrice = 34789.21f,
            priceChangePercentage24h = -2.34f
        ),
        CoinResponse(
            name = "Ethereum",
            coinId = "ETH",
            hashingAlgorithm = "Ethash",
            description = "Ethereum is an open-source, blockchain-based platform that enables developers to build and deploy smart contracts.",
            image = "https://example.com/ethereum.png",
            currentPrice = 2110.45f,
            priceChangePercentage24h = 0.78f
        ),
        CoinResponse(
            name = "Litecoin",
            coinId = "LTC",
            hashingAlgorithm = "Scrypt",
            description = "Litecoin is a peer-to-peer cryptocurrency that enables instant, near-zero cost payments to anyone in the world.",
            image = "https://example.com/litecoin.png",
            currentPrice = 129.57f,
            priceChangePercentage24h = -1.12f
        ),
        CoinResponse(
            name = "Ripple",
            coinId = "XRP",
            hashingAlgorithm = null,
            description = "Ripple is a digital payment protocol and cryptocurrency that is designed to facilitate fast, low-cost international money transfers.",
            image = "https://example.com/ripple.png",
            currentPrice = 0.5483f,
            priceChangePercentage24h = 1.45f
        )
    )

    val coinUIList = ArrayList<CoinUI>()

    coinList.forEach {
        val coinUI = CoinUI(
            name = it.name,
            coinId = it.coinId,
            description = it.description,
            image = it.image
        )
        coinUIList.add(coinUI)
    }

    coinUIList.forEach {
        println(
            "Name: ${it.name}, CoinID: ${it.coinId}, Description: ${it.description}, Image: ${it.image}"
        )
    }

}