package me.dio.simulador.ui;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import me.dio.simulador.R;
import me.dio.simulador.data.MatchesApi;
import me.dio.simulador.databinding.ActivityMainBinding;
import me.dio.simulador.domain.Match;
import me.dio.simulador.ui.adapter.MatchesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;
    private MatchesApi matchesApi;
    private RecyclerView.Adapter matchesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupHttpClient();
        setMatchesList();
        setMatchesRefresh();
        setFloatingActionButton();

    }

    private void setupHttpClient() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://diogofelismino.github.io/matches-simulator-api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        matchesApi = retrofit.create(MatchesApi.class);

    }

    private void setMatchesList() {
        binding.rvMatches.setHasFixedSize(true);
        binding.rvMatches.setLayoutManager(new LinearLayoutManager(this));

        matchesApi.getMatches().enqueue(new Callback<List<Match>>() {
            @Override
            public void onResponse(Call<List<Match>> call, Response<List<Match>> response) {
                if(response.isSuccessful()){
                    List<Match> matches = response.body();
                    matchesAdapter = new MatchesAdapter(matches);
                    binding.rvMatches.setAdapter(matchesAdapter);

                }else{
                    showErrorMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Match>> call, Throwable t) {
                showErrorMessage();
            }
        });
    }

    private void setMatchesRefresh() {
        // TODO recarregar a pagina com nova partidas
    }

    private void setFloatingActionButton() {
        // TODO botao que vai simular as partidas
    }

    private void showErrorMessage() {

        Snackbar.make(binding.fabSimulate, R.string.error_api, Snackbar.LENGTH_LONG).show();

    }

}
