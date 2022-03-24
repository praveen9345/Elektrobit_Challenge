package com.example.electrobitchallenge.ui

import android.view.View
import androidx.test.rule.ActivityTestRule
import com.example.electrobitchallenge.R
import com.google.common.truth.Truth
import org.junit.Assert.*

import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NewsActivityTest {
    @Rule
    @JvmField
    val mActivityTestRule: ActivityTestRule<NewsActivity>  =ActivityTestRule(NewsActivity::class.java)
    private lateinit var mActivity: NewsActivity
    @Before
    fun setUp() {
        mActivity=mActivityTestRule.activity
    }

    @Test
    fun testLaunch(){
        var view =mActivity.findViewById<View>(R.id.newsNavHostFragment)
        assertNotNull(view)
    }

    @After
    fun tearDown() {

    }
}