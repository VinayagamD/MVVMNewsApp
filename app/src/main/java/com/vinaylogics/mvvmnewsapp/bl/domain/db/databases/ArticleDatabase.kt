package com.vinaylogics.mvvmnewsapp.bl.domain.db.databases

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vinaylogics.mvvmnewsapp.bl.domain.db.converters.SourceConverters
import com.vinaylogics.mvvmnewsapp.bl.domain.db.daos.ArticleDao
import com.vinaylogics.mvvmnewsapp.bl.domain.models.Article

@Database(
    entities = [Article::class],
    version = 1
)
@TypeConverters(SourceConverters::class)
abstract class ArticleDatabase : RoomDatabase(){

    abstract fun getArticleDao() : ArticleDao

    companion object {
        @Volatile
        private var instance: ArticleDatabase? = null
        private val LOCK =  Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also{ instance = it}
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
            ArticleDatabase::class.java,
            "article_db.db"
            ).build()
    }
}