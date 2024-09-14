package com.layka.planner.network

import com.layka.planner.repository.entities.TaskDb
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface BackupApi {
    @GET("/")
    suspend fun getTasks(): TaskRequest

    @POST("/")
    suspend fun postTasks(@Body tasks: TaskRequest)
}