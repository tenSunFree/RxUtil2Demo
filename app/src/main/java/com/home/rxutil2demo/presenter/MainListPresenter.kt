package com.home.rxutil2demo.presenter

import com.home.rxutil2demo.model.MainListModel
import com.home.rxutil2demo.model.entity.MainListEntity

class MainListPresenter {

    private var mainListModel: MainListModel = MainListModel()

    fun getData(): MutableList<MainListEntity> {
        return mainListModel.getData()
    }
}