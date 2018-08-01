package br.com.leanwork.testedevandroidlean.data.repository

import android.arch.lifecycle.LiveData

interface IRepository<T> {

    fun findAll(): LiveData<MutableList<T>>

    fun save(obj: T): Long

    fun delete(obj: T)
}