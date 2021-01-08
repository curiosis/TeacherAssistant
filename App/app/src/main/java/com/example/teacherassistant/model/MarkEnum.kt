package com.example.teacherassistant.model

enum class MarkEnum (val mark: String, val value: Float){
    Bdb("Bardzo dobry", 5f),
    DbPlus("Dobry +", 4.5f),
    Db("Dobry", 4.0f),
    DstPlus("Dostateczny +", 3.5f),
    Dst("Dostateczny", 3.0f),
    Ndst("Niedostateczny", 2.0f)
}