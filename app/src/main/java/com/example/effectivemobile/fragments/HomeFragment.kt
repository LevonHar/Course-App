package com.example.effectivemobile.fragments

import CourseAdapter
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.effectivemobile.R
import com.example.effectivemobile.databinding.FragmentHomeBinding
import com.example.effectivemobile.model.Course
import com.example.effectivemobile.objects.BookmarkRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CourseAdapter

    private val originalList = mutableListOf<Course>() // full list
    private val displayList = mutableListOf<Course>()  // filtered + sorted list

    private var isSortedAsc = false
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupCourseList()
        setupRecyclerView()
        setupSearch()
        setupSorting()

        return binding.root
    }

    // -------------------- DATA --------------------

    private fun setupCourseList() {
        originalList.addAll(
            listOf(
                Course(
                    100,
                    "Java-разработчик с нуля",
                    R.string.java_course_text.toString(),
                    "999",
                    4.9f,
                    "2024-05-22",
                    "2024-02-02",
                    true
                ),
                Course(
                    101,
                    "3D-дженералист",
                    R.string.three_d_generalist_description.toString(),
                    "12 000",
                    3.9f,
                    "2024-09-10",
                    "2024-01-20",
                    true
                ),
                Course(
                    102,
                    "Python Advanced. Для продвинутых",
                    R.string.course_python_description.toString(),
                    "1 299",
                    4.3f,
                    "2024-10-12",
                    "2024-08-10",
                    true
                ),
                Course(
                    103,
                    "Системный аналитик",
                    R.string.course_data_analyst_description.toString(),
                    "1 199",
                    4.5f,
                    "2024-04-15",
                    "2024-01-13",
                    true
                ),
                Course(
                    104,
                    "Аналитик данных",
                    R.string.three_d_generalist_description.toString(),
                    "899",
                    4.7f,
                    "2024-06-20",
                    "2024-03-12",
                    true
                )
            )
        )

        displayList.addAll(originalList)
    }

    // -------------------- RECYCLER --------------------

    private fun setupRecyclerView() {
        adapter = CourseAdapter(
            list = displayList,
            onBookmarkClick = { BookmarkRepository.toggle(it) },
            onItemClick = { course ->
                parentFragmentManager.beginTransaction()
                    .replace(R.id.main, CourseFragment.newInstance(course))
                    .addToBackStack(null)
                    .commit()
            }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    // -------------------- SEARCH --------------------

    private fun setupSearch() {
        binding.editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterCourses(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filterCourses(query: String) {
        val searchText = query.lowercase(Locale.getDefault())

        displayList.clear()

        val filtered = originalList.filter {
            it.title.lowercase(Locale.getDefault()).contains(searchText)
        }

        displayList.addAll(filtered)
        sortCurrentList()

        adapter.notifyDataSetChanged()
    }

    // -------------------- SORT --------------------

    private fun setupSorting() {
        binding.sortLayout.setOnClickListener {
            isSortedAsc = !isSortedAsc
            sortCurrentList()
            adapter.notifyDataSetChanged()
        }
    }

    private fun sortCurrentList() {
        if (isSortedAsc) {
            displayList.sortBy {
                LocalDate.parse(it.publishDate, formatter)
            }
        } else {
            displayList.sortByDescending {
                LocalDate.parse(it.publishDate, formatter)
            }
        }
    }
}
