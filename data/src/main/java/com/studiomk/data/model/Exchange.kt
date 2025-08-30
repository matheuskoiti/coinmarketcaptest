package com.studiomk.data.model

import com.google.gson.annotations.SerializedName

data class Exchange(
    val id: Int,
    val name: String,
    val slug: String,
    val logo: String,
    val description: String,
    @SerializedName("date_launched")
    val dateLaunched: String?,
    val notice: String?,
    val countries: List<String>,
    val fiats: List<String>,
    val tags: List<String>?,
    val type: String,
    @SerializedName("maker_fee")
    val makerFee: Double,
    @SerializedName("taker_fee")
    val takerFee: Double,
    @SerializedName("weekly_visits")
    val weeklyVisits: Long,
    @SerializedName("spot_volume_usd")
    val spotVolumeUsd: Double,
    @SerializedName("spot_volume_last_updated")
    val spotVolumeLastUpdated: String,
    val urls: Urls
)