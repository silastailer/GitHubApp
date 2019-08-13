package br.com.fiap.githubapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.fiap.githubapp.api.GitHubService
import br.com.fiap.githubapp.model.Username
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchBtn.setOnClickListener{
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val service = retrofit.create(GitHubService::class.java)

            service.search(inputUsername.text.toString())
                .enqueue(object : Callback<Username>{
                    override fun onFailure(call: Call<Username>, t: Throwable) {
                        Toast.makeText(
                            this@MainActivity,
                            t.message,
                            Toast.LENGTH_LONG
                            ).show()
                    }

                    override fun onResponse(call: Call<Username>, response: Response<Username>) {
                        if(response.isSuccessful) {
                            val username = response.body()
                            resultUsername.text = username?.name

                            Picasso.get()
                                .load(username?.avatarUrl)
                                .into(tvUsername)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "Sorry, we could not make your search...",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                })
        }
    }
}
