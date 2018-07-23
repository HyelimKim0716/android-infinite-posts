package com.riiid.infiniteposts.riiidpostlist.ui.main

import com.riiid.infiniteposts.riiidpostlist.SchedulersRule
import com.riiid.infiniteposts.riiidpostlist.data.MockPostApi
import com.riiid.infiniteposts.riiidpostlist.data.model.ServerPost
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.hamcrest.Matchers
import org.hamcrest.collection.IsCollectionWithSize.hasSize
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {


    @Rule
    @JvmField
    val testSchedulerRule = SchedulersRule()

    lateinit var viewModel: MainViewModel

    lateinit var mockPostApi: MockPostApi

    @Before
    fun setup() {
        viewModelSetup()
    }

    private fun viewModelSetup() {
        mockPostApi = MockPostApi()
        viewModel = MainViewModel(mockPostApi)
        viewModel.loadPosts()

        viewModel.mainViewEventSender.test().awaitCount(1)
    }


    @Test
    fun init_checkPostItems() {
//        assertThat(viewModel.postList, hasSize(mockPostApi.postList.size))
//        assertThat(viewModel.postList, CoreMatchers.hasItem(Matchers.hasProperty(ServerPost::title.name, `is`(mockPostApi.postList[1].title))))
//        assertThat(viewModel.postList, Matchers.not(CoreMatchers.hasItem(Matchers.hasProperty(ServerPost::title.name, `is`(ServerPost().title)))))
    }



}