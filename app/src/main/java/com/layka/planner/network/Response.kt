package com.layka.planner.network

sealed class Response(val message: String) {
    class Success(msg: String): Response(message = msg)
    class Failure(msg: String): Response(message = msg)
}