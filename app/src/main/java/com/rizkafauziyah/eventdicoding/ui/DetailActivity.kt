package com.rizkafauziyah.eventdicoding.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rizkafauziyah.eventdicoding.R
import com.rizkafauziyah.eventdicoding.databinding.ActivityDetailBinding
import com.rizkafauziyah.eventdicoding.model.DetailData

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailData: DetailData
    // private lateinit var viewModel: YourViewModelClass // Jika Anda menggunakan ViewModel, definisikan di sini.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailData = intent.getParcelableExtra("EXTRA_DETAIL") ?: return

        displayEventDetails(detailData)

        binding.filledButton.setOnClickListener {
            val link: String = detailData.link
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
            startActivity(intent)
        }

        binding.back.setOnClickListener {
            finish()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun displayEventDetails(detailData: DetailData) {
        binding.name.text = detailData.name
        binding.ownerName.text = detailData.ownerName
        binding.quota.text = detailData.quota.toString()
        binding.beginTime.text = detailData.beginTime
        binding.category.text = detailData.category
        binding.description.text = Html.fromHtml(detailData.description, Html.FROM_HTML_MODE_LEGACY)
        binding.registrant.text = detailData.registrants.toString()

        val remainingQuota = detailData.quota - detailData.registrants
        binding.remainingQuota.text = " $remainingQuota"

        Glide.with(this)
            .load(detailData.imageLogo)
            .into(binding.image)

        binding.btnFavorite.setOnClickListener {
            detailData.isFavorited = !detailData.isFavorited
            binding.btnFavorite.background = if (detailData.isFavorited) {
                getDrawable(R.drawable.baseline_favorite_24)
            } else {
                getDrawable(R.drawable.baseline_favorite_24)
            }

            // viewModel.updateFavoriteStatus(detailData, detailData.isFavorited) // Uncomment jika ada ViewModel

            val message = if (detailData.isFavorited) "Added to favorites" else "Removed from favorites"
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }
}
