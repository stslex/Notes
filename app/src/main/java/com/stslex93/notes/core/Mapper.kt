package com.stslex93.notes.core

import java.lang.Exception

interface Mapper {

    interface Data<D, U> {
        fun map(data: D): U
    }

    interface DataToUI<D, U> : Data<D, U> {

        fun map(exception: Exception): U
        fun map(): U
    }
}