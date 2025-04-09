package com.zulfadar.konnetto.data.model

import com.zulfadar.konnetto.R

object FakePostDataSource {
    val dummyPosting = listOf(
        Post(
            id = 0,
            username = "Gugum",
            profilePict = R.drawable.logo,
            caption = "America ya, Halo, Halo, Halo, Halo, Halo",
            image = null,
            timestamp = "16 h",
            comments = null,
            isLiked = true,
            isSaved = false,
        ),
        Post(
            id = 1,
            username = "Char Aznable",
            profilePict = R.drawable.logo,
            caption = "Awikwok Test",
            image = R.drawable.memespongebob,
            timestamp = "16 h",
            comments = null,
            isLiked = false,
            isSaved = false,
        ),
        Post(
            id = 2,
            username = "Amuro",
            profilePict = R.drawable.logo,
            caption = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum at metus id eros dapibus venenatis. Duis volutpat, lacus in fermentum dapibus, mauris sapien rhoncus sapien, nec feugiat nisl risus non risus. Nulla facilisi. Cras eget felis nec odio tincidunt elementum. Curabitur sit amet leo vel nunc posuere dapibus. Aliquam erat volutpat. Suspendisse tincidunt arcu at lorem efficitur, in cursus nisl maximus. Integer tristique tincidunt massa, eu sollicitudin nisi suscipit non. Pellentesque in eros eget justo eleifend hendrerit. Aenean scelerisque, magna non convallis rhoncus, lorem elit ullamcorper augue, sit amet tincidunt turpis nisl sed nulla. Nam a leo at justo venenatis sodales. Vivamus id dui nec nulla sagittis vestibulum. Integer tempus purus sed turpis pharetra varius.",
            image = R.drawable.memespongebob,
            timestamp = "16 h",
            comments = null,
            isLiked = false,
            isSaved = false,
        ),
    )
}