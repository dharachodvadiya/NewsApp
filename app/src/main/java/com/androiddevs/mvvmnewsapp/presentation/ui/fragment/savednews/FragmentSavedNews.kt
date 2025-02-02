package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.savednews

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androiddevs.mvvmnewsapp.AppModule
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.presentation.adapter.NewsAdapter
import com.androiddevs.mvvmnewsapp.presentation.ui.fragment.ViewModelfactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_saved_news.rvSavedNews

class FragmentSavedNews : Fragment(R.layout.fragment_saved_news) {

    lateinit var viewModel: FragmentSavedNewsViewModel
    lateinit var newsAdapter: NewsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModelProviderFactory = ViewModelfactory(AppModule.provideNewsRepository(AppModule.provideNewsApi(), AppModule.provideDatabase(activity!!.baseContext)))
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FragmentSavedNewsViewModel::class.java)

         setupRecyclerView()

         newsAdapter.setOnItemClickListener {
             val bundle = Bundle().apply {
                 putSerializable("article", it)
             }
             findNavController().navigate(
                 R.id.action_fragmentSavedNews2_to_fragmentArticle,
                 bundle
             )
         }

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val article = newsAdapter.differ.currentList[position]
                viewModel.deleteArticle(article)
                Snackbar.make(view, "Successfully deleted article", Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveArticle(article)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rvSavedNews)
        }

         viewModel.getSavedNews().observe(viewLifecycleOwner, Observer { articles ->
             newsAdapter.differ.submitList(articles)
         })
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvSavedNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            //addOnScrollListener(this@FragmentBreakingNews.scrollListener)
        }
    }
}