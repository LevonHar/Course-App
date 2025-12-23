import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.R
import com.example.effectivemobile.data.BookmarkRepository
import com.example.effectivemobile.databinding.ItemBinding
import com.example.effectivemobile.model.Course

class CourseAdapter(
    private val list: List<Course>,
    private val onBookmarkClick: (Course) -> Unit,
    private val bookmarkIconRes: Int = R.drawable.bookmark_green // green icon
) : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CourseViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val item = list[position]

        with(holder.binding) {
            titleText.text = item.title
            price.text = item.price
            rateText.text = item.rate.toString()
            dateText.text = item.startDate

            // Update icon based on bookmark state
            fun updateIcon() {
                saveIcon.setImageResource(
                    if (BookmarkRepository.isBookmarked(item)) bookmarkIconRes
                    else R.drawable.bookmark_icon
                )
            }

            updateIcon() // initial state

            saveIcon.setOnClickListener {
                onBookmarkClick(item)       // toggle in repository
                updateIcon()                // immediately update icon
            }
        }
    }

    override fun getItemCount(): Int = list.size
}




