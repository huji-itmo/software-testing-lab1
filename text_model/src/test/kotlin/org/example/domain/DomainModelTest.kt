package org.example.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class DomainModelTest {

    @Test
    fun testLightPointCreation() {
        val lightPoint = LightPoint()
        assertTrue(lightPoint.isVisible)
        assertEquals(Brightness.BLINDING, lightPoint.brightness)
    }

    @Test
    fun testLightPointTransformsToCrescent() {
        val lightPoint = LightPoint()
        val crescent = lightPoint.transformToCrescent()
        assertNotNull(crescent)
        assertTrue(crescent.isNarrow)
    }

    @Test
    fun testCrescentExpandsToTwoSuns() {
        val crescent = Crescent()
        val celestialBodies = crescent.expand()
        assertEquals(2, celestialBodies.size)
        assertTrue(celestialBodies.all { it is Sun })
    }

    @Test
    fun testSunProperties() {
        val sun = Sun()
        assertEquals(CelestialBodyType.SUN, sun.type)
        assertTrue(sun.isBurning())
        assertEquals(FlameType.WHITE, sun.getFlameType())
        assertEquals("white", sun.color)
    }

    @Test
    fun testSunTemperature() {
        val sun = Sun(temperature = 6000.0)
        assertEquals(6000.0, sun.temperature, 0.1)
    }

    @Test
    fun testHorizonIsBlack() {
        val horizon = Horizon()
        assertTrue(horizon.isBlack)
        assertEquals("black", horizon.color)
    }

    @Test
    fun testAtmosphereIsRarefied() {
        val atmosphere = Atmosphere()
        assertTrue(atmosphere.isRarefied)
        assertTrue(atmosphere.density < 1.0)
    }

    @Test
    fun testSolarFlareFlowsThroughAtmosphere() {
        val atmosphere = Atmosphere(density = 0.3, isRarefied = true)
        val flare = SolarFlare(intensity = 1.0)
        assertTrue(flare.flowThrough(atmosphere))
    }

    @Test
    fun testSolarFlareDoesNotFlowThroughDenseAtmosphere() {
        val atmosphere = Atmosphere(density = 2.0, isRarefied = false)
        val flare = SolarFlare(intensity = 1.0)
        assertFalse(flare.flowThrough(atmosphere))
    }

    @Test
    fun testDarknessIsComplete() {
        val darkness = Darkness()
        assertTrue(darkness.isComplete)
        assertEquals(0.0, darkness.lightLevel, 0.001)
    }

    @Test
    fun testCelestialBodyTypes() {
        val star = CelestialBody("Star", CelestialBodyType.STAR, 5000.0, "yellow")
        val sun = Sun()
        val planet = CelestialBody("Planet", CelestialBodyType.PLANET, 300.0, "blue")
        
        assertEquals(CelestialBodyType.STAR, star.type)
        assertEquals(CelestialBodyType.SUN, sun.type)
        assertEquals(CelestialBodyType.PLANET, planet.type)
    }

    @Test
    fun testFlameTypes() {
        val whiteFlame = Sun().getFlameType()
        assertEquals(FlameType.WHITE, whiteFlame)
    }

    @Test
    fun testAtmosphereAllowsFlow() {
        val atmosphere = Atmosphere(isRarefied = true)
        val flare = SolarFlare(intensity = 0.5)
        assertTrue(atmosphere.allowsFlow(flare))
    }

    @Test
    fun testSolarFlareColorIsColorful() {
        val flare = SolarFlare()
        assertEquals("colorful", flare.color)
    }

    @Test
    fun testAtmosphereComposition() {
        val atmosphere = Atmosphere(composition = "thin")
        assertEquals("thin", atmosphere.composition)
    }

    @Test
    fun testMultipleSuns() {
        val crescent = Crescent()
        val suns = crescent.expand()
        
        val sun1 = suns[0] as Sun
        val sun2 = suns[1] as Sun
        
        assertNotEquals(sun1, sun2)
        assertTrue(sun1.isBurning())
        assertTrue(sun2.isBurning())
    }

    @Test
    fun testDomainModelFromText() {
        val darkness = Darkness(isComplete = true, lightLevel = 0.0)
        assertTrue(darkness.isComplete)
        
        val lightPoint = LightPoint(brightness = Brightness.BLINDING)
        val crescent = lightPoint.transformToCrescent()
        
        val suns = crescent.expand()
        val horizon = Horizon(color = "black")
        
        val atmosphere = Atmosphere(density = 0.1, isRarefied = true)
        val flare = SolarFlare(brightness = Brightness.BRIGHT, color = "colorful", intensity = 1.0)
        
        assertTrue(flare.flowThrough(atmosphere))
        assertEquals(2, suns.size)
        assertTrue(horizon.isBlack)
    }
}
