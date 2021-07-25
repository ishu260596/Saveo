package com.ishwar_arcore.saveo.data.model

import java.io.Serializable

data class WebChannel(
    val country: CountryX,
    val id: Int,
    val name: String
) : Serializable