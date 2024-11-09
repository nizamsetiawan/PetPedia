package com.ikajihan.app.animals

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.ikajihan.app.animals.databinding.ActivityDetailAnimalsBinding

class DetailAnimalsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailAnimalsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailAnimalsBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val animal = intent.getParcelableExtra<Animals>("animal")

        binding.tvItemName.text = animal?.name ?: ""
        binding.tvItemDescription.text = animal?.description ?: ""
        binding.tvItemHabitat.text = animal?.habitat ?: ""
        binding.tvItemCharacteristics.text = animal?.characteristics ?: ""
        binding.ivAnimal.setImageResource(animal?.photo ?: 0)

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }

        binding.actionShare.setOnClickListener {
            shareItem(animal)
        }
    }

    private fun shareItem(animal: Animals?) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, "Check out this animal!")
            putExtra(
                Intent.EXTRA_TEXT, """
                    ${animal?.name}
                    ${animal?.description}
                    ${animal?.habitat}
                    ${animal?.characteristics}
                """.trimIndent()
            )
        }
        startActivity(Intent.createChooser(shareIntent, "Share via"))
    }
}
