package com.ishwar_arcore.saveo.data.model

import java.io.Serializable

data class Country(
    val code: String,
    val name: String,
    val timezone: String
) : Serializable