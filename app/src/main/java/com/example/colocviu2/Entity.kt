package com.example.colocviu2

class Entity(
    var activity: String,
    var type: String,
    var participants: Int,
    var price: Float,
    var link: String,
    var key: String,
    var accessibility: Float
) {
    override fun toString(): String {
        return "Entity(activity='$activity', type='$type', participants=$participants, price=$price, link='$link', key='$key', accessibility=$accessibility)"
    }
}