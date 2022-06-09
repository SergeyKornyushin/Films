package com.example.films.kotlinapp.mvp.presenters.base

import com.example.films.kotlinapp.mvp.views.base.BaseView
import moxy.MvpPresenter

/**
 * Базовый presenter c наследованием Moxy
 */
open class BasePresenter<View : BaseView> : MvpPresenter<View>()