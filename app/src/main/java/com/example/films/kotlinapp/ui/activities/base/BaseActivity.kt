package com.example.films.kotlinapp.ui.activities.base

import androidx.annotation.LayoutRes
import moxy.MvpAppCompatActivity

/**
 * Базовая Activity
 */
abstract class BaseActivity(
    @LayoutRes contentLayoutId: Int
) : MvpAppCompatActivity(contentLayoutId)