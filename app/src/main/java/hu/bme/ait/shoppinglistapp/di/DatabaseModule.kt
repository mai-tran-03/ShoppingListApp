package hu.bme.ait.shoppinglistapp.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hu.bme.ait.shoppinglistapp.data.AppDatabase
import hu.bme.ait.shoppinglistapp.data.ShoppingDAO

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun provideShoppingDAO(appDatabase: AppDatabase) : ShoppingDAO {
        return appDatabase.shoppingDAO()
    }

    @Provides
    fun provideShoppingAppDatabase(@ApplicationContext context: Context) : AppDatabase {
        return AppDatabase.getDatabase(context)
    }
}