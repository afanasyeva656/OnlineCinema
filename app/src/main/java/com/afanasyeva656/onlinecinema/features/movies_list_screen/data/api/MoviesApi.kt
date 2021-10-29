package com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api

import com.afanasyeva656.onlinecinema.features.movies_list_screen.data.api.model.MovieResultsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface MoviesApi {
    @GET("/LukyanovAnatoliy/eca5141dedc79751b3d0b339649e06a3/raw/38f9419762adf27c34a3f052733b296385b6aa8d/")
/*
Можно в целевом адресе использовать алиасы, а затем их подставить в методе getMoviesList
Например: /{user_name}/eca5141dedc79751b3d0b339649e06a3/raw/38f9419762adf27c34a3f052733b296385b6aa8d/
@Path("user_name") userName: String = "LukyanovAnatoliy"

https://square.github.io/retrofit/ -> API Declaration -> URL MANIPULATION
*/
    suspend fun getMoviesList(): MovieResultsModel
}