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
import com.example.effectivemobile.databinding.FragmentHomeBinding
import com.example.effectivemobile.model.Course

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val courseList = listOf(
            Course(
                id = 100,
                title = "Java-разработчик с нуля",
                text = R.string.java_course_text.toString(),
                price = "999 $",
                rate = 4.9f,
                startDate = "2024-05-22",
                publishDate = "2024-02-02",
                hasLike = true
            ),
            Course(
                id = 101,
                title = "3D-дженералист",
                text = R.string.three_d_generalist_description.toString(),
                price = "12 000 $",
                rate = 3.9f,
                startDate = "2024-09-10",
                publishDate = "2024-01-20",
                hasLike = true
            ),
            Course(
                id = 102,
                title = "Python Advanced. Для продвинутых",
                text = R.string.course_python_description.toString(),
                price = "1 299 $",
                rate = 4.3f,
                startDate = "2024-10-12",
                publishDate = "2024-08-10",
                hasLike = true
            ),
            Course(
                id = 103,
                title = "Системный аналитик",
                text = R.string.course_data_analyst_description.toString(),
                price = "1 199 $",
                rate = 4.5f,
                startDate = "2024-04-15",
                publishDate = "2024-01-13",
                hasLike = true
            ),
            Course(
                id = 104,
                title = "Аналитик данных",
                text = R.string.three_d_generalist_description.toString(),
                price = "899 $",
                rate = 4.7f,
                startDate = "2024-06-20",
                publishDate = "2024-03-12",
                hasLike = true
            )
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = CourseAdapter(
                courseList,
                onBookmarkClick = { course ->
                    BookmarkRepository.toggle(course)
                },
                bookmarkIconRes = R.drawable.bookmark_green // icon when saved
            )
        }


        return binding.root
    }
}