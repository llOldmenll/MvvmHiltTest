package com.exercise.mvvmhilttest.presentation.repositories

import androidx.lifecycle.ViewModel
import com.exercise.domain.entity.Repositories
import com.exercise.domain.use_case.GetRepositoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class RepositoriesViewModel @Inject constructor(
    private val getRepositoriesUseCase: GetRepositoriesUseCase
) : ViewModel() {

    private val nextRepositoriesSubject: PublishSubject<Repositories> = PublishSubject.create()
    private val loadNextPageSubject: PublishSubject<Unit> = PublishSubject.create()
    private val errorsSubject: PublishSubject<Throwable> = PublishSubject.create()

    private val compositeDisposable = CompositeDisposable()
    private var nextPageUrl = ""

    init {
        initRx()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun isLoadNextAvailable() = nextPageUrl.isNotBlank()

    fun nextRepositories(): Observable<Repositories> = nextRepositoriesSubject

    fun errors(): Observable<Throwable> = errorsSubject

    fun loadNextPage() {
        loadNextPageSubject.onNext(Unit)
    }

    private fun initRx() {
        compositeDisposable += loadNextPageSubject.subscribe({ loadRepositories() }, {})
    }

    private fun loadRepositories() {
        compositeDisposable += getRepositoriesUseCase.execute(nextPageUrl)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                nextPageUrl = it.next ?: ""
                nextRepositoriesSubject.onNext(it)
            }, { errorsSubject.onNext(it) })
    }
}