package com.example.esiea3a.presentation.detail

import android.net.DnsResolver
import android.os.Bundle
import android.provider.CallLog
import android.telecom.Call
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.esiea3a.R
import com.example.esiea3a.presentation.api.PokemonDetailResponse
import com.example.esiea3a.presentation.list.Pokemon
import com.example.esiea3a.presentation.list.Singleton
import retrofit2.Response
import javax.security.auth.callback.Callback


class PokemonDetailFragment : Fragment() {

    private lateinit var textViewName: TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewName = view.findViewById(R.id.pokemon_detail_name)

        callApi()
    }

    private fun callApi() {
        val id = arguments?.getInt("pokemonId") ?: -1

        Singleton.pokeApi.getPokemonDetail(id).enqueue(object : retrofit2.Callback<PokemonDetailResponse> {
            override fun onFailure(
                    call: retrofit2.Call<PokemonDetailResponse>,
                    t: Throwable
            ) {

            }

            override fun onResponse(
                    call: retrofit2.Call<PokemonDetailResponse>,
                    response: Response<PokemonDetailResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    textViewName.text = response.body()!!.name
                }

            }
        })
    }
}
