package com.example.artistapp

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artistapp.ui.theme.ArtistAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtistAPPTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val leoDaVinci = Artist("Leonardo da Vinci","2024-02-12", image = R.drawable.ic_leonardo_da_vinci, art = R.drawable.ic_mona_lisa)
                    val picasso = Artist("Pablo Picasso","2024-02-11", image = R.drawable.ic_pablo_picasso, art = R.drawable.ic_beijo)
                    val dali = Artist("Salvador Dali","2024-02-14", image = R.drawable.ic_salvador_dali, art = R.drawable.ic_persistence_of_memory)
                    val vanGogh = Artist("Van Gogh","2024-02-14", image = R.drawable.ic_vincent_van_gogh, art = R.drawable.ic_starry_night)
                    val listArtist: List<Artist> = listOf(leoDaVinci,picasso,dali,vanGogh)
                    LazyColumn {
                        items(listArtist) {
                            artist ->ArtistCard(artist, modifier = Modifier.padding(innerPadding), onClick = {
                                Log.i("Name", artist.name)
                        }) }
                        }
                    /*ArtistCard(
                        Artist("Miguel","2024-02-12", image = R.drawable.ic_leonardo_da_vinci, art = R.drawable.ic_persistence_of_memory)
                        ,modifier = Modifier.padding(innerPadding)
                    )*/
                }
            }
        }
    }
}

@Composable
fun ArtistCard(
    artist: Artist,
    modifier: Modifier = Modifier,
    onClick: () -> Unit) {
    Column (modifier = modifier.padding(8.dp).clickable(onClick=onClick)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = artist.image),
                contentDescription = "Artist Image",
                modifier = modifier.size(60.dp).clip(CircleShape),
                contentScale = ContentScale.FillWidth
            )
            Column(modifier = modifier.padding(8.dp)) {
                Text(
                    text = artist.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = modifier.size(4.dp))
                Text(
                    text = artist.lastSeenOnline,
                    color = Color.Gray
                )
            }

        }
        Card (modifier = modifier.padding(8.dp), elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
            Image(painter = painterResource(id = artist.art),
            contentDescription = "Artist Art",
                modifier = modifier.fillMaxWidth().height(200.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}

data class Artist(
    val name: String,
    val lastSeenOnline: String,
    @DrawableRes val image: Int,
    @DrawableRes val art: Int
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtistAPPTheme {
        val leoDaVinci = Artist("Leonardo da Vinci","2024-02-12", image = R.drawable.ic_leonardo_da_vinci, art = R.drawable.ic_mona_lisa)
        val picasso = Artist("Pablo Picasso","2024-02-11", image = R.drawable.ic_pablo_picasso, art = R.drawable.ic_beijo)
        val dali = Artist("Salvador Dali","2024-02-14", image = R.drawable.ic_salvador_dali, art = R.drawable.ic_persistence_of_memory)
        val vanGogh = Artist("Van Gogh","2024-02-14", image = R.drawable.ic_vincent_van_gogh, art = R.drawable.ic_starry_night)
        val listArtist: List<Artist> = listOf(leoDaVinci,picasso,dali,vanGogh)
        LazyColumn {
            items(listArtist) {
                    artist ->ArtistCard(artist, onClick = {
                Log.i("Name", artist.name)
            }) }
        }    }
}