package com.example.findactivity.domain.repository

import com.example.findactivity.base.BaseRepository
import com.example.findactivity.common.ConnectivityUtils
import com.example.findactivity.data.dao.ActDAO
import com.example.findactivity.data.model.ActEntity
import com.example.findactivity.data.model.MyResult
import com.example.findactivity.data.model.getData
import com.example.findactivity.data.model.getDataList
import com.example.findactivity.networking.ActApi
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val actRepModule = module {
    factory { ActRepository(get(), get()) }
    factory { ConnectivityUtils(androidContext()) }
}

class ActRepository(private val actApi: ActApi, private val actDAO: ActDAO) :
    BaseRepository<ActEntity>() {

    suspend fun getRandomBoredActivity(lastKey: String): MyResult<ActEntity> {
        return fetchData(
            dataProvider = {
                actApi.getRandomActivity(lastKey).getData()
            })
    }

    suspend fun getActivityList(): MyResult<List<ActEntity>> {
        return fetchDataList {
            actDAO.getAllActivities().getDataList()
        }
    }

    suspend fun getAlreadyOnDB(key: String): MyResult<Boolean> {
        return fetchIfDataExistsFromDB {
            actDAO.findIfActExistsByKey(key).getData()
        }
    }

    suspend fun saveOnDB(boredActivityEntity: ActEntity): MyResult<Boolean> {
        return saveActivityOnDb {
            actDAO.saveActivity(boredActivityEntity)
            actDAO.findIfActExistsByKey(boredActivityEntity.key).getData()
        }
    }
}