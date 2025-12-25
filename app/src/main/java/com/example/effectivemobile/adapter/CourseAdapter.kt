import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.effectivemobile.R
import com.example.effectivemobile.objects.BookmarkRepository
import com.example.effectivemobile.databinding.ItemBinding
import com.example.effectivemobile.model.Course

class CourseAdapter(
    private val list: List<Course>,
    private val onBookmarkClick: (Course) -> Unit,
    private val onItemClick: (Course) -> Unit, // NEW
    private val bookmarkIconRes: Int = R.drawable.bookmark_green
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

            // Bookmark icon
            fun updateIcon() {
                saveIcon.setImageResource(
                    if (BookmarkRepository.isBookmarked(item)) bookmarkIconRes
                    else R.drawable.bookmark_icon
                )
            }
            updateIcon()

            saveIcon.setOnClickListener {
                onBookmarkClick(item)
                updateIcon()
            }

            root.setOnClickListener {
                onItemClick(item) // open CourseFragment
            }
        }
    }

    override fun getItemCount(): Int = list.size
}





