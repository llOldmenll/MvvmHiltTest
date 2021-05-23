package com.exercise.mvvmhilttest.mapper

import androidx.annotation.StringRes
import com.exercise.domain.entity.Repository
import com.exercise.domain.mapper.Mapper
import com.exercise.mvvmhilttest.R
import com.exercise.mvvmhilttest.entity.RepositoryDetailsVM
import com.exercise.mvvmhilttest.entity.RepositoryInfoVM

class RepositoryToRepositoryDetailsVMMapper : Mapper<Repository, RepositoryDetailsVM> {
    override fun map(from: Repository): RepositoryDetailsVM = RepositoryDetailsVM(
        from.fullName ?: "",
        from.ownerAvatar ?: "",
        provideDetails(from)
    )

    private fun provideDetails(from: Repository): List<RepositoryInfoVM> {
        return arrayListOf<RepositoryInfoVM>().apply {
            addInfo(R.string.owner, from.ownerName)
            addInfo(R.string.description, from.description)
            addInfo(R.string.language, from.language)
            addInfo(R.string.type, from.type)
            addInfo(R.string.createdOn, from.createdOn)
            addInfo(R.string.updatedOn, from.updatedOn)
            addInfo(R.string.repository, from.url)
            addInfo(R.string.ownerWebsite, from.ownerWebsite)
        }
    }

    private fun ArrayList<RepositoryInfoVM>.addInfo(
        @StringRes title: Int,
        description: String?
    ) {
        if (description?.isNotEmpty() == true) add(RepositoryInfoVM(title, description))
    }
}