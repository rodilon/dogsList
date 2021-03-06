package com.rodilon.dogs.features.dogs

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.rodilon.dogs.features.dogs.ImageDialogFragment.Companion.TAG
import com.rodilon.dogs.R
import com.rodilon.dogs.di.ApplicationModules
import com.rodilon.dogs.domain.Resource
import com.rodilon.dogs.util.Constants.CATEGORY
import com.rodilon.dogs.util.Constants.NUMBER_OF_COLUMNS
import com.rodilon.dogs.util.Constants.TOKEN
import kotlinx.android.synthetic.main.fragment_dogs.*

class DogsFragment : Fragment(R.layout.fragment_dogs), DogsAdapter.ZoomImageListener {

    companion object {
        fun newInstance(token: String, category: String): DogsFragment {
            val fragment = DogsFragment()
            fragment.arguments = Bundle().apply {
                putString(TOKEN, token)
                putString(CATEGORY, category)
            }
            return fragment
        }
    }

    private val viewModel: DogsViewModel by viewModels {
        DogsViewModelFactory(ApplicationModules.domainModule.dogsUseCase)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        arguments?.takeIf { it.containsKey(TOKEN) && it.containsKey(CATEGORY) }?.apply {
            viewModel.fetchDogs(getString(TOKEN).toString(), getString(CATEGORY).toString())
        }

        subscribeObservers()
        retrieveDogs()
    }

    private fun subscribeObservers() {
        viewModel.dogsLiveData.observe(viewLifecycleOwner, Observer { resource ->
            when (resource) {
                is Resource.Success -> {
                    if (resource.result.breedsUrlList.isEmpty()) {
                        showEmptyScreenOrError()
                    } else {
                        initRecyclerView(resource.result.breedsUrlList)
                        hideDisplayLoading()
                    }
                }
                is Resource.Error -> {
                    showEmptyScreenOrError(resource.error.message!!)
                    println("Retornou erro: ${resource.error.message}")
                }
                Resource.Loading -> {
                    displayLoading()
                    println("LOADING...")
                }
            }
        })
    }

    private fun initRecyclerView(images: List<String>) {
        showRecyclerView()
        recyclerViewDogs.layoutManager = GridLayoutManager(requireContext(), NUMBER_OF_COLUMNS)
        val adapter =
            DogsAdapter(images, this)
        recyclerViewDogs.adapter = adapter
    }

    private fun showEmptyScreenOrError(message: String = getString(R.string.has_no_list)) {
        hideDisplayLoading()
        hideRecyclerView()
        constraintEmptyOrError.visibility = VISIBLE
        textViewEmptyOrError.text = message
    }

    private fun displayLoading() {
        hideRecyclerView()
        hideEmptyScreenOrError()
        constraintProgressBar.visibility = VISIBLE
    }

    private fun hideDisplayLoading() {
        constraintProgressBar.visibility = GONE
    }

    private fun hideEmptyScreenOrError() {
        constraintEmptyOrError.visibility = GONE
    }

    private fun hideRecyclerView() {
        recyclerViewDogs.visibility = GONE
    }

    private fun showRecyclerView() {
        recyclerViewDogs.visibility = VISIBLE
    }

    private fun retrieveDogs() {
        textViewEmptyOrError.setOnClickListener {
            subscribeObservers()
        }
    }

    override fun onClickListener(url: String) {
        val transaction = childFragmentManager.beginTransaction()
        val fragment = ImageDialogFragment.newInstance(url)
        fragment.show(transaction, TAG)
    }
}