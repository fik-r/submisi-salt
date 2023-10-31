package com.example.common

import kotlinx.coroutines.flow.Flow

abstract class UseCase<SuccessType : Any, in Params> {

    abstract fun build(params: Params?): Flow<SuccessType>

    fun execute(params: Params? = null): Flow<SuccessType> {
        return build(params)
    }
}