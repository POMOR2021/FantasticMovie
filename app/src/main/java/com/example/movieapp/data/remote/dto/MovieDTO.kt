import android.icu.text.CaseMap

data class MovieDto(
    val id: Int,
    val title: String,
    val poster_path: String,
    val imdb_id: String,
    val release_date: String,
    val vote_average: Double
)