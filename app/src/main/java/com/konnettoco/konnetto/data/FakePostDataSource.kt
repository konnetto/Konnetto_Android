package com.konnettoco.konnetto.data

import com.konnettoco.konnetto.data.FakeUserDataSource.currentUserDummy
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy1
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy2
import com.konnettoco.konnetto.data.FakeUserDataSource.otherUserDummy3
import com.konnettoco.konnetto.data.model.Post

object FakePostDataSource {
    val otherDummyPost = listOf(
        Post(
            id = 0,
            author = currentUserDummy,
            image = "https://i.pinimg.com/564x/67/b9/4a/67b94abda90218b077da5762a0df0d2c.jpg",
            caption = "test",
            isLiked = true,
            isSaved = false,
            totalLike = 1,
            totalComments = 2,
            createdAt = 2,
            updatedAt = "2025-07-05T14:30:00Z"
        ),
        Post(
            id = 1,
            author = otherUserDummy1,
            image = null,
            caption = "Bjir wkwkwkwkwkwkwk",
            isLiked = false,
            isSaved = false,
            totalLike = 200,
            totalComments = 200,
            totalShare = 0,
            createdAt = 2,
            updatedAt = "2025-07-04T13:00:00Z"
        ),
        Post(
            id = 2,
            caption = "America ya, Halo, Halo, Halo, Halo, Halo",
            image = null,
            isLiked = true,
            isSaved = false,
            author = otherUserDummy2,
            totalLike = 1,
            totalComments = 0,
            totalShare = 1,
            createdAt = 5,
        ),
        Post(
            id = 3,
            caption = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum at metus id eros dapibus venenatis. Duis volutpat, lacus in fermentum dapibus, mauris sapien rhoncus sapien, nec feugiat nisl risus non risus. Nulla facilisi. Cras eget felis nec odio tincidunt elementum. Curabitur sit amet leo vel nunc posuere dapibus. Aliquam erat volutpat. Suspendisse tincidunt arcu at lorem efficitur, in cursus nisl maximus. Integer tristique tincidunt massa, eu sollicitudin nisi suscipit non. Pellentesque in eros eget justo eleifend hendrerit. Aenean scelerisque, magna non convallis rhoncus, lorem elit ullamcorper augue, sit amet tincidunt turpis nisl sed nulla. Nam a leo at justo venenatis sodales. Vivamus id dui nec nulla sagittis vestibulum. Integer tempus purus sed turpis pharetra varius.",
            image = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRV6D4fmxljYS1uPgI3qwcKNIRm0DWQchKisQ&s",
            isLiked = false,
            isSaved = false,
            author = otherUserDummy3,
            totalLike = 0,
            totalComments = 0,
            totalShare = 0,
            createdAt = 10,
        ),
        Post(
            id = 4,
            caption = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vestibulum at metus id eros dapibus venenatis. Duis volutpat, lacus in fermentum dapibus, mauris sapien rhoncus sapien, nec feugiat nisl risus non risus. Nulla facilisi. Cras eget felis nec odio tincidunt elementum. Curabitur sit amet leo vel nunc posuere dapibus. Aliquam erat volutpat. Suspendisse tincidunt arcu at lorem efficitur, in cursus nisl maximus. Integer tristique tincidunt massa, eu sollicitudin nisi suscipit non. Pellentesque in eros eget justo eleifend hendrerit. Aenean scelerisque, magna non convallis rhoncus, lorem elit ullamcorper augue, sit amet tincidunt turpis nisl sed nulla. Nam a leo at justo venenatis sodales. Vivamus id dui nec nulla sagittis vestibulum. Integer tempus purus sed turpis pharetra varius.",
            image = null,
            isLiked = true,
            isSaved = false,
            author = otherUserDummy3,
            totalLike = 10,
            totalComments = 5,
            totalShare = 3,
            createdAt = 11,
        ),
    )
}