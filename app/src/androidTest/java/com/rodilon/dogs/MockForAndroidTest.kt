package com.rodilon.dogs

import com.rodilon.dogs.domain.model.DogsData
import com.rodilon.dogs.domain.model.LoginData
import com.rodilon.dogs.domain.model.User

object MockForAndroidTest {

    const val EMAIL = "renan@gmail.com"
    val TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiwic3ViIjoiNWYyNmNlODY3M2M4MzAyZGU2MzJiYWFiIiwiaWF0IjoxNTk2Mzc4NzU4LCJleHAiOjE1OTc2NzQ3NTh9.HmdimN3adLUa064_m_-QyORffIxDDTnc7K2oLXpWlhY"

    private val USER = User(
        "5f26ce8673c8302de632baab",
        "renan@gmail.com",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9",
        "2020-08-02T14:32:38.517Z",
        "2020-08-02T14:32:38.517Z",
        0
    )

    val LOGIN_DATA = LoginData(USER)

    const val AUTHORIZATION = "5f26ce8673c8302de632baab"
    const val CATEGORY = "hound"

    private val list = listOf(
        "https://images.dog.ceo/breeds/husky/n02110185_10047.jpg",
        "https://images.dog.ceo/breeds/husky/n02110185_10116.jpg",
        "https://images.dog.ceo/breeds/husky/n02110185_10171.jpg",
        "https://images.dog.ceo/breeds/husky/n02110185_10175.jpg"
    )

    val DOGS_DATA = DogsData(
        "husky",
        list
    )
}