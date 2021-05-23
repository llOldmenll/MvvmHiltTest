package com.exercise.mvvmhilttest.presentation.repositories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.domain.entity.Repositories
import com.exercise.domain.entity.Repository
import com.exercise.mvvmhilttest.presentation.repositories.adapter.RepositoriesAdapter
import com.exercise.mvvmhilttest.presentation.repository_details.RepositoryDetailsViewModel
import com.exercise.mvvmhilttest.utils.ImageLoader
import com.exercise.mvvmhilttest.R
import com.exercise.mvvmhilttest.databinding.FragmentRepositoriesBinding
import com.exercise.mvvmhilttest.extension.showErrorDialog
import com.jakewharton.rxbinding4.view.clicks
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@AndroidEntryPoint
class RepositoriesFragment : Fragment(), RepositoriesAdapter.Listener {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var binding: FragmentRepositoriesBinding
    private lateinit var repositoriesAdapter: RepositoriesAdapter
    private val viewModel: RepositoriesViewModel by viewModels()
    private val detailsViewModel: RepositoryDetailsViewModel by activityViewModels()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        repositoriesAdapter = RepositoriesAdapter(imageLoader, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        initRx()
        if (repositoriesAdapter.isEmpty()) loadNextPage()
        else updateLoadBtnState()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    override fun onItemPressed(repository: Repository) {
        detailsViewModel.selectRepository(repository)
        findNavController().navigate(R.id.action_repositories_to_repositoryDetails)
    }

    private fun initUI() {
        binding.vRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = repositoriesAdapter
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun initRx() {
        compositeDisposable.addAll(
            viewModel.nextRepositories().subscribe({ insertRepositories(it) }, {}),
            viewModel.errors().subscribe({ handleError(it) }, {}),
            binding.vLoadMore.clicks().subscribe { loadNextPage() }
        )
    }

    private fun loadNextPage() = with(binding) {
        if (repositoriesAdapter.isEmpty()) vCentralProgress.visibility = VISIBLE
        else vBottomProgress.visibility = VISIBLE
        vLoadMore.visibility = GONE
        viewModel.loadNextPage()
    }

    private fun insertRepositories(repositories: Repositories) = with(repositories) {
        repositoriesAdapter.insertToEnd(list)
        hideProgress()
        updateLoadBtnState()
    }

    private fun handleError(error: Throwable) {
        hideProgress()
        binding.root.showErrorDialog(error)
    }

    private fun hideProgress() = with(binding) {
        vCentralProgress.visibility = GONE
        vBottomProgress.visibility = GONE
    }

    private fun updateLoadBtnState() {
        binding.vLoadMore.visibility = if (viewModel.isLoadNextAvailable()) VISIBLE else GONE
    }
}