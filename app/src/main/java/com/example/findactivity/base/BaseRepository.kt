package com.example.findactivity.base

import com.example.findactivity.common.ConnectivityUtils
import com.example.findactivity.common.CoroutineContextProvider
import com.example.findactivity.common.GENERAL_NETWORK_ERROR
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.data.model.Failure
import com.example.findactivity.data.model.GenericError
import com.example.findactivity.data.model.MyResult
import kotlinx.coroutines.withContext
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

abstract class BaseRepository<T : Any> : KoinComponent {
    private val connectivity: ConnectivityUtils by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    protected suspend fun fetchData(dataProvider: suspend () -> MyResult<T>): MyResult<T> {
        return if (connectivity.isNetworkAvailable()) {
            withContext(contextProvider.io) {
                dataProvider()
            }
        } else {
            Failure(GenericError(Throwable(GENERAL_NETWORK_ERROR)))
        }
    }

    protected suspend fun fetchIfDataExistsFromDB(dataProvider: () -> MyResult<Boolean>): MyResult<Boolean> {
        return withContext(contextProvider.io) {
            dataProvider()
        }
    }

    protected suspend fun fetchDataList(dataProvider: () -> MyResult<List<ActEntity>>): MyResult<List<ActEntity>> {
        return withContext(contextProvider.io) {
            dataProvider()
        }
    }

    protected suspend fun saveActivityOnDb(dataProvider: suspend () -> MyResult<Boolean>): MyResult<Boolean> {
        return withContext(contextProvider.io) {
            dataProvider()
        }
    }
}