package com.test.composestudy.part1.viewmodel

import javax.inject.Inject

//@HiltViewModel
//class GithubViewModel @Inject constructor(
//    private val githubService: GithubService
//) : ViewModel() {
//    val repos = mutableStateListOf<Repo>()
//
//    fun getRepos() {
//        repos.clear()
//        viewModelScope.launch {
//            val result = githubService.listRepos("SsongSik")
//            repos.addAll(result)
//        }
//    }
//}