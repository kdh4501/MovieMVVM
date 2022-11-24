package com.creativelab.moviemvvm.ui.popular_movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.creativelab.moviemvvm.data.api.POST_PER_PAGE
import com.creativelab.moviemvvm.data.api.TheMovieDBInterface
import com.creativelab.moviemvvm.data.repository.MovieDataSource
import com.creativelab.moviemvvm.data.repository.MovieDataSourceFactory
import com.creativelab.moviemvvm.data.repository.NetworkState
import com.creativelab.moviemvvm.data.vo.Movie
import io.reactivex.disposables.CompositeDisposable

class MoviePagedListRepository(private val apiService: TheMovieDBInterface) {

    lateinit var moviePagedList: LiveData<PagedList<Movie>>
    lateinit var movieDatasourceFactory: MovieDataSourceFactory

    fun fetchLiveMoviePagedList(compositeDisposable: CompositeDisposable) : LiveData<PagedList<Movie>> {
        movieDatasourceFactory = MovieDataSourceFactory(apiService, compositeDisposable)

        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(POST_PER_PAGE)
            .build()

        moviePagedList = LivePagedListBuilder(movieDatasourceFactory, config).build()

        return moviePagedList
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return Transformations.switchMap<MovieDataSource, NetworkState>(
            movieDatasourceFactory.moviesLiveDataSource, MovieDataSource::networkState
        )
    }

}