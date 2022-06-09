package com.example.films.kotlinapp.ui.activities.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.films.R

/**
 * Базовое Activity с навигацией
 */
abstract class BaseNavigationActivity(
    @LayoutRes contentLayoutId: Int = R.layout.main_activity
) : BaseActivity(contentLayoutId) {

    private lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigationController()
    }

    protected open fun getNavigationViewId() = R.id.main_controller

    private fun initNavigationController() {
        val navHost =
            supportFragmentManager.findFragmentById(getNavigationViewId()) as NavHostFragment
        navigationController = navHost.navController
    }
}