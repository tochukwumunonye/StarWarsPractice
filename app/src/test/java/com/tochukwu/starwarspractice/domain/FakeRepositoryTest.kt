package com.tochukwu.starwarspractice.domain

import com.tochukwu.starwarspractice.data.remote.model.PosterDto
import com.tochukwu.starwarspractice.data.remote.model.PosterDtoItem
import com.tochukwu.starwarspractice.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeRepositoryTest : MainRepository {


    val posters = listOf(
        PosterDtoItem("Finding Dory is a 2016 American 3D computer-animated adventure film produced " +
                "by Pixar Animation Studios and released by Walt Disney Pictures. Directed by Andrew Stanton with co-direction by " +
                "Angus MacLane,[5][6] the screenplay was written by Stanton and Victoria Strouse.[7] " +
                "The film is a sequel/spinoff[8][9] to 2003's Finding Nemo and features the returning voices of Ellen DeGeneres and Albert Brooks, " +
                "with Hayden Rolence (replacing Alexander Gould), Ed O'Neill, Kaitlin Olson, Ty Burrell, Diane Keaton, and Eugene Levy joining the cast. " +
                "The film focuses on the amnesiac fish Dory, who journeys to be reunited with her parents.[10]",
            "https://media1.giphy.com/media/yUt0xuAPgFpSM/giphy.gif?cid=ecf05e47xl77vojzdfyfsq05jj6lo" +
                    "k315g8vdhbwpedj1ja6&rid=giphy.gif&ct=g", 6, "Finding Dory", "1 h 45 min","Dory, a regal blue tang, gets separated from her parents, Jenny and Charlie, as a child. As she grows up, Dory attempts to search for them, but gradually forgets them due to her short-term memory loss. In a flashback (from Finding Nemo), she joins Marlin – a clownfish looking for his missing son Nemo – after accidentally swimming into him.",
            "https://user-images.githubusercontent.com/24237865/75088201-0ba84100-5542-11ea-8587-0c2823b05351.jpg","2016",
            ),
        PosterDtoItem(
            "happy",
            "http://j.com",
            7,
            "laugh", "222", "hahah", "yeyeye", "88"),
        PosterDtoItem(
            "yryry",
            "Ford v Ferrari",
            8,
            "https://", "ytyty", "yryryr", "ururur", "2099")
    )

    val poster = PosterDtoItem("hdhdh", "Matrix", 10, "https://", "hueuu", "urur", "wuyeyyyr", "ueueue")

   // private val posters: MutableList<PosterDtoItem> =  mutableListOf()

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean){
        shouldReturnNetworkError  = value
    }


    override fun getPoster(): Flow<Resource<List<PosterDtoItem>>> = flow{
        if(shouldReturnNetworkError){
            emit(Resource.error("Error", null))
        } else {
            emit(Resource.success(posters))
        }
    }

    override fun getPosterById(id: Int?): Flow<PosterDtoItem> = flow{

        for(PosterItemDto in posters){

            if(PosterItemDto.id === id){
                emit(PosterItemDto)
            }
        }

       // emit(PosterDtoItem("jajaj", "hshsh", 8, "hrhr", "jeje", "jeje", "kwkw", "jwjw"))

    }
}

