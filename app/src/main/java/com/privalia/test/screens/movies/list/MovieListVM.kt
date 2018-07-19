package com.privalia.test.screens.movies.list

import android.support.v7.util.DiffUtil
import com.privalia.test.MOVIES
import com.privalia.test.R
import com.privalia.test.mvi.BaseEvent
import com.privalia.test.mvi.viewmodel.BaseViewModel
import com.privalia.test.mvi.viewmodel.StateReducer
import com.privalia.test.screens.movies.entities.Movie
import com.privalia.test.screens.movies.list.events.GetMoviesEvent
import com.privalia.test.screens.movies.list.events.SearchMoviesEvent
import com.zeyad.gadapter.ItemInfo
import com.zeyad.usecases.api.IDataService
import com.zeyad.usecases.requests.GetRequest
import io.reactivex.Flowable
import io.reactivex.functions.Function

class MovieListVM(private var dataUseCase: IDataService) : BaseViewModel<MovieListState>() {

    override fun mapEventsToActions(): Function<BaseEvent<*>, Flowable<*>> {
        return Function { event ->
            when (event) {
                is GetMoviesEvent -> getMovies(event.getPayLoad())
                is SearchMoviesEvent -> search(event.getPayLoad())
                else -> Flowable.empty<Any>()
            }
        }
    }

    override fun stateReducer(): StateReducer<MovieListState> {
        return object : StateReducer<MovieListState> {
            override fun reduce(newResult: Any?, event: String?, currentStateBundle: MovieListState?): MovieListState {
                val currentItemInfo = currentStateBundle?.movies.toMutableList()
                val pair = Flowable.fromIterable<ResultsItem>((newResult as PlaceList).results!!)
                        .map { resultsItem -> ItemInfo(resultsItem, R.layout.movie_list_content) }
                        .toList()
                        .toFlowable()
                        .scan<Pair<MutableList<ItemInfo>, DiffUtil.DiffResult>>(Pair(currentItemInfo,
                                DiffUtil.calculateDiff(MovieDiffCallBack(mutableListOf(),
                                        mutableListOf()))))
                        { pair1, next ->
                            Pair(next, DiffUtil.calculateDiff(MovieDiffCallBack(pair1.first, next)))
                        }
                        .skip(1)
                        .blockingFirst()
                return MovieListState(pair.first, pair.second, isTwoPane = currentStateBundle?.isTwoPane!!)
            }
        }
    }

    private fun getMovies(currentPage: Int): Flowable<List<Movie>> {
        return dataUseCase.getList(GetRequest.Builder(Movie::class.java, false)
                .url(String.format(MOVIES, currentPage + 1)).build())
    }

    private fun search(query: String, movies: List<Movie>): Flowable<List<Movie>> {
        return Flowable.just(movies.filter { query == "" })
    }
}