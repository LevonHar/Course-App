package com.example.effectivemobile.fragments

import CourseAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.R
import com.example.effectivemobile.data.BookmarkRepository
import com.example.effectivemobile.databinding.FragmentBookmarkBinding

class BookmarkFragment : Fragment() {

    private lateinit var binding: FragmentBookmarkBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)

        BookmarkRepository.init(requireContext())

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CourseAdapter(
                BookmarkRepository.bookmarks,
                onBookmarkClick = { course ->
                    BookmarkRepository.toggle(course)
                    adapter?.notifyDataSetChanged()
                },
                bookmarkIconRes = R.drawable.bookmark_green
            )
        }



        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}
