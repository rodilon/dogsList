package com.rodilon.dogs.mock

import com.rodilon.dogs.domain.model.DogsData
import com.rodilon.dogs.domain.model.LoginData
import com.rodilon.dogs.domain.model.User

object MockForUnitTest {

    const val EMAIL = "renan@gmail.com"

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