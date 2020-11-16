package com.example.designapp.repository

import com.example.designapp.data.ServiceApi
import com.example.designapp.data.model.TopHeadlinesResponse
import com.example.designapp.other.Resource
import javax.inject.Inject

class NewsRepository @Inject constructor(private val serviceApi: ServiceApi) {
    suspend fun getTopHedlines ( country : String , page  : Int) :Resource<TopHeadlinesResponse>{
        return try {
            val response = serviceApi.getNews(country , page)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)

            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldnt reach to the  server, Check Internet Connection", null)
        }
    }
}