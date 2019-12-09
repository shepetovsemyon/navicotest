package ru.shepetov.navicotest

class NetworkConfig {
    val maxRequests = 72
    val maxRequestPerHost = 8
    val connectTimeoutSeconds = 10L
    val readTimeoutSeconds = 10L
    val writeTimeoutSeconds = 10L

    val retryTimeoutMillisec = 21_000L
    val retryIntervalMillisec = 750L
}