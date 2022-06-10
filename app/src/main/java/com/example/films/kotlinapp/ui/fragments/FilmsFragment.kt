package com.example.films.kotlinapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.films.R
import com.example.films.databinding.FilmsListFragmentBinding
import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.mvp.presenters.FilmsPresenter
import com.example.films.kotlinapp.mvp.views.FilmsView
import com.example.films.kotlinapp.ui.constants.UiConstants.SPAN_COUNT
import com.example.films.kotlinapp.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.example.films.kotlinapp.ui.list.ListItem
import com.example.films.kotlinapp.ui.list.adapters.ListAdapter
import com.example.films.kotlinapp.ui.list.adapters.base.ListExtension
import com.example.films.kotlinapp.ui.list.adapters.base.ListSpanSize
import com.example.films.kotlinapp.ui.list.view_holders.FilmViewHolder
import com.example.films.kotlinapp.ui.list.view_holders.GenreViewHolder
import com.example.films.utils.ScreenLocker
import com.example.films.utils.snackbar_holder.MessagesHolder
import moxy.ktx.moxyPresenter
import org.koin.android.ext.android.get

/**
 * Fragment с отображением списка фильмов и жанров
 */
class FilmsFragment :
    BaseWithAppBarNavigationFragment(R.layout.films_list_fragment),
    FilmsView,
    ScreenLocker,
    FilmViewHolder.FilmViewHolderListener,
    GenreViewHolder.GenreViewHolderListener {

    private var _binding: FilmsListFragmentBinding? = null
    private val binding get() = _binding!!
    private var _adapter: ListAdapter? = null
    private val adapter get() = _adapter!!

    private var listExtension: ListExtension? = null

    private val presenter by moxyPresenter {
        get<FilmsPresenter>()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FilmsListFragmentBinding.bind(view)

        initRecyclerViewAdapter()
        initAppBarProvider()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _adapter = null
    }

    override fun showFilms(films: List<ListItem>) {
        adapter.updateWithDiffUtils(films)
    }

    override fun showFilmInfo(film: Film, genresWithYear: String) {
        val action = FilmsFragmentDirections.actionFilmsToFilmPage(film, genresWithYear)
        navigate(action)
    }

    override fun startContentLoading() {
        binding.downloadProgressBar.isVisible = true
        lockScreen()
    }

    override fun endContentLoading() {
        binding.downloadProgressBar.isVisible = false
        unlockScreen()
    }

    override fun showContentLoadingError(error: String) {
        MessagesHolder(
            viewLifecycleOwner,
            binding.root
        ).showUnhiddenNetworkError(error) { presenter.onRepeatButtonClicked() }
    }

    override fun onFilmClick(filmId: Int) {
        presenter.onFilmClicked(filmId)
    }

    override fun onGenreClick(genre: String) {
        presenter.onGenreSelected(genre)
    }

    private fun initRecyclerViewAdapter() {
        _adapter = ListAdapter(layoutInflater)
        adapter.setListeners(this, this)

        listExtension = ListExtension(binding.filmsList)
        listExtension?.setAdapter(adapter)

        val gridLayoutManager = GridLayoutManager(context, SPAN_COUNT)
        gridLayoutManager.spanSizeLookup = ListSpanSize(adapter)
        listExtension?.setLayoutManager(gridLayoutManager)

        val itemAnim = binding.filmsList.itemAnimator
        (itemAnim as DefaultItemAnimator).supportsChangeAnimations = false
    }

    private fun initAppBarProvider() {
        appBarProvider?.setAppBarSettings(this)
        appBarProvider?.setCustomToolbarView(R.layout.centered_toolbar)
        (appBarProvider?.setCustomToolbarView(R.layout.centered_toolbar) as TextView)
            .text = resources.getString(R.string.title_films)
    }
}