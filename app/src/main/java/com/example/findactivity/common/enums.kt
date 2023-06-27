package com.example.findactivity.common

import com.example.findactivity.R

interface DefaultEnum {
    val nameId: Int
}

enum class StatusBored(val value: Int): DefaultEnum{
    IN_PROGRESS(0) {
        override val nameId = R.string.status_in_progress
    },
    DONE(1) {
        override val nameId = R.string.status_done
    },
    CANCELLED(2) {
        override val nameId = R.string.status_cancelled
    },
    NOT_SAVED(3) {
        override val nameId: Int = R.string.status_not_saved
    };

    companion object {
        fun getStatus(value: Int?):StatusBored{
            if(value == null) return NOT_SAVED
            return when(value){
                0-> IN_PROGRESS
                1-> DONE
                2-> CANCELLED
                else -> NOT_SAVED
            }
        }
    }
}