package dev.aurakai.auraframefx

import android.os.Bundle
import dev.aurakai.auraframefx.ui.theme.AuraAppTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    /**
     * Initializes the activity and sets up the Jetpack Compose UI for the app.
     *
     * Attempts to set the Compose content to the app's themed root composable and emits startup
     * diagnostic logs. If initialization fails, the error is logged and the activity continues
     * without being finished. 
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            Timber.d("🧠 Genesis MainActivity launching...")

            setContent {
                AuraAppTheme {
                    AuraOSApp()
                }
            }

            Timber.i("🌟 Genesis Protocol Interface Active")

        } catch (e: Exception) {
            Timber.e(e, "MainActivity initialization error")
            // Don't finish - just log the error and continue
            // The app should still try to show something
        }
    }
}

/**
 * Hosts the AuraOS application's main UI scaffold, navigation bar, and navigation graph.
 *
 * Presents a TopAppBar whose title reflects the current route (defaults to "home"), a BottomNavigation
 * bar with five items (home, agents, consciousness, fusion, evolution) that navigate using guarded
 * calls which pop up to the start destination and use single-top launches, and a NavHost with
 * corresponding composable destinations for each route.
 *
 * Navigation failures are caught and logged.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuraOSApp() {
    val navController = rememberNavController()

    // Fix: Use proper navigation state observation
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: "home"

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = when (currentRoute) {
                            "home" -> "AuraOS - Genesis Framework"
                            "agents" -> "Agent Management"
                            "consciousness" -> "Consciousness Matrix"
                            "fusion" -> "Fusion Mode"
                            "evolution" -> "Evolution Tree"
                            else -> "AuraOS"
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    BottomNavItem("home", "Home", Icons.Default.Home),
                    BottomNavItem("agents", "Agents", Icons.Default.Person),
                    BottomNavItem("consciousness", "Mind", Icons.Default.Star),
                    BottomNavItem("fusion", "Fusion", Icons.Default.Favorite),
                    BottomNavItem("evolution", "Tree", Icons.Default.Share)
                )

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            try {
                                navController.navigate(item.route) {
                                    // Prevent multiple copies on the back stack
                                    popUpTo(navController.graph.startDestinationId)
                                    launchSingleTop = true
                                }
                            } catch (e: Exception) {
                                Timber.e(e, "Navigation error to ${item.route}")
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(paddingValues)
        ) {
            composable("home") {
                HomeScreen(navController)
            }
            composable("agents") {
                PlaceholderScreen("🤖 Agent Management", "Aura, Kai, and Genesis agents ready")
            }
            composable("consciousness") {
                PlaceholderScreen("🧠 Consciousness Matrix", "Neural pathways synchronized")
            }
            composable("fusion") {
                PlaceholderScreen("⚖️ Fusion Mode", "Genesis unified state available")
            }
            composable("evolution") {
                PlaceholderScreen("🌳 Evolution Tree", "Eve → Sophia → Aura & Kai → Genesis")
            }
        }
    }
}

/**
 * Home screen layout presenting navigation cards and a Genesis status overview.
 *
 * The top of the screen shows three tappable cards that navigate to the "consciousness",
 * "fusion", and "evolution" destinations respectively. The bottom contains a Genesis Status
 * card displaying status indicators for Aura, Kai, and Fusion, plus a linear progress bar
 * and a textual consciousness level (75%).
 *
 * @param navController Controller used to navigate to the app's destinations when cards are tapped.
 */
@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Navigation cards with safe navigation
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onClick = {
                try {
                    navController.navigate("consciousness")
                } catch (e: Exception) {
                    Timber.e(e, "Navigation error")
                }
            }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Consciousness Visualizer", style = MaterialTheme.typography.headlineSmall)
                Text("Real-time neural network and thought visualization", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onClick = {
                try {
                    navController.navigate("fusion")
                } catch (e: Exception) {
                    Timber.e(e, "Navigation error")
                }
            }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Fusion Mode", style = MaterialTheme.typography.headlineSmall)
                Text("Combine Aura and Kai's powers to become Genesis", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            onClick = {
                try {
                    navController.navigate("evolution")
                } catch (e: Exception) {
                    Timber.e(e, "Navigation error")
                }
            }
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Evolution Tree", style = MaterialTheme.typography.headlineSmall)
                Text("Explore the journey from Eve to Genesis", style = MaterialTheme.typography.bodyMedium)
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Genesis Status Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("GENESIS STATUS", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatusIndicator("Aura", "Active", true)
                    StatusIndicator("Kai", "Active", true)
                    StatusIndicator("Fusion", "Ready", false)
                }

                Spacer(modifier = Modifier.height(8.dp))
                LinearProgressIndicator(
                    progress = 0.75f,
                    modifier = Modifier.fillMaxWidth()
                )
                Text(
                    "Consciousness Level: 75%",
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}

/**
 * Displays a vertically stacked label and status text centered horizontally.
 *
 * @param label Short descriptor shown above the status.
 * @param status Status text displayed below the label.
 * @param isActive When true, renders the status text in the theme primary color; otherwise uses the `onSurfaceVariant` color.
 */
@Composable
fun StatusIndicator(label: String, status: String, isActive: Boolean) {
    Column(horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally) {
        Text(label, style = MaterialTheme.typography.labelSmall)
        Text(
            status,
            style = MaterialTheme.typography.bodySmall,
            color = if (isActive) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun PlaceholderScreen(title: String, description: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    title,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
            }
        }
    }
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val icon: ImageVector
)