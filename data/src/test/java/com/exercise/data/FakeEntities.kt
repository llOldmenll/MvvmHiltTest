package com.exercise.data

import com.exercise.data.entity.*

@JvmField
val fakeRepositoryEntity = RepositoryEntity(
    "123",
    "Full Name",
    "kotlin",
    "repo",
    "Description",
    "2011-06-06T03:40:09.505792+00:00",
    "2014-11-16T23:19:16.674082+00:00",
    "",
    LinksEntity(HtmlEntity("https://test.com"), AvatarEntity("https://test.com/safasfsdf.png"))
)

@JvmField
val fakeRepositoriesEntity = RepositoriesEntity(
    "https://test.com/page2", 10, listOf(fakeRepositoryEntity)
)