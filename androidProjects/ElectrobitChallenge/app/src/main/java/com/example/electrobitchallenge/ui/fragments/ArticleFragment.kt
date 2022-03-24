package com.example.electrobitchallenge.ui.fragments

import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.electrobitchallenge.R
import com.example.electrobitchallenge.ui.NewsActivity
import com.example.electrobitchallenge.ui.NewsViewModel
import kotlinx.android.synthetic.main.fragment_article.*
import kotlinx.android.synthetic.main.item_article_preview.view.*
import org.w3c.dom.Text as Text1

class ArticleFragment : Fragment(R.layout.fragment_article){

    lateinit var viewModel: NewsViewModel

     val args: ArticleFragmentArgs by navArgs()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NewsActivity).viewModel
        val article = args.article


        view.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImageAr)
            tvSourceAr.text = article.source?.name
            tvTitleAr.text = article.title
            tvDescriptionAr.text = article.description
            tvPublishedAtAr.text = article.publishedAt
            tvLinkAr.apply {
                text=article.url
                movementMethod=LinkMovementMethod.getInstance()
            }
        }



    }

}




