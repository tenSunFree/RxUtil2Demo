package com.home.rxutil2demo.model

import com.home.rxutil2demo.model.entity.MainListEntity

class MainListModel {

    fun getData(): MutableList<MainListEntity> {
        val data = mutableListOf<MainListEntity>()
        data.add(
            MainListEntity(
                "https://img2.secretchina.com/pic/2018/10-11/p2280991a502350930-ss.jpg",
                "澳大利亞-悉尼",
                "是澳大利亞面積最大、人口最多的城市，被譽為南半球的「紐約」，連續多年被聯合國人居署評為全球最宜居的城市之一，高樓大廈林立，燈光璀璨，煙花盛開，非常美麗。"
            )
        )
        data.add(
            MainListEntity(
                "https://icrvb3jy.xinmedia.com/solomo/article/99186/0804AF3D-D3B1-E2E5-D3F9-47E38A3A506D.jpg",
                "法國-巴黎",
                "法國的巴黎市，與紐約、倫敦和東京並稱世界四個國際大都市，法國被稱為世界上最浪漫的國度，而巴黎則是這種浪漫之都的代表，夜景也非常美，如果有幸與情侶一起去看夜景，那將是人世間一件最浪漫的事。"
            )
        )
        data.add(
            MainListEntity(
                "https://i1.wp.com/inews.gtimg.com/newsapp_bt/0/1918817697/641",
                "英國-倫敦",
                "英國的倫敦，是世界上最大的金融中心之一，也是世界著名的旅遊勝地，倫敦是多元化的大都市，居民來自世界各地，一座種族、宗教與文化的大熔爐城市，使用的語言超過300多種。夜晚的倫敦百家燈火齊開，是一座非常美的城市，城市的繁榮美麗可見一斑。"
            )
        )
        return data
    }
}