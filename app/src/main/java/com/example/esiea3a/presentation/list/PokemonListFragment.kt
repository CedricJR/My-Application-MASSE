package com.example.esiea3a.presentation.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esiea3a.R
import com.example.esiea3a.presentation.api.PokeApi
import com.example.esiea3a.presentation.api.PokemonListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import com.example.esiea3a.presentation.list.PokemonLoader as PokemonLoader
import com.example.esiea3a.presentation.list.PokemonModel as PokemonModel
import com.example.esiea3a.presentation.list.PokemonError as PokemonError


class PokemonListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loader: ProgressBar
    private lateinit var textViewError: TextView

    private val adapter = PokemonAdapter(listOf(), ::onClickedPokemon)

    private val viewModel: PokemonListViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.pokemon_recyclerView)
        loader = view.findViewById(R.id.pokemon_loader)
        textViewError = view.findViewById(R.id.pokemon_error)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@PokemonListFragment.adapter
        }

        viewModel.pokeList.observe(viewLifecycleOwner, androidx.lifecycle.Observer { pokemonModel ->
            loader.isVisible = PokemonModel is PokemonLoader
            textViewError.isVisible = PokemonModel is PokemonError

                if (PokemonModel is PokemonSuccess) {
                    adapter.updateList(pokemonModel.pokeList)
                }

        })

    }


    private fun onClickedPokemon(id: Int) {
        findNavController().navigate(R.id.navigateToPokemonDetailFragment, bundleOf(
                "pokemonId" to (id +1)
        ))

    }
}

