package com.tochukwu.starwarspractice.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tochukwu.starwarspractice.R
import com.tochukwu.starwarspractice.databinding.FragmentHomeBinding
import com.tochukwu.starwarspractice.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterFragment(
    var disneyAdapter: DisneyAdapter,
    var viewModel: CharacterViewModel? = null
) :Fragment(R.layout.fragment_home){

   // private val viewModel: CharacterViewModel by viewModels()



    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel =
            viewModel ?: ViewModelProvider(requireActivity()).get(CharacterViewModel::class.java)

        viewModel?.getDisney()
        setUpObserver()
        setUpRecyclerView()
    }



    private fun setUpObserver(){
        viewModel?.disneyChannel?.observe(viewLifecycleOwner, {
            it?.getContentIfNotHandled()?.let { res->
                when(res.status){
                    Status.SUCCESS ->{
                        val data = res.data
                        disneyAdapter.submitList(data)
                    }
                    Status.LOADING ->{
                        val data = res.data
                        disneyAdapter.submitList(data)

                    }

                    Status.ERROR ->{
                        val data = res.data
                        disneyAdapter.submitList(data)
                        Toast.makeText(activity, res.message, Toast.LENGTH_SHORT).show()
                    }
                }


            }
        })

    }
    private fun setUpRecyclerView() {
       // disneyAdapter = DisneyAdapter()

        binding.recycler.apply{
            adapter = disneyAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = null
        }
    }

}
/**

}
}


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
       // val view = _binding!!.root

        _binding?.searchView?.setEndIconOnClickListener{
            setUpObserver(_binding?.searchView?.editText!!.text.toString())
            _binding?.charactersProgressBar?.isVisible = true
            _binding?.textViewError?.isVisible = true
            hideKeyboard()
        }

        setUpObserver("")
        setupAdapter()
        return _binding?.root
    }

    @SuppressLint("TimberArgCount")
    private fun setUpObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getCharacters(searchString).collectLatest{res->
                charadapter.submitData(lifecycle, res)
                Timber.d("happy",  "${res}")
            }


            }

    }

    private fun setupAdapter(){

        charadapter =  CharacterAdapter()

        binding.charactersRecyclerview.apply{
            adapter = charadapter
            layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
            itemAnimator?.changeDuration = 0
        }



        charadapter.addLoadStateListener { loadState ->
            if (loadState.refresh is LoadState.Loading) {
                if (charadapter.snapshot().isEmpty()) {
                    binding.charactersProgressBar.isVisible = true
                }
                binding.textViewError.isVisible = false

            } else {
                binding.charactersProgressBar.isVisible = false

                val error = when {
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error

                    else -> null
                }
                error?.let {
                    if (charadapter.snapshot().isEmpty()) {
                        binding.textViewError.isVisible = true
                        binding.textViewError.text = it.error.localizedMessage
                    }
                }
            }
        }
    }



    override fun onDestroy() {
        super.onDestroy()
    }


}


/**
 * private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel() {
init {
Log.d(TAG, "ViewModel instance created")
}
override fun onCleared() {
super.onCleared()
Log.d(TAG, "ViewModel instance about to be destroyed")
}
}
}
setUpObserver("")

private fun initAdapter() {
adapter = ArticleAdapter()
binding.articleList.adapter = adapter
adapter.addLoadStateListener { loadState->
binding.articleList.isVisible = loadState.refresh is LoadState.NotLoading
binding.progress.isVisible = loadState.refresh is LoadState.Loading
manageErrors(loadState)
}
}

private fun manageErrors(loadState: CombinedLoadStates) {
binding.errorText.isVisible = loadState.refresh is LoadState.Error
binding.retryButton.isVisible = loadState.refresh is LoadState.Error
binding.retryButton.setOnClickListener { adapter.retry() }

val errorState = loadState.source.append as? LoadState.Error
?: loadState.source.prepend as? LoadState.Error
?: loadState.append as? LoadState.Error
?: loadState.prepend as? LoadState.Error

errorState?.let {
val errorText = resources.getString(R.string.error) + it.error.toString()
binding.errorText.text = errorText
}
}

private fun getNewsAndNotifyAdapter() {
job?.cancel()
job = lifecycleScope.launch{
viewModel.loadNews().collectLatest{
adapter.submitData(it)
}
}

}

}

**/