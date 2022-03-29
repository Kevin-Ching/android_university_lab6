package com.codepath.nytimes.ui.books

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.util.Log
import android.view.View
import com.codepath.nytimes.R
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.GridLayoutManager
import com.codepath.nytimes.networking.NYTimesApiClient
import com.codepath.nytimes.networking.CallbackResponse
import com.codepath.nytimes.models.BestSellerBook

/**
 * A fragment representing a list of Items.
 */
class BestSellerBooksFragment
/**
 * Mandatory empty constructor for the fragment manager to instantiate the
 * fragment (e.g. upon screen orientation changes).
 */
    : Fragment(), OnListFragmentInteractionListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_best_seller_books_list, container, false)
        activity?.title = getString(R.string.action_bar_books)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
        updateAdapter(progressBar, recyclerView)
        activity!!.title = getString(R.string.action_bar_books)
        return view
    }

    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()
        val nyTimesApiClient = NYTimesApiClient()
        nyTimesApiClient.getBestSellersList(object : CallbackResponse<List<BestSellerBook>> {
            override fun onSuccess(models: List<BestSellerBook>) {
                progressBar.hide()
                recyclerView.adapter = BestSellerBooksRecyclerViewAdapter(models, this@BestSellerBooksFragment)
                Log.d("BestSellerBooksFragment", "response successful")
            }

            override fun onFailure(error: Throwable?) {
                progressBar.hide()
                Log.e("BestSellerBooksFragment", error!!.message!!)
            }
        })
    }

    override fun onItemClick(item: BestSellerBook?) {}

    companion object {
        fun newInstance(): BestSellerBooksFragment {
            return BestSellerBooksFragment()
        }
    }
}