package com.rodilon.dogs.features.dogs

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.rodilon.dogs.Constants.CATEGORY
import com.rodilon.dogs.Constants.HOUND
import com.rodilon.dogs.Constants.HUSKY
import com.rodilon.dogs.Constants.LABRADOR
import com.rodilon.dogs.Constants.NUMBER_OF_TABS
import com.rodilon.dogs.Constants.ONE
import com.rodilon.dogs.Constants.PUG
import com.rodilon.dogs.Constants.THREE
import com.rodilon.dogs.Constants.TOKEN
import com.rodilon.dogs.Constants.TWO
import com.rodilon.dogs.Constants.ZERO
import com.rodilon.dogs.R
import kotlinx.android.synthetic.main.fragment_viewpager.*

class ViewPagerFragment : Fragment(R.layout.fragment_viewpager) {

    private lateinit var authorization: String
    private lateinit var dogsCollectionAdapter: DogsCollectionAdapter
    private lateinit var viewPager: ViewPager2

    companion object {
        fun newInstance(token: String): ViewPagerFragment {
            val fragment = ViewPagerFragment()
            fragment.arguments = Bundle().apply {
                putString(TOKEN, token)
            }
            return fragment
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.takeIf { it.containsKey(TOKEN) }?.apply {
            authorization = getString(TOKEN)!!
        }

        dogsCollectionAdapter = DogsCollectionAdapter(this, authorization)
        viewPager = view.findViewById(R.id.viewPager)
        viewPager.adapter = dogsCollectionAdapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                ZERO -> {
                    tab.text = HUSKY
                }
                ONE -> {
                    tab.text = HOUND
                }
                TWO -> {
                    tab.text = PUG
                }
                THREE -> {
                    tab.text = LABRADOR
                }
            }

        }.attach()
    }

    class DogsCollectionAdapter(
        fragment: Fragment,
        private val authorization: String
    ) :
        FragmentStateAdapter(fragment) {

        lateinit var category: String

        override fun getItemCount(): Int = NUMBER_OF_TABS

        override fun createFragment(position: Int): Fragment {

            category = when (position) {
                ZERO -> HUSKY
                ONE -> HOUND
                TWO -> PUG
                THREE -> LABRADOR
                else -> HUSKY
            }

            val fragment =
                DogsFragment()
            fragment.arguments = Bundle().apply {
                putString(TOKEN, authorization)
                putString(CATEGORY, category)
            }
            return fragment
        }
    }
}