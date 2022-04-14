package com.stslex93.core

interface Object<out T> {
    fun <U> map(mapper: Mapper.DataToUI<in T, U>): U
}