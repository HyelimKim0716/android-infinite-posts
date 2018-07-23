package com.riiid.infiniteposts.riiidpostlist.ui.main.favorite_post

import android.databinding.ObservableField
import com.riiid.infiniteposts.riiidpostlist.base.PostRepository
import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPost
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class FavoritePostViewModel(private val postRepository: PostRepository) {
    val userName = ObservableField<String>()

    val searchedFavoriteUserList = ArrayList<ServerPost>()

    val favoriteUsersViewEventSender = PublishSubject.create<Pair<FavoritePostViewEvent, Any>>()
            .apply { subscribeOn(Schedulers.io()) }

    fun sendFavoriteUsersViewEvent(event: FavoritePostViewEvent, data: Any) {
        favoriteUsersViewEventSender.onNext(event to data)
    }

    fun searchFavoriteUsers() {
        sendFavoriteUsersViewEvent(FavoritePostViewEvent.HIDE_KEYBOARD, 0)

        searchedFavoriteUserList.clear()
        userName.get()?.let {

//            postRepository.getUsersWithName(it).forEach {
//                searchedFavoriteUserList.add(it)
//            }

            sendFavoriteUsersViewEvent(FavoritePostViewEvent.REFRESH_SEARCHED_USER_LIST, 0)

        } ?: sendFavoriteUsersViewEvent(FavoritePostViewEvent.GET_ALL_USERS, 0)
    }
}