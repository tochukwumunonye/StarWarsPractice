package com.tochukwu.starwarspractice.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.tochukwu.starwarspractice.R
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import com.tochukwu.starwarspractice.databinding.DisneyDetailBinding
import com.tochukwu.starwarspractice.presentation.CharacterViewModel
import com.tochukwu.starwarspractice.util.loadOrGone
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DisneyDetail : Fragment(R.layout.disney_detail) {

    private val args: DisneyDetailArgs by navArgs()
    private val disneyArticle: PosterDtoItem by lazy {
        args.selectedDisney
    }

    private val viewModel: DisneyViewModel by viewModels()

    private var _binding: DisneyDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DisneyDetailBinding.inflate(inflater, container, false)

        viewModel.fetchArticle(disneyArticle.id)

        //disneyArticle.id?.let { viewModel.fetchArticle(it) }

        viewModel.articleLiveData.observe(viewLifecycleOwner){poster ->
            displayPost(poster)
        }

        return binding.root
    }

    private fun displayPost(poster: PosterDtoItem?) {

        binding.apply{
            nameOfDetail.text = poster?.name
            //Glide.with(itemView).load(app.poster).into(logo)
            contentOfDetail.text = poster?.description
            Glide.with(requireContext()).load(poster?.poster).into(image)
        }
    }


}


/**



private fun setUpOpenWebsitesButton() {
binding.article.continueReadingButton.setOnClickListener {
articleViewModel.openWebsite(article.url)
}
}



private fun displayArticle(selectedArticle: Article) {
binding.apply{
article.author.loadOrGone(selectedArticle.author)
article.title.loadOrGone(selectedArticle.title.formatTitle())
article.content.loadOrGone(selectedArticle.content.formatContent())
article.description.loadOrGone(selectedArticle.description)
article.date.loadOrGone(selectedArticle.date.formatDate())
article.source.loadOrGone(selectedArticle.source.name)
Glide.with(requireContext()).load(selectedArticle.imgUrl).into(articleImage)

Glide.with(this@DisneyDetail).load(poster?.poster).into(image)
}
}  **/