package com.exercise.mvvmhilttest.presentation.repository_details

import androidx.lifecycle.ViewModel
import com.exercise.domain.entity.Repository
import com.exercise.domain.mapper.Mapper
import com.exercise.mvvmhilttest.entity.RepositoryDetailsVM
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

@HiltViewModel
class RepositoryDetailsViewModel @Inject constructor(
    private val repositoryDetailsMapper: Mapper<Repository, RepositoryDetailsVM>
) : ViewModel() {

    // TODO: Create separate ViewModel only for selected repository data sharing
    private val repositorySubject: BehaviorSubject<Repository> = BehaviorSubject.create()
    private val detailsSubject: BehaviorSubject<RepositoryDetailsVM> = BehaviorSubject.create()
    private val compositeDisposable = CompositeDisposable()

    init {
        initRx()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun selectRepository(repository: Repository) {
        repositorySubject.onNext(repository)
    }

    fun repositoryDetails(): Observable<RepositoryDetailsVM> = detailsSubject

    private fun initRx() {
        compositeDisposable += repositorySubject.subscribe({
            detailsSubject.onNext(repositoryDetailsMapper.map(it))
        }, {})
    }
}