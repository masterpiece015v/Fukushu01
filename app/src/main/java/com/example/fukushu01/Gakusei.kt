package com.example.fukushu01

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

class Gakusei(
    @PrimaryKey
    var id : Long = 0,
    var kurasu:String,
    var namae:String
): RealmObject()