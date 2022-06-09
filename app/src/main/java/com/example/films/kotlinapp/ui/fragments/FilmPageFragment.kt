package com.example.films.kotlinapp.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.example.films.R
import com.example.films.databinding.FilmPageFragmentBinding
import com.example.films.kotlinapp.mvp.models.entities.Film
import com.example.films.kotlinapp.mvp.views.FilmView
import com.example.films.kotlinapp.ui.fragments.base.BaseWithAppBarNavigationFragment
import com.example.films.utils.image_loader.ImageLoader
import com.example.films.utils.image_loader.ImageLoaderListener

/**
 * Fragment с отображением детальной информации о фильме
 */
class FilmPageFragment : BaseWithAppBarNavigationFragment(R.layout.film_page_fragment), FilmView {

    private val args: FilmPageFragmentArgs by navArgs()

    private var _binding: FilmPageFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FilmPageFragmentBinding.bind(view)

        initAppBarProvider()

        showFilm(args.film, args.genresWithYear)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun showFilm(film: Film, genresWithYear: String) {
        binding.apply {
            if (film.image_url.isNotEmpty())
                ImageLoader
                    .load(film.image_url)
                    .into(
                        filmPosterImage,
                        object : ImageLoaderListener {
                            override fun onError(error: String) {
                                posterNotFoundImage.isVisible = true
                            }

                            override fun onSuccess() {
                                posterNotFoundImage.isVisible = false
                            }
                        }
                    )
            else posterNotFoundImage.isVisible = true

            filmNameText.text = film.localized_name
            genresYearText.text = genresWithYear
            filmRatingText.text = film.rating
            ratingSourceText.text = getString(R.string.rating_source)
            filmDescriptionText.text = film.description
        }
    }

    private fun initAppBarProvider() {
        appBarProvider?.setAppBarSettings(this)
        (appBarProvider?.setCustomToolbarView(R.layout.centered_toolbar) as TextView)
            .text = args.film.localized_name
        appBarProvider?.setBackButtonVisibility(true)
        appBarProvider?.setHomeAsUpIndicator(R.drawable.ic_back_button)
    }
}