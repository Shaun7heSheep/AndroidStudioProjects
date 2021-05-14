package com.example.wordsapp

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wordsapp.databinding.FragmentWordListBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WordListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordListFragment : Fragment() {
    private var _binding: FragmentWordListBinding? = null
    private val binding get() = _binding!!
    //private var isLinearLayoutManager = true

    companion object {
        val LETTER = "letter"
        val SEARCH_PREFIX = "https://www.google.com/search?q=" // base URL for Google search
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentWordListBinding.inflate(inflater, container, false) // <- LayoutInflater
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = WordAdapter(activity?.intent?.extras?.getString(LETTER).toString(), requireContext())
        recyclerView.addItemDecoration(
                DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

        //chooseLayout()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /*private fun chooseLayout() {
        when (isLinearLayoutManager) {
            true -> {
                // Fragment is not a Context, so cannot pass in 'this' as LayoutManager's context
                // Instead use 'context' provided by Fragment
                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = LetterAdapter()
            }
            false -> {
                recyclerView.layoutManager = GridLayoutManager(context, 4)
                recyclerView.adapter = LetterAdapter()
            }
        }
    }

    private fun setIcon(menuItem: MenuItem?) {
        if (menuItem == null)
            return
        menuItem.icon =
                if (isLinearLayoutManager)
                // if the layout is linear, display grid icon (so that user can tap it to change to grid view)
                    ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_grid_layout)
                else ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_linear_layout)
    }*/

}