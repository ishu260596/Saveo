package com.ishwar_arcore.saveo.data.model

import java.io.Serializable

data class Network(
    val country: Country,
    val id: Int,
    val name: String
): Serializable