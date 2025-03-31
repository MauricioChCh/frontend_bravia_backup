package com.example.bravia.domain.model


class BusinessArea {
    var id: Long = 0
    var name: String = ""
    constructor()

    constructor(
        id: Long,
        name: String
    ) {
        this.id = id
        this.name = name
    }
}