package com.exercise.mvvmhilttest.presentation.repository_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.exercise.mvvmhilttest.entity.RepositoryDetailsVM
import com.exercise.mvvmhilttest.presentation.repository_details.adapter.RepositoryDetailsAdapter
import com.exercise.mvvmhilttest.utils.ImageLoader
import com.exercise.mvvmhilttest.databinding.FragmentRepositoryDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import javax.inject.Inject

@AndroidEntryPoint
class RepositoryDetailsFragment : Fragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var binding: FragmentRepositoryDetailsBinding
    private lateinit var detailsAdapter: RepositoryDetailsAdapter

    private val viewModel: RepositoryDetailsViewModel by activityViewModels()
    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsAdapter = RepositoryDetailsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRepositoryDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    override fun onResume() {
        super.onResume()
        initRx()
    }

    override fun onPause() {
        super.onPause()
        compositeDisposable.clear()
    }

    private fun initUI() = with(binding) {
        vRecycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = detailsAdapter
        }
        vClose.setOnClickListener { findNavController().popBackStack() }
    }

    private fun initRx() {
        compositeDisposable += viewModel.repositoryDetails()
            .subscribe({ updateRepositoryDetails(it) }, {})
    }

    private fun updateRepositoryDetails(details: RepositoryDetailsVM) = with(binding) {
        imageLoader.load(root.context, details.avatar, vAvatar)
        vName.text = details.name
        detailsAdapter.submit(details.list)
    }
}