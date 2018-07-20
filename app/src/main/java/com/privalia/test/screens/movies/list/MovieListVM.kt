package com.privalia.test.screens.movies.list

import android.support.v7.util.DiffUtil
import com.privalia.test.MOVIES
import com.privalia.test.R
import com.privalia.test.mvi.BaseEvent
import com.privalia.test.mvi.viewmodel.BaseViewModel
import com.privalia.test.mvi.viewmodel.StateReducer
import com.privalia.test.screens.movies.entities.Movie
import com.privalia.test.screens.movies.entities.MovieListResponse
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

    override fun stateReducer(): StateReducer<MovieListState> { // Todo Handle switching between search and get
        return object : StateReducer<MovieListState> {
            override fun reduce(newResult: Any, event: String, currentStateBundle: MovieListState): MovieListState {
                val currentItemInfo = currentStateBundle.movies.toMutableList()
                return when (currentStateBundle) {
                    is EmptyState -> when (newResult) {
                        is MovieListResponse -> {
                            val pair = Flowable.fromIterable<Movie>((newResult).results)
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
                            GetMovieState(gMovies = pair.first, gCallback = pair.second,
                                    gPage = currentStateBundle.page + 1)
                        }
                        else -> throw IllegalStateException("Can not reduce EmptyState with this" +
                                " result: $newResult!")
                    }
                    is GetMovieState -> when (newResult) {
                        is MovieListResponse -> {
                            val pair = Flowable.fromIterable<Movie>((newResult).results)
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
                            GetMovieState(gMovies = pair.first, gCallback = pair.second,
                                    gPage = currentStateBundle.page + 1)
                        }
                        is List<*> -> {
                            val pair = Flowable.just((newResult as List<ItemInfo>).toMutableList())
                                    .scan<Pair<MutableList<ItemInfo>, DiffUtil.DiffResult>>(Pair(currentItemInfo,
                                            DiffUtil.calculateDiff(MovieDiffCallBack(mutableListOf(),
                                                    mutableListOf()))))
                                    { pair1, next ->
                                        Pair(next, DiffUtil.calculateDiff(MovieDiffCallBack(pair1.first, next)))
                                    }
                                    .skip(1)
                                    .blockingFirst()
                            MovieSearchState(sMovies = pair.first, sCallback = pair.second,
                                    sPage = currentStateBundle.page + 1)
                        }
                        else -> throw IllegalStateException("Can not reduce GetMovieState with this" +
                                " result: $newResult!")
                    }
                    is MovieSearchState -> when (newResult) {
                        is MovieListResponse -> {
                            val pair = Flowable.fromIterable<Movie>((newResult).results)
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
                            GetMovieState(gMovies = pair.first, gCallback = pair.second,
                                    gPage = currentStateBundle.page + 1)
                        }
                        else -> throw IllegalStateException("Can not reduce MovieSearchState with this" +
                                " result: $newResult!")
                    }
                }
            }
        }
    }

    private fun getMovies(currentPage: Int): Flowable<MovieListResponse> {
        return dataUseCase.getObject(GetRequest.Builder(Movie::class.java, false)
                .url(MOVIES + currentPage + 1).build())
    }

    private fun search(pair: Pair<String, List<ItemInfo>>): Flowable<List<ItemInfo>> {
        return Flowable.just(pair.second.filter { pair.first == "" })
    }
}