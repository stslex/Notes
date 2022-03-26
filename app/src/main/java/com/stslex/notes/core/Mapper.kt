package com.stslex.notes.core

interface Mapper {

    interface Data<D, U> {
        fun map(data: D): U
    }

    interface DataToUI<D, U> : Data<D, U> {

        fun map(exception: Exception): U
        fun map(): U
    }
}