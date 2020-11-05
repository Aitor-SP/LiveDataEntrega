package com.example.livedataentrega;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

public class PokemonViewModel extends AndroidViewModel {
    Pokemon pokemon;

    LiveData<Integer> pokemonLiveData;

    public PokemonViewModel(@NonNull Application application) {
        super(application);

        pokemon = new Pokemon();

        pokemonLiveData = Transformations.switchMap(pokemon.evolucionLiveData, new Function<String, LiveData<Integer>>() {


            @Override
            public LiveData<Integer> apply(String evolucion) {

                int imagen;
                switch (evolucion) {
                    case "EVOLUCION1":
                    default:
                        imagen = R.drawable.charmander1;
                        break;
                    case "EVOLUCION2":
                        imagen = R.drawable.charmaleon;
                        break;
                    case "EVOLUCION3":
                        imagen = R.drawable.charizard;
                        break;
                    case "EVOLUCION4":
                        imagen = R.drawable.megacharizard;
                        break;
                }

                return new MutableLiveData<>(imagen);
            }
        });
    }

    LiveData<Integer> obtenerEvolucion(){
        return pokemonLiveData;
    }
}