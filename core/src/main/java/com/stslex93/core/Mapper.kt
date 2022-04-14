package com.stslex93.core

interface Mapper {

    interface Data<D, U> {
        fun map(data: D): U
    }

    interface DataToDomain<D, U> : Data<D, U> {
        fun map(exception: Exception): U
    }

    interface DataToUI<D, U> : DataToDomain<D, U> {
        fun map(): U
    }
}