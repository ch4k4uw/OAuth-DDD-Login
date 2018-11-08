package com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.dao

import androidx.room.*
import com.ch4k4uw.oauthdddlogin.infrastructure.platform.roomdb.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)

    @Query("select * from security_user order by id")
    fun getAllUsers(): List<UserEntity>

    @Query("select count(id) from security_user")
    fun getCount(): Int

    @Query("select * from security_user where id=:id limit 1")
    fun getUserById(id: Long): UserEntity

    @Query("select changes()")
    fun getChanges(): Int

    @Query("select last_insert_rowid()")
    fun getLastId(): Long

}