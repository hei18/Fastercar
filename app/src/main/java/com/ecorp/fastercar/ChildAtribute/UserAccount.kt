package com.ecorp.fastercar.ChildAtribute

class UserAccount (
    val ktp: String?,
    val nama: String?,
    val hp: String?,
    val email: String?,
    val password: String?){

    constructor():this("","","","","")
}