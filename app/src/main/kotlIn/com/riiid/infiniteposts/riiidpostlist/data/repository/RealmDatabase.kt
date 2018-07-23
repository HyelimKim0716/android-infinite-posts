package com.riiid.infiniteposts.riiidpostlist.data.repository

import android.content.Context
import com.riiid.infiniteposts.riiidpostlist.common.LogMgr
import io.reactivex.Completable
import io.reactivex.Single
import io.realm.*

open class RealmDatabase(val context: Context) {
    val TAG = "RealmDatabase"

    fun setup() {
        Realm.init(context)
        RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build().let {
                    Realm.setDefaultConfiguration(it)
                }
    }

    fun getRealm() = Realm.getDefaultInstance()

    /**
     * Find all data from realm matched with clazz.
     *
     * @param clazz
     * @return RealmResults matched with clazz.
     */
    open fun <T: RealmObject> findAll(clazz: Class<T>): RealmResults<T>
            = getRealm()
            .where(clazz)
            .findAll()

    open fun <T: RealmObject> findAll(clazz: Class<T>, sortFieldName: String, sortOrder: Sort): RealmResults<T>
            = getRealm().where(clazz)
            .sort(sortFieldName, sortOrder)
            .findAll()

    /**
     * Add one item to Realm
     *
     * @param item which is created.
     * @return Single observer returns success when realm created item successfully,
     *          and throws an error when copyToRealm threw an error.
     */
    open fun <T: RealmObject> addItem(item: T): Single<T>
            = Single.create<T> { emitter ->
        getRealm().executeTransactionAsync({
            it.copyToRealm(item)
            LogMgr.d("add Item $item")
        }, {
            emitter.onSuccess(item)
            LogMgr.d("Complete add item")
        }, {
            it.printStackTrace()
            emitter.onError(it)
            LogMgr.e("addItem Error : ${it.message}")
        })
    }

    open fun <T: RealmObject> deleteAllItems(clazz: Class<T>): Completable
            = Completable.create { emitter ->
        getRealm().executeTransactionAsync({
            it.delete(clazz)
        }, {
            emitter.onComplete()
            LogMgr.d("deleteAllItems completed")
        }, {
            emitter.onError(it)
            LogMgr.e("deleteAllItems error: ${it.message}")
        })
    }

    open fun <T: RealmObject> deleteItem(clazz: Class<T>, fieldName: String, value: Any): Completable
            = Completable.create { emitter ->
        getRealm().executeTransactionAsync({
            val item = getItem(clazz, arrayOf(fieldName to value))
            item?.deleteFromRealm() ?: emitter.onError(Exception())
        }, {
            LogMgr.d("deleteItem completed")
            emitter.onComplete()
        }, {
            it.printStackTrace()
            emitter.onError(it)
            LogMgr.e("deleteItem error: ${it.message}")
        })
    }

    open fun <T: RealmObject> getCount(clazz: Class<T>)
            = getRealm().where(clazz).count()

    fun <T: RealmObject> getItem(clazz: Class<T>, fieldName: String, value: Any)
            = getItem(clazz, arrayOf(fieldName to value))

    fun <T: RealmObject> getItem(clazz: Class<T>, equalToList: Array<Pair<String, Any>>): T?
            = getRealm().where(clazz).apply {

        equalToList.forEach {
            when (it.second) {
                is String -> this.equalTo(it.first, it.second as String)
                is Int -> this.equalTo(it.first, it.second as Int)
                is Long -> this.equalTo(it.first, it.second as Long)
            }
        }
    }.findFirst()

    fun <T: RealmObject> getContainsFieldValueItems(clazz: Class<T>, fieldName: String, value: String): RealmResults<T>
            = getContainsFieldValueItems(clazz, arrayOf(fieldName to value))

    fun <T: RealmObject> getContainsFieldValueItems(clazz: Class<T>, fieldName: String, value: String, sortFieldName: String, sort: Sort): RealmResults<T>
            = getContainsFieldValueItems(clazz, arrayOf(fieldName to value), sortFieldName, sort)

    fun <T: RealmObject> getContainsFieldValueItems(clazz: Class<T>, equalToList: Array<Pair<String, String>>): RealmResults<T>
            = getRealm().where(clazz).apply {
        equalToList.forEach {
            this.contains(it.first, it.second)
        }
    }.findAll()

    fun <T: RealmObject> getContainsFieldValueItems(clazz: Class<T>, equalToList: Array<Pair<String, String>>, sortFieldName: String, sort: Sort): RealmResults<T>
            = getRealm().where(clazz).apply {
        equalToList.forEach {
            this.contains(it.first, it.second)
        }
    }.sort(sortFieldName, sort).findAll()
}