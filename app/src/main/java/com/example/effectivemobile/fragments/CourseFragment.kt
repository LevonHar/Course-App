package com.example.effectivemobile.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.effectivemobile.R
import com.example.effectivemobile.UserActivity
import com.example.effectivemobile.databinding.FragmentBookmarkBinding
import com.example.effectivemobile.databinding.FragmentCourseBinding
import com.example.effectivemobile.model.Course

class CourseFragment : Fragment() {

    private lateinit var binding: FragmentCourseBinding

    companion object {
        private const val ARG_COURSE = "arg_course"

        fun newInstance(course: Course): CourseFragment {
            val fragment = CourseFragment()
            val bundle = Bundle()
            bundle.putParcelable(ARG_COURSE, course) // Course must implement Parcelable
            fragment.arguments = bundle
            return fragment
        }
    }

    private var course: Course? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        course = arguments?.getParcelable(ARG_COURSE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCourseBinding.inflate(inflater, container, false)

        course?.let { c ->
            binding.courseTitle.text = c.title
            binding.textCourse.text = c.text
            binding.rateText.text = c.rate.toString()
            binding.dateText.text = c.startDate
            binding.autor.text = "Автор"
            binding.academyName.text = "Merion Academy"
        }

        // Hide BottomNavigationView
        (activity as? UserActivity)?.hideBottomNavigation()

        // Back button
        binding.backIcon.setOnClickListener {
            parentFragmentManager.popBackStack()
            // Show BottomNavigationView when returning
            (activity as? UserActivity)?.showBottomNavigation()
        }

        return binding.root
    }
}