package com.androiddevs.mvvmnewsapp.presentation.ui.fragment.article

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.androiddevs.mvvmnewsapp.AppModule
import com.androiddevs.mvvmnewsapp.R
import com.androiddevs.mvvmnewsapp.presentation.ui.fragment.ViewModelfactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_article.fab
import kotlinx.android.synthetic.main.fragment_article.webView

class FragmentArticle : Fragment(R.layout.fragment_article) {

    lateinit var viewModel: FragmentArticleViewModel
    private val args: FragmentArticleArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        //viewModel = (activity as NewsActivity).viewModel

        val viewModelProviderFactory = ViewModelfactory(AppModule.provideNewsRepository(AppModule.provideNewsApi(), AppModule.provideDatabase(activity!!.baseContext)))
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(FragmentArticleViewModel::class.java)

         val article = args.article
         webView.apply {
             webViewClient = WebViewClient()
             loadUrl(article.url)
         }

         fab.setOnClickListener {
             viewModel.saveArticle(article)
             Snackbar.make(view, "Article saved successfully", Snackbar.LENGTH_SHORT).show()
         }
    }
}