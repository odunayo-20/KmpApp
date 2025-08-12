package org.example.project

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.seiko.imageloader.LocalImageLoader

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KmpApp",
    ) {
        CompositionLocalProvider(
                LocalImageLoader provides remember { generateImageLoader() },
        ) {
            App()

        }
    }


    fun generateImageLoader(): ImageLoader {
        return ImageLoader {
            components {
                setupDefaultComponents()
            }
            interceptor {
                // cache 32MB bitmap
                bitmapMemoryCacheConfig {
                    maxSize(32 * 1024 * 1024) // 32MB
                }
                // cache 50 image
                imageMemoryCacheConfig {
                    maxSize(50)
                }
                // cache 50 painter
                painterMemoryCacheConfig {
                    maxSize(50)
                }
                diskCacheConfig {
                    directory(getCacheDir().toOkioPath().resolve("image_cache"))
                    maxSizeBytes(512L * 1024 * 1024) // 512MB
                }
            }
        }
    }

    enum class OperatingSystem {
        Windows, Linux, MacOS, Unknown
    }

    private val currentOperatingSystem: OperatingSystem
    get() {
        val operSys = System.getProperty("os.name").lowercase()
        return if (operSys.contains("win")) {
            OperatingSystem.Windows
        } else if (operSys.contains("nix") || operSys.contains("nux") ||
            operSys.contains("aix")
        ) {
            OperatingSystem.Linux
        } else if (operSys.contains("mac")) {
            OperatingSystem.MacOS
        } else {
            OperatingSystem.Unknown
        }
    }

    // about currentOperatingSystem, see app
    fun getCacheDir() = when (currentOperatingSystem) {
        OperatingSystem.Windows -> File(System.getenv("AppData"), "$ApplicationName/cache")
        OperatingSystem.Linux -> File(System.getProperty("user.home"), ".cache/$ApplicationName")
        OperatingSystem.MacOS -> File(System.getProperty("user.home"), "Library/Caches/$ApplicationName")
        else -> throw IllegalStateException("Unsupported operating system")
    }

    private const val ApplicationName = "Kmp App Example"
}