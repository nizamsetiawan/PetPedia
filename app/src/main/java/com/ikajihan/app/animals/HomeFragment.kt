package com.ikajihan.app.animals

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikajihan.app.animals.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAnimals: RecyclerView
    private val list = ArrayList<Animals>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        rvAnimals = binding.rvItems
        rvAnimals.setHasFixedSize(true)
        list.addAll(getListAnimals())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        val orientation = resources.configuration.orientation
        rvAnimals.layoutManager = if (orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(requireContext(), 3)
        } else {
            LinearLayoutManager(requireContext())
        }

        val listAnimalsAdapter = ListAnimalsAdapter(list)
        rvAnimals.adapter = listAnimalsAdapter

        listAnimalsAdapter.setOnItemClickCallBack(object : ListAnimalsAdapter.OnItemClickCallBack {
            override fun onItemClicked(data: Animals) {
                showSelectedAnimal(data)
            }
        })
    }

    private fun showSelectedAnimal(animal: Animals) {
        Toast.makeText(context, "You choose ${animal.name}", Toast.LENGTH_SHORT).show()
    }

    private fun getListAnimals(): ArrayList<Animals> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescriptions = resources.getStringArray(R.array.data_description)
        val dataHabitat = resources.getStringArray(R.array.data_habitat)
        val dataCharacteristics = resources.getStringArray(R.array.data_characteristics)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)

        val listAnimals = ArrayList<Animals>()
        for (i in dataName.indices) {
            val animal = Animals(
                name = dataName[i],
                description = dataDescriptions[i],
                habitat = dataHabitat[i],
                characteristics = dataCharacteristics[i],
                photo = dataPhoto.getResourceId(i, -1)
            )
            listAnimals.add(animal)
        }
        dataPhoto.recycle()
        return listAnimals
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
