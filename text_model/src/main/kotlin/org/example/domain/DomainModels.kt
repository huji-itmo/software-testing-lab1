package org.example.domain

class LightPoint(
    val brightness: Brightness = Brightness.BLINDING,
    val isVisible: Boolean = true
) {
    fun transformToCrescent(): Crescent {
        return Crescent()
    }
}

class Crescent(
    val width: Double = 0.0,
    val isNarrow: Boolean = true
) {
    fun expand(): List<CelestialBody> {
        return listOf(Sun(), Sun())
    }
}

enum class Brightness {
    BLINDING,
    BRIGHT,
    DIM,
    DARK
}

enum class CelestialBodyType {
    STAR,
    SUN,
    PLANET,
    MOON
}

open class CelestialBody(
    val name: String,
    val type: CelestialBodyType,
    val temperature: Double = 0.0,
    val color: String = ""
)

class Sun(
    name: String = "Sun",
    temperature: Double = 5778.0,
    color: String = "white"
) : CelestialBody(name, CelestialBodyType.SUN, temperature, color) {
    
    fun isBurning(): Boolean = temperature > 0
    
    fun getFlameType(): FlameType = FlameType.WHITE
}

enum class FlameType {
    WHITE,
    RED,
    ORANGE,
    BLUE
}

class Horizon(
    val color: String = "black",
    val isBlack: Boolean = true
)

class SolarFlare(
    val brightness: Brightness = Brightness.BRIGHT,
    val color: String = "colorful",
    val intensity: Double = 1.0
) {
    fun flowThrough(medium: Atmosphere): Boolean {
        return medium.density < 1.0
    }
}

class Atmosphere(
    val density: Double = 0.5,
    val composition: String = "thin",
    val isRarefied: Boolean = true
) {
    fun allowsFlow(flame: SolarFlare): Boolean {
        return isRarefied && flame.intensity > 0
    }
}

class Darkness(
    val isComplete: Boolean = true,
    val lightLevel: Double = 0.0
)
