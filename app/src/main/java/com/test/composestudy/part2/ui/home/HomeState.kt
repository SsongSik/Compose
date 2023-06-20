package com.test.composestudy.part2.ui.home

import android.content.Intent
import com.test.composestudy.part2.ui.content.ContentActivity


class HomeState(val activity: HomeActivity) {
    fun showContent(index: Int) {
        activity.startActivity(Intent(activity, ContentActivity::class.java).apply {
            putExtra("id", index)
        })
    }
}