package hu.bme.ait.shoppinglistapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import hu.bme.ait.shoppinglistapp.R
import hu.bme.ait.shoppinglistapp.data.Category
import hu.bme.ait.shoppinglistapp.ui.screen.FilteredShoppingListScreen
import hu.bme.ait.shoppinglistapp.ui.screen.ShoppingListScreen


@Composable
fun ShoppingListNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val shoppingListRoute = stringResource(R.string.route_shopping_list)
    val filteredListRoute = stringResource(R.string.route_filtered_shopping_list)
    val categoryArg = stringResource(R.string.category)

    NavHost(
        navController = navController,
        startDestination = shoppingListRoute,
        modifier = modifier
    ) {
        composable(shoppingListRoute) {
            ShoppingListScreen(navController)
        }
        composable(
            route = filteredListRoute,
            arguments = listOf(
                navArgument(categoryArg) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val categoryName = backStackEntry.arguments?.getString(categoryArg)
                ?: Category.OTHER.name
            val category = try {
                Category.valueOf(categoryName)
            } catch (e: IllegalArgumentException) {
                Category.OTHER
            }
            FilteredShoppingListScreen(navController, category)
        }
    }
}