package com.rodilon.dogs.features.mock

import com.rodilon.dogs.domain.model.DogsData
import com.rodilon.dogs.domain.model.LoginData
import com.rodilon.dogs.domain.model.User

object MockForAndroidTest {

    const val TOKEN =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJpZGRvZy1zZXJ2aWNlIiwic3ViIjoiNWYyNmNlODY3M2M4MzAyZGU2MzJiYWFiIiwiaWF0IjoxNTk2Mzc4NzU4LCJleHAiOjE1OTc2NzQ3NTh9.HmdimN3adLUa064_m_-QyORffIxDDTnc7K2oLXpWlhY"

    private val USER = User(
        "5f26ce8673c8302de632baab",
        "renan@gmail.com",
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9",
        "2020-08-02T14:32:38.517Z",
        "2020-08-02T14:32:38.517Z",
        0
    )

    val LOGIN_DATA = LoginData(USER)

    private val listHusky = listOf(
        "https://images.dog.ceo/breeds/husky/n02110185_10047.jpg",
        "https://images.dog.ceo/breeds/husky/n02110185_10116.jpg",
        "https://images.dog.ceo/breeds/husky/n02110185_10171.jpg",
        "https://images.dog.ceo/breeds/husky/n02110185_10175.jpg"
    )

    private val listPug = listOf(
        "https://images.dog.ceo/breeds/pug/n02110958_10186.jpg",
        "https://images.dog.ceo/breeds/pug/n02110958_10193.jpg",
        "https://images.dog.ceo/breeds/pug/n02110958_10378.jpg",
        "https://images.dog.ceo/breeds/pug/n02110958_10842.jpg"
    )

    private val listHound = listOf(
        "https://images.dog.ceo/breeds/hound-english/n02089973_1000.jpg",
        "https://images.dog.ceo/breeds/hound-english/n02089973_1030.jpg",
        "https://images.dog.ceo/breeds/hound-english/n02089973_1066.jpg",
        "https://images.dog.ceo/breeds/hound-english/n02089973_1076.jpg"
    )

    private val listLabrador = listOf(
        "https://images.dog.ceo/breeds/labrador/n02099712_1150.jpg",
        "https://images.dog.ceo/breeds/labrador/n02099712_1200.jpg",
        "https://images.dog.ceo/breeds/labrador/n02099712_1229.jpg",
        "https://images.dog.ceo/breeds/labrador/n02099712_1254.jpg"
    )

    fun getDogsData(category: String): DogsData {
        val list = when (category) {
            "husky" -> listHusky
            "labrador" -> listLabrador
            "pug" -> listPug
            "hound" -> listHound
            else -> listOf()

        }
        return DogsData(
            category,
            list
        )

    }

    val DOGS_DATA = DogsData(
        "husky",
        listHusky
    )
}