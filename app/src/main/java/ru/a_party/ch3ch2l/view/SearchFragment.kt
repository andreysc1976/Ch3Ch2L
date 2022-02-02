package ru.a_party.ch3ch2l.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.a_party.ch3ch2l.databinding.FragmentSearchBinding


class SearchFragment() : BottomSheetDialogFragment() { //

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private var onSearchClickListener: OnClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val onSearchButtonClickListener =
        View.OnClickListener {
            onSearchClickListener?.onClick(binding.editTextSearch.text.toString())
            dismiss()
        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        Log.e("TEST", "Биндинг для фрагмента замутили")
        binding.searchImageButton.setOnClickListener(onSearchButtonClickListener)
        return binding.root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        onSearchClickListener = activity as OnClickListener
    }

    companion object {
        @JvmStatic
        fun newInstance() = SearchFragment()
    }

    interface OnClickListener {
        fun onClick(word: String)
    }
}